package com.yidong.pojo;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * m_10086_market办理记录表
 *响应参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePOJO implements Serializable {
	
	private static final long serialVersionUID = -5518217089724202857L;
	
	private String privNm;
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
//	private Date beginTime;
//	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
//	private Date endTime;
	

}
