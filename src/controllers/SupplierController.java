package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import models.Supplier;
import persistence.DBConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SupplierController {
	
	private static final EntityManagerFactory ENTITY_MANAGER_FACTOR = Persistence.createEntityManagerFactory("JavaHelps");
	
	public void addSupplier(Supplier s) {
		System.out.println("Start add supplier");
		EntityManager em = ENTITY_MANAGER_FACTOR.createEntityManager();
		EntityTransaction transaction = null;
		
		try {
		transaction = em.getTransaction();
		transaction.begin();
		
		em.persist(s);
		
		transaction.commit();
		} catch(Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}finally {
			em.close();
		}
		
		System.out.println("End add supplier");
	}
	
	/*
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
	*/
	
	
	
	
	

}
