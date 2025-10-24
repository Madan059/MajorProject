package com.DaoInterfaces;

import java.util.ArrayList;

import com.DAOModel.OrderItems;

public interface OrderItemsDao {
	void addOrderItem(OrderItems oi);
	ArrayList<OrderItems> FETCHAll();
	OrderItems FETCHONE(int OrderId) ;
	int update(int Quantity, int OrderId);
	int delete(int OrderId);

}
