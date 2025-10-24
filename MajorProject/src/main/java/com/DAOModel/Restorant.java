package com.DAOModel;

public class Restorant {
	
	private int RestorantId;
	private String Name;
	private String CuisineType;
	private int DeliveryTime;
	private String Address;
	private Float Ratings;
	private boolean isActive;
	private String ImagePath;
	public int getRestorantId() {
		return RestorantId;
	}
	public void setRestorantId(int restorantId) {
		RestorantId = restorantId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getCuisineType() {
		return CuisineType;
	}
	public void setCuisineType(String cuisineType) {
		CuisineType = cuisineType;
	}
	public int getDeliveryTime() {
		return DeliveryTime;
	}
	public void setDeliveryTime(int deliveryTime) {
		DeliveryTime = deliveryTime;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Float getRatings() {
		return Ratings;
	}
	public void setRatings(Float ratings) {
		Ratings = ratings;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getImagePath() {
		return ImagePath;
	}
	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}
	public Restorant() {
		super();
	}
	public Restorant(int restorantId, String Name, String cuisineType, int deliveryTime, String address, Float ratings,
			boolean isActive, String imagePath) {
		super();
		RestorantId = restorantId;
		this.Name = Name;
		CuisineType = cuisineType;
		DeliveryTime = deliveryTime;
		Address = address;
		Ratings = ratings;
		this.isActive = isActive;
		ImagePath = imagePath;
	}
	@Override
	public String toString() {
		return "Restorant [RestorantId=" + RestorantId + ", name=" + Name + ", CuisineType=" + CuisineType
				+ ", DeliveryTime=" + DeliveryTime + ", Address=" + Address + ", Ratings=" + Ratings + ", isActive="
				+ isActive + ", ImagePath=" + ImagePath + "]";
	}
	

}
