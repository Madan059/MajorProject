package com.DaoInterfaces;

import java.util.ArrayList;

import com.DAOModel.Restorant;

public interface RestorantDao {
	
	int Insert(Restorant r);
	ArrayList<Restorant>FETCH_ALL();
	Restorant FETCHONE(int RestorantId);
	int update(int DeliveryTime, int RestorantId);
	int delete(int RestorantId);

}
