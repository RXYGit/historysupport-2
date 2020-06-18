package com.yidong.sqlServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yidong.mysqlMapper.OutCallDataReturnDao;
import com.yidong.pojo.OutCallReturnPOJO;
import com.yidong.sqlservice.OutCallDataReturnService;

@Service
@Transactional
public class OutCallDataReturnImpl implements OutCallDataReturnService {

	@Autowired
	private OutCallDataReturnDao outCallDataReturnDao;
	@Override
	public int insertOutCallReturn(OutCallReturnPOJO outCall) {
		int sqlRows = outCallDataReturnDao.insertOutCallDataRetrun(outCall);
		
		return sqlRows;
	}

}
