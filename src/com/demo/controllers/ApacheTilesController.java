package com.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApacheTilesController {

	@RequestMapping(value="/apacheTilesDemo", method=RequestMethod.GET)
	public String  apacheTilesDemo() {
		return "tiles";
	}
}
