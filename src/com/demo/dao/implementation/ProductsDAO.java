package com.demo.dao.implementation;

import java.util.List;

import org.hibernate.Session;

import com.demo.dao.implementation.HibernateConnection;
import com.demo.pojo.Products;

public class ProductsDAO implements com.demo.dao.layer.ProductsDAO{
	
	public List<Products> getAllProducts() {
		Session session = HibernateConnection.doHibernateConnection().openSession();
		
		List<Products> allProducts = session.createQuery("From Products").list();
		session.close();
		return allProducts;
		
	}
	
	public List<Object[]> getAllProductsSQL() {
		Session session = HibernateConnection.doHibernateConnection().openSession();
		
		List<Object[]> allProducts = session.createSQLQuery("select * from products").list();
		session.close();
		
		return allProducts;
	}
	
	public boolean deleteProductById(String id) {
		try {
			Session session = HibernateConnection.doHibernateConnection().openSession();
			List<Products> products = session.createQuery("From Products where id='"+id+"'").list();
			
			if (products != null && products.get(0) != null) {
				session.beginTransaction();
				session.delete(products.get(0));
				session.getTransaction().commit();
				session.close();
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Products getProductByProductId(String id) {
		try {
			Session session = HibernateConnection.doHibernateConnection().openSession();
			List<Products> products = session.createQuery("From Products where id='"+id+"'").list();
			return products.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
