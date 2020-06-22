package com.yidong.mysqlMapper;

import java.util.List;

import com.yidong.pojo.PrepaidAndInstantSuperPOJO;
import org.apache.ibatis.annotations.Param;

import com.yidong.pojo.SingleStopOwePOJO;
import com.yidong.pojo.SingleStopOweWritebackPOJO;

public interface SingleStopOweDao {
	

	List<SingleStopOwePOJO> singleStopOweQry(@Param("nsdate")String nsdate,@Param("lastMonth")String lastMonth);
	void singleStopOweUpdate(@Param("idlist")List<String> idlist);
	int singleStopOweWriteback(@Param("param")List<SingleStopOweWritebackPOJO> param);


    List<SingleStopOwePOJO> selectStarCredit(@Param("startsdate")String startsdate, @Param("lastMonth")String lastMonth);
	void updateStarCredit(@Param("idss")List<String> idlist);
	int updateStarCreditWriteback(@Param("param")List<SingleStopOweWritebackPOJO> param);


    List<PrepaidAndInstantSuperPOJO> selectPrepaidAndSuperInstant(@Param("campaignid")String campaignid, @Param("nsdate")String nsdate, @Param("lastMonth")String lastMonth);
}
