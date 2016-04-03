package com.demo.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.pojo.Products;
import com.demo.pojo.ProductsList;

@Controller
public class RestConsumerController {

	@RequestMapping(value = "/jsonCosumeProductById/{productId}", method = RequestMethod.GET)
	public ModelAndView jsonCosumeProductById(@PathVariable("productId") String productId)
			throws JsonParseException, JsonMappingException, IOException {
		ModelAndView mav = new ModelAndView("restSingleProduct");

		String url = "http://localhost:8080/RestProvider/jsonGetProductById/" + productId;

		RestTemplate template = new RestTemplate();
		String jsonStr = template.getForObject(url, String.class);

		ObjectMapper mapper = new ObjectMapper();
		Products product = mapper.readValue(jsonStr, Products.class);
		mav.addObject("product", product);

		return mav;
	}

	@RequestMapping(value = "/jsonGetAllProducts", method = RequestMethod.GET)
	public ModelAndView jsonGetAllProducts() throws JsonParseException, JsonMappingException, IOException {
		ModelAndView mav = new ModelAndView("restAllProducts");
		String url = "http://localhost:8080/RestProvider/jsonGetAllProducts";

		RestTemplate template = new RestTemplate();
		String jsonArrayString = template.getForObject(url, String.class);
		ObjectMapper mapper = new ObjectMapper();

		List<Products> allProducts = mapper.readValue(jsonArrayString,
				TypeFactory.collectionType(List.class, Products.class));
		mav.addObject("allProducts", allProducts);
		return mav;

	}

	@RequestMapping(value = "/xmlConsumeProductById/{productId}", method = RequestMethod.GET)
	public ModelAndView xmlConsumeProductById(@PathVariable("productId") String productId) {
		ModelAndView mav = new ModelAndView("restSingleProduct");
		String url = "http://localhost:8080/RestProvider/xmlGetProductById/" + productId;

		RestTemplate template = new RestTemplate();
		Products products = template.getForObject(url, Products.class);
		mav.addObject("product", products);
		return mav;

	}

	@RequestMapping(value = "/xmlGetAllProducts", method = RequestMethod.GET)
	public ModelAndView xmlGetAllProducts() {
		ModelAndView mav = new ModelAndView("restAllProducts");
		String url = "http://localhost:8080/RestProvider/xmlGetAllProducts";

		RestTemplate template = new RestTemplate();
		ProductsList productsList = template.getForObject(url, ProductsList.class);
		mav.addObject("allProducts", productsList.getProducts());
		return mav;

	}
}
