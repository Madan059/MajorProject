package com.DAOModel;

import java.sql.Timestamp;

public class OrderHistory {

    private int OrderHistoryId;
    private int MenuId;
    private int OrderId;
    private int userId;
    private double TotalAmount;
    private String Status;
    private Timestamp DateAndTime;

    public OrderHistory() {}

    
    public OrderHistory(int orderHistoryId, int menuId, int orderId, int userId, double totalAmount, String status,
			Timestamp dateAndTime) {
		super();
		OrderHistoryId = orderHistoryId;
		MenuId = menuId;
		OrderId = orderId;
		this.userId = userId;
		TotalAmount = totalAmount;
		Status = status;
		DateAndTime = dateAndTime;
	}


	
    public int getOrderHistoryId() {
		return OrderHistoryId;
	}


	public void setOrderHistoryId(int orderHistoryId) {
		OrderHistoryId = orderHistoryId;
	}


	public int getMenuId() {
		return MenuId;
	}


	public void setMenuId(int menuId) {
		MenuId = menuId;
	}


	public int getOrderId() {
		return OrderId;
	}


	public void setOrderId(int orderId) {
		OrderId = orderId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public double getTotalAmount() {
		return TotalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		TotalAmount = totalAmount;
	}


	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		Status = status;
	}


	public Timestamp getDateAndTime() {
		return DateAndTime;
	}


	public void setDateAndTime(Timestamp dateAndTime) {
		DateAndTime = dateAndTime;
	}


	@Override
	public String toString() {
		return "OrderHistory [OrderHistoryId=" + OrderHistoryId + ", MenuId=" + MenuId + ", OrderId=" + OrderId
				+ ", userId=" + userId + ", TotalAmount=" + TotalAmount + ", Status=" + Status + ", DateAndTime="
				+ DateAndTime + "]";
	}


	
}
