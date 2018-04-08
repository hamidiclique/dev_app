package com.test.app.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

import com.test.app.dto.DecParamForm;
import com.test.app.dto.ViewAuthDto;
import com.test.app.entity.DecParam;
import com.test.app.entity.Function;
import com.test.app.entity.User;
import com.test.app.service.FunctionService;
import com.test.app.service.LoginParamService;
import com.test.app.util.SessionUtil;
import com.test.app.util.StringUtil;
import com.test.app.util.ViewAuthUtil;

@Controller
public class SystemLoginParamController {
	
	private static final Logger logger = Logger.getLogger(SystemLoginParamController.class);
	
	@Autowired
	LoginParamService loginParamService;
	@Autowired
	FunctionService funService;
	@Resource(name = "funHashmap")
	private Map<String, String> funHashmap;
	
	@RequestMapping(value = "/addDecParam", method = RequestMethod.GET)
	public String showAddSlpScreen(ModelMap model, HttpSession session, RedirectAttributes red) {
		try {
			if(SessionUtil.sessionValid(session, red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				DecParam slp = new DecParam();
				model.addAttribute("slp", slp);
			}
			else {
				return "redirect:login";
			}			
		} catch (Exception e) {
			logger.debug(e.toString());			
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";			
		}
		return "add_new_slp";
	}
	
	@RequestMapping(value = "/saveDecParam", method = RequestMethod.POST)
	public String handleLoginParamAdd(@ModelAttribute("slp") DecParam slp, BindingResult result, ModelMap model, HttpSession session) {
		try {	
			User currentUser = (User) session.getAttribute(StringUtil.USER_SESSION);
			slp.setModifyBy(currentUser.getUserId());
			slp.setModifyTime(new Timestamp(System.currentTimeMillis()));
			//loginParamService.addNewLoginParam(slp);
		}
		catch(Exception ex) {			
			logger.debug(ex.toString());
		}
		return "redirect:showAllLoginParams";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/system-login-parameters", method = RequestMethod.GET)
	public String view_FACL4000_SACL4000(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		List<DecParam> slpList = new ArrayList<DecParam>();
		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {					
					Function currentfunction = funService.getFunctionById(fid);
					slpList = loginParamService.fetchAllLoginParams();
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						funList = funModMap.get(mid);
						Iterator<String> funIterator = funList.iterator();
						while (funIterator.hasNext()) {
						    String element = funIterator.next();					   
						    if(element.length() > 8) {
						        funIterator.remove();
						    }
						}
					}
					model.addAttribute("funlist", funList);
					model.addAttribute("slpList", slpList);					
					model.addAttribute("functionDesc", currentfunction.getFunctionDesc());
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
					model.addAttribute("funmap", funHashmap);
				} 
				else {
					return "redirect:login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:index/notAuthorized";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}
	
	@RequestMapping(value = "/system-login-parameters-update", method = RequestMethod.GET)
	public String view_FACL4000_SACL4000U(@RequestParam String mid, @RequestParam String fid, @RequestParam String sid, @RequestParam String pid,
			ModelMap model, HttpServletRequest request, RedirectAttributes red) {	
		List<DecParam> slpList = new ArrayList<DecParam>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					slpList = loginParamService.fetchAllLoginParams();
					DecParamForm decParamForm = new DecParamForm();
					decParamForm.setDesisionParams(slpList);
					model.addAttribute("dpf", decParamForm);
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
					model.addAttribute("screen", sid);
					model.addAttribute("functionDesc", "System Login Parameters - Update");
				} else {
					return "redirect:login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:index/notAuthorized";
			}
		} catch (Exception ex) {
			logger.debug(ex.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}		
		return sid;
	}
	
	@RequestMapping(value = "/submit-system-login-parameters-update", method = RequestMethod.POST)
	public String handle_FACL4000_SACL4000U(@ModelAttribute("dpf") DecParamForm decParamForm, BindingResult result,
			ModelMap model, HttpServletRequest request, RedirectAttributes red) {
		List<DecParam> decisionParamList = new ArrayList<DecParam>();
		int retval = 0;
		try {
			if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				User currentUser = (User) request.getSession(false).getAttribute(StringUtil.USER_SESSION);
				decisionParamList = decParamForm.getDesisionParams();
				for (DecParam dp : decisionParamList) {
					dp.setModifyBy(currentUser.getUserId());
					dp.setModifyTime(new Timestamp(System.currentTimeMillis()));
					retval = loginParamService.updateLoginParamValue(dp);
					logger.debug("No. of rows affected: " + retval);
				}
				model.addAttribute("message", StringUtil.SACL4000U_SUCCESS);
			} 
			else {
				return "redirect:login";
			}

		} catch (Exception ex) {
			logger.debug(ex.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return "SACL4000U";

	}
	
}
