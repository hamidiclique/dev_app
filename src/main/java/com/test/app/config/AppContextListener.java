package com.test.app.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext sc= sce.getServletContext();
		Map<String, String> sessUtilMap = new ConcurrentHashMap<String, String>();
		//for test
		//sessUtilMap.put("abc", "3A6ADFE0EFEDEFE017322A4EEC66E512");
		sc.setAttribute("sessionUtilMap", sessUtilMap);
	}

}
