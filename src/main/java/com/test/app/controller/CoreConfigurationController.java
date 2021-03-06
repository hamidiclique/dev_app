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

import com.google.gson.Gson;
import com.test.app.dto.AtmDefDto;
import com.test.app.dto.AtmMaster;
import com.test.app.dto.TerminalEvent;
import com.test.app.dto.TrxnMonitor;
import com.test.app.dto.ViewAtmDto;
import com.test.app.dto.ViewAuthDto;
import com.test.app.entity.AtmCmdTab;
import com.test.app.entity.AtmCurrencyTab;
import com.test.app.entity.BranchTab;
import com.test.app.entity.CtimTab;
import com.test.app.entity.CtlaTab;
import com.test.app.entity.CtrTab;
import com.test.app.entity.DefTab;
import com.test.app.entity.DevAttribute;
import com.test.app.entity.DevKeyTab;
import com.test.app.entity.EcfopTab;
import com.test.app.entity.Function;
import com.test.app.entity.MstCurrIso;
import com.test.app.entity.RtTab;
import com.test.app.entity.TcpTab;
import com.test.app.entity.TmkCompTab;
import com.test.app.entity.TmkCompTabPK;
import com.test.app.entity.TmkReqTab;
import com.test.app.entity.User;
import com.test.app.service.ActivityLogService;
import com.test.app.service.AtmCmdService;
import com.test.app.service.AtmMasterService;
import com.test.app.service.BranchTabService;
import com.test.app.service.FunctionService;
import com.test.app.service.FunctiongrpService;
import com.test.app.service.FungrpFunMapSercice;
import com.test.app.service.LoginParamService;
import com.test.app.service.ModuleService;
import com.test.app.service.MstCurrencyService;
import com.test.app.service.PIdSequenceService;
import com.test.app.service.RoleFungrpMapService;
import com.test.app.service.RoleService;
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
	BranchTabService branchService;
	@Autowired
	PIdSequenceService pidSeqService;
	@Autowired
	TmkCompSequenceService tmkCompSeqService;
	@Autowired
	AtmMasterService atmMasterService;
	@Autowired
	AtmCmdService atmCmdService;
	@Autowired
	MstCurrencyService mstCurrencyService;
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
		int nora = 0;
		AtmMaster atmMaster;
		try {
			if (SessionUtil.sessionValid(req.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {				
				logger.debug(atmDef);
				atmMaster = mapUserInputToAtmMaster(atmDef);
				nora = atmMasterService.addNewAtmMachine(atmMaster);
				logger.debug(nora);
				if (nora >= 32) {
					model.addAttribute("message", StringUtil.SCFG3200_SUCCESS);
				} else {
					logger.debug("ERROR : INSERT ATM OPERATION WAS NOT SUCCESSFUL");
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

	@RequestMapping(value = "/submit-branch-maintenance-update", method = RequestMethod.POST)
	public String handle_FCFG3700_SCFG3700U(@ModelAttribute("branch") BranchTab branchTab, BindingResult result,
			ModelMap model, HttpServletRequest req, RedirectAttributes red) {
		int nora = 0;
		try {
			if (SessionUtil.sessionValid(req.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				logger.debug(branchTab);
				nora = branchService.editBranchInfo(branchTab);
				logger.debug(nora);
				if (nora > 0) {
					model.addAttribute("message", StringUtil.SCFG3700U_SUCCESS);
				} else {
					logger.debug("ERROR : BRANCH UPDATE OPERATION WAS NOT SUCCESSFUL");
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

	@RequestMapping(value = "/submit-branch-maintenance-add", method = RequestMethod.POST)
	public String handle_FCFG3700_SCFG3700A(@ModelAttribute("branch") BranchTab branchTab, BindingResult result,
			ModelMap model, HttpServletRequest req, RedirectAttributes red) {
		int nora = 0;
		try {
			if (SessionUtil.sessionValid(req.getSession(), red).equalsIgnoreCase(StringUtil.SESSION_VALID)) {
				logger.debug(branchTab);
				int branchId = branchService.generateBranchId();
				branchTab.setBranch_id(new Long(branchId));
				branchTab.setIso("555555");
				branchTab.setSequence(new BigDecimal(0));
				branchTab.setSequence_len(new BigDecimal(0));
				nora = branchService.addNewBranch(branchTab);
				logger.debug(nora);
				if (nora > 0) {
					model.addAttribute("message", StringUtil.SCFG3700U_SUCCESS);
				} else {
					logger.debug("ERROR : BRANCH UPDATE OPERATION WAS NOT SUCCESSFUL");
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/branch-maintenance", method = RequestMethod.GET)
	public String view_FCFG3700_SCFG3700(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		List<BranchTab> branchList = new ArrayList<BranchTab>();
		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					branchList = branchService.getAllBranches();
					Function currentfunction = funService.getFunctionById(fid);
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false)
								.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						funList = funModMap.get(mid);
						Iterator<String> funIterator = funList.iterator();
						while (funIterator.hasNext()) {
							String element = funIterator.next();
							if (element.length() > 8) {
								funIterator.remove();
							}
						}
					}
					model.addAttribute("funlist", funList);
					model.addAttribute("branchList", branchList);
					model.addAttribute("listsize", branchList.size());
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
	@RequestMapping(value = "/branch-maintenance-add", method = RequestMethod.GET)
	public String view_FCFG3700_SCFG3700A(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, RedirectAttributes red, HttpServletRequest request) {
		BranchTab branch = new BranchTab();
		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false)
								.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						funList = funModMap.get(mid);
						Iterator<String> funIterator = funList.iterator();
						while (funIterator.hasNext()) {
							String element = funIterator.next();
							if (element.length() > 8) {
								funIterator.remove();
							}
						}
					}
					model.addAttribute("funlist", funList);
					model.addAttribute("branch", branch);
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
					model.addAttribute("functionDesc", currentfunction.getFunctionDesc() + " - ADD");
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
	@RequestMapping(value = "/branch-maintenance-enquiry", method = RequestMethod.GET)
	public String view_FCFG3700_SCFG3700E(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, @RequestParam String pid, RedirectAttributes red, HttpServletRequest request) {
		BranchTab branch = new BranchTab();
		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false)
								.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						funList = funModMap.get(mid);
						Iterator<String> funIterator = funList.iterator();
						while (funIterator.hasNext()) {
							String element = funIterator.next();
							if (element.length() > 8) {
								funIterator.remove();
							}
						}
					}
					branch = branchService.findBranchById(pid);
					model.addAttribute("funlist", funList);
					model.addAttribute("branch", branch);
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
					model.addAttribute("functionDesc", currentfunction.getFunctionDesc() + " - ENQUIRY");
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
	@RequestMapping(value = "/branch-maintenance-update", method = RequestMethod.GET)
	public String view_FCFG3700_SCFG3700U(ModelMap model, @RequestParam String mid, @RequestParam String fid,
			@RequestParam String sid, @RequestParam String pid, RedirectAttributes red, HttpServletRequest request) {
		BranchTab branch = new BranchTab();
		Map<String, List<String>> funModMap = new HashMap<String, List<String>>();
		List<String> funList = new ArrayList<String>();
		try {
			ViewAuthDto vadto = ViewAuthUtil.isRequestValid(request, fid, sid);
			if (vadto.isAllowedAccess()) {
				if (SessionUtil.sessionValid(request.getSession(false), red)
						.equalsIgnoreCase(StringUtil.SESSION_VALID)) {
					Function currentfunction = funService.getFunctionById(fid);
					if (request.getSession(false).getAttribute(StringUtil.SESSION_FUN_MOD_MAP) != null) {
						funModMap = (Map<String, List<String>>) request.getSession(false)
								.getAttribute(StringUtil.SESSION_FUN_MOD_MAP);
						funList = funModMap.get(mid);
						Iterator<String> funIterator = funList.iterator();
						while (funIterator.hasNext()) {
							String element = funIterator.next();
							if (element.length() > 8) {
								funIterator.remove();
							}
						}
					}
					branch = branchService.findBranchById(pid);
					model.addAttribute("funlist", funList);
					model.addAttribute("branch", branch);
					model.addAttribute("btnList", vadto.getBtnList());
					model.addAttribute("function", fid);
					model.addAttribute("module", mid);
					model.addAttribute("functionDesc", currentfunction.getFunctionDesc() + " - UPDATE");
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
			@RequestParam String sid, @RequestParam String pid, RedirectAttributes red, HttpServletRequest request) {
		AtmMaster mstatmdto;
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
					mstatmdto = atmMasterService.findAtmMasterInfoByPid(pid);
					model.addAttribute("mstatm", mstatmdto);

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
		AtmMaster mstatmdto;
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

					mstatmdto = atmMasterService.findAtmMasterInfoByPid(pid);
					model.addAttribute("mstatm", mstatmdto);
					LinkedHashMap<String, Integer> cmdcodemap = cmdStatusMapForAtm(pid);
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
					activeAtmList = atmMasterService.getAllActiveAtm();
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
		/*return "SCFG3201";*/
	}

	@RequestMapping(value = "/getCmdStatusForSelectedATM", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxcall_method_SCFG3500(@RequestParam("pid") String pid, HttpServletRequest request) {
		logger.debug("pid: " + pid);
		LinkedHashMap<String, Integer> cmdcodemap = cmdStatusMapForAtm(pid);
		String jsonString = new Gson().toJson(cmdcodemap);
		return jsonString;
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
		atmcmd.setCmd_datetime(timestamp);
		atmcmd.setCmd_issuer(username);
		atmcmd.setCmd_code(new BigDecimal(cmdcode));
		atmcmd.setCmd_status(new BigDecimal(0));

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

	private LinkedHashMap<String, Integer> cmdStatusMapForAtm(String pid) {
		// retrieve relevant atm row
		// first check the cmd status
		// if cmd status is 0 / 1, then command is yet to be completed
		// in such case options (based on cmd_code) should be disabled
		// if cmd status is 2, then given command is successful
		// options can be visible
		LinkedHashMap<String, Integer> cmdcodemaptemp = new LinkedHashMap<String, Integer>();
		cmdcodemaptemp.put("1-Open ATM", 0);
		cmdcodemaptemp.put("2-Close ATM", 0);
		cmdcodemaptemp.put("3-Load Formats", 0);
		cmdcodemaptemp.put("4-Load States", 0);
		cmdcodemaptemp.put("5-Load Screens", 0);
		cmdcodemaptemp.put("6-Load configuration parameters", 0);
		cmdcodemaptemp.put("7-Load FIT Table", 0);
		cmdcodemaptemp.put("9-Load New TPK", 0);
		cmdcodemaptemp.put("11-Load Current Date and Time", 0);
		cmdcodemaptemp.put("38-Load EMV AID Table", 0);
		cmdcodemaptemp.put("39-Load EMV Currency Table", 0);
		cmdcodemaptemp.put("40-Load EMV Transaction Type Table", 0);
		cmdcodemaptemp.put("41-Load EMV Terminal Data", 0);
		cmdcodemaptemp.put("63-Load EJ Options and Timers", 0);
		cmdcodemaptemp.put("13-Request ATM To Send Supply Counters", 0);
		cmdcodemaptemp.put("52-Request ATM To Send Enhanced Supply Counters", 0);
		cmdcodemaptemp.put("53-Request ATM To Run Self-Test", 0);
		int code;
		AtmCmdTab atmcmdobj = atmCmdService.getAtmCmdDetailsByPid(pid);
		int status = atmcmdobj.getCmd_status().intValue();
		switch (status) {
		case 0:
		case 1:
			System.out.println("Command request submitted/ Command sent to ATM");
			code = atmcmdobj.getCmd_code().intValue();
			switch (code) {
			case 1:
				System.out.println("Open ATM");
				// show only ATM closing option in disabled state
				cmdcodemaptemp.replace("2-Close ATM", 2);
				break;
			// case 2:
			default:
				System.out.println("Closed ATM");
				// show all options in disabled state
				cmdcodemaptemp.replace("1-Open ATM", 2);
				cmdcodemaptemp.replace("3-Load Formats", 2);
				cmdcodemaptemp.replace("4-Load States", 2);
				cmdcodemaptemp.replace("5-Load Screens", 2);
				cmdcodemaptemp.replace("6-Load configuration parameters", 2);
				cmdcodemaptemp.replace("7-Load FIT Table", 2);
				cmdcodemaptemp.replace("9-Load New TPK", 2);
				cmdcodemaptemp.replace("11-Load Current Date and Time", 2);
				cmdcodemaptemp.replace("38-Load EMV AID Table", 2);
				cmdcodemaptemp.replace("39-Load EMV Currency Table", 2);
				cmdcodemaptemp.replace("40-Load EMV Transaction Type Table", 2);
				cmdcodemaptemp.replace("41-Load EMV Terminal Data", 2);
				cmdcodemaptemp.replace("63-Load EJ Options and Timers", 2);
				cmdcodemaptemp.replace("13-Request ATM To Send Supply Counters", 2);
				cmdcodemaptemp.replace("52-Request ATM To Send Enhanced Supply Counters", 2);
				cmdcodemaptemp.replace("53-Request ATM To Run Self-Test", 2);
				break;
			/*
			 * default: System.out.println("Invalid Code"); break;
			 */
			}
			break;
		case 2:
			System.out.println("Command completed");
			code = atmcmdobj.getCmd_code().intValue();
			switch (code) {
			case 1:
				System.out.println("Open ATM");
				// show only ATM closing option
				cmdcodemaptemp.replace("2-Close ATM", 1);
				break;
			// case 2:
			default:
				System.out.println("Closed ATM");
				// show all options
				cmdcodemaptemp.replace("1-Open ATM", 1);
				cmdcodemaptemp.replace("3-Load Formats", 1);
				cmdcodemaptemp.replace("4-Load States", 1);
				cmdcodemaptemp.replace("5-Load Screens", 1);
				cmdcodemaptemp.replace("6-Load configuration parameters", 1);
				cmdcodemaptemp.replace("7-Load FIT Table", 1);
				cmdcodemaptemp.replace("9-Load New TPK", 1);
				cmdcodemaptemp.replace("11-Load Current Date and Time", 1);
				cmdcodemaptemp.replace("38-Load EMV AID Table", 1);
				cmdcodemaptemp.replace("39-Load EMV Currency Table", 1);
				cmdcodemaptemp.replace("40-Load EMV Transaction Type Table", 1);
				cmdcodemaptemp.replace("41-Load EMV Terminal Data", 1);
				cmdcodemaptemp.replace("63-Load EJ Options and Timers", 1);
				cmdcodemaptemp.replace("13-Request ATM To Send Supply Counters", 1);
				cmdcodemaptemp.replace("52-Request ATM To Send Enhanced Supply Counters", 1);
				cmdcodemaptemp.replace("53-Request ATM To Run Self-Test", 1);
				break;
			/*
			 * default: System.out.println("Invalid Code"); break;
			 */
			}
			break;
		default:
			System.out.println("Invalid");
			break;
		}
		return cmdcodemaptemp;
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
		devKeyTab.setPci_compliant(new BigDecimal(atmdef.getPciCompliant()));
		devKeyTab.setPinblk_fmt(atmdef.getPinblkFmt());
		devKeyTab.setKey_length(new BigDecimal(32));
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
		ecfoptabvar.setEop_no(new BigDecimal(eop_no));
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
			atmCurrencyTab.setCanister_type(new BigDecimal(i));
			switch (i) {
			case 1:
				atmCurrencyTab.setIso_currency_type(new BigDecimal(atmdef.getD1curr()));
				break;
			case 2:
				atmCurrencyTab.setIso_currency_type(new BigDecimal(atmdef.getD2curr()));
				break;
			case 3:
				atmCurrencyTab.setIso_currency_type(new BigDecimal(atmdef.getD3curr()));
				break;
			case 4:
				atmCurrencyTab.setIso_currency_type(new BigDecimal(atmdef.getD4curr()));
				break;
			default:
				logger.debug("Index not valid");
			}
			
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
				ctimtab.setTimr_len(new BigDecimal(15));
				ctimtab.setTimr_no(new BigDecimal(i));
				ctimtablst.add(ctimtab);
				break;
			case 3:
			case 4:
				ctimtab = new CtimTab();
				ctimtab.setPid(pid);
				ctimtab.setTimr_len(new BigDecimal(60));
				ctimtab.setTimr_no(new BigDecimal(i));
				ctimtablst.add(ctimtab);
				break;
			case 5:
				ctimtab = new CtimTab();
				ctimtab.setPid(pid);
				ctimtab.setTimr_len(new BigDecimal(20));
				ctimtab.setTimr_no(new BigDecimal(i));
				ctimtablst.add(ctimtab);
				break;
			case 6:
				ctimtab = new CtimTab();
				ctimtab.setPid(pid);
				ctimtab.setTimr_len(new BigDecimal(10));
				ctimtab.setTimr_no(new BigDecimal(i));
				ctimtablst.add(ctimtab);
				break;
			case 7:
				ctimtab = new CtimTab();
				ctimtab.setPid(pid);
				ctimtab.setTimr_len(new BigDecimal(1));
				ctimtab.setTimr_no(new BigDecimal(i));
				ctimtablst.add(ctimtab);
				break;
			case 8:
			case 9:
				ctimtab = new CtimTab();
				ctimtab.setPid(pid);
				ctimtab.setTimr_len(new BigDecimal(30));
				ctimtab.setTimr_no(new BigDecimal(i));
				ctimtablst.add(ctimtab);
				break;
			default:
				ctimtab = new CtimTab();
				ctimtab.setPid(pid);
				ctimtab.setTimr_len(new BigDecimal(0));
				ctimtab.setTimr_no(new BigDecimal(i));
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
			ctrtab.setC1_start_bills(zero);
			ctrtab.setC2_start_bills(zero);
			ctrtab.setC3_start_bills(zero);
			ctrtab.setC4_start_bills(zero);
			ctrtab.setC_c1bills(zero);
			ctrtab.setC_c1brej(zero);
			ctrtab.setC_c2bills(zero);
			ctrtab.setC_c2brej(zero);
			ctrtab.setC_c3bills(zero);
			ctrtab.setC_c3brej(zero);
			ctrtab.setC_c4bills(zero);
			ctrtab.setC_c4brej(zero);
			ctrtab.setC_cacsha(zero);
			ctrtab.setC_cainq(zero);
			ctrtab.setC_caxfer(zero);
			ctrtab.setC_ccapt(zero);
			ctrtab.setC_cckbk(zero);
			ctrtab.setC_cdep(zero);
			ctrtab.setC_cdepac(zero);
			ctrtab.setC_cfrf("0");
			ctrtab.setC_cfseg(zero);
			ctrtab.setC_cicsha(zero);
			ctrtab.setC_ciinq(zero);
			ctrtab.setC_cinq(zero);
			ctrtab.setC_cixfer(zero);
			ctrtab.setC_cpmt(zero);
			ctrtab.setC_crect(zero);
			ctrtab.setC_csvcload(zero);
			ctrtab.setC_csvcunload(zero);
			ctrtab.setC_ctrans(zero);
			ctrtab.setC_cwdl_loc(zero);
			ctrtab.setC_cwdl_us(zero);
			ctrtab.setC_cxfer(zero);
			ctrtab.setC_tacsha_loc(zero);
			ctrtab.setC_tacsha_us(zero);
			ctrtab.setC_tdep(zero);
			ctrtab.setC_tdepc(zero);
			ctrtab.setC_tdeps(zero);
			ctrtab.setC_ticsha(zero);
			ctrtab.setC_tpmt(zero);
			ctrtab.setC_tpmtl(zero);
			ctrtab.setC_tpmtmc(zero);
			ctrtab.setC_tsvcload(zero);
			ctrtab.setC_tsvcunload(zero);
			ctrtab.setC_twdlc_loc(zero);
			ctrtab.setC_twdlc_us(zero);
			ctrtab.setC_twdl_loc(zero);
			ctrtab.setC_twdls_loc(zero);
			ctrtab.setC_twdls_us(zero);
			ctrtab.setC_twdl_us(zero);
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
			ctlatab.setMem_no(zero);
			ctlatab.setMiserr(zero);
			ctlatab.setMis_flag1(zero);
			ctlatab.setMis_flag2(zero);
			ctlatab.setMis_fld1(zero);
			ctlatab.setMis_fld2(zero);
			// ctlatab.setMsgCoordNum();
			ctlatab.setNon_readies(zero);
			ctlatab.setNtrans(zero);
			ctlatab.setOprob(zero);
			ctlatab.setOpstat(new BigDecimal(128));
			ctlatab.setOutctr(zero);
			ctlatab.setOuttot(zero);
			ctlatab.setPid(pid);
			ctlatab.setProcstat(zero);
			ctlatab.setRencon(zero);
			ctlatab.setRespno(zero);
			ctlatab.setSw_tab(new BigDecimal(7));
			ctlatab.setSw_skey(zero);
			ctlatab.setSw_req(new BigDecimal(2));
			ctlatab.setSw_lskey(new BigDecimal(999));
			ctlatab.setSw_ekey(zero);
			ctlatab.setSw_config(zero);
			ctlatab.setStatus_msg("OPEN");
			ctlatab.setStat1_1(zero);
			ctlatab.setStat1_2(zero);
			ctlatab.setStat1_3(zero);
			ctlatab.setStat1_4(zero);
			ctlatab.setStat1_5(zero);
			ctlatab.setStat1_6(zero);
			ctlatab.setStat1_7(zero);
			ctlatab.setStat1_8(zero);
			ctlatab.setStat1_9(zero);
			ctlatab.setStat1_10(zero);
			ctlatab.setStat2_1(zero);
			ctlatab.setStat2_2(zero);
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
			deftab.setGmt_offset(new BigDecimal(6));
			deftab.setId(pid);
			deftab.setLoader(atmdef.getLoader());
			deftab.setLuno(new BigDecimal(788));
			deftab.setMacing(new BigDecimal(0));
			deftab.setNumdisp(new BigDecimal(1));
			deftab.setRec(new BigDecimal(0));
			deftab.setRjwarn(new BigDecimal(100));
			deftab.setRwarn(new BigDecimal(50));
			deftab.setSecurity_mod(new BigDecimal(1));
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
			tcptab.setDepends_on(new BigDecimal(-1));
			tcptab.setFormat(atmdef.getFormat());
			tcptab.setHeader_len(new BigDecimal(4));
			tcptab.setInitiator(atmdef.getInitiator());
			tcptab.setLocal_port(atmdef.getLocalPort());
			tcptab.setMachine(atmdef.getMachine());
			tcptab.setPing_check(new BigDecimal(1));
			tcptab.setRemote_address(atmdef.getRemoteAddress());
			tcptab.setRemote_port(atmdef.getRemotePort());
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
		List<BranchTab> brnchList = new ArrayList<BranchTab>();
		Map<String, String> branchOptions = new LinkedHashMap<String, String>();
		try {
			brnchList = branchService.getAllBranches();
			for (BranchTab branch : brnchList) {
				// if(branch.getBranchId() < 52)
				branchOptions.put(String.valueOf(branch.getBranch_id()), branch.getBranch_name());
			}
			return branchOptions;
		} catch (Exception e) {
			// TODO: handle exception
			return branchOptions;
		}
	}

	@ModelAttribute("currencyOptions")
	protected Map<String, String> getCurrencyOptions() {
		List<MstCurrIso> currencyList = new ArrayList<MstCurrIso>();
		Map<String, String> currencyOptions = new LinkedHashMap<String, String>();
		try {
			currencyList = mstCurrencyService.getCurrencyList();
			for (MstCurrIso currency : currencyList) {
				// if(branch.getBranchId() < 52)
				currencyOptions.put(String.valueOf(currency.getIso_num()), currency.getIso_code());
			}
			return currencyOptions;
		} catch (Exception e) {
			// TODO: handle exception
			return currencyOptions;
		}
	}

	@ModelAttribute("deviceList")
	protected Map<String, String> getDeviceList() {
		Map<String, String> deviceOptions = new LinkedHashMap<String, String>();
		try {
			deviceOptions.put("C1", "Camera 1");
			deviceOptions.put("C2", "Camera 2");
			deviceOptions.put("C3", "Camera 3");
			deviceOptions.put("C4", "Camera 4");
			deviceOptions.put("D1", "Disk 1");
			return deviceOptions;
		} catch (Exception e) {
			// TODO: handle exception
			return deviceOptions;
		}
	}

}
