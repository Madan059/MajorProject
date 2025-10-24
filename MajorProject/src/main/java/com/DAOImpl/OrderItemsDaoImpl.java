package com.DAOImpl;

import java.sql.*;
import java.util.ArrayList;

import com.DAOModel.OrderItems;
import com.DBUtill.DBConnection;
import com.DaoInterfaces.OrderItemsDao;

public class OrderItemsDaoImpl implements OrderItemsDao {

    private static final Connection con = DBConnection.connect();

    private static final String INSERT =
        "INSERT INTO OrderItems (OrderId, MenuId, Quantity, ItemsTotal) VALUES (?, ?, ?, ?)";
    private static final String FETCH_ALL = "SELECT * FROM OrderItems";
    private static final String FETCH_ONE = "SELECT * FROM OrderItems WHERE OrderId = ?";
    private static final String UPDATE = "UPDATE OrderItems SET Quantity = ?, ItemsTotal = ? WHERE OrderId = ?";
    private static final String DELETE = "DELETE FROM OrderItems WHERE OrderId = ?";

    // ✅ Insert OrderItem
    @Override
    public void addOrderItem(OrderItems item) {
        try (PreparedStatement ps = con.prepareStatement(INSERT)) {
            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getMenuId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getItemTotal()); // Still using item.getItemTotal() in Java
            ps.executeUpdate();
            System.out.println("OrderItem Saved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Fetch All OrderItems
    @Override
    public ArrayList<OrderItems> FETCHAll() {
        ArrayList<OrderItems> orderItemsList = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(FETCH_ALL)) {

            while (rs.next()) {
                orderItemsList.add(new OrderItems(
                    rs.getInt("OrderId"),
                    rs.getInt("MenuId"),
                    rs.getInt("Quantity"),
                    rs.getInt("ItemsTotal") // ✅ Correct column name
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemsList;
    }

    // ✅ Fetch One by OrderId
    @Override
    public OrderItems FETCHONE(int orderId) {
        OrderItems oi = null;
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_ONE)) {
            pstmt.setInt(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    oi = new OrderItems(
                        rs.getInt("OrderId"),
                        rs.getInt("MenuId"),
                        rs.getInt("Quantity"),
                        rs.getInt("ItemsTotal") // ✅ Correct column name
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oi;
    }

    // ✅ Update Quantity and ItemsTotal by OrderId
    @Override
    public int update(int quantity, int orderId) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE)) {
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, quantity); // Replace with actual total if needed
            pstmt.setInt(3, orderId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ✅ Delete OrderItems by OrderId
    @Override
    public int delete(int orderId) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE)) {
            pstmt.setInt(1, orderId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
