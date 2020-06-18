package com.yidong.controller.message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *短信上下行功能解耦，短信上下行查询接口
 */
@RestController
public class MessageUpDown {
	private final static String[] MSG = new String[9];
	 {
		 MSG[0] = "tel";//手机号码
		 MSG[1] = "source";//来源
		 MSG[2] = "smsdesc";//短信内容
		 MSG[3] = "creatDate";//生成时间
		 MSG[4] = "smsDate";//发送时间
		 MSG[5] = "realDate";//实际发送时间
		 MSG[6] = "status";//状态
		 MSG[7] = "resCode";//返回码
		 MSG[8] = "resDesc";//返回信息
	    }
	@RequestMapping(value = "/findObj/sms/messageUpDown", method = {RequestMethod.POST})
	public Map<String, Object> messageUpDown(@RequestBody Map<String, Object> params){
		Map<String, Object> map1 = new HashMap<>();
		
		//请求参数
		@SuppressWarnings("unchecked")
		Map<String, Object> value = (Map<String, Object>) params.get("params");
		String cookie = value.get("cookie").toString();
		String param = value.get("param").toString();
		String prtUrl = value.get("prtUrl").toString();
		String url = value.get("url").toString();
		//String url = "http://10.19.190.91/report2/do?"+param;
		url = url +"?"+ param;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		PrintWriter out = null;
		String resp = "";
		HttpURLConnection conn =null;
		try {
			URL feedback = new URL(url);
			conn = (HttpURLConnection) feedback.openConnection();
			conn.setRequestProperty("Cookie", cookie);
			conn.setRequestProperty("Referer", prtUrl);
			conn.setDoOutput(true);conn.setDoOutput(true);conn.setUseCaches(false);conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-type", "application/json");
			conn.setConnectTimeout(3000);conn.setReadTimeout(5000);
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "GB2312"));
			out.flush();
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GB2312"));
			StringBuffer sb = new StringBuffer();
			if (reader != null) {
				String str123 = "";
				while ((str123 = reader.readLine()) != null) {
					sb.append(str123);
				}
			}
			resp = sb.toString();//html
			Document doc = Jsoup.parse(resp);
			Element el = doc.getElementById("theTable2");
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            if (el==null) {
				map1.put("result", list);
				map1.put("rtnCode",  "-1");
				map1.put("rtnMsg",  "失败");
				return map1;
			}
			List<Element> listTR = el.select("tr");
			if (listTR==null || listTR.size()==0) {
				map1.put("result", list);
				map1.put("rtnCode",  "-1");
				map1.put("rtnMsg",  "失败");
				return map1;
			}
			for (int i = 1; i < listTR.size(); i++) {
				Map<String, Object> map2 = new HashMap<>();
				List<Element> listTD = listTR.get(i).select("td");
				for (int j = 0; j < listTD.size(); j++) {
					if(j>8) {
						map2.put("key"+j, listTD.get(j).text());
				        }else {
				        map2.put(MSG[j], listTD.get(j).text());
				        }
					
				}
				list.add(map2);
			}
			map1.put("result", list);
			map1.put("rtnCode",  "0");
			map1.put("rtnMsg",  "成功");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return map1;
	}
}
