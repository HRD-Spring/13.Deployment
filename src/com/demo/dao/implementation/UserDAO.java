package com.demo.dao.implementation;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.demo.dao.implementation.DatabaseConnectivity;
import com.demo.dao.implementation.HibernateConnection;
import com.demo.pojo.User;

public class UserDAO implements com.demo.dao.layer.UserDAO {

	public String doLoginProcess(String username, String password) {
		try {
			DatabaseConnectivity dConnectivity = new DatabaseConnectivity();
			Statement statement = dConnectivity.doDBConnection();

			ResultSet rs = statement.executeQuery(
					"select count(*) from user where username='" + username + "' and password = '" + password + "'");
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			if (count == 1) {
				return "login success";
			} else {
				return "username or password does not match";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "Something went wrong... Please try agian!!";
		}
	}

	public String doHibernateLogin(String username, String password) {
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();

			session.beginTransaction();
			List<User> users = session
					.createQuery("From User where username='" + username + "' and password = '" + password + "'")
					.list();

			session.close();
			if (users.size() == 1) {
				return "login success";
			} else {
				return "username or password does not match";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "Something went wrong... Please try agian!!";
		}
	}

	public String doSignUp(String username, String password, String gender, String vehicle, String country,
			String image) {
		try {
			DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
			Statement statement = databaseConnectivity.doDBConnection();

			statement.execute("insert into user values('" + username + "','" + password + "','" + gender + "','"
					+ vehicle + "','" + country + "','" + image + "')");
			return "Sign Up Succesffuly...";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Something went wrong please try again!!!";
		}
	}

	public String doHibernateSigup(User user) {
		try {
			Session session = HibernateConnection.doHibernateConnection().openSession();
			session.beginTransaction();
			session.save(user);

			// hibernate will do this session depend on primary key
			// session.update(user); //update user
			// session.delete(user); //delete user

			// insert, update, delete multiple user by using List of User
			// List<User> users = null;
			// session.save(users);

			// this if no user > save, else update user depend of primary key
			// session.saveOrUpdate(user);

			session.getTransaction().commit();
			session.close();
			return "Sign Up Seccessfully...";

		} catch (Exception e) {
			// TODO: handle exception
			return "User is already there with this username";
		}
	}
}
