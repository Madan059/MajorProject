package com.DaoInterfaces;

import java.util.ArrayList;
import java.util.List;

import com.DAOModel.OrderHistory;
import com.DAOModel.OrderSummary;

public interface OrderHistoryDao {
	
	static int insert(OrderHistory oh) {
		return 0;
	}
		
	ArrayList<OrderHistory> FETCHALL();
	 OrderHistory FETCHONE(int orderId);
	 int update(double TotalAmount, int orderId);
	 int delete(int userId);
	List<OrderHistory> fetchOrderHistoryByUserId(int userId);
	List<OrderSummary> fetchOrderSummariesByUserId(int userId);
	

}
