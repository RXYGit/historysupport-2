package com.yidong.sqlServiceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yidong.mysqlMapper.HistoryDao;
import com.yidong.pojo.MoutboundmarketPOJO;
import com.yidong.pojo.ResponsePOJO;
import com.yidong.pojo.ResponseSupport2;
import com.yidong.sqlservice.HistoryService;
import com.yidong.vo.RequestVo;


@Service
@Transactional(readOnly = true)
public class HistoryServiceImpl implements HistoryService {
	
	@Autowired
	private HistoryDao historyDao;
	
	@Override
	public List<ResponseSupport2> findObj(RequestVo requestVo) {
		return historyDao.findAllObj( requestVo);
	}

	@Override
	public List<ResponsePOJO> findItem(String userMobile,String callTime) {
		return historyDao.findItem( userMobile,callTime);
	}

	@Override
	public MoutboundmarketPOJO findCall(String userMobile) {
		return historyDao.findCall(userMobile);
	}

}
