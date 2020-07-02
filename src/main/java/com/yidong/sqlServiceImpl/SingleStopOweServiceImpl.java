package com.yidong.sqlServiceImpl;

import java.util.List;
import java.util.Map;

import com.yidong.pojo.PrepaidAndInstantRequerPOJO;
import com.yidong.pojo.PrepaidAndInstantSuperPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yidong.mysqlMapper.SingleStopOweDao;
import com.yidong.pojo.SingleStopOwePOJO;
import com.yidong.pojo.SingleStopOweWritebackPOJO;
import com.yidong.sqlservice.SingleStopOweService;

@Service
@Transactional
public class SingleStopOweServiceImpl implements SingleStopOweService {

	@Autowired
	private SingleStopOweDao singleStopOweDao;
	
	@Override
	public List<SingleStopOwePOJO> singleStopOweQry(String nsdate ,String lastMonth) {
		List<SingleStopOwePOJO> singleStopOweQry = singleStopOweDao.singleStopOweQry(nsdate,lastMonth);
		return singleStopOweQry;
	}

	@Override
	public void singleStopOweUpdate(List<String> idlist) {
		singleStopOweDao.singleStopOweUpdate(idlist);
		
	}

	@Override
	public int singleStopOweWriteback(List<SingleStopOweWritebackPOJO> param) {
		return  singleStopOweDao.singleStopOweWriteback(param);
	}

	@Override
	public List<SingleStopOwePOJO> selectStarCredit(String startsdate, String lastMonth) {
		List<SingleStopOwePOJO> starCredit = singleStopOweDao.selectStarCredit(startsdate,lastMonth);
		return starCredit;
	}

	@Override
	public void updateStartCredit(List<String> idlist) {
		singleStopOweDao.updateStarCredit(idlist);
	}

	@Override
	public int updateStarCredits(List<SingleStopOweWritebackPOJO> param) {
		return  singleStopOweDao.updateStarCreditWriteback(param);

	}

	//预付费欠费外呼和瞬时超套欠费外呼查询接口
	@Override
	public List<PrepaidAndInstantSuperPOJO> selectPrepaidAndInstantSuper(String taskId, String nsdate, String lastMonth) {
		return singleStopOweDao.selectPrepaidAndSuperInstant(taskId,nsdate,lastMonth);
	}

	@Override
	public void updatePrepaipushstatus(List<String> idList) {
		singleStopOweDao.updatePrepaipushstatus(idList);
	}

	@Override
	public int updateprepaidExt1Status(List<PrepaidAndInstantRequerPOJO> param) {
		return singleStopOweDao.updatePrepaidExt1Status(param);
	}


}
