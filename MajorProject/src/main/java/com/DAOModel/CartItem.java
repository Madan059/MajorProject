package com.DAOModel;



public class CartItem{
	
	
	private int ItemId;
	private int RestorantId;
    private String name;
    private int quantity;
    private double price;
    
	public int getItemId() {
		return ItemId;
	}
	public void setItemId(int itemId) {
		ItemId = itemId;
	}
	public int getRestorantId() {
		return RestorantId;
	}
	public void setRestorantId(int restorantId) {
		RestorantId = restorantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public CartItem(int itemId, int restorantId, String name, int quantity, double price) {
		super();
		ItemId = itemId;
		RestorantId = restorantId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	public CartItem() {
		super();
	}
	@Override
	public String toString() {
		return "CartItem [ItemId=" + ItemId + ", RestorantId=" + RestorantId + ", name=" + name + ", quantity="
				+ quantity + ", price=" + price + "]";
	}
	
	

	
	
	
    
    
    
   

    }

