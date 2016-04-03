package com.demo.controllers;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.dao.registery.RegisteryDAO;
import com.demo.pojo.User;

@Controller
public class SignUpController {

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public ModelAndView loadSignup() {
		ModelAndView modelAndView = new ModelAndView("signup");
		return modelAndView;
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ModelAndView doSignUpProcess(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("signup");
		
		String message ="";
		
		if(ServletFileUpload.isMultipartContent(request)){
			try {
				List<FileItem> data = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				String username = data.get(0).getString();
				String password = data.get(1).getString();
				String repassword = data.get(2).getString();
				String gender = data.get(3).getString();
				String vehicle = data.get(4).getString();
				String country = data.get(5).getString();
				
				String image = new File(data.get(6).getName()).getName();
				
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setGender(gender);
				user.setCountry(country);
				user.setVehicle(vehicle);
				user.setImage(image);
								
				if (password.equals(repassword)) {
					
					//message = RegisteryDAO.userDAO.doSignUp(username, repassword, gender, vehicle, country, image);
					
					message = RegisteryDAO.userDAO.doHibernateSigup(user);
					
					String path = request.getSession().getServletContext().getRealPath("/") + "//WEB-INF//images//";
					data.get(6).write(new File(path + File.separator + image));
				}else {
					message = "Password does not match.. please try again";
				}

			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				message = "Please try again...";
			}
		}
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	@RequestMapping(value="/dynamic/{message}", method=RequestMethod.GET)
	public ModelAndView dynamicDemo(@PathVariable("message") String message ) {
		ModelAndView modelAndView = new ModelAndView("signup");
		
		return modelAndView;
	}
}
