package com.yidong.mysqlMapper;

import java.util.List;
import java.util.Map;

import com.yidong.pojo.PrepaidAndInstantRequerPOJO;
import com.yidong.pojo.PrepaidAndInstantSuperPOJO;
import org.apache.ibatis.annotations.Param;

import com.yidong.pojo.SingleStopOwePOJO;
import com.yidong.pojo.SingleStopOweWritebackPOJO;

public interface SingleStopOweDao {
	
	//欠费单停外呼
	List<SingleStopOwePOJO> singleStopOweQry(@Param("nsdate")String nsdate,@Param("lastMonth")String lastMonth);
	void singleStopOweUpdate(@Param("idlist")List<String> idlist);
	int singleStopOweWriteback(@Param("param")List<SingleStopOweWritebackPOJO> param);

	//星级信用外呼
    List<SingleStopOwePOJO> selectStarCredit(@Param("startsdate")String startsdate, @Param("lastMonth")String lastMonth);
	void updateStarCredit(@Param("idss")List<String> idlist);
	int updateStarCreditWriteback(@Param("param")List<SingleStopOweWritebackPOJO> param);

	//预付费欠费外呼和瞬时超套外呼
    List<PrepaidAndInstantSuperPOJO> selectPrepaidAndSuperInstant(@Param("taskId")String taskId, @Param("nsdate")String nsdate, @Param("lastMonth")String lastMonth);
	void updatePrepaipushstatus(@Param("idList")List<String> idList);
	int updatePrepaidExt1Status(@Param("param")List<PrepaidAndInstantRequerPOJO> param);
}
