package com.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.DAOModel.User;
import com.DBUtill.DBConnection;
import com.DaoInterfaces.UserDao;

public class UserDaoImpl implements UserDao {
	
	private static final String insertQuery = "insert into user (username,password,email,address) values(?,?,?,?)";
	private static final String FETCH_ALL="select * from user";
	private static final String FECTH_ONE="select* from user where username=? ";
	private static final String UPDATE="update user set password=? where username=?";
	private static final String DELETE="delete user where username=?";
	private static final String FETCH_BY_EMAIL="select* from user where email=? ";
	
	
	
	
	
	private static Connection con;

	static {
	con=DBConnection.connect();

}

	private PreparedStatement pstmt;
	
	private User user;




	private Statement stmt;
	private ResultSet resultSet;

	@Override
	public int insert(User user) {
		
		
		try {
			pstmt=con.prepareStatement(insertQuery);
			pstmt.setString(1,user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			
		return 	pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public ArrayList<User> FETCHALL() {
		// TODO Auto-generated method stub
		
		ArrayList<User> UserList=new ArrayList<>();
		
		try {
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(insertQuery);
			while(resultSet.next()) {
				UserList.add(new User(resultSet.getString("username"),resultSet.getString("password"),
						resultSet.getString("email"),resultSet.getString("address")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return UserList;
	}

	@Override
	public User FETCHONE(String username) {
		// TODO Auto-generated method stub
		
		try {
			pstmt=con.prepareStatement(FECTH_ONE);
			pstmt.setString(1, user.getUsername());
			resultSet=pstmt.executeQuery();
			
			while(resultSet.next()) {
				user=new User(resultSet.getString("username"),resultSet.getString("password"),
						resultSet.getString("email"),resultSet.getString("address"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public int update(String password, String userId) { 
		// TODO Auto-generated method stub
		
		try {
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getUsername());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
	}

	@Override
	public int delete(String username) {
		// TODO Auto-generated method stub
		
		try {
			pstmt=con.prepareStatement(DELETE);
			pstmt.setString(1, user.getUsername());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
	}

	@Override
	public User getUserByEmail(String email) {
		User user=null;
		// TODO Auto-generated method stub
	
		           try {
					pstmt=con.prepareStatement(FETCH_BY_EMAIL);
					   pstmt.setString(1, email);
					   resultSet=pstmt.executeQuery();

		        if (resultSet.next()) {
		            // only initialize User when a record is found
		            user = new User();
		          
		            user.setUserID(resultSet.getInt("userId"));	            
		            user.setUsername(resultSet.getString("username"));
		            user.setEmail(resultSet.getString("email"));
		            user.setPassword(resultSet.getString("password"));
		            user.setAddress(resultSet.getString("address"));
		        }
                     } catch (Exception e) {
                 e.printStackTrace();
                     }
    

		   	
		    return user; // will return null if no record found
		}

		
	}


