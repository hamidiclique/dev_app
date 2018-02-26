package com.test.app.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.app.entity.SysLoginParam;
import com.test.app.entity.User;
import com.test.app.service.LoginParamService;
import com.test.app.util.SessionUtil;
import com.test.app.util.StringUtil;

@Controller
public class LoginParamController {
	
	private static final Logger logger = Logger.getLogger(LoginParamController.class);
	
	@Autowired
	LoginParamService loginParamService;
	
	@RequestMapping(value = "/addSysLoginParam", method = RequestMethod.GET)
	public String showAddSlpScreen(ModelMap model, HttpSession session, RedirectAttributes red) {
		try {
			if(SessionUtil.sessionValid(session, red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				SysLoginParam slp = new SysLoginParam();
				model.addAttribute("slp", slp);
			}
			else {
				return "redirect:/login";
			}			
		} catch (Exception e) {
			logger.debug(e.toString());			
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";			
		}
		return "add_new_slp";
	}
	
	@RequestMapping(value = "/saveSysLoginParam", method = RequestMethod.POST)
	public String handleLoginParamAdd(@ModelAttribute("slp") SysLoginParam slp, BindingResult result, ModelMap model, HttpSession session) {
		try {	
			User currentUser = (User) session.getAttribute(StringUtil.USER_SESSION);
			slp.setModifyBy(currentUser.getUserId());
			slp.setModifyTime(new Timestamp(System.currentTimeMillis()));
			loginParamService.addNewLoginParam(slp);
		}
		catch(Exception ex) {			
			logger.debug(ex.toString());
		}
		return "redirect:showAllLoginParams";
	}
	
	@RequestMapping(value = "/showAllLoginParams", method = RequestMethod.GET)
	public String showListOfLoginParams(ModelMap model) {
		List<SysLoginParam> slpList = new ArrayList<SysLoginParam>();
		try {
			slpList = loginParamService.fetchAllLoginParams();
			model.addAttribute("slpList", slpList);
		} catch (Exception ex) {
			logger.debug(ex.toString());
		}
		return "show_all_login_params";
	}
	
	@RequestMapping(value = "/editLoginParamValues", method = RequestMethod.GET)
	public String displayLoginParamsForUpdate(@RequestParam String paramId, ModelMap model) {
		String searchId = paramId;
		SysLoginParam slpToUpdate = loginParamService.getLoginParamById(searchId);
		model.addAttribute("updateSlp", slpToUpdate);		
		return "update_slp";
	}
	
	@RequestMapping(value = "/submitUpdatedLoginParams", method = RequestMethod.POST)
	public String handleLoginParamUpdate(@ModelAttribute("upSlp") SysLoginParam slp, BindingResult result, ModelMap model, HttpSession session) {
		try {
			User currentUser = (User) session.getAttribute(StringUtil.USER_SESSION);
			slp.setModifyBy(currentUser.getUserId());
			slp.setModifyTime(new Timestamp(System.currentTimeMillis()));
			loginParamService.updateLoginParamValue(slp);			
		}
		catch(Exception ex) {
			ex.toString();
			return "redirect:showAllLoginParams";
		}
		return "redirect:showAllLoginParams";
	}
	
}
