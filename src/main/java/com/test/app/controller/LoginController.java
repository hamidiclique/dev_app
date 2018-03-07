package com.test.app.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.app.dto.ChangePwdDto;
import com.test.app.dto.LoginDto;
import com.test.app.entity.Function;
import com.test.app.entity.Module;
import com.test.app.entity.ScreenFunMap;
import com.test.app.entity.User;
import com.test.app.service.FunctionService;
import com.test.app.service.ModuleService;
import com.test.app.service.ScreenFunMapService;
import com.test.app.service.UserService;
import com.test.app.util.DateUtil;
import com.test.app.util.SessionUtil;
import com.test.app.util.StringUtil;

@Controller
public class LoginController {

	private HttpSession session;
	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	UserService userService;
	@Autowired
	ModuleService moduleService;
	@Autowired
	FunctionService funcService;
	@Autowired
	ScreenFunMapService scrnFunMapService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginWindow(ModelMap model, @ModelAttribute("cause") String flashAttr) {
		try {
			LoginDto loginDto = new LoginDto();
			model.addAttribute("credentials", loginDto);
			model.addAttribute("cause", flashAttr);
		} catch (Exception ex) {
			ex.toString();
		}
		return "login";
	}

	@RequestMapping(value = "/authLogin", method = RequestMethod.POST)
	public String handleAddNewUser(@ModelAttribute("credentials") LoginDto loginDto, BindingResult result,
			ModelMap model, RedirectAttributes red, HttpServletRequest request) {
		String loginStatus = "";		
		try {
			User temp = userService.getUserById(loginDto.getUserId());
			if (temp != null) {
				loginStatus = this.doValidation(loginDto.getUserId(), loginDto.getPassword(), temp);
			} else {
				loginStatus = StringUtil.INVALID_USER;
				logger.debug(loginStatus + ":" + loginDto.getUserId() + "doesn't exist");
			}
			logger.debug(loginStatus);
			switch (loginStatus) {
			case StringUtil.LOCKED_USER:
				logger.debug(loginStatus + ":" + "user is locked");
				red.addFlashAttribute("cause", StringUtil.LOCKED_USER);
				return "redirect:login";

			case StringUtil.INACTIVE_USER:
				logger.debug(loginStatus + ":" + "user is inactive");
				red.addFlashAttribute("cause", StringUtil.INACTIVE_USER);
				return "redirect:login";

			case StringUtil.USER_PWD_EXPIRED:
				logger.debug(loginStatus + ":" + "user password has expired");
				red.addFlashAttribute("cause", StringUtil.USER_PWD_EXPIRED);
				return "redirect:login";

			case StringUtil.USER_ACC_EXPIRED:
				logger.debug(loginStatus + ":" + "user account has expired");
				red.addFlashAttribute("cause", StringUtil.USER_ACC_EXPIRED);
				return "redirect:login";

			case StringUtil.WRONG_PASSWORD:
				logger.debug(loginStatus + ":" + "user has entered wrong password");
				red.addFlashAttribute("cause", StringUtil.WRONG_PASSWORD);
				return "redirect:login";

			case StringUtil.INVALID_USER:
				logger.debug(loginStatus + ":" + "user does not exist");
				red.addFlashAttribute("cause", StringUtil.INVALID_USER);
				return "redirect:login";

			case StringUtil.USER_PWD_VALID:
				logger.debug(loginStatus + ":" + "user ready to log in");
				updateSession(temp, request);
				return "redirect:loginInfo";

			default:
				logger.debug(loginStatus + ":" + "default case hit");
				red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
				return "redirect:login";
			}
		} catch (Exception ex) {
			logger.debug(ex.toString());
		}
		return "redirect:login";
	}

	private String doValidation(String userId, String password, User utemp) {
		String rootcause = StringUtil.UNKNOW_REASON;
		boolean validPwd = false;
		validPwd = BCrypt.checkpw(password, utemp.getPassword());
		if (validPwd) {
			rootcause = StringUtil.PASSWORD_VERIFIED;
			if (utemp.getStatus().equalsIgnoreCase(StringUtil.USER_STATUS_LOCKED)) {
				rootcause = StringUtil.LOCKED_USER;
				logger.debug(rootcause + ":" + userId + "user is locked");
				return rootcause;
			} else if (utemp.getStatus().equalsIgnoreCase(StringUtil.USER_STATUS_INACTIVE)) {
				rootcause = StringUtil.INACTIVE_USER;
				logger.debug(rootcause + ":" + userId + "user is not active");
				return rootcause;
			} else {
				rootcause = StringUtil.ACTIVE_USER;
				logger.debug(rootcause + ":" + userId + "user is active");
				Date today = new Date();
				Date accValidUpto = DateUtil.convertStringToDate(utemp.getAccountExpDate());
				int checkAccValid = today.compareTo(accValidUpto);
				if (checkAccValid < 0) {
					rootcause = StringUtil.USER_ACC_VALID;
					logger.debug(rootcause + ":" + userId + "user account not expired");
					if (utemp.getPasswordExpDate() != null) {
						Date pwdvalidupto = utemp.getPasswordExpDate();
						int checkPwdValid = today.compareTo(pwdvalidupto);
						if (checkPwdValid < 0) {
							rootcause = StringUtil.USER_PWD_VALID;
							logger.debug(rootcause + ":" + userId + "user password not expired");
							return rootcause;
						} else {
							rootcause = StringUtil.USER_PWD_EXPIRED;
							logger.debug(rootcause + ":" + userId + "user password expired");
							return rootcause;
						}
					}
				} else {
					rootcause = StringUtil.USER_ACC_EXPIRED;
					logger.debug(rootcause + ":" + userId + "user account expired");
					return rootcause;
				}
			}
		} else {
			rootcause = StringUtil.WRONG_PASSWORD;
			logger.debug(rootcause + ":" + "username/password do not match");
			return rootcause;
		}

		return rootcause;
	}

	private void updateSession(User temp, HttpServletRequest request) {
		// TODO Auto-generated method stub
		session = request.getSession();
		session.setAttribute("USER_LOGGED_IN", temp);
	}

	@RequestMapping(value = "/loginInfo", method = RequestMethod.GET)
	public String handleValidLogin(ModelMap model, HttpServletRequest request, RedirectAttributes red) {
		try {
			if (request.getSession() != null) {
				logger.debug("session exists");
				if (SessionUtil.sessionValid(session, red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					User currentUser = (User) session.getAttribute(StringUtil.USER_SESSION);
					model.addAttribute("user", currentUser);
				} else {
					logger.debug("no active user found");
					red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
					return "redirect:/login";
				}
			} else {
				logger.debug("no session found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.toString());
			logger.debug("session already invalid");
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
		return "login_info";
	}

	@RequestMapping(value = "/userLogout", method = RequestMethod.GET)
	public String handleLogout(ModelMap model, HttpServletRequest request) {
		try {
			if (request.getSession() != null) {
				logger.debug("session exists");
				// session.removeAttribute(USER_SESSION);
				session.invalidate();
				return "redirect:/login";
			} else {
				logger.debug("no session found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.toString());
		}
		return "login_info";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String passwordChangeOption(ModelMap model, HttpServletRequest request, RedirectAttributes red, @ModelAttribute("error") String flashAttr) {
		try {
			if (SessionUtil.sessionValid(session, red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				User currentUser = (User) session.getAttribute(StringUtil.USER_SESSION);
				ChangePwdDto cpwddto = new ChangePwdDto();
				cpwddto.setUserId(currentUser.getUserId());
				cpwddto.setUserName(currentUser.getUserName());
				model.addAttribute("cpdto", cpwddto);
				model.addAttribute("error", flashAttr);
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
		return "show_change_password";
	}
	
	@RequestMapping(value = "/handlePasswordChange", method = RequestMethod.POST)
	public String handleAddNewUser(@ModelAttribute("cpdto") ChangePwdDto cpDto, BindingResult result, ModelMap model,
			RedirectAttributes red, HttpServletRequest request) {
		boolean validPwd;
		User temp = (User) request.getSession().getAttribute(StringUtil.USER_SESSION);
		validPwd = BCrypt.checkpw(cpDto.getPassword(), temp.getPassword());
		if (validPwd) {
			logger.debug("old password verified");
			// if matches then update user data and session
			String generatedSecuredPasswordHash = BCrypt.hashpw(cpDto.getNewPassword(), BCrypt.gensalt(12));
			temp.setPassword(generatedSecuredPasswordHash);
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			temp.setPasswordCreDate(today);
			temp.setModifyBy(cpDto.getUserId());
			temp.setModifyTime(new Timestamp(System.currentTimeMillis()));
			userService.processPasswordChange(temp);
			User updatedUser = userService.getUserById(cpDto.getUserId());
			updateSession(updatedUser, request);
			return "change_password_success";
		} else {
			logger.debug("old password verification failed");
			red.addFlashAttribute("error", StringUtil.OLD_NEW_PASSWORD_MISMATCH);
			return "redirect:/changePassword";
		}
	}

	@RequestMapping(value = "/showModules", method = RequestMethod.GET)
	public String handleLoadingModules(ModelMap model, HttpServletRequest request, RedirectAttributes red) {
		List<Module> moduleList = new ArrayList<Module>();
		List<Function> functionListByUser = new ArrayList<Function>();
		List<Function> functionList = new ArrayList<Function>();
		List<ScreenFunMap> scrnFunMapListByUser = new ArrayList<ScreenFunMap>();
		Map<String, String> moduleMap = new HashMap<String, String>();
		Map<String, String> functionMap = new HashMap<String, String>();
		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		Map<String, List<ScreenFunMap>> mapScreenFunMap = new HashMap<String, List<ScreenFunMap>>(); 
		List<String> functionSorter;
		List<ScreenFunMap> screenSorter;

		try {
			if (SessionUtil.sessionValid(session, red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				User currentUser = (User) session.getAttribute(StringUtil.USER_SESSION);
				// moduleList = moduleService.getModulesByUser(currentUser.getUserId());
				moduleList = moduleService.getAllModules();
				for (Module m : moduleList) {
					moduleMap.put(m.getModuleId(), m.getModuleDesc());
				}
				functionList = funcService.getAllFunctions();
				for (Function fun : functionList) {
					functionMap.put(fun.getFunctionId(), fun.getFunctionDesc());
				}
				functionListByUser = funcService.getFunctionsByUser(currentUser.getUserId());
				for (Function f : functionListByUser) {
					// check if moduleId already exists or not
					if (funModMap.containsKey(f.getModuleId())) {
						// if moduleId already there as a key
						// retrieve the value of that key
						// append the new String to list
						// replace new value for that key
						functionSorter = new ArrayList<String>();
						functionSorter = funModMap.get(f.getModuleId());
						functionSorter.add(f.getFunctionId());
						funModMap.replace(f.getModuleId(), functionSorter);
						logger.debug(functionSorter);
					} else {
						// if moduleId is a new one
						// create a new list of String
						// append value to list
						// put list as value for key
						functionSorter = new ArrayList<String>();
						functionSorter.add(f.getFunctionId());
						funModMap.put(f.getModuleId(), functionSorter);
						logger.debug(functionSorter);
					}
				}
				//get list of scrnFunMap by user
				scrnFunMapListByUser = scrnFunMapService.getAllScrnFunMapByUser(currentUser.getUserId());
				logger.debug(scrnFunMapListByUser);
				for (ScreenFunMap sfm : scrnFunMapListByUser) {
					if (mapScreenFunMap.containsKey(sfm.getId().getFunctionId())) {
						screenSorter = new ArrayList<ScreenFunMap>();
						screenSorter = mapScreenFunMap.get(sfm.getId().getFunctionId());
						screenSorter.add(sfm);
						mapScreenFunMap.replace(sfm.getId().getFunctionId(), screenSorter);
						logger.debug(screenSorter);
					} else {
						screenSorter = new ArrayList<ScreenFunMap>();
						screenSorter.add(sfm);
						mapScreenFunMap.put(sfm.getId().getFunctionId(), screenSorter);
						logger.debug(screenSorter);
					}
				}
				
				session = request.getSession();
				session.setAttribute(StringUtil.SESSION_MODULES, moduleMap);
				session.setAttribute(StringUtil.SESSION_FUNCTIONS, functionMap);
				session.setAttribute(StringUtil.SESSION_FUN_MOD_MAP, funModMap);
				session.setAttribute(StringUtil.SESSION_SCR_FUN_MAP, mapScreenFunMap);
				model.addAttribute("modmap", moduleMap);
				model.addAttribute("funmodmap", funModMap);
				model.addAttribute("scrfunmap", mapScreenFunMap);
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
		return "load_modules";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/viewFunctions", method = RequestMethod.GET)
	public String displayFunctionsByModule(@RequestParam String moduleId, ModelMap model, RedirectAttributes red) {
		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		Map<String, String> functionMap = new HashMap<String, String>();
		Map<String, String> moduleMap = new HashMap<String, String>();
		List<String> funList = new ArrayList<String>();
		try {
			if (SessionUtil.sessionValid(session, red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {				
				if (session.getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
					funModMap = (Map<String, List<String>>) session.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
					funList = funModMap.get(moduleId);
					Iterator<String> funIterator = funList.iterator();
					while (funIterator.hasNext()) {
					    String element = funIterator.next();					   
					    if(element.length() > 8) {
					        funIterator.remove();
					    }
					}
				}
				if (session.getAttribute(StringUtil.SESSION_FUNCTIONS) != null) {
					functionMap = (Map<String, String>) session.getAttribute(StringUtil.SESSION_FUNCTIONS);
				}
				if (session.getAttribute(StringUtil.SESSION_MODULES) != null) {
					moduleMap = (Map<String, String>) session.getAttribute(StringUtil.SESSION_MODULES);
				}
				model.addAttribute("modmap", moduleMap);
				model.addAttribute("funlist", funList);
				model.addAttribute("funmap", functionMap);
				model.addAttribute("module", moduleId);
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
		return "load_functions";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/viewScreen", method = RequestMethod.GET)
	public String displayScreenByFunction(@RequestParam String moduleId, @RequestParam String functionId, ModelMap model, RedirectAttributes red) {		
		/*Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		Map<String, String> functionMap = new HashMap<String, String>();
		Map<String, String> moduleMap = new HashMap<String, String>();
		List<String> funList = new ArrayList<String>();*/
		Map<String, List<ScreenFunMap>> mapScrnFunMap = new HashMap<String, List<ScreenFunMap>>();
		List<ScreenFunMap> scrnFunList = new ArrayList<ScreenFunMap>();
		String screenId = "";
		try {
			if (SessionUtil.sessionValid(session, red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {			
				if (session.getAttribute(StringUtil.SESSION_SCR_FUN_MAP) != null) {
					mapScrnFunMap = (Map<String, List<ScreenFunMap>>) session.getAttribute(StringUtil.SESSION_SCR_FUN_MAP);
					scrnFunList = mapScrnFunMap.get(functionId);
					for (ScreenFunMap sfm : scrnFunList) {
						if(sfm.getId().getButtonDef().equalsIgnoreCase("0")) {
							screenId = sfm.getId().getScreenId(); 
						}
					}
					red.addAttribute("mid", moduleId);
					red.addAttribute("fid", functionId);
					red.addAttribute("sid", screenId);					
					red.addAttribute("pid", "");
					logger.debug(scrnFunList);
				}
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
		return "redirect:/common";
	}

}