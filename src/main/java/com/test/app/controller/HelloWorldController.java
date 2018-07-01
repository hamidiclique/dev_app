package com.test.app.controller;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.app.dao.HotelMapper;
import com.test.app.entity.Hotel;

@Controller
public class HelloWorldController {

	@Autowired
	HotelMapper hmapper;

	//Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	private final String SUCCESS = "successful";
	private final String FAILURE = "failed";

	@RequestMapping(value = "/listOfHotel", method = RequestMethod.GET)
	public String showListOfHotels(ModelMap model) {
		try {
			model.addAttribute("htlList", hmapper.getAllHotels());
		} catch (Exception ex) {
			model.addAttribute("htlList", new ArrayList<Hotel>());
		}
		return "list_hotel";
	}

	@RequestMapping(value = "/addHotel", method = RequestMethod.GET)
	public String addNewHotel(ModelMap model) {
		Hotel htl = new Hotel();
		model.addAttribute("htl", htl);
		return "add_hotel";
	}

	@RequestMapping(value = "/saveHotelProcess", method = RequestMethod.POST)
	public String handleAddNewHotel(@ModelAttribute("htl") Hotel hotel, BindingResult result, ModelMap model) {
		hotel.setHotelId(hotel.getCity()+gen());
		hotel.setStatus("A");
		try{
			hmapper.saveHotel(hotel);
			model.addAttribute("outcome", SUCCESS);
		}
		catch(Exception ex) {
			ex.toString();
			model.addAttribute("outcome", FAILURE);
		}
		return "add_hotel_success";
	}
	
	@RequestMapping(value = "/updateHotelData", method = RequestMethod.GET)
	public String displayHotelUpdateData(@RequestParam String hotelId, ModelMap model) {
		String searchId = hotelId;
		Hotel hotelToUpdate = hmapper.getHotelById(searchId);
		model.addAttribute("upHotel", hotelToUpdate);
		return "update_hotel";
	}
	
	@RequestMapping(value = "/updateHotelProcess", method = RequestMethod.POST)
	public String handleHotelUpdate(@ModelAttribute("upHotel") Hotel uhotel, ModelMap model) {
		try {
			hmapper.updateHotel(uhotel);
			model.addAttribute("outcome", SUCCESS);
		}
		catch(Exception ex) {
			ex.toString();
			model.addAttribute("outcome", FAILURE);
		}
		return "update_hotel_success";
	}
	
	@RequestMapping(value = "/removeHotel", method = RequestMethod.GET)
	public String removeHotelRecord(@RequestParam String hotelId, ModelMap model, HttpServletResponse response) {
		String searchId = hotelId;
		try {
			hmapper.deleteHotel(searchId);
			return "redirect:listOfHotel";
		}
		catch(Exception ex) {
			ex.toString();
			return "redirect:listOfHotel";
		}		
	}
	
	public int gen() {
	    Random r = new Random( System.currentTimeMillis() );
	    return 10000 + r.nextInt(20000);
	}

}