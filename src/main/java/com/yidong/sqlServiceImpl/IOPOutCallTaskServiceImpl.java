package com.yidong.sqlServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yidong.mysqlMapper.IOPOutCallTaskDao;
import com.yidong.pojo.IOPOutCallTaskPOJO;
import com.yidong.sqlservice.IOPOutCallTaskService;

@Service
@Transactional
public class IOPOutCallTaskServiceImpl implements IOPOutCallTaskService {

	@Autowired
	private IOPOutCallTaskDao iopOutCallTaskDao;

	@Override
	public int insertOutCallTask(IOPOutCallTaskPOJO arryIOP) {
		return iopOutCallTaskDao.insertOutCallTask(arryIOP);
	}
	
}
