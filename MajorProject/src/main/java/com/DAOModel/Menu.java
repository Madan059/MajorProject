package com.DAOModel;

public class Menu {
	private int MenuId;
	private int RestorantId;
	private String Name;
	private String Description;
	private int Price;
	private boolean isAvailable;
	private String ImagePath;
	
	
	public int getMenuId() {
		return MenuId;
	}
	public void setMenuId(int menuId) {
		MenuId = menuId;
	}
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
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getImagePath() {
		return ImagePath;
	}
	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}
	public Menu() {
		super();
	}
	public Menu(int menuId, int restorantId, String name, String description, int price, boolean isAvailable,
			String imagePath) {
		super();
		MenuId = menuId;
		RestorantId = restorantId;
		Name = name;
		Description = description;
		Price = price;
		this.isAvailable = isAvailable;
		ImagePath = imagePath;
	}
	@Override
	public String toString() {
		return "Menu [MenuId=" + MenuId + ", RestorantId=" + RestorantId + ", Name=" + Name + ", Description="
				+ Description + ", Price=" + Price + ", isAvailable=" + isAvailable + ", ImagePath=" + ImagePath + "]";
	}
	
	
	

}
