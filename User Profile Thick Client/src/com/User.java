package com;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	
	
	
	//DB Connection	
		public static Connection connect()
		{
		 Connection con = null;

		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");
		 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/registerdetails",
		 "root", "");
		 //For testing
		 System.out.print("Successfully connected");
		 }
		 catch(Exception e)
		 {
			 System.out.print("Could not connect");
		 }

		 return con;
		}
		
		
		
		
		
	//Insert Method
		
		public String insertuserdetails(String Name, String Email, String Address, String Username,String Password)
		{
		 String output = "";
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database";
		 }
		 // create a prepared statement
		 String query = " insert into signup values (?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, Name);
		 preparedStmt.setString(3, Email);
		 preparedStmt.setString(4, Address);
		 preparedStmt.setString(5, Username);
		 preparedStmt.setString(6, Password);
		
		
		//execute the statement
		 preparedStmt.execute();
		 con.close();

		 String newUser = readDetails();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newUser + "\"}";
		 
		 
		 }
		catch (Exception e)
		 {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting User Details.\"}"; 
		 System.err.println(e.getMessage());
		 }
		return output;
		}
		
		
		
		
		

	//Read Method	
		
		public String readDetails()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Name</th>" +
		 "<th>Email</th>" +
		 "<th>Address</th>" +
		 "<th>Username</th>" +
		 "<th>Password</th>" +
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from signup";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String ID = Integer.toString(rs.getInt("ID"));
		 String Name = rs.getString("Name");
		 String Email = rs.getString("Email");
		 String Address = rs.getString("Address");
		 String Username = rs.getString("Username");
		 String Password = rs.getString("Password");
		 
		 // Add into the html table
		 
		 output += "<tr><td><input id='hidUserIDUpdate' name='hidUserIDUpdate' type='hidden' value='"+ID+"'>" + Name + "</td>";
		 output += "<td>" + Email + "</td>";
		 output += "<td>" + Address + "</td>";
		 output += "<td>" + Username + "</td>";
		 output += "<td>" + Password + "</td>";
		 
		 // buttons
		 output += "<td><input name='btnUpdate' " 
				 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-userid='" + ID + "'></td>"
				 + "<td><form method='post' action='User.jsp'>"
				 + "<input name='btnRemove' " 
				 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='" + ID + "'>"
				 + "<input name='hidUserIDDelete' type='hidden' " 
				 + " value='" + ID + "'>" + "</form></td></tr>"; 
				 } 
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the user details.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		
		
		
		

		
		
	//Update Method	
		
		public String updateDetails(String ID, String Name, String Email, String Address, String Username,String Password) {
			String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE signup SET Name=?,Email=?,Address=?,Username=?,Password=? WHERE ID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, Name);
			 preparedStmt.setString(2, Email);
			 preparedStmt.setString(3, Address);
			 preparedStmt.setString(4, Username);
			 preparedStmt.setString(5, Password);
			 preparedStmt.setString(6, ID);
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();

			 String newUser = readDetails();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newUser + "\"}";
			 }
			 catch (Exception e)
			 {
				 output = "{\"status\":\"error\", \"data\":\"Error while updating User Details.\"}";
			 System.err.println(e.getMessage());
			 }
			 return output; 
			
		}
		
		
	//Delete Method	
		public String deleteDetails(String ID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from signup where ID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();

		 String newUser = readDetails();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newUser + "\"}";
		 
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\":\"Error while deleting User Details.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		
		
	
	

}
