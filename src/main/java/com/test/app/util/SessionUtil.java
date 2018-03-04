package com.test.app.util;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class SessionUtil {

	private static final Logger logger = Logger.getLogger(SessionUtil.class);

	public static String sessionValid(HttpSession session, RedirectAttributes red) {
		// TODO Auto-generated method stub
		try {
			if (session.getAttribute(StringUtil.USER_SESSION) == null
					|| session.getAttribute(StringUtil.USER_SESSION).equals("")) {
				logger.debug("no active user found");
				return StringUtil.SESSION_NOT_VALID;
			} else {
				return StringUtil.SESSION_VALID;
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			logger.debug("session already invalid");
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
	}
}
