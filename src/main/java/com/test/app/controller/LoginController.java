package com.test.app.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import com.test.app.dto.LoginParam;
import com.test.app.dto.UserSessionDto;
import com.test.app.entity.ActiveUser;
import com.test.app.entity.DecParam;
import com.test.app.entity.Function;
import com.test.app.entity.ScreenFunMap;
import com.test.app.entity.User;
import com.test.app.service.ActivityLogService;
import com.test.app.service.FunctionService;
import com.test.app.service.LoginParamService;
import com.test.app.service.ModuleService;
import com.test.app.service.ScreenFunMapService;
import com.test.app.service.UserService;
import com.test.app.util.DateUtil;
import com.test.app.util.SessionUtil;
import com.test.app.util.StringUtil;

@Controller
@RequestMapping("/")
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);
	Map<String, String> sessionmap;

	@Autowired
	UserService userService;
	@Autowired
	ModuleService moduleService;
	@Autowired
	FunctionService funcService;
	@Autowired
	ScreenFunMapService scrnFunMapService;
	@Autowired
	LoginParamService loginParamService;
	@Autowired
	ActivityLogService activityLogService;
	@Resource(name = "modHashmap")
	private Map<String, String> modHashmap;
	@Resource(name = "funHashmap")
	private Map<String, String> funHashmap;

	// @SuppressWarnings("unchecked")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginWindow(ModelMap model, @ModelAttribute("cause") String flashAttr, HttpServletRequest request,
			RedirectAttributes red) {
		try {
			/*
			 * request.getSession(false).getServletContext().getAttribute("sessionUtilMap");
			 * logger.debug(sessionmap);
			 */
			if (request.getSession(false) != null) {
				logger.debug("session exists");
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_NOT_VALID)) {
					LoginParam loginDto = new LoginParam();
					model.addAttribute("credentials", loginDto);
					model.addAttribute("cause", flashAttr);
				} else {
					logger.debug("active user found");
					return "redirect:loginInfo";
				}
			} else {
				logger.debug("no session found");
				return "redirect:loginInfo";
			}
		} catch (Exception ex) {
			logger.debug(ex.toString());
			ex.printStackTrace();
		}
		return "login";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/authLogin", method = RequestMethod.POST)
	public String authenticateLogin(@ModelAttribute("credentials") LoginParam loginParam, BindingResult result,
			ModelMap model, RedirectAttributes red, HttpServletRequest request) {
		DecParam dp = new DecParam();
		int failCount = 0, remAttempt = 0;
		String loginStatus = "";
		try {
			User temp = userService.getUserById(loginParam.getUserId());
			if (temp != null) {
				if (temp.getStatus().equalsIgnoreCase(StringUtil.USER_STATUS_LOCKED))
					loginStatus = StringUtil.LOCKED_USER;
				else if (temp.getStatus().equalsIgnoreCase(StringUtil.USER_STATUS_INACTIVE))
					loginStatus = StringUtil.INACTIVE_USER;
				else
					loginStatus = this.doValidation(loginParam.getUserId(), loginParam.getPassword(), temp);
			} else {
				loginStatus = StringUtil.INVALID_USER;
				logger.debug(loginStatus + ":" + loginParam.getUserId() + "doesn't exist");
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
				try {
					dp = loginParamService.getLoginParamById(StringUtil.NUMBER_OF_FAILED_ATTEMPTS_ALLOWED);
					failCount = temp.getFailCount().intValue();
					temp.setFailCount(new BigDecimal(++failCount));
					temp.setModifyTime(new Timestamp(System.currentTimeMillis()));
					int nora = userService.updateUserLoginFailCount(temp);
					if (nora > 0) {
						logger.debug(loginStatus + ":" + "user info updated");
						if (failCount < dp.getParamValue().intValue()) {
							// do nothing
							remAttempt = dp.getParamValue().intValue() - failCount;
							red.addFlashAttribute("cause",
									StringUtil.WRONG_PASSWORD + ", REMAINING ATTEMPTS: " + remAttempt);
						} else {
							temp = userService.getUserById(loginParam.getUserId());
							temp.setStatus(StringUtil.USER_STATUS_LOCKED);
							temp.setModifyTime(new Timestamp(System.currentTimeMillis()));
							nora = userService.updateUserStatus(temp);
							if (nora > 0) {
								logger.debug(loginStatus + ":" + "user status locked");
								red.addFlashAttribute("cause", StringUtil.TOO_MANY_TRY);
							} else {
								logger.debug(loginStatus + ":" + "user info update failed");
							}
						}
					} else {
						logger.debug(loginStatus + ":" + "user info update failed");
					}

					return "redirect:login";
				} catch (Exception e) {
					// TODO: handle exception
					return "redirect:login";
				}

			case StringUtil.INVALID_USER:
				logger.debug(loginStatus + ":" + "user does not exist");
				red.addFlashAttribute("cause", StringUtil.INVALID_USER);
				return "redirect:login";

			case StringUtil.USER_PWD_VALID:
				logger.debug(loginStatus + ":" + "user ready to log in");
				temp.setFailCount(new BigDecimal(0));
				int nora = userService.updateUserLoginFailCount(temp);
				logger.debug(nora);
				temp = userService.getUserById(loginParam.getUserId());
				// prompt user, abort session or continue
				request.getSession(false).setAttribute(StringUtil.USER_SESSION, temp);
				sessionmap = (Map<String, String>) request.getSession(false).getServletContext()
						.getAttribute("sessionUtilMap");
				if (sessionmap.containsKey(temp.getUserId())) {
					String userLoggedIn = temp.getUserId();
					String existingSessionId = sessionmap.get(temp.getUserId());
					String sessionId = request.getSession(false).getId();
					if (existingSessionId.equalsIgnoreCase(sessionId)) {
						// multiple logins detected from same browser
						return "redirect:logout";
					} else {
						UserSessionDto usdto = new UserSessionDto(userLoggedIn, existingSessionId, sessionId);
						model.addAttribute("cursor", temp);
						model.addAttribute("usdto", usdto);
						model.addAttribute("message", StringUtil.MULTIPLE_LOGIN_PROMPT);
						return "multiple_login";
					}
				} else {
					sessionmap.put(temp.getUserId(), request.getSession(false).getId());
					request.getSession(false).getServletContext().setAttribute("sessionUtilMap", sessionmap);
				}
				addUserActivityLog(temp, "SINF0000", "LOGIN INFO");
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/makeOtherSessionsInvalid", method = RequestMethod.POST)
	public String replaceUserSessionEntry(@ModelAttribute("usdto") UserSessionDto usDto, BindingResult result,
			ModelMap model, RedirectAttributes red, HttpServletRequest request) {
		User tempuser;
		try {
			logger.debug(usDto);
			sessionmap = (Map<String, String>) request.getSession(false).getServletContext()
					.getAttribute("sessionUtilMap");
			sessionmap.replace(usDto.getUserId(), usDto.getExSessionId(), usDto.getSessionId());
			request.getSession(false).getServletContext().setAttribute("sessionUtilMap", sessionmap);
			logger.debug("EXISTING ENTRY FOR USER: " + usDto.getUserId() + " IN SESSION MAP HAS BEEN REPLACED");
			tempuser = userService.getUserById(usDto.getUserId());
			request.getSession(false).setAttribute("USER_LOGGED_IN", tempuser);
		} 
		catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		updateUserActivityLog(tempuser, "SINF0000", "LOGIN INFO");
		return "redirect:loginInfo";
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
	
	private int updateUserLastLoginTime(String uid) {
		int nora = 0;
		try {
			User cuser = userService.getUserById(uid);
			cuser.setLastLogin(new Timestamp(System.currentTimeMillis()));
			int loginAttempt = cuser.getLoginAttempt().intValue();
			if (loginAttempt > 0) {
				cuser.setLoginAttempt(new BigDecimal(++loginAttempt));
				nora = userService.updateUserLastLogin(cuser);
				return nora;
			} else if (loginAttempt == 0) {
				DecParam dp = loginParamService.getLoginParamById(StringUtil.FIRST_TIME_LOGIN_PASSWORD_CHANGE);
				if (dp.getParamValue().intValue() == 0) {
					cuser.setLoginAttempt(new BigDecimal(++loginAttempt));
					nora = userService.updateUserLastLogin(cuser);
				}
				return nora;
			} else {
				return nora;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			return nora;
		}
	}

	@RequestMapping(value = "/loginInfo", method = RequestMethod.GET)
	public String handleValidLogin(ModelMap model, HttpServletRequest request, RedirectAttributes red) {
		try {
			if (request.getSession(false) != null) {
				logger.debug("session exists");
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					User currentUser = (User) request.getSession(false).getAttribute(StringUtil.USER_SESSION);
					model.addAttribute("cursor", currentUser);
					int ret = updateUserLastLoginTime(currentUser.getUserId());
					logger.debug(ret);
				} else {
					logger.debug("no active user found");
					return "redirect:login";
				}
			} else {
				logger.debug("no session found");
				return "redirect:login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.toString());
			logger.debug("session already invalid");
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return "login_info";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String handleLogout(ModelMap model, HttpServletRequest request) {
		String sessionIdValueForThis;
		int retval;
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				logger.debug("session exists");
				// session.removeAttribute(USER_SESSION);
				sessionmap = (Map<String, String>) session.getServletContext().getAttribute("sessionUtilMap");
				if (!sessionmap.isEmpty()) {
					User temp = (User) session.getAttribute(StringUtil.USER_SESSION);
					if (temp != null) {
						sessionIdValueForThis = sessionmap.get(temp.getUserId());
						if (sessionIdValueForThis.equalsIgnoreCase(session.getId())) {
							sessionmap.remove(temp.getUserId(), sessionIdValueForThis);
							retval = activityLogService.removeUserFromActivelist(temp.getUserId());
							if (retval > 0) {
								logger.debug(StringUtil.SACL6000_SUCCESS);
							}
							else {
								logger.debug(StringUtil.FAILURE);
							}
						}
					}					
				}				
				request.getSession(false).invalidate();
				return "redirect:login";
			} else {
				logger.debug("no session found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.toString());
		}
		return "login";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String viewChangePasswordOption(ModelMap model, HttpServletRequest request, RedirectAttributes red,
			@ModelAttribute("error") String flashAttr, @ModelAttribute("alertText") String flashAttr2) {
		String minPasswdLen = StringUtil.MINIMUM_PASSWORD_LENGTH;
		String maxPasswdLen = StringUtil.MAXIMUM_PASSWORD_LENGTH;
		DecParam decParam, decParam2;
		try {
			if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				User currentUser = (User) request.getSession(false).getAttribute(StringUtil.USER_SESSION);
				model.addAttribute("cursor", currentUser);
				ChangePwdDto cpwddto = new ChangePwdDto();
				cpwddto.setUserId(currentUser.getUserId());
				cpwddto.setUserName(currentUser.getUserName());
				decParam = loginParamService.getLoginParamById(StringUtil.COMPLICATED_PASSWORD_SUPPORTED);
				BigDecimal ifPasswdValidNeed = decParam.getParamValue();
				model.addAttribute("testPasswdValid", ifPasswdValidNeed);
				if (ifPasswdValidNeed.intValue() == 0) {
					logger.debug("Complicated Password Disabled");
					model.addAttribute("passMinSize", new BigDecimal(1));
					model.addAttribute("passMaxSize", new BigDecimal(999));
				} else {
					decParam2 = loginParamService.getLoginParamById(minPasswdLen);
					BigDecimal minPasswdVal = decParam2.getParamValue();
					model.addAttribute("passMinSize", minPasswdVal);
					decParam2 = loginParamService.getLoginParamById(maxPasswdLen);
					BigDecimal maxPasswdVal = decParam2.getParamValue();
					model.addAttribute("passMaxSize", maxPasswdVal);
				}
				model.addAttribute("cpdto", cpwddto);
				model.addAttribute("alert", flashAttr2);
				model.addAttribute("error", flashAttr);
				updateUserActivityLog(currentUser, "SPWD0000", "CHANGE PASSWORD");
			} else {
				red.addFlashAttribute("cause", StringUtil.SESSION_EXPIRED_OR_INVALID);
				return "redirect:login";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return "show_change_password";
	}

	@RequestMapping(value = "/handlePasswordChange", method = RequestMethod.POST)
	public String handlePasswordChange(@ModelAttribute("cpdto") ChangePwdDto cpDto, BindingResult result, ModelMap model,
			RedirectAttributes red, HttpServletRequest request) {
		try {
			if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				boolean validPwd;
				User temp = (User) request.getSession(false).getAttribute(StringUtil.USER_SESSION);
				validPwd = BCrypt.checkpw(cpDto.getPassword(), temp.getPassword());
				if (validPwd) {
					logger.debug("old password verified");
					// if matches then update user data and session
					String generatedSecuredPasswordHash = BCrypt.hashpw(cpDto.getNewPassword(), BCrypt.gensalt(12));
					temp.setPassword(generatedSecuredPasswordHash);
					if (temp.getLoginAttempt().intValue() == 0) {
						temp.setLoginAttempt(new BigDecimal(1));
						temp.setLastLogin(new Timestamp(System.currentTimeMillis()));
						userService.updateUserLastLogin(temp);
					}
					Calendar cal = Calendar.getInstance();
					Date today = cal.getTime();
					temp.setPasswordCreDate(today);
					temp.setModifyBy(cpDto.getUserId());
					temp.setModifyTime(new Timestamp(System.currentTimeMillis()));
					userService.processPasswordChange(temp);
					User updatedUser = userService.getUserById(cpDto.getUserId());
					request.getSession(false).setAttribute("USER_LOGGED_IN", updatedUser);
				} else {
					logger.debug("old password verification failed");
					red.addFlashAttribute("alertText", StringUtil.OLD_NEW_PASSWORD_MISMATCH);
					return "redirect:changePassword";
				}
			} else {
				red.addFlashAttribute("cause", StringUtil.SESSION_EXPIRED_OR_INVALID);
				return "redirect:login";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		model.addAttribute("message", StringUtil.PASSWD_CHANGE_SUCCESS);
		return "change_password_success";
	}

	@RequestMapping(value = "/showModules", method = RequestMethod.GET)
	public String handleLoadingModules(ModelMap model, HttpServletRequest request, RedirectAttributes red) {
		List<Function> functionListByUser = new ArrayList<Function>();
		List<ScreenFunMap> scrnFunMapListByUser = new ArrayList<ScreenFunMap>();
		Map<String, List<String>> funModMap = new LinkedHashMap<String, List<String>>();
		Map<String, List<ScreenFunMap>> mapScreenFunMap = new LinkedHashMap<String, List<ScreenFunMap>>();
		List<String> functionSorter;
		List<ScreenFunMap> screenSorter;
		try {
			if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				User currentUser = (User) request.getSession(false).getAttribute(StringUtil.USER_SESSION);
				DecParam dp = loginParamService.getLoginParamById(StringUtil.FIRST_TIME_LOGIN_PASSWORD_CHANGE);
				if (dp.getParamValue().intValue() == 1) {
					if (currentUser.getLoginAttempt().intValue() == 0) {
						// user logging in for the first time
						red.addFlashAttribute("alertText",
								"You must change your password before logging on the first time. Please update your password or contact your system administrator for technical support");
						return "redirect:changePassword";
					}
				}
				model.addAttribute("cursor", currentUser);
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
				// get list of scrnFunMap by user
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
				request.getSession(false).setAttribute(StringUtil.SESSION_FUN_MOD_MAP, funModMap);
				request.getSession(false).setAttribute(StringUtil.SESSION_SCR_FUN_MAP, mapScreenFunMap);
				model.addAttribute("modmap", modHashmap);
				model.addAttribute("funmodmap", funModMap);
				model.addAttribute("scrfunmap", mapScreenFunMap);
				updateUserActivityLog(currentUser, "MAIN0000", "MAIN MENU");
			} else {
				red.addFlashAttribute("cause", StringUtil.SESSION_EXPIRED_OR_INVALID);
				return "redirect:logout";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return "load_modules";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/viewFunctions", method = RequestMethod.GET)
	public String displayFunctionsByModule(@RequestParam String mid, @RequestParam String fid, @RequestParam String sid,
			ModelMap model, RedirectAttributes red, HttpServletRequest request) {
		Map<String, List<String>> funModMap = new LinkedHashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>(), tempfunlist;
		List<Function> funListDb = new ArrayList<Function>();
		try {
			if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				User currentUser = (User) request.getSession(false).getAttribute(StringUtil.USER_SESSION);
				model.addAttribute("cursor", currentUser);
				if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
					funModMap = (Map<String, List<String>>) request.getSession(false)
							.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
					tempfunlist = funModMap.get(mid);
					funListDb = funcService.listFunctionsByModule(mid);
					Iterator<Function> funIter = funListDb.iterator();
					while (funIter.hasNext()) {
						Function funelem = funIter.next();
						if (Integer.parseInt(funelem.getFunctionFlag()) == 1) {
							if (tempfunlist.contains(funelem.getFunctionId())) {
								funList.add(funelem.getFunctionId().trim());
							}
						}
					}
					model.addAttribute("funlist", funList);
				}
				model.addAttribute("modmap", modHashmap);
				model.addAttribute("funmap", funHashmap);
				model.addAttribute("module", mid);
				model.addAttribute("funmodmap", request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP));
				String screenDesc = modHashmap.get(mid);
				updateUserActivityLog(currentUser, mid.replaceFirst("L", "S"), screenDesc);
			} else {
				red.addFlashAttribute("cause", StringUtil.SESSION_EXPIRED_OR_INVALID);
				return "redirect:login";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return "load_functions";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/viewScreen", method = RequestMethod.GET)
	public String displayScreenByFunction(@RequestParam String moduleId, @RequestParam String functionId,
			ModelMap model, HttpServletRequest request, RedirectAttributes red) {
		/*
		 * Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		 * Map<String, String> functionMap = new HashMap<String, String>(); Map<String,
		 * String> moduleMap = new HashMap<String, String>(); List<String> funList = new
		 * ArrayList<String>();
		 */
		Map<String, List<ScreenFunMap>> mapScrnFunMap = new LinkedHashMap<String, List<ScreenFunMap>>();
		List<ScreenFunMap> scrnFunList = new ArrayList<ScreenFunMap>();
		String screenId = "";
		try {
			if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				if (request.getSession(false).getAttribute(StringUtil.SESSION_SCR_FUN_MAP) != null) {
					mapScrnFunMap = (Map<String, List<ScreenFunMap>>) request.getSession(false)
							.getAttribute(StringUtil.SESSION_SCR_FUN_MAP);
					scrnFunList = mapScrnFunMap.get(functionId);
					for (ScreenFunMap sfm : scrnFunList) {
						if (sfm.getId().getButtonDef().equalsIgnoreCase("0")) {
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
				red.addFlashAttribute("cause", StringUtil.SESSION_EXPIRED_OR_INVALID);
				return "redirect:login";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return "redirect:common";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(ModelMap model) {
		logger.debug("REDIRECTING TO /loginInfo");
		return "redirect:loginInfo";
	}
	
	private void addUserActivityLog(User currentUser, String scrn, String scrnDesc) {
		// TODO Auto-generated method stub
		int retval = 0;
		try {
			ActiveUser activeUsr = new ActiveUser();
			activeUsr.setUserId(currentUser.getUserId());
			activeUsr.setContactNumber(currentUser.getContactNumber());
			activeUsr.setScreenId(scrn);
			activeUsr.setScreenDesc(scrnDesc);
			activeUsr.setLastActiveTime(new Timestamp(System.currentTimeMillis()));
			activeUsr.setBatchRunFlag("0");
			retval = activityLogService.addActiveUser(activeUsr);
			if (retval > 0) 
				logger.debug("USER ACTIVITY LOGGED SUCCESSFULLY");
			else
				logger.debug("USER ACTIVITY LOG INSERT UNSUCCESSFUL");
		} 
		catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.toString());
		}
	}
	
	private void updateUserActivityLog(User currentUser, String scrn, String scrnDesc) {
		// TODO Auto-generated method stub
		int retval = 0;
		try {
			ActiveUser activeUsr = new ActiveUser();
			activeUsr.setUserId(currentUser.getUserId());
			activeUsr.setContactNumber(currentUser.getContactNumber());
			activeUsr.setScreenId(scrn);
			activeUsr.setScreenDesc(scrnDesc);
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
			logger.debug(e.toString());
		}
	}

}