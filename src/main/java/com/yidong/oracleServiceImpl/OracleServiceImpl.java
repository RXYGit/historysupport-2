package com.yidong.oracleServiceImpl;


import com.yidong.orcaleMapperThree.OrcaleMapperThree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yidong.oracleservice.OracleService;
import com.yidong.orcaleMapper.OrcaleMapper;
import com.yidong.orcaleMapperTwo.OrcaleMapperTwo;
import com.yidong.pojo.MysqlPOJO;

import javax.annotation.Resource;

@Service
@Transactional
public class OracleServiceImpl implements OracleService {
	//@Resource
	private OrcaleMapper orcaleMapper;
	//@Resource
	private OrcaleMapperTwo orcaleMapperTwo;
	@Resource
	private OrcaleMapperThree orcaleMapperThree;
	
	@Override
	public MysqlPOJO finTel(String callerno) {

		return  orcaleMapper.findOrcel(callerno);

	}

	@Override
	public MysqlPOJO finIvr(String timeend) {
		return  orcaleMapperTwo.findIvr(timeend);

	}

	@Override
	public MysqlPOJO findtest(String serialno) {
		return  orcaleMapperThree.findtest(serialno);

	}


}
