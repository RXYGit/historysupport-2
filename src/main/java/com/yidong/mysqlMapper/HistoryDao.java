package com.yidong.mysqlMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yidong.pojo.MoutboundmarketPOJO;
import com.yidong.pojo.ResponsePOJO;
import com.yidong.pojo.ResponseSupport2;
import com.yidong.vo.RequestVo;

public interface HistoryDao {
	
	List<ResponseSupport2> findAllObj(RequestVo requestVo);
	
	List<ResponsePOJO> findItem(@Param("userMobile")String userMobile
						,@Param("callTime")String callTime) ;
	
	MoutboundmarketPOJO findCall(@Param("userMobile")String userMobile);
}
