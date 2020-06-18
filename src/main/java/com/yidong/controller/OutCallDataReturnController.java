package com.yidong.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yidong.pojo.OutCallReturnPOJO;
import com.yidong.sqlservice.OutCallDataReturnService;

/**
 *外呼数据回传，入库
 */
@RestController
public class OutCallDataReturnController {

	@Autowired
	private OutCallDataReturnService outCallDataReturnService;
	//目前此接口测试的服务器地址   10.19.138.238:8092/findObj/outCall/dataReturn（生产上线在x.x.x.235：8090/XX/xx/xx）
	@RequestMapping(value = "/findObj/outCall/dataReturn",method= {RequestMethod.POST})
	public Map<String, Object> outCall(@RequestBody OutCallReturnPOJO outCall){
		Map<String, Object> hashMap = new HashMap<>();
		String strNull = "";
		if (outCall.getCallResultCode().equals(strNull) ||outCall.getCallResultCode()==null||outCall.getPhoneNum()==null ||outCall.getPhoneNum().equals(strNull) || outCall.getTaskId()==null || outCall.getTaskId().equals(strNull) || outCall.getCrtTime()==null||outCall.getCrtTime().equals(strNull)) {
			hashMap.put("respDesc", "入库失败");
			hashMap.put("respCode", "-1");
			return hashMap;
		}else {
			//数据入库
			String cretime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			outCall.setCretime(cretime);
			outCallDataReturnService.insertOutCallReturn(outCall);
			hashMap.put("respDesc", "入库成功");
			hashMap.put("respCode", "00000");
			return hashMap;
		}			
		
	}
	
}
