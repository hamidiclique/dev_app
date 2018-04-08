package com.test.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.app.dto.ButtonDto;
import com.test.app.dto.ViewAuthDto;
import com.test.app.entity.ScreenFunMap;
import com.test.app.entity.User;
import com.test.app.service.ScreenFunMapService;

@Component
public class ViewAuthUtil {
	private static final Logger logger = Logger.getLogger(SessionUtil.class);	
    private static ScreenFunMapService scrnfunmapService;

    @Autowired
    public ViewAuthUtil(ScreenFunMapService scrnfunmapService) {       
        ViewAuthUtil.scrnfunmapService = scrnfunmapService;
    }

	@SuppressWarnings("unchecked")
	public static ViewAuthDto isRequestValid(HttpServletRequest request, String function, String screen) {
		// TODO Auto-generated method stub
		ViewAuthDto viewDto = new ViewAuthDto();
		Map<String, List<ScreenFunMap>> mapScrnFunMap;
		List<ScreenFunMap> screenList;
		List<ButtonDto> tempBtnList;
		String resultPage = "";
		boolean tempHasAccess = false;
		ButtonDto btn;
		User currentuser = (User) request.getSession(false).getAttribute(StringUtil.USER_SESSION);
		List<ScreenFunMap> scrnfunList = scrnfunmapService.getDistinctScrnFunPairByUser(currentuser.getUserId());
		Map<String, String> tempMap = new HashMap<String, String>();
		for (ScreenFunMap sfm : scrnfunList) {
			tempMap.put(sfm.getId().getFunctionId(), sfm.getId().getScreenId());
		}
		
		try {			
			mapScrnFunMap = (Map<String, List<ScreenFunMap>>) request.getSession(false).getAttribute(StringUtil.SESSION_SCR_FUN_MAP);
			if (mapScrnFunMap.containsKey(function)) {
				screenList= mapScrnFunMap.get(function);
				for (ScreenFunMap sfm : screenList) {					
					if (sfm.getResultPage() != null && !(sfm.getResultPage().isEmpty())) {
						resultPage = sfm.getResultPage(); 
					}
					if (sfm.getId().getScreenId().equalsIgnoreCase(screen)) {
						//has a match with parent screen
						//check the size of the screen list
						if (screenList.size() > 1) {
							//when screen list has more than one element
							//then has to be buttons declared for that page
							tempBtnList = new ArrayList<ButtonDto>();
							for (ScreenFunMap sfmforbtn : screenList) {
								if (Integer.parseInt(sfmforbtn.getId().getButtonDef()) > 0) {
									if (tempMap.containsValue(sfmforbtn.getResultPage())) {
										btn = new ButtonDto();
										btn.setButtonDef(sfmforbtn.getId().getButtonDef());
										btn.setButtonDesc(sfmforbtn.getButtonDesc());
										btn.setResultPage(sfmforbtn.getResultPage());
										tempBtnList.add(btn);
									}
									else {
										logger.debug("button not added :" + StringUtil.FUNCTION_NOT_IN_FUNCTION_GROUP);
									}
								}
							}
							viewDto.setBtnList(tempBtnList);
						}
						else {
							tempBtnList = new ArrayList<ButtonDto>();
							viewDto.setBtnList(tempBtnList);
						}
						tempHasAccess = true;
						viewDto.setAllowedAccess(tempHasAccess);
						break;
					}
					else if (resultPage.equalsIgnoreCase(screen)) {
						//has a match with child screen
						if (tempMap.containsValue(resultPage)) {
							tempBtnList = new ArrayList<ButtonDto>();
							tempHasAccess = true;
							viewDto.setBtnList(tempBtnList);
							viewDto.setAllowedAccess(tempHasAccess);
							break;
						}
					}
				}
			}
			else {
				tempHasAccess = false;
				tempBtnList = new ArrayList<ButtonDto>();
				viewDto.setBtnList(tempBtnList);
				viewDto.setAllowedAccess(tempHasAccess);
			}
			return viewDto;
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.toString());
			return viewDto;
		}
	}
}
