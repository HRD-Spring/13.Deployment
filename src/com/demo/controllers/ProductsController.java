package com.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.dao.registery.RegisteryDAO;

@Controller
@RequestMapping("/products")
public class ProductsController {
	
	@RequestMapping(name="/producs", method=RequestMethod.GET)
	public ModelAndView loadProducts() {
		ModelAndView modelAndView = new ModelAndView("products");
		modelAndView.addObject("allProducts", RegisteryDAO.getProductsDAO().getAllProducts());
		
		//ModelAndView modelAndView = new ModelAndView("products_sql");
		//modelAndView.addObject("allProducts", RegisteryDAO.getProductsDAO().getAllProductsSQL());
		return modelAndView;
	}
}
