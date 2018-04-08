package com.test.app.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.app.dto.ViewAuthDto;
import com.test.app.entity.ActiveUser;
import com.test.app.entity.User;
import com.test.app.service.ActivityLogService;
import com.test.app.util.SessionUtil;
import com.test.app.util.StringUtil;
import com.test.app.util.ViewAuthUtil;

@Controller
public class CommonScreenController {
	
	@Autowired
	ActivityLogService activityLogService;
	@Autowired
	private Environment env;

	private static final Logger logger = Logger.getLogger(CommonScreenController.class);

	@RequestMapping(value = "/common", method = RequestMethod.GET)
	public String handleRedirect(@RequestParam String mid, @RequestParam String fid, @RequestParam String sid, @RequestParam String pid, ModelMap model,
			RedirectAttributes red, HttpServletRequest req) {
		String key, url = "";		
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(req, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(req.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {					
					key = fid + "." + sid;
					url = env.getProperty(key);
					if (url != null && !url.isEmpty()) {
						red.addAttribute("mid", mid);
						red.addAttribute("fid", fid);
						red.addAttribute("sid", sid);
						red.addAttribute("pid", pid);
						updateUserActivityLog(sid, url, req);
						return "redirect:"+url;
					} else {
						red.addFlashAttribute("msg", StringUtil.NO_MAPPING_FOUND);
						return "redirect:notAuthorized";
					}
				} else {
					return "redirect:login";
				}
			} else {
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:notAuthorized";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}			
	}

	@RequestMapping(value = "/notAuthorized", method = RequestMethod.GET)
	public String handleNotAuthorized(ModelMap model, @ModelAttribute("msg") String msgString) {
		model.addAttribute("error", msgString);
		return "no_permission";
	}
	
	private void updateUserActivityLog(String scrn, String scrnDesc, HttpServletRequest request) {
		// TODO Auto-generated method stub
		int retval = 0;
		String screenDescMod = scrnDesc;
		screenDescMod = screenDescMod.replace('-', ' ');
		screenDescMod = screenDescMod.toUpperCase();
		try {
			ActiveUser activeUsr = new ActiveUser();
			User currentUser = (User) request.getSession(false).getAttribute(StringUtil.USER_SESSION);
			activeUsr.setUserId(currentUser.getUserId());
			activeUsr.setContactNumber(currentUser.getContactNumber());
			activeUsr.setScreenId(scrn);
			activeUsr.setScreenDesc(screenDescMod);
			activeUsr.setLastActiveTime(new Timestamp(System.currentTimeMillis()));
			activeUsr.setBatchRunFlag("0");
			retval = activityLogService.updateUserActivityLog(activeUsr);
			if (retval > 0) 
				logger.debug("USER ACTIVITY LOG UPDATED SUCCESSFULLY");
			else
				logger.debug("USER ACTIVITY LOG UPDATE UNSUCCESSFUL");
		} 
		catch (Exception e) {
			// TODO: handle exception
			logger.debug("USER ACTIVITY LOG UPDATE UNSUCCESSFUL");
			logger.debug(e.toString());
		}
	}

}
