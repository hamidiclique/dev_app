package com.test.app.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

import com.test.app.dto.FungrpFunMapDto;
import com.test.app.dto.ViewAuthDto;
import com.test.app.entity.Function;
import com.test.app.entity.Functiongrp;
import com.test.app.entity.FungrpFunMap;
import com.test.app.entity.FungrpFunMapPK;
import com.test.app.entity.Module;
import com.test.app.entity.Role;
import com.test.app.entity.User;
import com.test.app.service.FunctionService;
import com.test.app.service.FunctiongrpService;
import com.test.app.service.FungrpFunMapSercice;
import com.test.app.service.ModuleService;
import com.test.app.service.RoleService;
import com.test.app.service.UserService;
import com.test.app.util.SessionUtil;
import com.test.app.util.StringUtil;
import com.test.app.util.ViewAuthUtil;

@Controller
public class AccessControlListController {

	private static final Logger logger = Logger.getLogger(AccessControlListController.class);

	@Autowired
	UserService userService;
	@Autowired
	FunctiongrpService fungrpService;
	@Autowired
	ModuleService moduleService;
	@Autowired
	FunctionService funService;
	@Autowired
	FungrpFunMapSercice fungrpFunMapService;
	@Autowired
	RoleService roleService;

	@RequestMapping(value = "/user-id-maintenance-add", method = RequestMethod.GET)
	public String showAddUserScreen(ModelMap model, @RequestParam String mid, @RequestParam String fid, @RequestParam String sid,
			@RequestParam String pid, HttpServletRequest request, RedirectAttributes red) {
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					//do something
					User user = new User();
					model.addAttribute("user", user);
				} else {
					return "redirect:/login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:/index/notAuthorized";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
		logger.debug("return "+sid+".jsp");
		return sid;
	}

	@RequestMapping(value = "/saveNewUser", method = RequestMethod.POST)
	public String handleAddNewUser(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {
		User newUser = user;
		newUser.getUserId();
		try {
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			cal.add(Calendar.YEAR, 1);
			Date nextYear = cal.getTime();
			user.setPasswordCreDate(today);
			user.setPasswordExpDate(nextYear);
			user.setModifyBy(user.getUserId());
			user.setModifyTime(new Timestamp(System.currentTimeMillis()));
			user.setLastLogin(new Timestamp(System.currentTimeMillis()));
			user.setLoginAttempt(new BigDecimal(1));
			user.setFailCount(new BigDecimal(0));
			String generatedSecuredPasswordHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
			user.setPassword(generatedSecuredPasswordHash);
			userService.storeNewUserData(user);

			/*
			 * String originalPassword = user.getPassword(); String
			 * generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword,
			 * BCrypt.gensalt(12)); System.out.println(generatedSecuredPasswordHash);
			 * boolean matched = BCrypt.checkpw(originalPassword,
			 * generatedSecuredPasswordHash); System.out.println(matched);
			 */
		} catch (Exception ex) {
			ex.toString();
			// model.addAttribute("", );
		}
		return "add_hotel_success";
	}

	@RequestMapping(value = "/user-id-maintenance", method = RequestMethod.GET)
	public String view_FACL3000_SACL3000(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		List<User> userList = new ArrayList<User>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					userList = userService.fetchAllUsers();
					model.addAttribute("userList", userList);
					model.addAttribute("listsize", userList.size());
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("functionId", fid);
					model.addAttribute("moduleId", mid);
				} else {
					return "redirect:/login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:/index/notAuthorized";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
		logger.debug("return "+sid+".jsp");
		return sid;
	}

	@RequestMapping(value = "/function-group-maintenance", method = RequestMethod.GET)
	public String view_FACL1000_SACL1000(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		List<Functiongrp> fungrpList = new ArrayList<Functiongrp>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);
					fungrpList = fungrpService.listFunctionGroups();
					model.addAttribute("fungrpList", fungrpList);
					model.addAttribute("listsize", fungrpList.size());
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("functionId", fid);
					model.addAttribute("moduleId", mid);
					model.addAttribute("functionDesc", currentfunction.getFunctionDesc());
				} else {
					return "redirect:/login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:/index/notAuthorized";
			}
		} catch (Exception ex) {
			logger.debug(ex.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
		logger.debug("return "+sid+".jsp");
		return sid;
	}

	@RequestMapping(value = "/user-id-maintenance-update", method = RequestMethod.GET)
	public String displayUserInfoForUpdate(@RequestParam String mid, @RequestParam String fid, @RequestParam String sid, @RequestParam String pid,
			ModelMap model) {
		String searchId = pid;
		User userToUpdate = userService.getUserById(searchId);
		model.addAttribute("upUser", userToUpdate);
		return "update_user";
	}

	@RequestMapping(value = "/submitUpdatedUserInfo", method = RequestMethod.POST)
	public String handleUserUpdate(@ModelAttribute("upUser") User user, BindingResult result, ModelMap model) {
		try {
			user.setModifyBy(user.getUserId());
			user.setModifyTime(new Timestamp(System.currentTimeMillis()));
			userService.updateUserInfoProcess(user);
		} catch (Exception ex) {
			ex.toString();
			return "redirect:showAllUsers";
		}
		return "redirect:showAllUsers";
	}
	
	@RequestMapping(value = "/function-group-maintenance-add", method = RequestMethod.GET)
	public String view_FACL1000_SACL1000A(ModelMap model, @RequestParam String mid, @RequestParam String fid, @RequestParam String sid,
			@RequestParam String pid, HttpSession session, RedirectAttributes red) {
		try {
			if (SessionUtil.sessionValid(session, red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				User user = new User();
				model.addAttribute("user", user);
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
		return "add_new_user";
	}
	
	@RequestMapping(value = "/submit-function-group-maintenance-update", method = RequestMethod.POST)
	public String handle_FACL1000_SACL1000U(ModelMap model, @ModelAttribute("ffMapDto") FungrpFunMapDto fungrpfunmapdto,
			BindingResult result, HttpServletRequest req, RedirectAttributes red) {
		logger.debug(fungrpfunmapdto);		
		List<FungrpFunMap> fungrpFunMapList;
		FungrpFunMap ffMap;
		FungrpFunMapPK ffMapId;
		
		try {
			if (SessionUtil.sessionValid(req.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				User currentUser = (User) req.getSession().getAttribute(StringUtil.USER_SESSION);
				fungrpFunMapService.removeElementsForFungrp(fungrpfunmapdto.getFunctiongrpId());
				fungrpFunMapList = new ArrayList<FungrpFunMap>();
				for (String str : fungrpfunmapdto.getFunctionList()) {
					ffMap = new FungrpFunMap();
					ffMapId = new FungrpFunMapPK();
					ffMapId.setFunctionId(str);
					ffMapId.setFunctiongrpId(fungrpfunmapdto.getFunctiongrpId());
					ffMap.setId(ffMapId);
					ffMap.setModifyBy(currentUser.getUserId());
					ffMap.setModifyTime(new Timestamp(System.currentTimeMillis()));
					ffMap.setSysFlag("1");
					fungrpFunMapList.add(ffMap);
				}
				logger.debug(fungrpFunMapList);
				fungrpFunMapService.mapCheckedFunctionsToGroup(fungrpFunMapList);
				
			} else {
				return "redirect:/login";
			}

		} catch (Exception ex) {
			logger.debug(ex.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
		return null;
	}
	
	@RequestMapping(value = "/function-group-maintenance-update", method = RequestMethod.GET)
	public String view_FACL1000_SACL1000U(ModelMap model, @RequestParam String mid, @RequestParam String fid, @RequestParam String sid,
			@RequestParam String pid, HttpServletRequest request, RedirectAttributes red) {
		Functiongrp fungrp;
		Function funForMod;
		Module module;
		List<Function> funListByModule, funListByFungrp;
		Map<String, Boolean> funCheckMap = new HashMap<String, Boolean>();
				
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					funForMod = funService.getModuleIdByFungrp(pid);
					funListByModule = funService.listFunctionsByModule(funForMod.getModuleId());
					int sizeOfListByModule = funListByModule.size();
					for (Function fun : funListByModule) {
						funCheckMap.put(fun.getFunctionId(), false);
					}
					//copyFunListByModule = funListByModule;
					funListByFungrp = funService.listFunctionsByFungrp(pid);
					int sizeOfListByFungrp = funListByFungrp.size();
					if (sizeOfListByFungrp == 0) {
						//no function will be checked					
					}
					else if (sizeOfListByModule == sizeOfListByFungrp) {
						//all functions will be checked
						for (Function fun : funListByModule) {
							funCheckMap.put(fun.getFunctionId(), true);
						}
					}
					else {
						//find functions that will be checked
						for (Function fun : funListByFungrp) {
							if (funCheckMap.containsKey(fun.getFunctionId())) {
								funCheckMap.replace(fun.getFunctionId(), true);
							} else {
								//do nothing
								funCheckMap.replace(fun.getFunctionId(), false);
							}
						}
						
	/*					Iterator<Function> outerIterator = funListByFungrp.iterator();
						while (outerIterator.hasNext()) {
							Function outerFun = outerIterator.next();
							
							//Iterator<Function> innerIterator = copyFunListByModule.iterator();
							Iterator<Function> innerIterator = funListByModule.iterator();
							while (innerIterator.hasNext()) {
								Function innerFun = innerIterator.next();
								
								if (innerFun.getFunctionId().equalsIgnoreCase(outerFun.getFunctionId())) {
									//innerIterator.remove();
									break;
								}
							}
						}*/ 
					}
					fungrp = fungrpService.getFunctionGroupById(pid);
					module = moduleService.getModuleById(mid);
					FungrpFunMapDto ffMapDto = new FungrpFunMapDto();
					ffMapDto.setFunctiongrpId(fungrp.getFunctiongrpId());
					ffMapDto.setFunctiongrpDesc(fungrp.getFunctiongrpDesc());
					ffMapDto.setModule(mid);
					ffMapDto.setFunction(fid);
					ffMapDto.setScreen(sid);
					
					model.addAttribute("module", module);
					model.addAttribute("functionDesc", "Function Group Maintenance - Update");
					model.addAttribute("allFunctions", funListByModule);
					model.addAttribute("switchMap", funCheckMap);
					model.addAttribute("ffMapDto", ffMapDto);
				} else {
					return "redirect:/login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:/index/notAuthorized";
			}			
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
		return sid;
	}
	
	@RequestMapping(value = "/user-role-maintenance", method = RequestMethod.GET)
	public String view_FACL2000_SACL2000(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		List<Role> roleList;
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					//do something
					Function currentfunction = funService.getFunctionById(fid);
					roleList = roleService.listAllRoles();					 
					model.addAttribute("roleList", roleList);
					model.addAttribute("listsize", roleList.size());
					model.addAttribute("functionDesc", currentfunction.getFunctionDesc());
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("functionId", fid);
					model.addAttribute("moduleId", mid);
				} else {
					return "redirect:/login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:/index/notAuthorized";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:/login";
		}
		logger.debug("return "+sid+".jsp");
		return sid;
	}

	@ModelAttribute("commonStatus")
	protected Map<String, String> getCommonStatusVariables() {
		Map<String, String> commonVarList = new HashMap<String, String>();
		commonVarList.put("Y", "Yes");
		commonVarList.put("N", "No");
		return commonVarList;
	}

	@ModelAttribute("panMaskOptions")
	protected Map<String, String> getPanMaskOptions() {
		Map<String, String> panMaskOptions = new HashMap<String, String>();
		panMaskOptions.put("0", "0 - Mask Always");
		panMaskOptions.put("1", "1 - Conditional Masking");
		panMaskOptions.put("2", "2 - Do Not Mask");
		return panMaskOptions;
	}

	@ModelAttribute("userRoleOptions")
	protected Map<String, String> getUserRoles() {
		Map<String, String> userRoleOptions = new HashMap<String, String>();
		userRoleOptions.put("SUPER", "Super");
		userRoleOptions.put("TEST", "Test");
		userRoleOptions.put("DEBUG", "Debug");
		return userRoleOptions;
	}

	@ModelAttribute("statusOptions")
	protected Map<String, String> getStatusOptions() {
		Map<String, String> statusOptions = new HashMap<String, String>();
		statusOptions.put("ACTIVE", "ACTIVE");
		statusOptions.put("INACTIVE", "INACTIVE");
		statusOptions.put("LOCKED", "LOCKED");
		return statusOptions;
	}

}
