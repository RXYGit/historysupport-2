package com.yidong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yidong.oracleservice.OracleService;
import com.yidong.pojo.MysqlPOJO;
import com.yidong.sqlservice.SqlService;

import java.util.Map;

@RestController
public class DBController {
	
	@Autowired
	private SqlService sqlService;
	
	@Autowired
	private OracleService oracleService;
	
	@RequestMapping("/testsql")
	public MysqlPOJO test(String acptTelnum) {
		MysqlPOJO a = sqlService.finTel(acptTelnum);
		System.out.println("MYSQL："+a);
		return a;
	} 
	    
	@RequestMapping("/testorcle")
	public MysqlPOJO testorcle(String callerno) {
		MysqlPOJO a = oracleService.finTel(callerno);
		System.out.println("oraclePhb："+a);
		//String jsonOutput = JSON.toJSONString(MysqlPOJO,SerializerFeature.BeanToArray);
		//jsonOutput = JSON.toJSONString (MysqlPOJO ,SerializerFeature.BeanToArray );
		//String b = JSON.toJSONString(MysqlPOJO);
		return a;
	}
	 
	@RequestMapping("/testoracle2")
	public String testoracle2(HttpServletRequest req,HttpServletResponse resp) {
		
		return "index";
	}
		
	@RequestMapping("/mainPage")
	public String mainPage(HttpServletRequest req,HttpServletResponse resp) {
		
		return "index2";
	}

	@RequestMapping(value = "/testorcle3",method = {RequestMethod.POST})
	public MysqlPOJO testorcle3(@RequestBody Map<String,String> serialnos) {
		String serialno = serialnos.get("serialno");
		MysqlPOJO a = oracleService.findtest(serialno);
		System.out.println("oraclePhb："+a);
		return a;
	}

}
