package com.test.app.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.app.entity.User;
import com.test.app.service.UserService;
import com.test.app.util.SessionUtil;
import com.test.app.util.StringUtil;

@Controller
public class AccessControlListController {
	
	private static final Logger logger = Logger.getLogger(AccessControlListController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/addNewUser", method = RequestMethod.GET)
	public String showAddUserScreen(ModelMap model, HttpSession session, RedirectAttributes red) {
		try {
			if(SessionUtil.sessionValid(session, red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				User user = new User();
				model.addAttribute("user", user);
			}
			else {
				return "redirect:/login";
			}			
		} catch (Exception e) {
			logger.debug(e.toString());			
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";			
		}
		return "add_new_user";
	}

	@RequestMapping(value = "/saveNewUser", method = RequestMethod.POST)
	public String handleAddNewUser(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {
		User newUser = user;
		newUser.getUserId();
		try{			
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			cal.add(Calendar.YEAR, 1);
			Date nextYear = cal.getTime();
			user.setPasswordCreDate(today);
			user.setPasswordExpDate(nextYear);
			user.setModifyBy(user.getUserId());
			user.setModifyTime(new Timestamp(System.currentTimeMillis()));
			user.setLastLogin(new Timestamp(System.currentTimeMillis()));
			user.setLoginAttempt(new BigDecimal(1));
			user.setFailCount(new BigDecimal(0));
			String generatedSecuredPasswordHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
			user.setPassword(generatedSecuredPasswordHash);
			userService.storeNewUserData(user);
			
	        /*String  originalPassword = user.getPassword();
	        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
	        System.out.println(generatedSecuredPasswordHash);	         
	        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
	        System.out.println(matched);*/
		}
		catch(Exception ex) {
			ex.toString();
			//model.addAttribute("", );
		}
		return "add_hotel_success";
	}
	
	@RequestMapping(value = "/showAllUsers", method = RequestMethod.GET)
	public String showListOfUsers(ModelMap model) {
		List<User> userList = new ArrayList<User>();
		try {
			userList = userService.fetchAllUsers();
			model.addAttribute("userList", userList);
		} catch (Exception ex) {
			ex.toString();
		}
		return "show_all_users";
	}
	
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.GET)
	public String displayUserInfoForUpdate(@RequestParam String userId, ModelMap model) {
		String searchId = userId;
		User userToUpdate = userService.getUserById(searchId);
		model.addAttribute("upUser", userToUpdate);		
		return "update_user";
	}
	
	@RequestMapping(value = "/submitUpdatedUserInfo", method = RequestMethod.POST)
	public String handleUserUpdate(@ModelAttribute("upUser") User user, BindingResult result, ModelMap model) {
		try {
			user.setModifyBy(user.getUserId());
			user.setModifyTime(new Timestamp(System.currentTimeMillis()));
			userService.updateUserInfoProcess(user);			
		}
		catch(Exception ex) {
			ex.toString();
			return "redirect:showAllUsers";
		}
		return "redirect:showAllUsers";
	}
	
	@ModelAttribute("commonStatus")
	protected Map<String, String> getCommonStatusVariables() {
		Map<String, String> commonVarList = new HashMap<String, String>();
		commonVarList.put("Y", "Yes");
		commonVarList.put("N", "No");		
        return commonVarList;
    }
	
	@ModelAttribute("panMaskOptions")
	protected Map<String, String> getPanMaskOptions() {
		Map<String, String> panMaskOptions = new HashMap<String, String>();
		panMaskOptions.put("0", "0 - Mask Always");
		panMaskOptions.put("1", "1 - Conditional Masking");
		panMaskOptions.put("2", "2 - Do Not Mask");
        return panMaskOptions;
    }
	
	@ModelAttribute("userRoleOptions")
	protected Map<String, String> getUserRoles() {
		Map<String, String> userRoleOptions = new HashMap<String, String>();
		userRoleOptions.put("SUPER", "Super");
		userRoleOptions.put("TEST", "Test");
		userRoleOptions.put("DEBUG", "Debug");
        return userRoleOptions;
    }
	
	@ModelAttribute("statusOptions")
    protected Map<String, String> getStatusOptions() {
		Map<String, String> statusOptions = new HashMap<String, String>();
		statusOptions.put("ACTIVE", "ACTIVE");
		statusOptions.put("INACTIVE", "INACTIVE");
		statusOptions.put("LOCKED", "LOCKED");
        return statusOptions;
    }
	
	
	
}
