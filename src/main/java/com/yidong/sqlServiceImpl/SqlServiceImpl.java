package com.yidong.sqlServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yidong.mysqlMapper.MysqlMapper;
import com.yidong.pojo.MysqlPOJO;
import com.yidong.sqlservice.SqlService;

@Service
@Transactional
public class SqlServiceImpl implements SqlService {
	
	@Autowired 
	private MysqlMapper mysqlMapper;
	
	@Override
	public MysqlPOJO finTel(String acptTelnum) {
		MysqlPOJO find = mysqlMapper.find(acptTelnum);
		return find;
	}

}
