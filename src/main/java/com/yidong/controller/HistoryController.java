package com.yidong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yidong.pojo.MoutboundmarketPOJO;
import com.yidong.pojo.ResponsePOJO;
import com.yidong.pojo.ResponseSupport2;
import com.yidong.sqlservice.HistoryService;
import com.yidong.vo.RequestVo;


/**
 *外呼历史轨迹查询
 */
@RestController
public class HistoryController {

	@Autowired
	private HistoryService historyService;

	@RequestMapping(value = "/findObj/outcall/raceQry" ,method= {RequestMethod.POST})
	public Map<String, Object> findData( @RequestBody RequestVo requestVo) {
		String userMobile = requestVo.getUserMobile();
		//查询m_outboundmarket表
		MoutboundmarketPOJO findCall = historyService.findCall(userMobile);
		Map<String, Object> hashMap = new HashMap<>();
		//查询m_outcall_sum表
		List<ResponseSupport2> findObj = historyService.findObj(requestVo);
		if (findObj.size()<=0||findObj.isEmpty()) {
			hashMap.put("resultRows", String.valueOf(findObj.size()));
			hashMap.put("isBound", findCall.getIsBound());
			hashMap.put("result",findObj);
			return hashMap;
		}
		for (int i = 0; i <findObj.size(); i++) {
			ResponseSupport2 responseSupport2 = findObj.get(i);
			String callTime = responseSupport2.getCallTime();
			String str = responseSupport2.getAnswerFlag()==null?"":responseSupport2.getAnswerFlag();
			if("101".equals(str) || "否".equals(str)||"".equals(str)) {
				responseSupport2.setAnswerFlag("1");
			}else {
				responseSupport2.setAnswerFlag("0");
			}
			//查询m_10086_market表的privNm
			List<ResponsePOJO> findItem = historyService.findItem(userMobile,callTime);
			if(findItem.isEmpty()) {
				responseSupport2.setPrivNm("");
			}else {
				responseSupport2.setPrivNm(String.valueOf(findItem.get(0).getPrivNm()));
			}
		}
		hashMap.put("resultRows", String.valueOf(findObj.size()));
		hashMap.put("isBound", findCall.getIsBound());
		hashMap.put("result",findObj);
		return hashMap;
	}
}
