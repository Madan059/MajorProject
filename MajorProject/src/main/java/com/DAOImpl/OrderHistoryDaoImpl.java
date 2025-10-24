package com.DAOImpl;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.DAOModel.OrderHistory;
import com.DAOModel.OrderSummary;
import com.DBUtill.DBConnection;
import com.DaoInterfaces.OrderHistoryDao;

public class OrderHistoryDaoImpl implements OrderHistoryDao {

    private static final String INSERT = "INSERT INTO OrderHistory (MenuId, OrderId, userId, TotalAmount, Status, DateAndTime) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FETCH_ALL = "SELECT * FROM OrderHistory";
    private static final String FETCH_ONE = "SELECT * FROM OrderHistory WHERE OrderId=?";
    private static final String UPDATE = "UPDATE OrderHistory SET TotalAmount = ? WHERE OrderId=?";
    private static final String DELETE = "DELETE FROM OrderHistory WHERE userId=?";
    private static final String FETCH_BY_USER = "SELECT * FROM OrderHistory WHERE userId=?";

    private static Connection con;

    static {
        con = DBConnection.connect();
    }

    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;

    public int insert(OrderHistory oh) {
        int rowsAffected = 0;

        try (PreparedStatement ps = con.prepareStatement(INSERT)) {
            ps.setInt(1, oh.getMenuId());
            ps.setInt(2, oh.getOrderId());
            ps.setInt(3, oh.getUserId());
            ps.setDouble(4, oh.getTotalAmount());
            ps.setString(5, oh.getStatus());
            ps.setTimestamp(6, oh.getDateAndTime()); // ✅ Added DateAndTime

            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ OrderHistory inserted successfully!");
            } else {
                System.out.println("❌ Failed to insert OrderHistory — No rows affected.");
            }

        } catch (SQLException e) {
            System.out.println("❌ SQL Error while inserting into OrderHistory:");
            e.printStackTrace();
        }

        return rowsAffected;
    }

    @Override
    public ArrayList<OrderHistory> FETCHALL() {
        ArrayList<OrderHistory> orderHistoryList = new ArrayList<>();
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCH_ALL);
            while (resultSet.next()) {
                OrderHistory oh = new OrderHistory();
                oh.setOrderHistoryId(resultSet.getInt("OrderHistoryId"));
                oh.setMenuId(resultSet.getInt("MenuId"));
                oh.setOrderId(resultSet.getInt("OrderId"));
                oh.setUserId(resultSet.getInt("userId"));
                oh.setTotalAmount(resultSet.getDouble("TotalAmount"));
                oh.setStatus(resultSet.getString("Status"));
                oh.setDateAndTime(resultSet.getTimestamp("DateAndTime")); // ✅ Added DateAndTime
                orderHistoryList.add(oh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    @Override
    public OrderHistory FETCHONE(int orderId) {
        OrderHistory oh = null;
        try {
            pstmt = con.prepareStatement(FETCH_ONE);
            pstmt.setInt(1, orderId);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                oh = new OrderHistory();
                oh.setOrderHistoryId(resultSet.getInt("OrderHistoryId"));
                oh.setMenuId(resultSet.getInt("MenuId"));
                oh.setOrderId(resultSet.getInt("OrderId"));
                oh.setUserId(resultSet.getInt("userId"));
                oh.setTotalAmount(resultSet.getDouble("TotalAmount"));
                oh.setStatus(resultSet.getString("Status"));
                oh.setDateAndTime(resultSet.getTimestamp("DateAndTime")); // ✅ Added DateAndTime
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oh;
    }

    @Override
    public int update(double totalAmount, int orderId) {
        try {
            pstmt = con.prepareStatement(UPDATE);
            pstmt.setDouble(1, totalAmount);
            pstmt.setInt(2, orderId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int userId) {
        try {
            pstmt = con.prepareStatement(DELETE);
            pstmt.setInt(1, userId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

	
		public ArrayList<OrderHistory> fetchOrderHistoryByUserId(int userId) {
		    ArrayList<OrderHistory> userOrderHistoryList = new ArrayList<>();
		    try {
		        pstmt = con.prepareStatement(FETCH_BY_USER);
		        pstmt.setInt(1, userId);
		        resultSet = pstmt.executeQuery();
		        while (resultSet.next()) {
		            OrderHistory oh = new OrderHistory();
		            oh.setOrderHistoryId(resultSet.getInt("OrderHistoryId"));
		            oh.setMenuId(resultSet.getInt("MenuId"));
		            oh.setOrderId(resultSet.getInt("OrderId"));
		            oh.setUserId(resultSet.getInt("userId"));
		            oh.setTotalAmount(resultSet.getDouble("TotalAmount"));
		            oh.setStatus(resultSet.getString("Status"));
		            oh.setDateAndTime(resultSet.getTimestamp("DateAndTime")); // ✅ Include timestamp
		            userOrderHistoryList.add(oh);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return userOrderHistoryList;
		}
		
		public List<OrderSummary> fetchOrderSummariesByUserId(int userId) {
		    List<OrderSummary> summaries = new ArrayList<>();
		    String sql = "SELECT oh.MenuId, m.Name, m.Description, oh.DateAndTime, oh.Status " +
		                 "FROM OrderHistory oh JOIN Menu m ON oh.MenuId = m.MenuId " +
		                 "WHERE oh.userId = ?";

		    try (PreparedStatement ps = con.prepareStatement(sql)) {
		        ps.setInt(1, userId);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		            OrderSummary summary = new OrderSummary();
		            summary.setMenuId(rs.getInt("MenuId"));
		            summary.setName(rs.getString("Name"));
		            summary.setDescription(rs.getString("Description"));
		            summary.setDateAndTime(rs.getTimestamp("DateAndTime"));
		            summary.setStatus(rs.getString("Status"));
		            summaries.add(summary);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return summaries;
		}


	}
