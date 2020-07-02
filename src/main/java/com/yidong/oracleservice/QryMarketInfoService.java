package com.yidong.oracleservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


public interface QryMarketInfoService {

	/**
	 * Description: 地址库信息查询
	 * Author: 毕研泽   bWX608729
	 * Version: 1.0
	 * Create Date Time: 2020年6月28日 下午14:43:16.
	 * Update Date Time: 
	 * @see
	 */
	List<Map<String,Object>> qryMarketInfo(Map<String, Object> dataMap) ;
}
