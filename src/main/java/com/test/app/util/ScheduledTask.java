package com.test.app.util;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.test.app.entity.ActiveUser;
import com.test.app.service.ActivityLogService;

@Component
public class ScheduledTask {

	@Autowired
	ActivityLogService activityLogService;
	@Autowired
	ServletContext servletContext;

	/*
	 * @Value("${fixedDelay}") private String fixeddelay;
	 * 
	 * @Value("${initialDelay}") private String initialdelay;
	 */

	private static final Logger logger = Logger.getLogger(ScheduledTask.class);
	private static final long MAX_INACTIVE_SESSION_TIME = 10 * 6 * 10000;
	Map<String, String> globalSessionMap;
	List<ActiveUser> listactiveuser;

	// long fixed = Long.parseLong(fixeddelay);
	// long initial = Long.parseLong(initialdelay);

	// @Scheduled(fixedDelay = (60 * 1000))
	// @Scheduled(fixedRate = (60 * 1000))
	// @Scheduled(fixedDelay = (60 * 1000), initialDelay = (30 * 1000))

	@SuppressWarnings("unchecked")
	@Scheduled(fixedDelay = (60 * 1000), initialDelay = (30 * 1000))
	public void scheduleRemoveIdleUserTask() {
		logger.debug("inside scheduled method");
		//logger.debug(MAX_INACTIVE_SESSION_TIME);
		int retval = 0;
		try {
			globalSessionMap = (Map<String, String>) servletContext.getAttribute("sessionUtilMap");
			listactiveuser = activityLogService.getAllActiveUsers();
			for (ActiveUser activeUser : listactiveuser) {
				if (System.currentTimeMillis() - activeUser.getLastActiveTime().getTime() > MAX_INACTIVE_SESSION_TIME) {
					globalSessionMap.remove(activeUser.getUserId());
					retval = activityLogService.removeUserFromActivelist(activeUser.getUserId());
					if (retval > 0) {
						logger.debug(StringUtil.SACL6000_SUCCESS);
					}
					else {
						logger.debug(StringUtil.FAILURE);
					}
				}
			}
			Timestamp now = new Timestamp(System.currentTimeMillis());
			System.out.println("Schedulded task to remove idle users completed @ " + now);
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.toString());
		}

	}

}
