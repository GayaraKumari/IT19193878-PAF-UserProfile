package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


@WebServlet("/UserAPI")
public class UserAPI extends HttpServlet {
	User Obj =new User();
       
    
    public UserAPI() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = Obj.insertuserdetails(request.getParameter("Name"), 
				 request.getParameter("Email"), 
				request.getParameter("Address"), 
				request.getParameter("Username"),
				request.getParameter("Password")); 
				response.getWriter().write(output); 
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Map paras = getParasMap(request);
		 String output = Obj.updateDetails(paras.get("hidUserIDSave").toString(),
		 paras.get("Name").toString(),
		 paras.get("Email").toString(),
		paras.get("Address").toString(),
		paras.get("Username").toString(),
		paras.get("Password").toString()
				 );
		response.getWriter().write(output); 
		
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Map paras = getParasMap(request);
		 String output = Obj.deleteDetails(paras.get("ID").toString());
		response.getWriter().write(output); 
	}
	
	
	
	
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
		 String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}
	

	 }


