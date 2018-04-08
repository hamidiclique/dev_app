package com.test.app.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.test.app.dto.ActivityLogDto;
import com.test.app.dto.FungrpFunMapDto;
import com.test.app.dto.RoleFungrpMapDto;
import com.test.app.dto.ViewAuthDto;
import com.test.app.entity.ActiveUser;
import com.test.app.entity.DecParam;
import com.test.app.entity.Function;
import com.test.app.entity.Functiongrp;
import com.test.app.entity.FungrpFunMap;
import com.test.app.entity.FungrpFunMapPK;
import com.test.app.entity.Module;
import com.test.app.entity.Role;
import com.test.app.entity.RoleFungrpMap;
import com.test.app.entity.RoleFungrpMapPK;
import com.test.app.entity.User;
import com.test.app.service.ActivityLogService;
import com.test.app.service.FunctionService;
import com.test.app.service.FunctiongrpService;
import com.test.app.service.FungrpFunMapSercice;
import com.test.app.service.LoginParamService;
import com.test.app.service.ModuleService;
import com.test.app.service.RoleFungrpMapService;
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
	@Autowired
	RoleFungrpMapService roleFungrpMapService;
	@Autowired
	LoginParamService loginParamService;
	@Autowired
	ActivityLogService activityLogService;
	@Resource(name = "modHashmap")
	private Map<String, String> modHashmap;
	@Resource(name = "funHashmap")
	private Map<String, String> funHashmap;

	@RequestMapping(value = "/user-id-maintenance-add", method = RequestMethod.GET)
	public String view_FACL3000_SACL3000A(ModelMap model, @RequestParam String mid, @RequestParam String fid, @RequestParam String sid,
			@RequestParam String pid, HttpServletRequest request, RedirectAttributes red) {
		String minPasswdLen = StringUtil.MINIMUM_PASSWORD_LENGTH;
		String maxPasswdLen = StringUtil.MAXIMUM_PASSWORD_LENGTH;
		DecParam decParam, decParam2;
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {					
					User user = new User();
					decParam = loginParamService.getLoginParamById(StringUtil.COMPLICATED_PASSWORD_SUPPORTED);
					BigDecimal ifPasswdValidNeed = decParam.getParamValue();
					model.addAttribute("testPasswdValid", ifPasswdValidNeed);
					if (ifPasswdValidNeed.intValue() == 0) {
						logger.debug("Complicated Password Disabled");
						model.addAttribute("passMinSize", new BigDecimal(1));
						model.addAttribute("passMaxSize", new BigDecimal(999));
					}
					else {
						decParam2 = loginParamService.getLoginParamById(minPasswdLen);
						BigDecimal minPasswdVal = decParam2.getParamValue();
						model.addAttribute("passMinSize", minPasswdVal);
						decParam2 = loginParamService.getLoginParamById(maxPasswdLen);
						BigDecimal maxPasswdVal = decParam2.getParamValue();					
						model.addAttribute("passMaxSize", maxPasswdVal);					
					}									
					model.addAttribute("user", user);
					model.addAttribute("module", mid);
					model.addAttribute("function", fid);
					model.addAttribute("screen", sid);
					model.addAttribute("functionDesc", "User ID Maintenance - Add");	
				} else {
					return "redirect:login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:notAuthorized";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}
	
	@RequestMapping(value = "/user-id-maintenance-delete", method = RequestMethod.GET)
	public String view_FACL3000_SACL3000D(ModelMap model, @RequestParam String mid, @RequestParam String fid, @RequestParam String sid,
			@RequestParam String pid, HttpServletRequest request, RedirectAttributes red) {
		int retval = 0;
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {					
					retval = userService.deleteUserById(pid);
					if (retval > 0) {
						model.addAttribute("message", StringUtil.SACL3000D_SUCCESS);						
					}
					else {
						model.addAttribute("message", StringUtil.FAILURE);
					}
				} else {
					return "redirect:login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:notAuthorized";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}	

	@RequestMapping(value = "/submit-user-id-maintenance-add", method = RequestMethod.POST)
	public String handle_FACL3000_SACL3000A(@ModelAttribute("user") User user, BindingResult result, ModelMap model,
			HttpServletRequest req, RedirectAttributes red) {
		int retval;		
		try {
			if (SessionUtil.sessionValid(req.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				retval = 0;
				Calendar cal = Calendar.getInstance();
				Date today = cal.getTime();
				cal.add(Calendar.YEAR, 1);
				Date nextYear = cal.getTime();
				user.setPasswordCreDate(today);
				user.setPasswordExpDate(nextYear);
				user.setModifyBy(user.getUserId());
				user.setModifyTime(new Timestamp(System.currentTimeMillis()));
				user.setLastLogin(new Timestamp(System.currentTimeMillis()));
				user.setLoginAttempt(new BigDecimal(0));
				user.setFailCount(new BigDecimal(0));
				String generatedSecuredPasswordHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
				user.setPassword(generatedSecuredPasswordHash);				
				retval = userService.storeNewUserData(user);
				if (retval > 0) {
					model.addAttribute("message", StringUtil.SACL3000A_SUCCESS);	
				}
				else {
					logger.debug("ERROR : INSERT USER OPERATION WAS NOT SUCCESSFUL");
					model.addAttribute("message", StringUtil.FAILURE);
				}				
			} else {
				return "redirect:login";
			}

		} catch (Exception ex) {
			logger.debug(ex.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return "common_result";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/user-id-maintenance", method = RequestMethod.GET)
	public String view_FACL3000_SACL3000(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request, @ModelAttribute("message") String msg) {
		List<User> userList = new ArrayList<User>();
		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					userList = userService.fetchAllUsers();
					Function currentfunction = funService.getFunctionById(fid);
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						funList = funModMap.get(mid);
						Iterator<String> funIterator = funList.iterator();
						while (funIterator.hasNext()) {
						    String element = funIterator.next();					   
						    if(element.length() > 8) {
						        funIterator.remove();
						    }
						}
					}
					model.addAttribute("funlist", funList);
					model.addAttribute("userList", userList);
					model.addAttribute("listsize", userList.size());
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
					model.addAttribute("functionDesc", currentfunction.getFunctionDesc());
					model.addAttribute("message", msg);
					model.addAttribute("funmap", funHashmap);
				} else {
					return "redirect:login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:notAuthorized";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/function-group-maintenance", method = RequestMethod.GET)
	public String view_FACL1000_SACL1000(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		List<Functiongrp> fungrpList = new ArrayList<Functiongrp>();
		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);
					fungrpList = fungrpService.listFunctionGroups();
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						funList = funModMap.get(mid);
						Iterator<String> funIterator = funList.iterator();
						while (funIterator.hasNext()) {
						    String element = funIterator.next();					   
						    if(element.length() > 8) {
						        funIterator.remove();
						    }
						}
					}
					model.addAttribute("funlist", funList);
					model.addAttribute("fungrpList", fungrpList);
					model.addAttribute("listsize", fungrpList.size());
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
					model.addAttribute("functionDesc", currentfunction.getFunctionDesc());
					model.addAttribute("funmap", funHashmap);
				} else {
					return "redirect:login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:notAuthorized";
			}
		} catch (Exception ex) {
			logger.debug(ex.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}		
		return sid;
	}

	@RequestMapping(value = "/user-id-maintenance-update", method = RequestMethod.GET)
	public String view_FACL3000_SACL3000U(@RequestParam String mid, @RequestParam String fid, @RequestParam String sid, @RequestParam String pid,
			ModelMap model, HttpServletRequest request, RedirectAttributes red) {		
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);
					User userToUpdate = userService.getUserById(pid);
					model.addAttribute("user", userToUpdate);
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
					model.addAttribute("screen", sid);
					model.addAttribute("functionDesc", "User ID Maintenance - Update");
				} else {
					return "redirect:login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:notAuthorized";
			}
		} catch (Exception ex) {
			logger.debug(ex.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}		
		return sid;
	}

	@RequestMapping(value = "/submit-user-id-maintenance-update", method = RequestMethod.POST)
	public String handle_FACL3000_SACL3000U(@ModelAttribute("user") User user, BindingResult result, ModelMap model, HttpServletRequest request, RedirectAttributes red) {
		int retval;		
		try {
			if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				retval = 0;
				user.setModifyBy(user.getUserId());
				user.setModifyTime(new Timestamp(System.currentTimeMillis()));
				retval = userService.updateUserInfoProcess(user);
				if (retval > 0) {
					model.addAttribute("message", StringUtil.SACL3000U_SUCCESS);	
				}
				else {
					logger.debug("ERROR : USER UPDATE OPERATION WAS NOT SUCCESSFUL");
					model.addAttribute("message", StringUtil.FAILURE);
				}				
			} else {
				return "redirect:login";
			}

		} catch (Exception ex) {
			logger.debug(ex.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return "common_result";
	}
	
	@RequestMapping(value = "/function-group-maintenance-add", method = RequestMethod.GET)
	public String view_FACL1000_SACL1000A(ModelMap model, @RequestParam String mid, @RequestParam String fid, @RequestParam String sid,
			@RequestParam String pid, HttpServletRequest request, RedirectAttributes red) {
		FungrpFunMapDto ffMapDto;
		List<Module> allModules;
		
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					ffMapDto = new FungrpFunMapDto();
					ffMapDto.setFunction(fid);
					ffMapDto.setScreen(sid);
					allModules = moduleService.getAllModules();
					model.addAttribute("allModules", allModules);
					model.addAttribute("ffMapDto", ffMapDto);
					model.addAttribute("functionDesc", "Function Group Maintenance - Add");					
				} 
				else {
					return "redirect:login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:notAuthorized";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}		
		return sid;
	}	
	
	@RequestMapping(value = "/submit-user-role-maintenance-update", method = RequestMethod.POST)
	public String handle_FACL2000_SACL2000U(ModelMap model, @ModelAttribute("rfMapDto") RoleFungrpMapDto rolefungrpmapdto,
					BindingResult result, HttpServletRequest req, RedirectAttributes red) {
		List<RoleFungrpMap> roleFungrpMaps;
		RoleFungrpMap rfMap;
		RoleFungrpMapPK rfMapId;
		int retval, retval2, retval3;		
		try {
			if (SessionUtil.sessionValid(req.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				User currentUser = (User) req.getSession().getAttribute(StringUtil.USER_SESSION);
				logger.debug(rolefungrpmapdto);
				Role role = new Role();
				role.setRoleId(rolefungrpmapdto.getRoleId());
				role.setRoleDesc(rolefungrpmapdto.getRoleDesc());
				role.setModifyBy(currentUser.getUserId());
				role.setModifyTime(new Timestamp(System.currentTimeMillis()));				
				retval = 0;
				retval = roleService.updateRoleById(role);
				if (retval > 0) {
					logger.debug("USER ROLE UPDATE WAS SUCCESSFUL");					
				}
				else {
					logger.debug("ERROR : USER ROLE UPDATE WAS NOT SUCCESSFUL");
				}
				retval2 = 0;
				retval2 = roleFungrpMapService.deleteFungroupsForRole(rolefungrpmapdto.getRoleId());
				if (retval2 > 0) {
					//execute rest of the codes
					roleFungrpMaps = new ArrayList<RoleFungrpMap>();
					for (String str : rolefungrpmapdto.getFungrpList()) {
						rfMap = new RoleFungrpMap();
						rfMapId = new RoleFungrpMapPK();
						rfMapId.setFunctiongrpId(str);
						rfMapId.setRoleId(rolefungrpmapdto.getRoleId());
						rfMap.setId(rfMapId);
						rfMap.setModifyBy(currentUser.getUserId());
						rfMap.setModifyTime(new Timestamp(System.currentTimeMillis()));
						rfMap.setSysFlag("1");
						roleFungrpMaps.add(rfMap);
					}
					retval3 = 0;
					retval3 = roleFungrpMapService.insertFungroupsForRole(roleFungrpMaps);
					if (retval3 > 0) {
						model.addAttribute("message", StringUtil.SACL2000U_SUCCESS);
					}
					else {
						model.addAttribute("message", StringUtil.FAILURE);
					}
				}
				else {
					logger.debug("ERROR : DELETE OPERATION WAS NOT SUCCESSFUL");
					model.addAttribute("message", StringUtil.FAILURE);
				}				
			} else {
				return "redirect:login";
			}
			
		} catch (Exception ex) {
			logger.debug(ex.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return rolefungrpmapdto.getScreen();
	}
			
	@RequestMapping(value = "/submit-function-group-maintenance-update", method = RequestMethod.POST)
	public String handle_FACL1000_SACL1000U(ModelMap model, @ModelAttribute("ffMapDto") FungrpFunMapDto fungrpfunmapdto,
			BindingResult result, HttpServletRequest req, RedirectAttributes red) {
		logger.debug(fungrpfunmapdto);		
		List<FungrpFunMap> fungrpFunMapList;
		FungrpFunMap ffMap;
		FungrpFunMapPK ffMapId;
		int retval, retval2;		
		try {
			if (SessionUtil.sessionValid(req.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				User currentUser = (User) req.getSession().getAttribute(StringUtil.USER_SESSION);
				Functiongrp fungrp = new Functiongrp();
				fungrp.setFunctiongrpId(fungrpfunmapdto.getFunctiongrpId());
				fungrp.setFunctiongrpDesc(fungrpfunmapdto.getFunctiongrpDesc());
				fungrp.setModifyBy(currentUser.getUserId());
				fungrp.setModifyTime(new Timestamp(System.currentTimeMillis()));				
				retval = 0;
				retval = fungrpService.updateFunctionGroup(fungrp);
				if (retval > 0) {
					logger.debug("FUNCTION GROUP UPDATE WAS SUCCESSFUL");					
				}
				else {
					logger.debug("ERROR : FUNCTION GROUP UPDATE WAS NOT SUCCESSFUL");
				} 
				retval = 0;		
				retval = fungrpFunMapService.removeElementsForFungrp(fungrpfunmapdto.getFunctiongrpId());
				if (retval > 0) {
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
					retval2 = 0;
					retval2 = fungrpFunMapService.mapCheckedFunctionsToGroup(fungrpFunMapList);
					if (retval2 > 0) {
						model.addAttribute("message", StringUtil.SACL1000U_SUCCESS);
					}
					else {
						model.addAttribute("message", StringUtil.FAILURE);
					}
				}
				else {
					logger.debug("ERROR : DELETE OPERATION WAS NOT SUCCESSFUL");
					model.addAttribute("message", StringUtil.FAILURE);
				}				
			} else {
				return "redirect:login";
			}

		} catch (Exception ex) {
			logger.debug(ex.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return "common_result";
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
				if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					funForMod = funService.getModuleIdByFungrp(pid);
					module = moduleService.getModuleById(funForMod.getModuleId());
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
					return "redirect:login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:notAuthorized";
			}			
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}
	
	@RequestMapping(value = "/submit-function-group-maintenance-add", method = RequestMethod.POST)
	public String handle_FACL1000_SACL1000A(ModelMap model, @ModelAttribute("ffMapDto") FungrpFunMapDto fungrpfunmapdto,
			BindingResult result, HttpServletRequest req, RedirectAttributes red) {
		logger.debug(fungrpfunmapdto);		
		List<FungrpFunMap> fungrpFunMapList;
		FungrpFunMap ffMap;
		FungrpFunMapPK ffMapId;
		int retval, retval2;
		try {
			if (SessionUtil.sessionValid(req.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				User currentUser = (User) req.getSession().getAttribute(StringUtil.USER_SESSION);				
				Functiongrp fungrpToAdd = new Functiongrp();
				fungrpToAdd.setFunctiongrpId(fungrpfunmapdto.getFunctiongrpId());
				fungrpToAdd.setFunctiongrpDesc(fungrpfunmapdto.getFunctiongrpDesc());
				fungrpToAdd.setModifyBy(currentUser.getUserId());
				fungrpToAdd.setModifyTime(new Timestamp(System.currentTimeMillis()));
				retval = fungrpService.insertFunctionGroup(fungrpToAdd);
				if (retval > 0) {
					//function group created
					//map selected functions to new function group
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
					retval2 = 0;
					retval2 = fungrpFunMapService.mapCheckedFunctionsToGroup(fungrpFunMapList);
					if (retval2 > 0) {
						model.addAttribute("message", StringUtil.SACL1000A_SUCCESS);
					}
					else {
						model.addAttribute("message", StringUtil.FAILURE);
					}
				}
				else {
					logger.debug("ERROR : FUNCTION GROUP INSERT OPERATION WAS NOT SUCCESSFUL");
					model.addAttribute("message", StringUtil.FAILURE);
				}
			} else {
				return "redirect:login";
			}

		} catch (Exception ex) {
			logger.debug(ex.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return "common_result";
	}
	
	@RequestMapping(value = "/submit-user-role-maintenance-add", method = RequestMethod.POST)
	public String handle_FACL2000_SACL2000A(ModelMap model, @ModelAttribute("rfMapDto") RoleFungrpMapDto rolefungrpmapdto,
			BindingResult result, HttpServletRequest req, RedirectAttributes red) {
		logger.debug(rolefungrpmapdto);		
		List<RoleFungrpMap> roleFungrpMapList;
		RoleFungrpMap rfMap;
		RoleFungrpMapPK rfMapId;
		int retval, retval2;
		try {
			if (SessionUtil.sessionValid(req.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				User currentUser = (User) req.getSession().getAttribute(StringUtil.USER_SESSION);
				//create role first then map function groups to role
				Role role = new Role();
				role.setRoleId(rolefungrpmapdto.getRoleId());
				role.setRoleDesc(rolefungrpmapdto.getRoleDesc());
				role.setModifyBy(currentUser.getUserId());
				role.setModifyTime(new Timestamp(System.currentTimeMillis()));
				retval = roleService.addNewRole(role);
				if (retval > 0) {
					List<String> selFungrpList = rolefungrpmapdto.getFungrpList();
					roleFungrpMapList = new ArrayList<RoleFungrpMap>();
					for (String str : selFungrpList) {
						rfMap = new RoleFungrpMap();
						rfMapId = new RoleFungrpMapPK();
						rfMapId.setRoleId(rolefungrpmapdto.getRoleId());
						rfMapId.setFunctiongrpId(str);
						rfMap.setId(rfMapId);
						rfMap.setSysFlag("1");
						rfMap.setModifyBy(currentUser.getUserId());
						rfMap.setModifyTime(new Timestamp(System.currentTimeMillis()));
						roleFungrpMapList.add(rfMap);
					}
					logger.debug(roleFungrpMapList);
					retval2 = 0;
					retval2 = roleFungrpMapService.insertFungroupsForRole(roleFungrpMapList);
					if (retval2 > 0) {
						model.addAttribute("message", StringUtil.SACL2000A_SUCCESS);
					}
					else {
						model.addAttribute("message", StringUtil.FAILURE);
					}
				}
				else {
					logger.debug("ERROR : USER ROLE INSERT OPERATION WAS NOT SUCCESSFUL");
					model.addAttribute("message", StringUtil.FAILURE);
				}				
			} else {
				return "redirect:login";
			}

		} catch (Exception ex) {
			logger.debug(ex.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return "common_result";
	}
	
	@RequestMapping(value = "/user-role-maintenance-add", method = RequestMethod.GET)
	public String view_FACL2000_SACL2000A(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, @RequestParam String pid, RedirectAttributes red, HttpServletRequest request) {
		RoleFungrpMapDto rfMapDto;
		List<Functiongrp> allFungroups;
		
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					rfMapDto = new RoleFungrpMapDto();
					allFungroups = fungrpService.listFunctionGroups();
					model.addAttribute("outbox", allFungroups);
					model.addAttribute("rfMapDto", rfMapDto);
					model.addAttribute("module", mid);
					model.addAttribute("function", fid);
					model.addAttribute("screen", sid);
					model.addAttribute("functionDesc", "User Role Maintenance - Add");					
				} 
				else {
					return "redirect:login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:notAuthorized";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}
	
	//check tag
	@RequestMapping(value = "/user-role-maintenance-update", method = RequestMethod.GET)
	public String view_FACL2000_SACL2000U(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, @RequestParam String pid, RedirectAttributes red, HttpServletRequest request) {
		Role editRole;
		RoleFungrpMapDto rfMapDto;
		List<Functiongrp> fungrpList;
		List<RoleFungrpMap> mapFungrpByRole;
		List<String> inbox = new ArrayList<String>(); 
		List<String> outbox = new ArrayList<String>();
		
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					fungrpList = fungrpService.listFunctionGroups();					
					for (Functiongrp fg : fungrpList) {
						outbox.add(fg.getFunctiongrpId());
					}							
					mapFungrpByRole = roleFungrpMapService.mapFungroupsByRole(pid);
					Iterator<RoleFungrpMap> outerIterator = mapFungrpByRole.iterator();
					while (outerIterator.hasNext()) {
						RoleFungrpMap outerVar = outerIterator.next();
						
						Iterator<String> innerIterator = outbox.iterator();
						while (innerIterator.hasNext()) {
							String innerVar = innerIterator.next();
							
							if (innerVar.equalsIgnoreCase(outerVar.getId().getFunctiongrpId())) {
								inbox.add(innerVar);
								innerIterator.remove();
								break;
							}
						}
					}
					editRole = roleService.getRoleById(pid);
					rfMapDto = new RoleFungrpMapDto();
					rfMapDto.setFunction(fid);
					rfMapDto.setModule(mid);
					rfMapDto.setScreen(sid);
					rfMapDto.setRoleId(editRole.getRoleId());
					rfMapDto.setRoleDesc(editRole.getRoleDesc());
					model.addAttribute("inbox", inbox);
					model.addAttribute("outbox", outbox);
					model.addAttribute("functionDesc", "User Role Maintenance - Update");
					model.addAttribute("rfMapDto", rfMapDto);
					
				} else {
					return "redirect:login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:notAuthorized";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}		
		return sid;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/user-role-maintenance", method = RequestMethod.GET)
	public String view_FACL2000_SACL2000(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		List<Role> roleList;
		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {					
					Function currentfunction = funService.getFunctionById(fid);
					roleList = roleService.listAllRoles();	
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						funList = funModMap.get(mid);
						Iterator<String> funIterator = funList.iterator();
						while (funIterator.hasNext()) {
						    String element = funIterator.next();					   
						    if(element.length() > 8) {
						        funIterator.remove();
						    }
						}
					}
					model.addAttribute("funlist", funList);
					model.addAttribute("roleList", roleList);
					model.addAttribute("listsize", roleList.size());
					model.addAttribute("functionDesc", currentfunction.getFunctionDesc());
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
					model.addAttribute("funmap", funHashmap);
				} 
				else {
					return "redirect:login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:notAuthorized";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/view-active-users", method = RequestMethod.GET)
	public String view_FACL6000_SACL6000(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		List<ActiveUser> activeUsersList = new ArrayList<ActiveUser>();
		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {					
					Function currentfunction = funService.getFunctionById(fid);
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						funList = funModMap.get(mid);
						Iterator<String> funIterator = funList.iterator();
						while (funIterator.hasNext()) {
						    String element = funIterator.next();					   
						    if(element.length() > 8) {
						        funIterator.remove();
						    }
						}
					}
					activeUsersList = activityLogService.getAllActiveUsers();
					ActivityLogDto actvtylogdto = new ActivityLogDto();
					actvtylogdto.setFunction(fid);
					actvtylogdto.setModule(mid);
					actvtylogdto.setScreen(sid);
					
					model.addAttribute("aldto", actvtylogdto);
					model.addAttribute("actvUsrLst", activeUsersList);
					model.addAttribute("funlist", funList);
					model.addAttribute("functionDesc", currentfunction.getFunctionDesc());
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
					model.addAttribute("funmap", funHashmap);
				} 
				else {
					return "redirect:login";
				}
			} else {
				logger.debug(StringUtil.NO_ACCESS_TOKEN);
				red.addFlashAttribute("msg", StringUtil.NO_PERMISSION_FOR_SCREEN);
				return "redirect:notAuthorized";
			}
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/submit-view-active-users", method = RequestMethod.POST)
	public String handle_FACL6000_SACL6000(ModelMap model, @ModelAttribute("aldto") ActivityLogDto actvtylogdto,
			BindingResult result, HttpServletRequest req, RedirectAttributes red) {
		
		int retval;		
		try {
			if (SessionUtil.sessionValid(req.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				Map<String, String> globalsessionmap =  (Map<String, String>) req.getSession(false).getServletContext().getAttribute("sessionUtilMap");
				for (String str : actvtylogdto.getActiveUserList()) {
					if (globalsessionmap.containsKey(str)) {
						globalsessionmap.remove(str);
						retval = activityLogService.removeUserFromActivelist(str);
						if (retval > 0) {
							model.addAttribute("message", StringUtil.SACL6000_SUCCESS);
						}
						else {
							model.addAttribute("message", StringUtil.FAILURE);
						}
					}
					else {
						logger.debug("USER REMOVAL FROM GLOBAL HASHMAP FAILED, NO ENTRY FOUND FOR SELECTED USER");
					}
				}				
			} 
			else {
				return "redirect:login";
			}
		} catch (Exception ex) {
			logger.debug(ex.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return actvtylogdto.getScreen();
	}
	
	@RequestMapping(value = "/getAllFunctionsForSelectedModule", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxcall_method_SACL1000A(@RequestParam("module") String mod) {
		//logger.debug("hey i am here");
		List<Function> funListByModule = funService.listFunctionsByModule(mod);	    
	    return new Gson().toJson(funListByModule);
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
		List<Role> listOfRoles = new ArrayList<Role>();
		listOfRoles = roleService.listAllRoles();
		for (Role r : listOfRoles) {
			userRoleOptions.put(r.getRoleId(), r.getRoleDesc());
		}
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
