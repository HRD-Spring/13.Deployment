package com.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyProfileController {

	@RequestMapping(value="/myprofile", method=RequestMethod.GET)
	public ModelAndView loadMyProfile(HttpSession session) {
		ModelAndView modelAndView=new ModelAndView("myprofile");
		String username = session.getAttribute("username").toString();
		modelAndView.addObject("user", username);
		return modelAndView;
	}
}
