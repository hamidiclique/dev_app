package com.test.app.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.test.app.entity.Function;
import com.test.app.entity.Module;
import com.test.app.interceptor.SessionTimerInterceptor;
import com.test.app.service.FunctionService;
import com.test.app.service.ModuleService;

@EnableWebMvc
@ComponentScan(basePackages = "com.test.app")
@Configuration
@EnableScheduling 
public class AppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	ModuleService moduleService;
	@Autowired
	FunctionService funcService;
	@Autowired
	private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

	@PostConstruct
	public void init() {
		requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
	}

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(new SessionTimerInterceptor());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public Map<String, String> modHashmap() {
		List<Module> moduleList = new ArrayList<Module>();
		final Map<String, String> modHashmap = new LinkedHashMap<String, String>();
		moduleList = moduleService.getAllModules();
		for (Module m : moduleList) {
			modHashmap.put(m.getModuleId(), m.getModuleDesc());
		}
		return modHashmap;
	}

	@Bean
	public Map<String, String> funHashmap() {
		List<Function> functionList = new ArrayList<Function>();
		final Map<String, String> funHashmap = new LinkedHashMap<String, String>();
		functionList = funcService.getAllFunctions();
		for (Function fun : functionList) {
			funHashmap.put(fun.getFunctionId(), fun.getFunctionDesc());
		}
		return funHashmap;
	}

	/*@Bean
	public LoggingAspect logAspect() {
		return new LoggingAspect();
	}*/

}