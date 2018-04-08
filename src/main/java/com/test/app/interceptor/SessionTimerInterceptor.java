package com.test.app.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.test.app.entity.User;
import com.test.app.util.StringUtil;

public class SessionTimerInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(SessionTimerInterceptor.class);
	private static final long MAX_INACTIVE_SESSION_TIME = 10 * 6 * 10000;
	Map<String, String> sessionmap;

	@Autowired
	private HttpSession session;

	/**
	 * Executed before actual handler is executed
	 **/
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		logger.debug("Pre handle method - check handling start time");
		long startTime = System.currentTimeMillis();
		request.setAttribute("executionTime", startTime);
		session = request.getSession();
		if (session.getAttribute(StringUtil.USER_SESSION) != null) {
			logger.info("Time since last request in this session: "
					+ (System.currentTimeMillis() - request.getSession(false).getLastAccessedTime()) + "ms");
			if (System.currentTimeMillis() - session.getLastAccessedTime() > MAX_INACTIVE_SESSION_TIME) {
				logger.warn("Logging out, due to inactive session");
				SecurityContextHolder.clearContext();				
				//request.getSession(false).invalidate();
				try {					
					if (session != null) {
						sessionmap = (Map<String, String>) session.getServletContext().getAttribute("sessionUtilMap");
						if (!sessionmap.isEmpty()) {
							User temp = (User) session.getAttribute(StringUtil.USER_SESSION);
							if (temp != null) {
								String sessionIdValueForThis = sessionmap.get(temp.getUserId());
								if (sessionIdValueForThis.equalsIgnoreCase(session.getId())) {
									// no force logout
									sessionmap.remove(temp.getUserId(), sessionIdValueForThis);
								}
							}							
						}
						request.getSession(false).invalidate();
					} else {
						logger.debug("no session found");
					}
				} catch (Exception e) {
					// TODO: handle exception
					logger.debug(e.toString());
				}
			}
		}
		return true;
	}

	/**
	 * Executed after handler is executed
	 **/
	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView model) throws Exception {
		logger.debug("Post handle method - check execution time of handling");
		long startTime = (Long) request.getAttribute("executionTime");
		logger.debug("Execution time for handling the request was: " + (System.currentTimeMillis() - startTime) + "ms");
	}
}
