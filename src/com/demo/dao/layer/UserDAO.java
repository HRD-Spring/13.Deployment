package com.demo.dao.layer;

import com.demo.pojo.User;

public interface UserDAO {
	// For login
	public String doLoginProcess(String username, String password);

	public String doHibernateLogin(String username, String password);
	
	
	// For sign up
	public String doSignUp(String username, String password, String gender, String vehicle, String country, String image);
	
	public String doHibernateSigup(User user) ;
	
	public User getUserByUsername(String username);

}
