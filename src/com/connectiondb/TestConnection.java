package com.connectiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
	
	public void testDB() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("driver loaded");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","richirich@");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from testuser");
		
		while(rs.next()){
			String s=rs.getString("firstname");
			System.out.println(s);
		}
		
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		TestConnection ts= new TestConnection();
		ts.testDB();

	}

}
