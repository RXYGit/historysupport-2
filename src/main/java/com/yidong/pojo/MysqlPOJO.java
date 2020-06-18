package com.yidong.pojo;

import com.alibaba.fastjson.annotation.JSONField;


public class MysqlPOJO {
	@JSONField(name = "ACPTTELNUM" )
	private String acptTelnum;
	@JSONField(name = "TIMEEND" )
	private String timeend;
	@JSONField(name = "ACPTTELNUM" )
	public String getAcptTelnum() {
		return acptTelnum;
	}
	@JSONField
	public void setAcptTelnum(String acptTelnum) {
		this.acptTelnum = acptTelnum;
	}
	@JSONField
	public String getTimeend() {
		return timeend;
	}
	@JSONField
	public void setTimeend(String timeend) {
		this.timeend = timeend;
	}
	
}
