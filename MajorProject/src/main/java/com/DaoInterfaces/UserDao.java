package com.DaoInterfaces;

import java.util.ArrayList;

import com.DAOModel.User;

public interface UserDao {
	
	int insert(User user);
	ArrayList<User> FETCHALL();
	User FETCHONE(String username);
	int update(String password, String username);
	int delete(String usename);
	User getUserByEmail(String email);

}
