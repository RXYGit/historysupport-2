package com.yidong.controller.crm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yidong.oracleservice.QryProcessTargetService;
import com.yidong.sqlservice.SqlService;

@RestController
public class QryProcessTargetController {
	
	@Autowired
	private QryProcessTargetService qryProcessTargetService;

	/**
		 * Description: 定制争议类工单投诉和咨询占比
		 * Author: 毕研泽   bWX608729
		 * Version: 
		 * Create Date Time: 2020年5月29日 上午11:22:39
		 * Update Date Time: 
		 * @see
		 
	 */
	@RequestMapping("/findObj/crm/qryProcessTarget")
	@SuppressWarnings("unchecked")
	public Map<String,	Object> qryProcessTarget() {
		
		Map<String, Object> repMap = new HashMap<String, Object>();
		
		try {
			String type = "";
			//查询结果 
			Double target = qryProcessTargetService.qryProcessTarget();
			
			if(target >= 0.3) {
				type = "2";
			}else {
				type = "1";
			}
			//System.out.println("crmPro===="+crmPro);
			repMap.put("rspCode", "1");
			repMap.put("rspMsg", "成功");
			repMap.put("num", target);
			repMap.put("type", type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return repMap;
	}
	
}
