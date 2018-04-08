package com.test.app.util;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.app.entity.User;

public class SessionUtil {

	private static final Logger logger = Logger.getLogger(SessionUtil.class);

	@SuppressWarnings("unchecked")
	public static String sessionValid(HttpSession session, RedirectAttributes red) {
		// TODO Auto-generated method stub
		try {
			if (session.getAttribute(StringUtil.USER_SESSION) == null
					|| session.getAttribute(StringUtil.USER_SESSION).equals("")) {
				logger.debug("no active user found");
				return StringUtil.SESSION_NOT_VALID;
			} else {
				//map entry matches with parameter session id then session valid otherwise not
				Map<String, String> sessionmap = (Map<String, String>) session.getServletContext().getAttribute("sessionUtilMap");				
				User temp = (User) session.getAttribute(StringUtil.USER_SESSION);
				String exSessionId = sessionmap.get(temp.getUserId());
				if (session.getId().equalsIgnoreCase(exSessionId)) {
					return StringUtil.SESSION_VALID;
				}
				else {
					logger.debug("no active user found");
					return StringUtil.SESSION_NOT_VALID;
				}				
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			logger.debug("session already invalid");
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
	}
}
