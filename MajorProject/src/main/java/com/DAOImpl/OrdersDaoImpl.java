package com.DAOImpl;

import java.sql.*;
import java.util.ArrayList;

import com.DAOModel.Orders;
import com.DBUtill.DBConnection;
import com.DaoInterfaces.OrdersDao;

public class OrdersDaoImpl implements OrdersDao {

    private static final Connection con = DBConnection.connect();

    private static final String INSERT_QUERY =
        "INSERT INTO Orders (userId, RestorantId, OrderDate, TotalAmount, Status, PaymentMode) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String FETCH_ALL = "SELECT * FROM Orders";
    private static final String FETCH_ONE = "SELECT * FROM Orders WHERE OrderId = ?";
    private static final String UPDATE_AMOUNT = "UPDATE Orders SET TotalAmount = ? WHERE OrderId = ?";
    private static final String DELETE_ORDER = "DELETE FROM Orders WHERE OrderId = ?";

    @Override
    
    public int addOrder(Orders order) {
        int generatedOrderId = -1;
        
        try (PreparedStatement ps = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestorantId());
            ps.setTimestamp(3, order.getOrderDate());
            ps.setDouble(4, order.getTotalAmount());
            ps.setString(5, order.getStatus());
            ps.setString(6, order.getPaymentMode());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedOrderId = rs.getInt(1);
                order.setOrderId(generatedOrderId); // set back to object
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedOrderId;
    }



    // ✅ Fetch All Orders
    @Override
    public ArrayList<Orders> FETCHAll() {
        ArrayList<Orders> orderList = new ArrayList<>();

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(FETCH_ALL)) {

            while (rs.next()) {
                Orders order = new Orders(
                    rs.getInt("OrderId"),
                    rs.getInt("userId"),
                    rs.getInt("RestorantId"),
                    rs.getTimestamp("OrderDate"),
                    rs.getInt("TotalAmount"),
                    rs.getString("Status"),
                    rs.getString("PaymentMode")
                );
                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    // ✅ Fetch One Order
    @Override
    public Orders fetchOne(int orderId) {
        Orders order = null;

        try (PreparedStatement pstmt = con.prepareStatement(FETCH_ONE)) {
            pstmt.setInt(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    order = new Orders(
                        rs.getInt("OrderId"),
                        rs.getInt("userId"),
                        rs.getInt("RestorantId"),
                        rs.getTimestamp("OrderDate"),
                        rs.getInt("TotalAmount"),
                        rs.getString("Status"),
                        rs.getString("PaymentMode")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    // ✅ Update Total Amount
    @Override
    public int update(int orderId, int totalAmount) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_AMOUNT)) {
            pstmt.setInt(1, totalAmount);
            pstmt.setInt(2, orderId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ✅ Delete Order
    @Override
    public int delete(int orderId) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_ORDER)) {
            pstmt.setInt(1, orderId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
