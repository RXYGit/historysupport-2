package com.yidong.pojo;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSupport2 implements Serializable{

	private static final long serialVersionUID = 4053553480192704244L;
	
	private String callTime;    //call_begin用于判断外呼时间 	    m_outcall_sum
	private String busiNm;      //actv_nm外呼业务				m_outcall_sum
	private String callTypeNm;  //chnl_type_cd_desc外呼方式	m_outcall_sum
	private String answerFlag ; //staff_id是否接通 	 			m_outcall_sum
	private String subPort;    //暂缓支撑
	private String privNm;      //privname优惠名称成功必传		m_10086_market
	private String callChnl;	//team_desc外呼渠道			m_outcall_sum
	private String duration ;	//call_duration外呼均长		m_outcall_sum
//	private String resultRows; //记录行数						m_outcall_sum
	
	
}
