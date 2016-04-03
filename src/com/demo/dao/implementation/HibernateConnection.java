package com.demo.dao.implementation;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.demo.pojo.Products;
import com.demo.pojo.User;

public class HibernateConnection {

	public static SessionFactory sessionFactory;

	public static SessionFactory doHibernateConnection() {
		Properties database = new Properties();
		database.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		database.setProperty("hibernate.connection.username", "root");
		database.setProperty("hibernate.connection.password", "");
		database.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/spring");
		database.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect"); 

		Configuration configuration = new Configuration()
				.setProperties(database)
				.addPackage("com.demo.pojo")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Products.class);
		
		StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		
		sessionFactory = configuration.buildSessionFactory(standardServiceRegistryBuilder.build());
		
		return sessionFactory;
		
	}
}
