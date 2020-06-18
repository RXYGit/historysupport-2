package com.yidong.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleStopOwePOJO implements Serializable{
	private static final long serialVersionUID = -7422135723151527458L;
	
	private String callNo;//外呼号码
	private String callTime;//插入时间
	private String callId;//外呼ID
	private String ext1;//扩展字段1,分支编码
	private String ext2;//扩展字段2，渠道编码

}
