package com.test.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.app.entity.User;

@Controller
public class AccessControlListController {
	
	@RequestMapping(value = "/addNewUser", method = RequestMethod.GET)
	public String showAddUserScreen(ModelMap model) {
		try {
			User user = new User();
			model.addAttribute("user", user);
		} catch (Exception ex) {
			ex.toString();
		}
		return "add_new_user";
	}
	
	@RequestMapping(value = "/saveNewUser", method = RequestMethod.POST)
	public String handleAddNewUser(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {
/*		hotel.setHotelId(hotel.getCity()+gen());
		hotel.setStatus("A");
		try{
			hmapper.saveHotel(hotel);
			model.addAttribute("outcome", SUCCESS);
		}
		catch(Exception ex) {
			ex.toString();
			model.addAttribute("outcome", FAILURE);
		}*/
		return "add_hotel_success";
	}
	
}
