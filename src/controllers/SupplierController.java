package controllers;


import java.util.List;
import java.util.Optional;
import java.util.Vector;

import models.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class SupplierController {
	
	private static final EntityManagerFactory ENTITY_MANAGER_FACTOR = Persistence.createEntityManagerFactory("JavaHelps");

	public List<Supplier> getAllSupplier() {
		EntityManager em = ENTITY_MANAGER_FACTOR.createEntityManager();
		List<Supplier> resultSupplierList = em.createQuery("SELECT s FROM Supplier s", Supplier.class).getResultList(); 
		return resultSupplierList;
		
	}
	
	public Supplier getSupplier(String name, String address, String telephone) {
		EntityManager em = ENTITY_MANAGER_FACTOR.createEntityManager();
		TypedQuery<Supplier> query = em.createQuery("SELECT s FROM Supplier s where name = :name and address = :address and telephone = :telephone", Supplier.class); 
		Supplier supplier = query.setParameter("name", name).setParameter("address", address).setParameter("telephone", Integer.parseInt(telephone)).getSingleResult();
		if (supplier == null) {
			System.out.println("No supplier found");
			return null;
		}else {
			return supplier;
		}
		
	}
	
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


	public Supplier updateSupplier(Integer id, String name, String address, String telephone) {
		System.out.println("Start Update supplier");
		EntityManager em = ENTITY_MANAGER_FACTOR.createEntityManager();
		EntityTransaction transaction = null;
		Supplier supplier = null;
		
		try {
		transaction = em.getTransaction();
		transaction.begin();
		
		supplier = em.find(Supplier.class, id);
		supplier.setName(name);
		supplier.setAddress(address);
		supplier.setTelephone(Integer.parseInt(telephone));
		
		em.persist(supplier);
		
		transaction.commit();
		} catch(Exception e) {
			if (transaction != null) {
				transaction.rollback();
				return null;
			}
			e.printStackTrace();
		}finally {
			em.close();
		}
		
		System.out.println("End update supplier");
		return supplier;
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
