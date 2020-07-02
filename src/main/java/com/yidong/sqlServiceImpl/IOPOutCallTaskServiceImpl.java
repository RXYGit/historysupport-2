package com.yidong.sqlServiceImpl;


import com.yidong.pojo.IOPSelecTaskId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yidong.mysqlMapper.IOPOutCallTaskDao;
import com.yidong.pojo.IOPOutCallTaskPOJO;
import com.yidong.sqlservice.IOPOutCallTaskService;

import java.util.List;

@Service
@Transactional
public class IOPOutCallTaskServiceImpl implements IOPOutCallTaskService {

	@Autowired
	private IOPOutCallTaskDao iopOutCallTaskDao;

	@Override
	public int insertOutCallTask(IOPOutCallTaskPOJO arryIOP) {
		return iopOutCallTaskDao.insertOutCallTask(arryIOP);
	}

	@Override
	public IOPSelecTaskId selectIds(String campaignid) {
		return iopOutCallTaskDao.selectIds(campaignid);
	}

	@Override
	public int insertOutcalltaskinfo(List<IOPOutCallTaskPOJO> arryIOP) {
		return iopOutCallTaskDao.insertOutCallTaskInfo(arryIOP);
	}

	@Override
	public void updateTaskId(String childTask,String taskid,  String pushDate, String campaignid) {
		iopOutCallTaskDao.updateTaskId(childTask,taskid,pushDate,campaignid);
	}



}
