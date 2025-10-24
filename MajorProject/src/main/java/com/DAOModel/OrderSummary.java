package com.DAOModel;

import java.sql.Timestamp;

public class OrderSummary {
    private int menuId;
    private String name;
    private String description;
    private Timestamp dateAndTime;
    private String status;

    // Getters and Setters
    public int getMenuId() { return menuId; }
    public void setMenuId(int menuId) { this.menuId = menuId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Timestamp getDateAndTime() { return dateAndTime; }
    public void setDateAndTime(Timestamp dateAndTime) { this.dateAndTime = dateAndTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
