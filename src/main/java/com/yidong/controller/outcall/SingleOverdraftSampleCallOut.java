package com.yidong.controller.outcall;

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

import com.yidong.common.ObjectMapperJson;
import com.yidong.pojo.SingleStopOwePOJO;
import com.yidong.pojo.SingleStopOweWritebackPOJO;
import com.yidong.sqlservice.SingleStopOweService;

/**
 *单停欠费外呼样本推送查询
 *单停欠费外呼样本回写(接收值，修改status状态,00已呼、01待呼、02正呼)
 *2020-6-3
 *王滨,白文成提出
 */
@RestController
public class SingleOverdraftSampleCallOut {

	@Autowired
	private SingleStopOweService singleStopOwe;


	@RequestMapping(value = "/findObj/outCall/singleStopOwe",method = {RequestMethod.POST} )
	public Map<String, Object> singleStopOwe(@RequestBody Map<String, Object> params){
		Map<String, Object> map = new HashMap<>();
		ArrayList<Object> arrayList = new ArrayList<>();
		if (String.valueOf(params)==null||params.isEmpty()||"".equals(params.get("params"))) {
			map.put("respCode", "-1");map.put("respDesc", "fail");
			map.put("resultRows",String.valueOf(arrayList.size()));
			map.put("result", arrayList);
			return map;
		}
		@SuppressWarnings("unchecked")
		Map<String, String> param = (Map<String, String>) params.get("params");
		if (String.valueOf(param)==null||param.isEmpty()) {
			map.put("respCode", "-1");map.put("respDesc", "fail");
			map.put("resultRows",String.valueOf(arrayList.size()));
			map.put("result", arrayList);
			return map;
		}
		String inputCode = String.valueOf(param.get("inputCode"));
		String code = "CMOSSINGLESTOPOWEOUTCALLTASKCODE";
		if (!code.equals(inputCode)) {
			map.put("respCode", "-1");
			map.put("respDesc", "fail");
			map.put("resultRows",String.valueOf(arrayList.size()) );
			map.put("result", arrayList);
			return map;
		}

		//查询日期 例 ：2020-06-03
		String nsdate = LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd "));
		//一个月前的日期
		String lastMonth = LocalDateTime.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd "));
		//查询记录
		List<SingleStopOwePOJO> singleStopOweQry = singleStopOwe.singleStopOweQry(nsdate,lastMonth);
		if(singleStopOweQry.isEmpty()) { 
			map.put("respCode", "-1");
			map.put("respDesc", "fail");
			map.put("resultRows", String.valueOf(singleStopOweQry.size()));
			map.put("result", singleStopOweQry);
			return map;
		}
		List<String> idlist = new ArrayList<>();
		for (int i = 0; i < singleStopOweQry.size(); i++) {
			idlist.add(singleStopOweQry.get(i).getCallId());
		}
		//根据id值批量的更新
		singleStopOwe.singleStopOweUpdate(idlist);

		map.put("respCode", "0");
		map.put("respDesc", "success");
		map.put("resultRows", String.valueOf(singleStopOweQry.size()));
		map.put("result", singleStopOweQry);

		return map;
	}

	/**
	 * 单停欠费回写接口(修改status状态,00已呼、01待呼、02正呼)
	 */
	@RequestMapping(value = "/findObj/outCall/singleStopOweWriteback",method = {RequestMethod.POST} )
	public Map<String, Object> singleStopOweWriteback(@RequestBody Map<String, Object> params ){
		Map<String, Object> map = new HashMap<>();
		if (String.valueOf(params)==null||params.isEmpty()||"".equals(params.get("params"))) {map.put("respCode", "-1");map.put("respDesc", "fail");
			return map;
		}
		@SuppressWarnings("unchecked")
		List<SingleStopOweWritebackPOJO> param = (List<SingleStopOweWritebackPOJO>) params.get("params");
		if (param.size()==0||param.isEmpty()||String.valueOf(param)==null) {
			map.put("respCode", "-1");map.put("respDesc", "fail");
			return map;
		}
		
		for (int i = 0; i < param.size(); i++) {
			/*
			String json = ObjectMapperJson.toJson(param.get(i));
			try {
				JsonNode tree = new ObjectMapper().readTree(json);
				String ca = tree.get("callId").toString();
				System.out.println(ca);
			} catch (Exception e) {
				e.printStackTrace();
			}
			*/
			@SuppressWarnings("unchecked")
			Map<String,Object> m = (Map<String,Object>) param.get(i);
			if(m.get("callId")==null||"".equals(m.get("callId"))){map.put("respCode", "-1");map.put("respDesc", "fail"); return map;}
			if(m.get("callNo")==null||"".equals(m.get("callNo"))){map.put("respCode", "-1");map.put("respDesc", "fail"); return map;}
			if(m.get("status")==null||"".equals(m.get("status"))){map.put("respCode", "-1");map.put("respDesc", "fail"); return map;}
		}
		//批量更新
		int rows = singleStopOwe.singleStopOweWriteback(param);
		if (rows>=1) {map.put("respCode", "0");map.put("respDesc", "success");
		}else {map.put("respCode", "-1");map.put("respDesc", "fail");return map;}
		return map;
	}
}



