package com.yidong.oracleservice;

import java.util.List;
import java.util.Map;



public interface QryCrmProcessService {

	/**
	 * Description: 山东crm工单查询接口
	 * Author: 毕研泽   bWX608729
	 * Version: 1.0
	 * Create Date Time: 2020年5月14日 上午8:42:49.
	 * Update Date Time: 
	 * @see
	 */
	List<Map<String,Object>> qryCrmProcess(Map<String, Object> dataMap) ;
}
