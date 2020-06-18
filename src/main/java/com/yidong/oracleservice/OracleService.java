package com.yidong.oracleservice;

import com.yidong.pojo.MysqlPOJO;

public interface OracleService {

	MysqlPOJO finTel(String  callerno);

	MysqlPOJO finIvr(String timeend);

	MysqlPOJO findtest(String serialno);

	//List<Map<String,Object>> qryCrmProcess(Map<String, Object> dataMap) ;
}
