package com.yidong.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yidong.pojo.IOPOutCallTaskPOJO;
import com.yidong.sqlservice.IOPOutCallTaskService;

/**
 *多波次营销外呼样本接收接口(前店后厂)
 */
@RestController
public class IOPOutCallTaskCollection {
	@Autowired
	private IOPOutCallTaskService iopOutCallTaskService;

	@RequestMapping(value = "/findObj/outCall/taskCollection",method = {RequestMethod.POST})
	@SuppressWarnings("unchecked")
	public Map<String, Object> outCallTasks(@RequestBody Map<String, Object> params){
		//入参的校验
		String str = "";
		Map<String, Object> hashMap = new HashMap<>();
		Map<String, Object>  param= (Map<String, Object>) params.get("params");
		if (params==null||param==null) {
			hashMap.put("rspCode", "-1");
			hashMap.put("rspMsg", "入库失败");
			return hashMap;
		}
		Object activityList0 = param.get("activityList");
		String subsNumber = (String) param.get("subsNumber");   //手机号码
		
		if (activityList0==null||str.equals(activityList0)) {
			hashMap.put("rspCode", "-1");
			hashMap.put("rspMsg", "入库失败");
			return hashMap;
		}
		List<Object> activityList = (List<Object>) activityList0;//活动列表
		if (activityList==null||activityList.size()==0) {
			hashMap.put("rspCode", "-1");
			hashMap.put("rspMsg", "入库失败");
			return hashMap;
		}
		Map<String,Object> msg = (Map<String,Object>) activityList.get(0);
		String activityName = String.valueOf(msg.get("activity_name")) ;            //活动名称
		String activityid = String.valueOf(msg.get("activityid")) ;                 //活动编号
		String acttype = String.valueOf(msg.get("acttype")) ;                       //活动类型
		String marketscencename = String.valueOf(msg.get("marketscencename")) ;     //营销活动目的
		String activityPriority = String.valueOf(msg.get("activityPriority")) ;     //活动优先级。数值越大，优先级越高
		Object campaignList0 = msg.get("campaignList");
		if (campaignList0==null||str.equals(campaignList0)) {
			hashMap.put("rspCode", "-1");
			hashMap.put("rspMsg", "入库失败");
			return hashMap;
		}
		List<Object> campaignList = (List<Object>) campaignList0;//战役列表
		if (campaignList==null||campaignList.size()==0) {
			hashMap.put("rspCode", "-1");
			hashMap.put("rspMsg", "入库失败");
			return hashMap;
		}
		String pushDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		List<IOPOutCallTaskPOJO> arryIOP = new ArrayList<>();
		for (int j = 0; j < campaignList.size(); j++) {
			Map<String, Object> msg2 = (Map<String, Object>) campaignList.get(j);
			String campaignid = String.valueOf(msg2.get("campaignid")) ;           //战役编码（任务编码）
			String campaignName = String.valueOf(msg2.get("campaignName")) ;       //    (也是入库参数)
			
			Object treatMentList0 = msg2.get("treatMentList");
			if (treatMentList0==null||str.equals(treatMentList0)) {
				hashMap.put("rspCode", "-1");
				hashMap.put("rspMsg", "入库失败");
				return hashMap;
			}
			List<Object> treatMentList = (List<Object>) treatMentList0;
			if (treatMentList==null||treatMentList.size()==0) {
				hashMap.put("rspCode", "-1");
				hashMap.put("rspMsg", "入库失败");
				return hashMap;
			}
			for (int k = 0; k < treatMentList.size(); k++) {
				Map<String, Object> map3 = (Map<String, Object>) treatMentList.get(k);
				String treatmentid = (String) map3.get("Treatmentid");
				IOPOutCallTaskPOJO iopPOJO = new IOPOutCallTaskPOJO();
				if(subsNumber==null||str.equals(subsNumber)||activityName==null||str.equals(activityName)||activityid==null||str.equals(activityid)
						||acttype==null||str.equals(acttype)||marketscencename==null||str.equals(marketscencename)
						||activityPriority==null||str.equals(activityPriority)||campaignid==null||str.equals(campaignid)
						||campaignName==null||str.equals(campaignName)||treatmentid==null||str.equals(treatmentid)) {
					hashMap.put("rspCode", "-1");
					hashMap.put("rspMsg", "入库失败");
					return hashMap;
				}else {
					iopPOJO.setActivityid(activityid);
					iopPOJO.setActivityName(activityName);
					iopPOJO.setActivityPriority(activityPriority);
					iopPOJO.setActtype(acttype);
					iopPOJO.setCampaignid(campaignid);
					iopPOJO.setCampaignName(campaignName);
					iopPOJO.setMarketscencename(marketscencename);
					iopPOJO.setPushDate(pushDate);
					iopPOJO.setSubsNumber(subsNumber);
					iopPOJO.setTreatmentid(treatmentid);
					arryIOP.add(iopPOJO);
				}
			}
		}
		//此处可优化，可采用批量更新
		for (int i = 0; i < arryIOP.size(); i++) {
			IOPOutCallTaskPOJO iopPo = arryIOP.get(i);
			iopOutCallTaskService.insertOutCallTask(iopPo);
		}
		hashMap.put("rspCode", "0");
		hashMap.put("rspMsg", "入库成功");
		return hashMap;
	}

}

