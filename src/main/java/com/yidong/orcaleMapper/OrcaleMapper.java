package com.yidong.orcaleMapper;

import java.util.List;
import java.util.Map;

import com.yidong.pojo.MysqlPOJO;
import com.yidong.pojo.QryCrmProcessPOJO;

public interface OrcaleMapper {
	MysqlPOJO findOrcel(String callerno);
	
	List<QryCrmProcessPOJO> qryCrmProcess(Map<String, Object> dataMap);
	
}
 