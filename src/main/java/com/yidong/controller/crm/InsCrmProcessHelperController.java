package com.yidong.controller.crm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yidong.sqlservice.InsCrmProcessHelperService;

@RestController
public class InsCrmProcessHelperController {
	
	@Autowired
	private InsCrmProcessHelperService insCrmProcessHelperService;

	/**
		 * Description: 山东CRM争议助手立单信息记录
		 * Author: 毕研泽   bWX608729
		 * Version: 1.0.0
		 * Create Date Time: 2020年6月2日 上午11:35:39
		 * Update Date Time: 
		 * @see
	 */
	@RequestMapping("/findObj/crm/insCrmProcessHelper")
	@SuppressWarnings("unchecked")
	public Map<String,	Object> insCrmProcessHelper(@RequestBody Map<String,Object> map) {
		
		Map<String, Object> repMap = new HashMap<String, Object>();
		Integer insNum = 0;
		
		try {
			
			if(map != null && map.size() > 0) {
				
				insNum = insCrmProcessHelperService.insCrmProcessHelper(map);
				
				if(insNum > 0) {
					repMap.put("rspCode", "1");
					repMap.put("rspMsg", "成功");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return repMap;
	}
	
}
