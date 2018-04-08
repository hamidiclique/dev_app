/*package com.test.app.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext container) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.test.app.config.AppConfig");

		container.addListener(new ContextLoaderListener(context));
		container.addListener(new SessionListener());

		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(context));

		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
}*/