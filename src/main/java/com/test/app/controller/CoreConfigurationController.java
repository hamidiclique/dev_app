package com.test.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.app.dto.AtmDefDto;
import com.test.app.dto.AtmMaster;
import com.test.app.dto.TerminalEvent;
import com.test.app.dto.TrxnMonitor;
import com.test.app.dto.ViewAtmDto;
import com.test.app.dto.ViewAuthDto;
import com.test.app.entity.AtmCmdTab;
import com.test.app.entity.AtmCurrencyTab;
import com.test.app.entity.CtimTab;
import com.test.app.entity.CtlaTab;
import com.test.app.entity.CtrTab;
import com.test.app.entity.DefTab;
import com.test.app.entity.DevAttribute;
import com.test.app.entity.DevKeyTab;
import com.test.app.entity.EcfopTab;
import com.test.app.entity.Function;
import com.test.app.entity.MstBranch;
import com.test.app.entity.RtTab;
import com.test.app.entity.TcpTab;
import com.test.app.entity.TmkCompTab;
import com.test.app.entity.TmkCompTabPK;
import com.test.app.entity.TmkReqTab;
import com.test.app.entity.User;
import com.test.app.service.ActivityLogService;
import com.test.app.service.AtmCmdService;
import com.test.app.service.FunctionService;
import com.test.app.service.FunctiongrpService;
import com.test.app.service.FungrpFunMapSercice;
import com.test.app.service.LoginParamService;
import com.test.app.service.ModuleService;
import com.test.app.service.MstBranchService;
import com.test.app.service.PIdSequenceService;
import com.test.app.service.RoleFungrpMapService;
import com.test.app.service.RoleService;
import com.test.app.service.SomeService;
import com.test.app.service.TmkCompSequenceService;
import com.test.app.service.UserService;
import com.test.app.util.DateUtil;
import com.test.app.util.SessionUtil;
import com.test.app.util.StringUtil;
import com.test.app.util.ViewAuthUtil;

@Controller
public class CoreConfigurationController {

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
	@Autowired
	MstBranchService branchService;
	@Autowired
	PIdSequenceService pidSeqService;
	@Autowired
	TmkCompSequenceService tmkCompSeqService;
	@Autowired
	SomeService someService;
	@Autowired
	AtmCmdService atmCmdService;
	@Autowired
	private Environment env;

	@Resource(name = "modHashmap")
	private Map<String, String> modHashmap;
	@Resource(name = "funHashmap")
	private Map<String, String> funHashmap;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/atm-configuration", method = RequestMethod.GET)
	public String view_FCFG3000_SCFG3000(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		Map<String, List<String>> funModMap = new LinkedHashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>(), funOptList = new ArrayList<String>(), tempfunlist;
		List<Function> funListDb = new ArrayList<Function>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);

					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false)
								.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						tempfunlist = funModMap.get(mid);
						funListDb = funService.listFunctionsByModule(mid);
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
						funIter = funListDb.iterator();
						while (funIter.hasNext()) {
							Function optelem = funIter.next();
							if (Integer.parseInt(optelem.getFunctionFlag()) == 0
									&& optelem.getFunctionId().length() < 9) {
								if (tempfunlist.contains(optelem.getFunctionId())) {
									funOptList.add(optelem.getFunctionId().trim());
								}
							}
						}
						model.addAttribute("funOptList", funOptList);
					}
					model.addAttribute("functionDesc", currentfunction.getFunctionDesc());
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
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

	@RequestMapping(value = "/submit-add-new-atm", method = RequestMethod.POST)
	public String handle_FCFG3200_SCFG3200(@ModelAttribute("atmDef") AtmDefDto atmDef, BindingResult result,
			ModelMap model, HttpServletRequest req, RedirectAttributes red) {
		int retval = 0;
		AtmMaster atmMaster;
		try {
			if (SessionUtil.sessionValid(req.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				retval = 0;
				logger.debug(atmDef);
				atmMaster = mapUserInputToAtmMaster(atmDef);
				int nora = someService.addNewAtmMachine(atmMaster);
				logger.debug(nora);
				if (retval > 0) {
					model.addAttribute("message", StringUtil.SCFG3100_SUCCESS);
				} else {
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
	@RequestMapping(value = "/current-terminal-events", method = RequestMethod.GET)
	public String view_FCFG3600_SCFG3600(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {

		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>(), tempfunlist;
		List<Function> funListDb = new ArrayList<Function>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false)
								.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						tempfunlist = funModMap.get(mid);
						funListDb = funService.listFunctionsByModule(mid);
						Iterator<Function> funIter = funListDb.iterator();
						while (funIter.hasNext()) {
							Function funelem = funIter.next();
							if (Integer.parseInt(funelem.getFunctionFlag()) == 0
									&& funelem.getFunctionId().length() < 9) {
								if (tempfunlist.contains(funelem.getFunctionId())) {
									funList.add(funelem.getFunctionId().trim());
								}
							}
						}
						model.addAttribute("funlist", funList);
					}
					String parentfunction = fid.substring(0, 5) + "000";
					String parentscreen = parentfunction.replaceFirst("F", "S");
					String url = env.getProperty(parentfunction + "." + parentscreen);
					model.addAttribute("url", url);
					model.addAttribute("funlist", funList);
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
					model.addAttribute("parentfunction", parentfunction);
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
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}

	@RequestMapping(value = "/transactions-monitoring", method = RequestMethod.GET)
	public String view_FCFG3400_SCFG3400V(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		List<TrxnMonitor> trxnMonitoringList = new ArrayList<TrxnMonitor>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					trxnMonitoringList.add(new TrxnMonitor("4840970123456789", "IBFT", "BDT", "900", "23-MAY-2018",
							"14:22:54", "590655", "094", "Trxn is not allowed", "NPSB", "HOST", "90001000"));
					trxnMonitoringList.add(new TrxnMonitor("4840970123456789", "IBFT", "BDT", "850", "23-MAY-2018",
							"14:24:13", "591222", "094", "Trxn is not allowed", "NPSB", "HOST", "90001000"));
					trxnMonitoringList.add(new TrxnMonitor("1501102659072001", "GDC Inter bank", "BDT", "1500",
							"23-MAY-2018", "14:25:01", "868840", "000", "Approved", "IB", "NPSB", "00600009"));
					trxnMonitoringList.add(new TrxnMonitor("1501102659072001", "GDC Inter bank", "BDT", "2000",
							"23-MAY-2018", "14:26:30", "868842", "000", "Approved", "IB", "NPSB", "00600009"));
					trxnMonitoringList.add(new TrxnMonitor("4321491501000108", "Balance Inquiry", "", "", "23-MAY-2018",
							"13:37:20", "297888", "094", "Trxn is not allowed", "ATM", "HOST", "0490"));
					trxnMonitoringList.add(new TrxnMonitor("4321491501000108", "Withdrawal", "BDT", "4000",
							"23-MAY-2018", "13:57:51", "297892", "094", "Trxn is not allowed", "ATM", "HOST", "0490"));
					trxnMonitoringList.add(new TrxnMonitor("4321491501000108", "Mini Statement", "", "", "23-MAY-2018",
							"13:51:33", "297881", "014", "Warm Card", "ATM", "HOST", "0490"));
					trxnMonitoringList
							.add(new TrxnMonitor("1501102659072001", "GDC Inter bank", "BDT", "1500", "23-MAY-2018",
									"14:25:08", "888636", "052", "No checking account", "IB", "NPSB", "00600009"));
					model.addAttribute("trxnmonitorlist", trxnMonitoringList);
					model.addAttribute("functionDesc", "Transactions Monitoring");
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

	@RequestMapping(value = "/view-terminal-events", method = RequestMethod.GET)
	public String view_FCFG3600_SCFG3600V(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		List<TerminalEvent> terminalEventList = new ArrayList<TerminalEvent>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					terminalEventList.add(new TerminalEvent("98588247", "0490", "TEST NCR BIT DHAKA BD",
							"06-MAY-2018 20:07:34", "ATM", "OPEN", "Operator issued open command to ATM"));
					terminalEventList.add(new TerminalEvent("98588246", "0490", "TEST NCR BIT DHAKA BD",
							"06-MAY-2018 20:07:32", "ATM", "LOAD TIME", "SWITCH_POWERUP"));
					terminalEventList
							.add(new TerminalEvent("98588242", "0490", "TEST NCR BIT DHAKA BD", "06-MAY-2018 20:07:29",
									"ATM", "CLOSED", "ATM is not configured or has lost configuration"));
					terminalEventList.add(new TerminalEvent("98588241", "0002", "TEST TTT Wincor EMV Lab BD",
							"06-MAY-2018 20:07:23", "ATM", "OPEN", "Operator issued open command to ATM"));
					terminalEventList.add(
							new TerminalEvent("98588231", "0002", "TEST TTT Wincor EMV Lab BD", "06-MAY-2018 20:07:20",
									"ATM", "CLOSED", "ATM is not configured or has lost configuration"));
					terminalEventList.add(new TerminalEvent("98588228", "0490", "TEST NCR BIT DHAKA BD",
							"06-MAY-2018 20:05:18", "ATM", "STATUS_UNKNOWN", "State unknown but link up"));
					model.addAttribute("terminalEventList", terminalEventList);
					model.addAttribute("functionDesc", "Terminal Events Monitoring");
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
	@RequestMapping(value = "/transaction-enquiry", method = RequestMethod.GET)
	public String view_FCFG3400_SCFG3400(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {

		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>(), tempfunlist;
		List<Function> funListDb = new ArrayList<Function>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false)
								.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						tempfunlist = funModMap.get(mid);
						funListDb = funService.listFunctionsByModule(mid);
						Iterator<Function> funIter = funListDb.iterator();
						while (funIter.hasNext()) {
							Function funelem = funIter.next();
							if (Integer.parseInt(funelem.getFunctionFlag()) == 0
									&& funelem.getFunctionId().length() < 9) {
								if (tempfunlist.contains(funelem.getFunctionId())) {
									funList.add(funelem.getFunctionId().trim());
								}
							}
						}
						model.addAttribute("funlist", funList);
					}
					String parentfunction = fid.substring(0, 5) + "000";
					String parentscreen = parentfunction.replaceFirst("F", "S");
					String url = env.getProperty(parentfunction + "." + parentscreen);
					model.addAttribute("url", url);
					model.addAttribute("funlist", funList);
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
					model.addAttribute("parentfunction", parentfunction);
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
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/atm-summary", method = RequestMethod.GET)
	public String view_FCFG3300_SCFG3300(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {

		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>(), tempfunlist;
		List<Function> funListDb = new ArrayList<Function>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false)
								.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						tempfunlist = funModMap.get(mid);
						funListDb = funService.listFunctionsByModule(mid);
						Iterator<Function> funIter = funListDb.iterator();
						while (funIter.hasNext()) {
							Function funelem = funIter.next();
							if (Integer.parseInt(funelem.getFunctionFlag()) == 0
									&& funelem.getFunctionId().length() < 9) {
								if (tempfunlist.contains(funelem.getFunctionId())) {
									funList.add(funelem.getFunctionId().trim());
								}
							}
						}
						model.addAttribute("funlist", funList);
					}
					String parentfunction = fid.substring(0, 5) + "000";
					String parentscreen = parentfunction.replaceFirst("F", "S");
					String url = env.getProperty(parentfunction + "." + parentscreen);
					model.addAttribute("url", url);
					model.addAttribute("funlist", funList);
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
					model.addAttribute("parentfunction", fid.substring(0, 5) + "000");
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
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/view-dashboard", method = RequestMethod.GET)
	public String view_FCFG3100_SCFG3100(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {

		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>(), tempfunlist;
		List<Function> funListDb = new ArrayList<Function>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false)
								.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						tempfunlist = funModMap.get(mid);
						funListDb = funService.listFunctionsByModule(mid);
						Iterator<Function> funIter = funListDb.iterator();
						while (funIter.hasNext()) {
							Function funelem = funIter.next();
							if (Integer.parseInt(funelem.getFunctionFlag()) == 0
									&& funelem.getFunctionId().length() < 9) {
								if (tempfunlist.contains(funelem.getFunctionId())) {
									funList.add(funelem.getFunctionId().trim());
								}
							}
						}
						model.addAttribute("funlist", funList);
					}
					String parentfunction = fid.substring(0, 5) + "000";
					String parentscreen = parentfunction.replaceFirst("F", "S");
					String url = env.getProperty(parentfunction + "." + parentscreen);
					model.addAttribute("url", url);
					model.addAttribute("funlist", funList);
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("parentfunction", fid.substring(0, 5) + "000");
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
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/view-atm-details", method = RequestMethod.GET)
	public String view_FCFG3500_SCFG3500V(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {

		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>(), tempfunlist;
		List<Function> funListDb = new ArrayList<Function>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false)
								.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						tempfunlist = funModMap.get(mid);
						funListDb = funService.listFunctionsByModule(mid);
						Iterator<Function> funIter = funListDb.iterator();
						while (funIter.hasNext()) {
							Function funelem = funIter.next();
							if (Integer.parseInt(funelem.getFunctionFlag()) == 0
									&& funelem.getFunctionId().length() < 9) {
								if (tempfunlist.contains(funelem.getFunctionId())) {
									funList.add(funelem.getFunctionId().trim());
								}
							}
						}
						model.addAttribute("funlist", funList);
					}
					model.addAttribute("funlist", funList);
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
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/update-atm-details", method = RequestMethod.GET)
	public String view_FCFG3500_SCFG3500U(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, @RequestParam String pid, RedirectAttributes red, HttpServletRequest request) {

		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>(), tempfunlist;
		List<Function> funListDb = new ArrayList<Function>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false)
								.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						tempfunlist = funModMap.get(mid);
						funListDb = funService.listFunctionsByModule(mid);
						Iterator<Function> funIter = funListDb.iterator();
						while (funIter.hasNext()) {
							Function funelem = funIter.next();
							if (Integer.parseInt(funelem.getFunctionFlag()) == 0
									&& funelem.getFunctionId().length() < 9) {
								if (tempfunlist.contains(funelem.getFunctionId())) {
									funList.add(funelem.getFunctionId().trim());
								}
							}
						}
						model.addAttribute("funlist", funList);
					}

					// retrieve relevant atm row
					// first check the cmd status
					// if cmd status is 0 / 1, then command is yet to be completed
					// in such case options (based on cmd_code) should be disabled
					// if cmd status is 2, then given command is successful
					// options can be visible

					LinkedHashMap<String, Integer> cmdcodemap = new LinkedHashMap<String, Integer>();
					cmdcodemap.put("1-Open ATM", 0);
					cmdcodemap.put("2-Close ATM", 0);
					cmdcodemap.put("3-Load Formats", 0);
					cmdcodemap.put("4-Load States", 0);
					cmdcodemap.put("5-Load Screens", 0);
					cmdcodemap.put("6-Load configuration parameters", 0);
					cmdcodemap.put("7-Load FIT Table", 0);
					cmdcodemap.put("9-Load New TPK", 0);
					cmdcodemap.put("11-Load Current Date and Time", 0);
					cmdcodemap.put("38-Load EMV AID Table", 0);
					cmdcodemap.put("39-Load EMV Currency Table", 0);
					cmdcodemap.put("40-Load EMV Transaction Type Table", 0);
					cmdcodemap.put("41-Load EMV Terminal Data", 0);
					cmdcodemap.put("63-Load EJ Options and Timers", 0);
					cmdcodemap.put("13-Request ATM To Send Supply Counters", 0);
					cmdcodemap.put("52-Request ATM To Send Enhanced Supply Counters", 0);
					cmdcodemap.put("53-Request ATM To Run Self-Test", 0);
					int code;
					AtmCmdTab atmcmdobj = atmCmdService.getAtmCmdDetailsByPid(pid);
					int status = atmcmdobj.getCmdStatus().intValue();
					switch (status) {
					case 0:
					case 1:
						System.out.println("Command request submitted/ Command sent to ATM");
						code = atmcmdobj.getCmdCode().intValue();
						switch (code) {
						case 1:
							System.out.println("Open ATM");
							// show only ATM closing option in disabled state
							cmdcodemap.replace("2-Close ATM", 2);
							break;
						//case 2:
						default:	
							System.out.println("Closed ATM");
							// show all options in disabled state
							cmdcodemap.replace("1-Open ATM", 2);
							cmdcodemap.replace("3-Load Formats", 2);
							cmdcodemap.replace("4-Load States", 2);
							cmdcodemap.replace("5-Load Screens", 2);
							cmdcodemap.replace("6-Load configuration parameters", 2);
							cmdcodemap.replace("7-Load FIT Table", 2);
							cmdcodemap.replace("9-Load New TPK", 2);
							cmdcodemap.replace("11-Load Current Date and Time", 2);
							cmdcodemap.replace("38-Load EMV AID Table", 2);
							cmdcodemap.replace("39-Load EMV Currency Table", 2);
							cmdcodemap.replace("40-Load EMV Transaction Type Table", 2);
							cmdcodemap.replace("41-Load EMV Terminal Data", 2);
							cmdcodemap.replace("63-Load EJ Options and Timers", 2);
							cmdcodemap.replace("13-Request ATM To Send Supply Counters", 2);
							cmdcodemap.replace("52-Request ATM To Send Enhanced Supply Counters", 2);
							cmdcodemap.replace("53-Request ATM To Run Self-Test", 2);
							break;
/*						default:
							System.out.println("Invalid Code");
							break;*/
						}
						break;
					case 2:
						System.out.println("Command completed");
						code = atmcmdobj.getCmdCode().intValue();
						switch (code) {
						case 1:
							System.out.println("Open ATM");
							// show only ATM closing option
							cmdcodemap.replace("2-Close ATM", 1);
							break;
						//case 2:
						default:	
							System.out.println("Closed ATM");
							// show all options
							cmdcodemap.replace("1-Open ATM", 1);
							cmdcodemap.replace("3-Load Formats", 1);
							cmdcodemap.replace("4-Load States", 1);
							cmdcodemap.replace("5-Load Screens", 1);
							cmdcodemap.replace("6-Load configuration parameters", 1);
							cmdcodemap.replace("7-Load FIT Table", 1);
							cmdcodemap.replace("9-Load New TPK", 1);
							cmdcodemap.replace("11-Load Current Date and Time", 1);
							cmdcodemap.replace("38-Load EMV AID Table", 1);
							cmdcodemap.replace("39-Load EMV Currency Table", 1);
							cmdcodemap.replace("40-Load EMV Transaction Type Table", 1);
							cmdcodemap.replace("41-Load EMV Terminal Data", 1);
							cmdcodemap.replace("63-Load EJ Options and Timers", 1);
							cmdcodemap.replace("13-Request ATM To Send Supply Counters", 1);
							cmdcodemap.replace("52-Request ATM To Send Enhanced Supply Counters", 1);
							cmdcodemap.replace("53-Request ATM To Run Self-Test", 1);
							break;
						/*default:
							System.out.println("Invalid Code");
							break;*/
						}
						break;
					default:
						System.out.println("Invalid");
						break;
					}
					model.addAttribute("cmdcodemap", cmdcodemap);

					model.addAttribute("funlist", funList);
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("pid", pid);
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
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/view-active-atm", method = RequestMethod.GET)
	public String view_FCFG3500_SCFG3500(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		List<ViewAtmDto> activeAtmList = new ArrayList<ViewAtmDto>();
		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>(), tempfunlist;
		List<Function> funListDb = new ArrayList<Function>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					activeAtmList = someService.getAllActiveAtm();
					Function currentfunction = funService.getFunctionById(fid);
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false)
								.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						tempfunlist = funModMap.get(mid);
						funListDb = funService.listFunctionsByModule(mid);
						Iterator<Function> funIter = funListDb.iterator();
						while (funIter.hasNext()) {
							Function funelem = funIter.next();
							if (Integer.parseInt(funelem.getFunctionFlag()) == 0
									&& funelem.getFunctionId().length() < 9) {
								if (tempfunlist.contains(funelem.getFunctionId())) {
									funList.add(funelem.getFunctionId().trim());
								}
							}
						}
						model.addAttribute("funlist", funList);
					}
					String parentfunction = fid.substring(0, 5) + "000";
					String parentscreen = parentfunction.replaceFirst("F", "S");
					String url = env.getProperty(parentfunction + "." + parentscreen);
					model.addAttribute("url", url);
					model.addAttribute("funlist", funList);
					model.addAttribute("activeAtmList", activeAtmList);
					model.addAttribute("listsize", activeAtmList.size());
					model.addAttribute("parentfunction", fid.substring(0, 5) + "000");
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
		} catch (Exception e) {
			logger.debug(e.toString());
			red.addFlashAttribute("cause", StringUtil.UNKNOW_REASON);
			return "redirect:login";
		}
		return sid;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/add-new-atm", method = RequestMethod.GET)
	public String view_FCFG3200_SCFG3200(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		Map<String, List<String>> funModMap = new LinkedHashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>(), tempfunlist;
		List<Function> funListDb = new ArrayList<Function>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);
					AtmDefDto atmDefDto = new AtmDefDto();

					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false)
								.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						tempfunlist = funModMap.get(mid);
						funListDb = funService.listFunctionsByModule(mid);
						Iterator<Function> funIter = funListDb.iterator();
						while (funIter.hasNext()) {
							Function funelem = funIter.next();
							if (Integer.parseInt(funelem.getFunctionFlag()) == 0
									&& funelem.getFunctionId().length() < 9) {
								if (tempfunlist.contains(funelem.getFunctionId())) {
									funList.add(funelem.getFunctionId().trim());
								}
							}
						}
						model.addAttribute("funlist", funList);
					}
					String parentfunction = fid.substring(0, 5) + "000";
					String parentscreen = parentfunction.replaceFirst("F", "S");
					String url = env.getProperty(parentfunction + "." + parentscreen);
					model.addAttribute("url", url);
					model.addAttribute("atmDef", atmDefDto);
					model.addAttribute("functionDesc", currentfunction.getFunctionDesc());
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("parentfunction", fid.substring(0, 5) + "000");
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
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

	@RequestMapping(value = "/updateCmdStatusForSelectedATM", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxcall_method_SCFG3500U(@RequestParam("cmdcode") String cmdcode, @RequestParam("pid") String pid,
			HttpServletRequest request) {
		logger.debug("pid: " + pid + "cmdcode: " + cmdcode);
		String username = "";
		HttpSession session = request.getSession(false);
		if (session != null) {
			User temp = (User) session.getAttribute(StringUtil.USER_SESSION);
			if (temp != null) {
				username = temp.getUserName();
			}
		}
		String timestamp = DateUtil.getCurrentTimestampToString();
		AtmCmdTab atmcmd = new AtmCmdTab();
		atmcmd.setPid(new Long(pid));
		atmcmd.setCmdDatetime(timestamp);
		atmcmd.setCmdIssuer(username);
		atmcmd.setCmdCode(new BigDecimal(cmdcode));
		atmcmd.setCmdStatus(new BigDecimal(0));

		int nora = 0;
		try {
			nora = atmCmdService.updateAtmCmdInfoByPid(atmcmd);
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		if (nora > 0) 
			return "200";
		else
			return "500";
	}

	private AtmMaster mapUserInputToAtmMaster(AtmDefDto atmDef) {
		// TODO Auto-generated method stub
		AtmMaster atmm = new AtmMaster();

		RtTab atm_rttab = populateRtTab(atmDef);
		TcpTab atm_tcptab = populateTcpTab(atmDef, atm_rttab.getPid());
		DefTab atm_deftab = populateDefTab(atmDef, atm_rttab.getPid());
		CtlaTab atm_ctlatab = populateCtlaTab(atmDef, atm_rttab.getPid());
		CtrTab atm_ctrtab = populateCtrTab(atmDef, atm_rttab.getPid());
		List<CtimTab> atm_ctimtablst = populateCtimTab(atmDef, atm_rttab.getPid());
		List<AtmCurrencyTab> atm_currencytablst = populateAtmCurrencyTab(atmDef, atm_rttab.getPid());
		List<EcfopTab> atm_ecfoptablst = populateEcfopTab(atmDef, atm_rttab.getPid());
		DevAttribute atm_devattr = populateDevAttr(atmDef, atm_rttab.getPid());
		DevKeyTab atm_devkeytab = populateDevKeyTab(atmDef, atm_rttab.getPid());
		TmkCompTab atm_tmkcomptab = populateTmkCompTab(atmDef, atm_rttab.getPid());
		// List<TmkCompTab> atm_tmkcomptablst = populateTmkCompTab(atmDef,
		// atm_rttab.getPid());
		TmkReqTab atm_tmkreqtab = populateTmkReqTab(atmDef, atm_rttab.getPid());

		atmm.setRttab(atm_rttab);
		atmm.setTcptab(atm_tcptab);
		atmm.setDeftab(atm_deftab);
		atmm.setCtlatab(atm_ctlatab);
		atmm.setCtrtab(atm_ctrtab);
		atmm.setCtimtablist(atm_ctimtablst);
		atmm.setAtmcurrtablist(atm_currencytablst);
		atmm.setEcfoptablist(atm_ecfoptablst);
		atmm.setDevattr(atm_devattr);
		atmm.setDevkeytab(atm_devkeytab);
		atmm.setTmkcomptab(atm_tmkcomptab);
		atmm.setTmkreqtab(atm_tmkreqtab);

		logger.debug(atmm);

		return atmm;
	}

	private TmkReqTab populateTmkReqTab(AtmDefDto atmdef, Long pid) {
		// TODO Auto-generated method stub
		TmkReqTab tmkReqTab = new TmkReqTab();
		tmkReqTab.setOrgdev(pid);
		tmkReqTab.setBranchc(atmdef.getBranch());
		tmkReqTab.setStatus("P");
		return tmkReqTab;
	}

	private TmkCompTab populateTmkCompTab(AtmDefDto atmdef, Long pid) {
		int seq = 0;
		TmkCompTab tmkCompTab = new TmkCompTab();
		TmkCompTabPK tctpk = new TmkCompTabPK();
		try {
			seq = tmkCompSeqService.getNextTmkCompSeqValue();
			tctpk.setSequence(new Long(seq));
			tctpk.setOrgdev(pid);
			tmkCompTab.setId(tctpk);
			tmkCompTab.setFormed("N");
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return tmkCompTab;
	}

	private DevKeyTab populateDevKeyTab(AtmDefDto atmdef, Long pid) {
		// TODO Auto-generated method stub
		DevKeyTab devKeyTab = new DevKeyTab();
		devKeyTab.setPciCompliant(new BigDecimal(atmdef.getPciCompliant()));
		devKeyTab.setPinblkFmt(atmdef.getPinblkFmt());
		devKeyTab.setKeyLength(new BigDecimal(32));
		devKeyTab.setPid(pid);
		devKeyTab.setDirection("-");
		return devKeyTab;
	}

	private DevAttribute populateDevAttr(AtmDefDto atmdef, Long pid) {
		// TODO Auto-generated method stub
		DevAttribute devAttribute = new DevAttribute();
		devAttribute.setAttribute(new BigDecimal(atmdef.getAttribute()));
		devAttribute.setPid(pid);
		return devAttribute;
	}

	private List<EcfopTab> populateEcfopTab(AtmDefDto atmdef, Long pid) {
		// TODO Auto-generated method stub
		List<EcfopTab> ecfoptablst = new ArrayList<EcfopTab>();
		EcfopTab ecfoptab;
		ecfoptab = createEcfopObj(1, 1, pid);
		ecfoptablst.add(ecfoptab);
		ecfoptab = createEcfopObj(12, 1, pid);
		ecfoptablst.add(ecfoptab);
		ecfoptab = createEcfopObj(24, 1, pid);
		ecfoptablst.add(ecfoptab);
		ecfoptab = createEcfopObj(33, 1, pid);
		ecfoptablst.add(ecfoptab);
		ecfoptab = createEcfopObj(36, 1, pid);
		ecfoptablst.add(ecfoptab);
		ecfoptab = createEcfopObj(37, 1, pid);
		ecfoptablst.add(ecfoptab);
		ecfoptab = createEcfopObj(69, 14, pid);
		ecfoptablst.add(ecfoptab);
		ecfoptab = createEcfopObj(70, 1, pid);
		ecfoptablst.add(ecfoptab);

		return ecfoptablst;
	}

	private EcfopTab createEcfopObj(int eop_no, int eopt, Long pid) {
		// TODO Auto-generated method stub
		EcfopTab ecfoptabvar = new EcfopTab();
		ecfoptabvar.setEopNo(new BigDecimal(eop_no));
		ecfoptabvar.setEopt(new BigDecimal(eopt));
		ecfoptabvar.setPid(pid);
		return ecfoptabvar;
	}

	private List<AtmCurrencyTab> populateAtmCurrencyTab(AtmDefDto atmdef, Long pid) {
		// TODO Auto-generated method stub
		List<AtmCurrencyTab> atmcurrtablst = new ArrayList<AtmCurrencyTab>();
		int num_rows = 5;
		AtmCurrencyTab atmCurrencyTab;
		for (int i = 1; i < num_rows; i++) {
			atmCurrencyTab = new AtmCurrencyTab();
			atmCurrencyTab.setPid(pid);
			atmCurrencyTab.setCanisterType(new BigDecimal(i));
			atmCurrencyTab.setIsoCurrencyType(new BigDecimal(50));
			atmcurrtablst.add(atmCurrencyTab);
		}
		return atmcurrtablst;
	}

	private List<CtimTab> populateCtimTab(AtmDefDto atmdef, Long pid) {
		// TODO Auto-generated method stub
		List<CtimTab> ctimtablst = new ArrayList<CtimTab>();
		int num_rows = 11;
		CtimTab ctimtab;
		for (int i = 0; i < num_rows; i++) {
			switch (i) {
			case 0:
			case 1:
			case 2:
				ctimtab = new CtimTab();
				ctimtab.setPid(pid);
				ctimtab.setTimrLen(new BigDecimal(15));
				ctimtab.setTimrNo(new BigDecimal(i));
				ctimtablst.add(ctimtab);
				break;
			case 3:
			case 4:
				ctimtab = new CtimTab();
				ctimtab.setPid(pid);
				ctimtab.setTimrLen(new BigDecimal(60));
				ctimtab.setTimrNo(new BigDecimal(i));
				ctimtablst.add(ctimtab);
				break;
			case 5:
				ctimtab = new CtimTab();
				ctimtab.setPid(pid);
				ctimtab.setTimrLen(new BigDecimal(20));
				ctimtab.setTimrNo(new BigDecimal(i));
				ctimtablst.add(ctimtab);
				break;
			case 6:
				ctimtab = new CtimTab();
				ctimtab.setPid(pid);
				ctimtab.setTimrLen(new BigDecimal(10));
				ctimtab.setTimrNo(new BigDecimal(i));
				ctimtablst.add(ctimtab);
				break;
			case 7:
				ctimtab = new CtimTab();
				ctimtab.setPid(pid);
				ctimtab.setTimrLen(new BigDecimal(1));
				ctimtab.setTimrNo(new BigDecimal(i));
				ctimtablst.add(ctimtab);
				break;
			case 8:
			case 9:
				ctimtab = new CtimTab();
				ctimtab.setPid(pid);
				ctimtab.setTimrLen(new BigDecimal(30));
				ctimtab.setTimrNo(new BigDecimal(i));
				ctimtablst.add(ctimtab);
				break;
			default:
				ctimtab = new CtimTab();
				ctimtab.setPid(pid);
				ctimtab.setTimrLen(new BigDecimal(0));
				ctimtab.setTimrNo(new BigDecimal(i));
				ctimtablst.add(ctimtab);
				break;
			}
		}
		return ctimtablst;
	}

	private CtrTab populateCtrTab(AtmDefDto atmdef, Long pid) {
		// TODO Auto-generated method stub
		CtrTab ctrtab = new CtrTab();
		try {
			BigDecimal zero = new BigDecimal(0);
			ctrtab.setC1StartBills(zero);
			ctrtab.setC2StartBills(zero);
			ctrtab.setC3StartBills(zero);
			ctrtab.setC4StartBills(zero);
			ctrtab.setCC1bills(zero);
			ctrtab.setCC1brej(zero);
			ctrtab.setCC2bills(zero);
			ctrtab.setCC2brej(zero);
			ctrtab.setCC3bills(zero);
			ctrtab.setCC3brej(zero);
			ctrtab.setCC4bills(zero);
			ctrtab.setCC4brej(zero);
			ctrtab.setCCacsha(zero);
			ctrtab.setCCainq(zero);
			ctrtab.setCCaxfer(zero);
			ctrtab.setCCcapt(zero);
			ctrtab.setCCckbk(zero);
			ctrtab.setCCdep(zero);
			ctrtab.setCCdepac(zero);
			ctrtab.setCCfrf("0");
			ctrtab.setCCfseg(zero);
			ctrtab.setCCicsha(zero);
			ctrtab.setCCiinq(zero);
			ctrtab.setCCinq(zero);
			ctrtab.setCCixfer(zero);
			ctrtab.setCCpmt(zero);
			ctrtab.setCCrect(zero);
			ctrtab.setCCsvcload(zero);
			ctrtab.setCCsvcunload(zero);
			ctrtab.setCCtrans(zero);
			ctrtab.setCCwdlLoc(zero);
			ctrtab.setCCwdlUs(zero);
			ctrtab.setCCxfer(zero);
			ctrtab.setCTacshaLoc(zero);
			ctrtab.setCTacshaUs(zero);
			ctrtab.setCTdep(zero);
			ctrtab.setCTdepc(zero);
			ctrtab.setCTdeps(zero);
			ctrtab.setCTicsha(zero);
			ctrtab.setCTpmt(zero);
			ctrtab.setCTpmtl(zero);
			ctrtab.setCTpmtmc(zero);
			ctrtab.setCTsvcload(zero);
			ctrtab.setCTsvcunload(zero);
			ctrtab.setCTwdlcLoc(zero);
			ctrtab.setCTwdlcUs(zero);
			ctrtab.setCTwdlLoc(zero);
			ctrtab.setCTwdlsLoc(zero);
			ctrtab.setCTwdlsUs(zero);
			ctrtab.setCTwdlUs(zero);
			ctrtab.setPid(pid);
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return ctrtab;
	}

	private CtlaTab populateCtlaTab(AtmDefDto atmdef, Long pid) {
		// TODO Auto-generated method stub
		CtlaTab ctlatab = new CtlaTab();
		try {
			BigDecimal zero = new BigDecimal(0);
			ctlatab.setAcct1tp(zero);
			ctlatab.setAcct2tp(zero);
			ctlatab.setAreject(zero);
			ctlatab.setClosed(zero);
			ctlatab.setCmdwt(zero);
			ctlatab.setCtype(zero);
			ctlatab.setDisab1(zero);
			ctlatab.setDisab2(zero);
			ctlatab.setHcmore(zero);
			ctlatab.setHcsent(zero);
			ctlatab.setHcstart(zero);
			ctlatab.setHreject(zero);
			// ctlatab.setLacc2();
			// ctlatab.setLacci();
			ctlatab.setLamt(zero);
			ctlatab.setLauth(zero);
			ctlatab.setLcardtype(zero);
			ctlatab.setLdisp(zero);
			// ctlatab.setLpani();
			ctlatab.setLscreen(zero);
			ctlatab.setLutrnno(zero);
			ctlatab.setMemNo(zero);
			ctlatab.setMiserr(zero);
			ctlatab.setMisFlag1(zero);
			ctlatab.setMisFlag2(zero);
			ctlatab.setMisFld1(zero);
			ctlatab.setMisFld2(zero);
			// ctlatab.setMsgCoordNum();
			ctlatab.setNonReadies(zero);
			ctlatab.setNtrans(zero);
			ctlatab.setOprob(zero);
			ctlatab.setOpstat(new BigDecimal(128));
			ctlatab.setOutctr(zero);
			ctlatab.setOuttot(zero);
			ctlatab.setPid(pid);
			ctlatab.setProcstat(zero);
			ctlatab.setRencon(zero);
			ctlatab.setRespno(zero);
			ctlatab.setSwTab(new BigDecimal(7));
			ctlatab.setSwSkey(zero);
			ctlatab.setSwReq(new BigDecimal(2));
			ctlatab.setSwLskey(new BigDecimal(999));
			ctlatab.setSwEkey(zero);
			ctlatab.setSwConfig(zero);
			ctlatab.setStatusMsg("OPEN");
			ctlatab.setStat22(zero);
			ctlatab.setStat21(zero);
			ctlatab.setStat19(zero);
			ctlatab.setStat18(zero);
			ctlatab.setStat17(zero);
			ctlatab.setStat16(zero);
			ctlatab.setStat15(zero);
			ctlatab.setStat14(zero);
			ctlatab.setStat13(zero);
			ctlatab.setStat12(zero);
			ctlatab.setStat110(zero);
			ctlatab.setStat11(zero);
			ctlatab.setTtype(zero);
			ctlatab.setTto(zero);
			ctlatab.setWarn1(zero);
			ctlatab.setWarn2(zero);
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return ctlatab;
	}

	private DefTab populateDefTab(AtmDefDto atmdef, Long pid) {
		// TODO Auto-generated method stub
		DefTab deftab = new DefTab();
		try {
			deftab.setAcct(atmdef.getAcct());
			deftab.setBranch(new BigDecimal(atmdef.getBranch()));
			deftab.setBwarn(new BigDecimal(100));
			deftab.setCircuit(atmdef.getCircuit());
			deftab.setCity(atmdef.getCity());
			deftab.setD1bill(new BigDecimal(4000));
			deftab.setD1type(new BigDecimal(1));
			deftab.setD1val(new BigDecimal(atmdef.getD1val()));
			deftab.setD2bill(new BigDecimal(4000));
			deftab.setD2type(new BigDecimal(2));
			deftab.setD2val(new BigDecimal(atmdef.getD2val()));
			deftab.setD3bill(new BigDecimal(4000));
			deftab.setD3type(new BigDecimal(3));
			deftab.setD3val(new BigDecimal(atmdef.getD3val()));
			deftab.setD4bill(new BigDecimal(4000));
			deftab.setD4type(new BigDecimal(4));
			deftab.setD4val(new BigDecimal(atmdef.getD4val()));
			deftab.setDwarn(new BigDecimal(100));
			deftab.setEcp("Y");
			deftab.setGmtOffset(new BigDecimal(6));
			deftab.setId(pid);
			deftab.setLoader(atmdef.getLoader());
			deftab.setLuno(new BigDecimal(788));
			deftab.setMacing(new BigDecimal(0));
			deftab.setNumdisp(new BigDecimal(1));
			deftab.setRec(new BigDecimal(0));
			deftab.setRjwarn(new BigDecimal(100));
			deftab.setRwarn(new BigDecimal(50));
			deftab.setSecurityMod(new BigDecimal(1));
			deftab.setState(atmdef.getState());
			deftab.setStreet(atmdef.getStreet());
			deftab.setTchar(atmdef.getTchar());
			deftab.setTermchar(new BigDecimal(1));
			deftab.setTid("TERMINAL");
			deftab.setTime1("00:00");
			deftab.setTime2("23:59");
			deftab.setTime3("00:00");
			deftab.setTime4("23:59");
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return deftab;
	}

	private TcpTab populateTcpTab(AtmDefDto atmdef, Long pid) {
		// TODO Auto-generated method stub
		TcpTab tcptab = new TcpTab();
		try {
			tcptab.setPid(pid);
			tcptab.setDependsOn(new BigDecimal(-1));
			tcptab.setFormat(atmdef.getFormat());
			tcptab.setHeaderLen(new BigDecimal(4));
			tcptab.setInitiator(atmdef.getInitiator());
			tcptab.setLocalPort(atmdef.getLocalPort());
			tcptab.setMachine(atmdef.getMachine());
			tcptab.setPingCheck(new BigDecimal(1));
			tcptab.setRemoteAddress(atmdef.getRemoteAddress());
			tcptab.setRemotePort(atmdef.getRemotePort());
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return tcptab;
	}

	private RtTab populateRtTab(AtmDefDto atmdef) {
		// TODO Auto-generated method stub
		int pid = 0;
		RtTab rttab = new RtTab();
		try {
			pid = pidSeqService.getNextPIdSeqValue();
			rttab.setPid(new Long(pid));
			rttab.setDatax(atmdef.getDatax());
			rttab.setProto(atmdef.getProto());
			rttab.setEtype(new BigDecimal(1));
			rttab.setOwner(new BigDecimal(20));
			rttab.setFormater(new BigDecimal(13));
			rttab.setMonitor(new BigDecimal(14));
			rttab.setOffln(new BigDecimal(1));
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		}
		return rttab;
	}

	@ModelAttribute("dataxOptions")
	protected Map<String, String> getDataxOptions() {
		Map<String, String> dataxOptions = new LinkedHashMap<String, String>();
		dataxOptions.put("NCR", "NCR");
		return dataxOptions;
	}

	@ModelAttribute("protoOptions")
	protected Map<String, String> getProtoOptions() {
		Map<String, String> protoOptions = new LinkedHashMap<String, String>();
		protoOptions.put("TCP", "TCP");
		return protoOptions;
	}

	@ModelAttribute("remotePortOptions")
	protected Map<String, String> getRemotePortOptions() {
		Map<String, String> remotePortOptions = new LinkedHashMap<String, String>();
		remotePortOptions.put("ANY", "ANY");
		return remotePortOptions;
	}

	@ModelAttribute("localPortOptions")
	protected Map<String, String> getLocalPortOptions() {
		Map<String, String> localPortOptions = new LinkedHashMap<String, String>();
		localPortOptions.put("5100", "5100");
		return localPortOptions;
	}

	@ModelAttribute("initOptions")
	protected Map<String, String> getInitiatorOptions() {
		Map<String, String> initiatorOptions = new LinkedHashMap<String, String>();
		initiatorOptions.put("REMOTE", "REMOTE");
		return initiatorOptions;
	}

	@ModelAttribute("formatOptions")
	protected Map<String, String> getFormatOptions() {
		Map<String, String> formatOptions = new LinkedHashMap<String, String>();
		formatOptions.put("NCR", "NCR");
		return formatOptions;
	}

	@ModelAttribute("tcharOptions")
	protected Map<String, String> getTcharOptions() {
		Map<String, String> tcharOptions = new LinkedHashMap<String, String>();
		tcharOptions.put("64", "64");
		tcharOptions.put("128", "128");
		return tcharOptions;
	}

	@ModelAttribute("pinblkFmtOptions")
	protected Map<String, String> getPinblkFmtOptions() {
		Map<String, String> pinblkFmtOptions = new LinkedHashMap<String, String>();
		pinblkFmtOptions.put("03", "03");
		pinblkFmtOptions.put("01", "01");
		return pinblkFmtOptions;
	}

	@ModelAttribute("pciCompliantOptions")
	protected Map<String, String> getPciCompliantOptions() {
		Map<String, String> pciCompliantOptions = new LinkedHashMap<String, String>();
		pciCompliantOptions.put("1", "YES");
		pciCompliantOptions.put("0", "NO");
		return pciCompliantOptions;
	}

	@ModelAttribute("cardCaptureOptions")
	protected Map<String, String> getCardCaptureOptions() {
		Map<String, String> cardCaptureOptions = new LinkedHashMap<String, String>();
		cardCaptureOptions.put("1", "YES");
		cardCaptureOptions.put("0", "NO");
		return cardCaptureOptions;
	}

	@ModelAttribute("dvalOptions")
	protected Map<String, String> getDvalOptions() {
		Map<String, String> dvalOptions = new LinkedHashMap<String, String>();
		dvalOptions.put("1000", "1000");
		dvalOptions.put("500", "500");
		dvalOptions.put("100", "100");
		dvalOptions.put("50", "50");
		return dvalOptions;
	}

	@ModelAttribute("stateOptions")
	protected Map<String, String> getStateOptions() {
		Map<String, String> stateOptions = new LinkedHashMap<String, String>();
		stateOptions.put("DH", "Dhaka");
		stateOptions.put("CH", "Chittagong");
		stateOptions.put("SY", "Sylhet");
		stateOptions.put("RA", "Rajshahi");
		stateOptions.put("BA", "Barisal");
		stateOptions.put("KH", "Khulna");
		stateOptions.put("RN", "Rangpur");
		stateOptions.put("MY", "Mymensingh");
		return stateOptions;
	}

	@ModelAttribute("circuitOptions")
	protected Map<String, String> getCircuitOptions() {
		Map<String, String> circuitOptions = new LinkedHashMap<String, String>();
		circuitOptions.put("1", "Dhaka");
		circuitOptions.put("2", "Chittagong");
		circuitOptions.put("3", "Sylhet");
		circuitOptions.put("4", "Rajshahi");
		circuitOptions.put("5", "Barisal");
		circuitOptions.put("6", "Khulna");
		circuitOptions.put("7", "Rangpur");
		circuitOptions.put("8", "Mymensingh");
		return circuitOptions;
	}

	@ModelAttribute("branchOptions")
	protected Map<String, String> getBranchOptions() {
		List<MstBranch> brnchList = new ArrayList<MstBranch>();
		Map<String, String> branchOptions = new LinkedHashMap<String, String>();
		try {
			brnchList = branchService.getAllBranches();
			for (MstBranch branch : brnchList) {
				// if(branch.getBranchId() < 52)
				branchOptions.put(String.valueOf(branch.getBranchId()), branch.getBranchName());
			}
			return branchOptions;
		} catch (Exception e) {
			// TODO: handle exception
			return branchOptions;
		}
	}

}
