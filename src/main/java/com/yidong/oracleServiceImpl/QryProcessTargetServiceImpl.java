package com.yidong.oracleServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yidong.oracleservice.OracleService;
import com.yidong.oracleservice.QryProcessTargetService;
import com.yidong.orcaleMapper.OrcaleMapper;
import com.yidong.orcaleMapper.QryProcessTargetMapper;
import com.yidong.orcaleMapperTwo.OrcaleMapperTwo;


@Service
@Transactional
public class QryProcessTargetServiceImpl implements QryProcessTargetService {
	@Autowired
	private QryProcessTargetMapper qryProcessTargetMapper;
	
	/**
		 * Description: 定制争议类工单投诉和咨询占比
		 * Author: 毕研泽   bWX608729
		 * Version: 
		 * Create Date Time: 2020年5月29日 上午11:32:35
		 * Update Date Time: 
		 * @see
	 */
	@Override
	public Double qryProcessTarget() {
		Double target = qryProcessTargetMapper.qryProcessTarget();
		return target;
	}
	
	

}
