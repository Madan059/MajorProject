package com.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.DAOModel.Restorant;
import com.DBUtill.DBConnection;
import com.DaoInterfaces.RestorantDao;



public class RestorantDaoImpl implements RestorantDao {
	private static final String insertQuery = "insert into Restorant (RestorantId,Name,CuisineType,DeliveryTime,Address,Ratings,isActive,ImagePath) values(?,?,?,?,?,?,?,?)";
	private static final String FETCH_ALL="select * from Restorant";
	private static final String FETCH_ONE="select * from Restorant where RestorantId=?";
	private static final String UPDATE="update Restorant set DeliveryTime=? where RestorantId=? ";
	private static final String DELETE="delete from Restorant where RestorantId=?";
	private static Connection con;

	static {
		con = DBConnection.connect();
	}

	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	
	private static Restorant r;

	@Override
	public int Insert(Restorant r) {
		// TODO Auto-generated method stub

		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setInt(1, r.getRestorantId());
			pstmt.setString(2, r.getName());
			pstmt.setString(3, r.getCuisineType());
			pstmt.setInt(4, r.getDeliveryTime());
			pstmt.setString(5, r.getAddress());
			pstmt.setFloat(6, r.getRatings());
			pstmt.setBoolean(7, r.isActive());
			pstmt.setString(8, r.getImagePath());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public ArrayList<Restorant> FETCH_ALL() {
		// TODO Auto-generated method stub
		ArrayList<Restorant> RestorantList=new ArrayList<>();
		
		
		try {
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(FETCH_ALL);
			
			while(resultSet.next()) {
				RestorantList.add(new Restorant(resultSet.getInt("RestorantId"),resultSet.getString("Name"),
						resultSet.getString("CuisineType"),resultSet.getInt("DeliveryTime"),resultSet.getString("Address"),
						resultSet.getFloat("Ratings"),resultSet.getBoolean("isActive"),resultSet.getString("ImagePath")));
				
			
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return RestorantList;
	}

	@Override
	public Restorant FETCHONE(int RestorantId) {
		
		try {
			pstmt=con.prepareStatement(FETCH_ONE);
			pstmt.setInt(1, r.getRestorantId());
			resultSet=pstmt.executeQuery();
			
			while(resultSet.next()) {
				r=new Restorant(resultSet.getInt("RestorantId"),resultSet.getString("Name"),
						resultSet.getString("CuisineType"),resultSet.getInt("DeliveryTime"),resultSet.getString("Address"),
						resultSet.getFloat("Ratings"),resultSet.getBoolean("isActive"),resultSet.getString("ImagePath"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// TODO Auto-generated method stub
		return r;
	}

	@Override
	public int update(int DeliveryTime, int RestorantId) {
		
		try {
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setInt(1, r.getDeliveryTime());
			pstmt.setInt(2, r.getRestorantId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public int delete(int RestorantId) {
		
		try {
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, r.getRestorantId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		// TODO Auto-generated method stub
		return 0;
	}


}
