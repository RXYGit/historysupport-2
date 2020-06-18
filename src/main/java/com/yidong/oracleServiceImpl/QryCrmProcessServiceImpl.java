package com.yidong.oracleServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yidong.oracleservice.QryCrmProcessService;
import com.yidong.orcaleMapper.QryCrmProcessMapper;

@Service
@Transactional
public class QryCrmProcessServiceImpl implements QryCrmProcessService {
	@Autowired
	private QryCrmProcessMapper qryCrmProcessMapper;
	
	/**
	 * Description: 山东crm工单查询接口
	 * Author: 毕研泽   bWX608729
	 * Version: 1.0
	 * Create Date Time: 2020年5月14日 上午8:42:49.
	 * Update Date Time: 
	 * @see
	 */
	@Override
	public List<Map<String,Object>> qryCrmProcess(Map<String, Object> dataMap) {
		qryCrmProcessMapper.qryCrmProcess(dataMap);
		ArrayList<Map<String, Object>> cursorList = (ArrayList<Map<String, Object>>) dataMap.get("result");
		return cursorList;
	}
	
	

}
