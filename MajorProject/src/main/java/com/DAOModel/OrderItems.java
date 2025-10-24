package com.DAOModel;

public class OrderItems {
	
//	private int OrderItemsId;
	private int OrderId;
	private int MenuId;
	private int Quantity;
	private double ItemTotal;
	
//	public int getOrderItemsId() {
//		return OrderItemsId;
//	}
//	public void setOrderItemsId(int orderItemsId) {
//		OrderItemsId = orderItemsId;
//	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public int getMenuId() {
		return MenuId;
	}
	public void setMenuId(int menuId) {
		MenuId = menuId;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public double getItemTotal() {
		return ItemTotal;
	}
	public void setItemTotal(double d) {
		ItemTotal = d;
	}
	public OrderItems() {
		super();
	}
	public OrderItems(int orderId, int menuId, int quantity, int itemTotal) {
		super();
		
		OrderId = orderId;
		MenuId = menuId;
		Quantity = quantity;
		ItemTotal = itemTotal;
	}
	@Override
	public String toString() {
		return "OrderItems [OrderId=" + OrderId + ", MenuId=" + MenuId + ", Quantity=" + Quantity + ", ItemTotal="
				+ ItemTotal + "]";
	}
	public void setPrice(double price) {
		// TODO Auto-generated method stub
		
	}
}
	
	
	
	



