package com.yidong.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * m_outboundmarket 免打扰客户表
 *请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoutboundmarketPOJO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7304987321193643981L;
	
	private String isBound;//subsnumber是否免打扰客户		(m_outboundmarket)
}	
