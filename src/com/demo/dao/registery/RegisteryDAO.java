package com.demo.dao.registery;

import com.demo.dao.implementation.ProductsDAO;
import com.demo.dao.implementation.UserDAO;

public class RegisteryDAO {
	public static com.demo.dao.layer.ProductsDAO productsDAO;
	public static com.demo.dao.layer.UserDAO userDAO;
	
	static {
		productsDAO = new ProductsDAO();
		userDAO = new UserDAO();
	}

	public static com.demo.dao.layer.UserDAO getUserDAO() {
		return userDAO;
	}

	public static void setUserDAO(com.demo.dao.layer.UserDAO userDAO) {
		RegisteryDAO.userDAO = userDAO;
	}



	public static com.demo.dao.layer.ProductsDAO getProductsDAO() {
		return productsDAO;
	}

	public static void setProductsDAO(com.demo.dao.layer.ProductsDAO productsDAO) {
		RegisteryDAO.productsDAO = productsDAO;
	}
	
}
