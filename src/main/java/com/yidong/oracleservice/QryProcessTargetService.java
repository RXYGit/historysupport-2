package com.yidong.oracleservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yidong.oracleServiceImpl.OracleServiceImpl;
import com.yidong.pojo.QryCrmProcessPOJO;

public interface QryProcessTargetService {

	/**
	 * Description: 定制争议类工单投诉和咨询占比
	 * Author: 毕研泽   bWX608729
	 * Version: 1.0
	 * Create Date Time: 2020年5月29日 上午11:32:35.
	 * Update Date Time: 
	 * @see
	 */
	Double qryProcessTarget() ;
}
