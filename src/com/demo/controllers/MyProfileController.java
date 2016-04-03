package com.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyProfileController {

	@RequestMapping(value = "/myprofile", method = RequestMethod.GET)
	public String loadMyProfile(HttpSession session, ModelMap map) {
		if (session.getAttribute("username") != null) {

			String username = session.getAttribute("username").toString();
			map.addAttribute("user", username);
		}
		return "myprofile";
	}
}
