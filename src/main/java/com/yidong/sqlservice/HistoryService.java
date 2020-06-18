package com.yidong.sqlservice;

import java.util.List;

import com.yidong.pojo.MoutboundmarketPOJO;
import com.yidong.pojo.ResponsePOJO;
import com.yidong.pojo.ResponseSupport2;
import com.yidong.vo.RequestVo;

public interface HistoryService {

	/**
	 * 依据开始时间和结束时间查询,电话
	 * @param userMobile
	 * @param beginTime
	 * @param beginTime2
	 */
	//查询m_outcall_sum表
	List<ResponseSupport2> findObj(RequestVo requestVo);
	//查询m_10086_market表的privNm
	List<ResponsePOJO> findItem(String userMobile,String callTime);
	//查询m_outboundmarket表
	MoutboundmarketPOJO findCall(String userMobile);
}
