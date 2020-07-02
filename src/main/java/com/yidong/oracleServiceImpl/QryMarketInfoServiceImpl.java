package com.yidong.oracleServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yidong.oracleservice.QryMarketInfoService;
import com.yidong.orcaleMapper.QryMarketInfoMapper;

@Service
@Transactional
public class QryMarketInfoServiceImpl implements QryMarketInfoService {
	@Autowired
	private QryMarketInfoMapper qryMarketInfoMapper;
	
	/**
	 * Description: 地址库信息查询
	 * Author: 毕研泽   bWX608729
	 * Version: 1.0
	 * Create Date Time: 2020年6月28日 下午14:44:16.
	 * Update Date Time: 
	 * @see
	 */
	@Override
	public List<Map<String,Object>> qryMarketInfo(Map<String, Object> dataMap) {
		qryMarketInfoMapper.qryMarketInfo(dataMap);
		ArrayList<Map<String, Object>> cursorList = (ArrayList<Map<String, Object>>) dataMap.get("result");
		return cursorList;
	}
	
	

}
