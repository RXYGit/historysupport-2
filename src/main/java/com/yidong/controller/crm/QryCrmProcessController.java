package com.yidong.controller.crm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yidong.oracleservice.QryCrmProcessService;
import com.yidong.pojo.QryCrmProcessPOJO;

@RestController
public class QryCrmProcessController {
	
	@Autowired
	private QryCrmProcessService qryCrmProcessService;
	

	/**
	 * Description: 山东crm工单查询接口
	 * Author: 毕研泽   bWX608729
	 * Version: 1.0
	 * Create Date Time: 2020年5月14日 上午8:42:49.
	 * Update Date Time: 
	 * @see
	 */
	@RequestMapping("/findObj/crm/qryCrmProcess")
	@SuppressWarnings("unchecked")
	public Map<String,	Object> qryCrmProcess(@RequestBody Map<String,Object> map) {
		
		//获取请求参数
		Map<String, Object> reqMap = (Map<String, Object>) map.get("params");
		//返回列表消息头
		Map<String, Object> repMap = new HashMap<String, Object>();
		//查询参数map
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
			//取出参数处理
			dataMap.put("callerno", reqMap.get("callerno")== null?"":String.valueOf(reqMap.get("callerno")));
			dataMap.put("staffId", reqMap.get("staffid") == null?"":String.valueOf(reqMap.get("staffid")));
			dataMap.put("beginDate", reqMap.get("begindate") == null?"":String.valueOf(reqMap.get("begindate")));
			dataMap.put("endDate", reqMap.get("enddate") == null?"":String.valueOf(reqMap.get("enddate")));
			
			//查询结果 list
			List<Map<String,Object>> crmPro = qryCrmProcessService.qryCrmProcess(dataMap);
			
			//查询条数
			int count = 0;
			if(crmPro != null && crmPro.size() > 0) {
				count = crmPro.size();
			}
			
			//System.out.println("crmPro===="+crmPro);
			repMap.put("rspCode", "1");
			repMap.put("rspMsg", "成功");
			repMap.put("count", count);
			repMap.put("list", crmPro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return repMap;
	}
	
}
