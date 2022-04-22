package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import models.Supplier;
import persistence.DBConnection;

public class SupplierController {
		
	
	public void addSupplier(Supplier s) {
		Connection connection = DBConnection.createConnection();
		System.out.println("Start add supplier");
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO supplier (id,name,address,telephone) VALUES (" + s.getId() + ",'" + s.getName() + "', '" + s.getAddress() + "'," + s.getTelephone() + ");");
			DBConnection.closeConnection(connection);
		} catch (SQLException e) {
			System.out.println("Error in Statement creation : " +e);
		}
		System.out.println("End add supplier");
	}
	
	
	
	
	

}
