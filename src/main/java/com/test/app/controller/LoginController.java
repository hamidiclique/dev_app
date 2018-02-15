package com.test.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.app.entity.User;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginWindow(ModelMap model) {
		try {
			User user = new User();			
			model.addAttribute("user", user);			
		} catch (Exception ex) {
			ex.toString();
		}
		return "login";
	}
	
}
