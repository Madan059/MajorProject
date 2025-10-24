package com.DaoInterfaces;

import java.util.ArrayList;

import com.DAOModel.Orders;

public interface OrdersDao {
	
int addOrder(Orders order);
	ArrayList<Orders> FETCHAll();
	Orders fetchOne(int OrderId);
	int update(int OrderId, int TotalAmount);
	int delete(int orderId);
	 

}
