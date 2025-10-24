package com.Controller;

import java.io.IOException;
import java.util.ArrayList;

import com.DAOImpl.RestorantDaoImpl;
import com.DAOModel.Restorant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/GetRestorants")
public class GetAllRestorants extends HttpServlet{
	
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	            throws ServletException, IOException {
	        
	        RestorantDaoImpl dao = new RestorantDaoImpl();
	        ArrayList<Restorant> restorantList = dao.FETCH_ALL();
	        
	        req.setAttribute("restorantList", restorantList);
	        
	        RequestDispatcher rd = req.getRequestDispatcher("Welcome.jsp");
	        rd.forward(req, resp);

}
}
