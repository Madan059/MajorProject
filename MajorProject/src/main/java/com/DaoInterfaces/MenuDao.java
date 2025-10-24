package com.DaoInterfaces;

import java.util.ArrayList;

import com.DAOModel.Menu;

public interface MenuDao {
	
	int insert(Menu m);
	ArrayList<Menu> FETCHAll();
	Menu fetchOne(int MenuId) ;
	int update(int MenuId, int Price);
	int delete(int MenuId) ;
	ArrayList<Menu> fetchMenuByRestorantId(int RestorantId);
	
	
}
