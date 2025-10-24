package com.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.DAOModel.Menu;
import com.DBUtill.DBConnection;
import com.DaoInterfaces.MenuDao;

public class MenuDaoImpl implements MenuDao {
	
	static Menu m;

	private static final String INSERT = "insert into Menu (MenuId,RestorantId,Name,Description,Price,isAvailable,ImagePath) values(?,?,?,?,?,?,?)";
	private static final String FETCH_ALL = "select * from Menu";
	private static final String FETCH_ONE="SELECT * FROM Menu WHERE MenuId=?";
	private static final String UPDATE="update Menu set Price = ? where MenuId=?";
	private static final String DELETE="delete from Menu where MenuId=?";
    private static final String FETCH_BY_RESTORANT_ID = "select * from Menu where RestorantId=?";

	private static Connection con;

	static {
		con = DBConnection.connect();
	}

	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;

	@Override
	public int insert(Menu m) {

		try {
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, m.getMenuId());
			pstmt.setInt(2, m.getRestorantId());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getDescription());
			pstmt.setInt(5, m.getPrice());
			pstmt.setBoolean(6, m.isAvailable());
			pstmt.setString(7, m.getImagePath());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Menu> FETCHAll() {

		ArrayList<Menu> MenuList = new ArrayList<>();

		try {
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(FETCH_ALL);

			while (resultSet.next()) {
				MenuList.add(new Menu(resultSet.getInt("MenuId"), resultSet.getInt("RestorantId"),
						resultSet.getString("Name"), resultSet.getString("Description"), resultSet.getInt("Price"),
						resultSet.getBoolean("isAvailable"), resultSet.getString("ImagePath"))

				);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return MenuList;
	}

	@Override
	public Menu fetchOne(int menuId) {
	    Menu m = null;

	    try {
	        pstmt = con.prepareStatement(FETCH_ONE);
	        pstmt.setInt(1, menuId);

	        resultSet = pstmt.executeQuery();

	        if (resultSet.next()) {
	            m = new Menu(
	                resultSet.getInt("MenuId"),
	                resultSet.getInt("RestorantId"),
	                resultSet.getString("Name"),
	                resultSet.getString("Description"),
	                resultSet.getInt("Price"),
	                resultSet.getBoolean("isAvailable"),
	                resultSet.getString("ImagePath")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return m;
	}


	@Override
	public int update(int MenuId, int Price) {
		// TODO Auto-generated method stub
		
		try {
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setInt(1, m.getPrice());
			pstmt.setInt(2, m.getMenuId());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
		
		
	}

	@Override
	public int delete(int MenuId) {
		
		try {
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1,m.getMenuId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public ArrayList<Menu> fetchMenuByRestorantId(int RestorantId) {
	    ArrayList<Menu> menuList = new ArrayList<>();

	    try {
	        System.out.println("🔍 Fetching menus for RestorantId = " + RestorantId);

	        pstmt = con.prepareStatement(FETCH_BY_RESTORANT_ID);
	        pstmt.setInt(1, RestorantId);
	        resultSet = pstmt.executeQuery();

	        boolean found = false;

	        while (resultSet.next()) {
	            found = true;

	            int menuId = resultSet.getInt("MenuId");
	            int restId = resultSet.getInt("RestorantId");
	            String name = resultSet.getString("Name");
	            String description = resultSet.getString("Description");
	            int price = resultSet.getInt("Price");
	            boolean available = resultSet.getBoolean("isAvailable");
	            String imagePath = resultSet.getString("ImagePath");

	            System.out.println("✅ Found menu item: " + menuId + " | " + name + " | Price: " + price);

	            menuList.add(new Menu(menuId, restId, name, description, price, available, imagePath));
	        }

	        if (!found) {
	            System.out.println("⚠ No menu items found for RestorantId = " + RestorantId);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return menuList;
	}


	

}
