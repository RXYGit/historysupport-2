package com.yidong.sqlServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yidong.mysqlMapper2.InsCrmProcessHelperMapper;
import com.yidong.sqlservice.InsCrmProcessHelperService;

@Service
@Transactional
public class InsCrmProcessHelperServiceImpl implements InsCrmProcessHelperService {

	@Autowired
	private InsCrmProcessHelperMapper insCrmProcessHelperMapper;

	/**
	 * Description: 山东CRM争议助手立单信息记录
	 * Author: 毕研泽   bWX608729
	 * Version: 1.0.0
	 * Create Date Time: 2020年6月2日 上午11:35:39
	 * Update Date Time: 
	 * @see
 */
	@Override
	public Integer insCrmProcessHelper(Map<String, Object> map) {
		Integer insNum = insCrmProcessHelperMapper.insCrmProcessHelper(map);
		return insNum;
	}
	
	
	
	
	
	
	
	

}
