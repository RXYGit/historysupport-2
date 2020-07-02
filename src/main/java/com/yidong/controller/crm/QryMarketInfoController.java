package com.yidong.controller.crm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yidong.oracleservice.QryMarketInfoService;


@RestController
public class QryMarketInfoController {
	
	@Autowired
	private QryMarketInfoService qryMarketInfoService;
	

	/**
	 * Description: 地址库信息查询
	 * Author: 毕研泽   bWX608729
	 * Version: 1.0
	 * Create Date Time: 2020年6月28日 下午14:42:49.
	 * Update Date Time: 
	 * @see
	 */
	@RequestMapping("/findObj/crm/qryMarketInfo")
	@SuppressWarnings("unchecked")
	public Map<String,	Object> qryMarketInfo(@RequestBody Map<String,Object> map) {
		
		//获取请求参数
		Map<String, Object> reqMap = (Map<String, Object>) map.get("params");
		//返回列表消息头
		Map<String, Object> repMap = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		//查询参数map
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//Map<String, Object> object = new HashMap<String, Object>();
		
		try {
			//取出参数处理
			dataMap.put("region", reqMap.get("region")== null?"":String.valueOf(reqMap.get("region")));
			dataMap.put("county", reqMap.get("county") == null?"":String.valueOf(reqMap.get("county")));
			dataMap.put("address", reqMap.get("address") == null?"":String.valueOf(reqMap.get("address")));
			
			//查询结果 list
			List<Map<String,Object>> busiInfos =qryMarketInfoService.qryMarketInfo(dataMap);
			//String aaa = "\"rpsContent\":\"{\"rtnCode\": \"0\",\"rtnMsg\": \"成功!\",\"bean\": {},\"beans\": [],\"object\": {\"result\": {\"busiInfos\": [{\"address\": \"无\"}]},\"respCode\": \"0\",\"respDesc\": \"success\"}}";
			if(null != busiInfos && busiInfos.size() > 0) {
				result.put("busiInfos", busiInfos);
				
				repMap.put("respCode", "0");
				repMap.put("respDesc", "success");
				repMap.put("result", result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return repMap;
	}
	
}
