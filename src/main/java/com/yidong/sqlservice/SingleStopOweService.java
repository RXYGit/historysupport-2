package com.yidong.sqlservice;

import java.util.List;
import java.util.Map;

import com.yidong.pojo.PrepaidAndInstantRequerPOJO;
import com.yidong.pojo.PrepaidAndInstantSuperPOJO;
import com.yidong.pojo.SingleStopOwePOJO;
import com.yidong.pojo.SingleStopOweWritebackPOJO;

public interface SingleStopOweService {
	//欠费单停
	List<SingleStopOwePOJO> singleStopOweQry(String nsdate,String lastMonth);
	void singleStopOweUpdate(List<String> idlist);
	int singleStopOweWriteback(List<SingleStopOweWritebackPOJO> param);

	//星级信用欠费外呼
	List<SingleStopOwePOJO> selectStarCredit(String startsdate, String lastMonth);
	void updateStartCredit(List<String> idlist);
	int updateStarCredits(List<SingleStopOweWritebackPOJO> param);

	//预付费欠费外呼和瞬时超套欠费外呼查询接口
    List<PrepaidAndInstantSuperPOJO> selectPrepaidAndInstantSuper(String taskId, String nsdate, String lastMonth);
	void updatePrepaipushstatus(List<String> idlist);
	int updateprepaidExt1Status(List<PrepaidAndInstantRequerPOJO> param);//预付费和瞬时超套回写更新
}
