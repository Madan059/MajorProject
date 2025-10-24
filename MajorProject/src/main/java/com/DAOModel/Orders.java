package com.DAOModel;

import java.sql.Timestamp;

public class Orders {

    private int OrderId;
    private int userId;
    private int RestorantId;
    private Timestamp OrderDate;
    private double TotalAmount;
    private String Status;
    private String PaymentMode;

    // Constructors
    public Orders() {
        super();
    }

    public Orders(int orderId, int userId, int restorantId, Timestamp orderDate, double totalAmount, String status, String paymentMode) {
        super();
        this.OrderId = orderId;
        this.userId = userId;
        this.RestorantId = restorantId;
        this.OrderDate = orderDate;
        this.TotalAmount = totalAmount;
        this.Status = status;
        this.PaymentMode = paymentMode;
    }

    // Getters and Setters
    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        this.OrderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestorantId() {
        return RestorantId;
    }

    public void setRestorantId(int restorantId) {
        this.RestorantId = restorantId;
    }

    public Timestamp getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.OrderDate = orderDate;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount2) {
        this.TotalAmount = totalAmount2;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getPaymentMode() {
        return PaymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.PaymentMode = paymentMode;
    }

    @Override
    public String toString() {
        return "Orders [OrderId=" + OrderId + ", userId=" + userId + ", RestorantId=" + RestorantId +
               ", OrderDate=" + OrderDate + ", TotalAmount=" + TotalAmount +
               ", Status=" + Status + ", PaymentMode=" + PaymentMode + "]";
    }
}
