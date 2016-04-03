package com.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.dao.registery.RegisteryDAO;
import com.demo.pojo.User;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loadLoginPage(HttpServletResponse response,
			@CookieValue(value = "hits", defaultValue = "0") Long hits) {
		ModelAndView modelAndView = new ModelAndView("login");
		System.out.println("Login method has been called");

		User user = new User();
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	@RequestMapping(value = "/springLogin", method = RequestMethod.GET)
	public ModelAndView springLogin() {
		ModelAndView modelAndView = new ModelAndView("springLogin");
		return modelAndView;
	}
	
	@RequestMapping(value = "/springHome", method = RequestMethod.GET)
	public ModelAndView springHome() {
		ModelAndView modelAndView = new ModelAndView("springHome");
		String usernmae = SecurityContextHolder.getContext().getAuthentication().getName();
		
		System.out.println(usernmae);
		modelAndView.addObject("username", usernmae);
		return modelAndView;
	}


	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public
	 * String loadLoginPage() { return "login"; }
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest request, Model md, HttpSession session, @Valid User user,
			BindingResult bindingResult) {
		try {

			// System.out.println(bindingResult.getAllErrors().size());

			String username = request.getParameter("username");
			String password = request.getParameter("password");

			System.out.println(username + " " + password);

			if (bindingResult.getAllErrors().size() > 0) {
				System.err.println("Server side validation take place...");
			} else {

				String message = RegisteryDAO.userDAO.doHibernateLogin(username, password);
				if (message.equals("login success")) {
					session.setAttribute("username", username);
					return "redirect:/myprofile";
				} else {
					md.addAttribute("errorMsg", message);
				}

				/*
				 * String message =
				 * RegisteryDAO.userDAO.doLoginProcess(username, password); if
				 * (message.equals("login success")) {
				 * session.setAttribute("username", username); return
				 * "redirect:/myprofile"; // user redirect to change url } else
				 * { md.addAttribute("errorMsg", message); }
				 */
			}
			return "login";
		} catch (Exception e) {
			// TODO: handle exception
			return "login";
		}
	}
}
