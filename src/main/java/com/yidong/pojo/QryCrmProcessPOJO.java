package com.yidong.pojo;



public class QryCrmProcessPOJO {
	
	private String serialno;//工单流水号
	private String urgentid;//紧急程度
	private String accepttime;//受理时间
	private String processstate;//工单状态
	private String subsnumber;//受理号码
	private String subsname;//客户姓名
	private String srtypeid;//服务请求类型ID
	private String fullname;//服务请求全称
	private String acceptstaffno;//受理员工号
	private String subslevel;//客户级别
	private String subscity;//客户地市
	private String content;//业务内容
	private String acceptmode;//受理方式
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getUrgentid() {
		return urgentid;
	}
	public void setUrgentid(String urgentid) {
		this.urgentid = urgentid;
	}
	public String getAccepttime() {
		return accepttime;
	}
	public void setAccepttime(String accepttime) {
		this.accepttime = accepttime;
	}
	public String getProcessstate() {
		return processstate;
	}
	public void setProcessstate(String processstate) {
		this.processstate = processstate;
	}
	public String getSubsnumber() {
		return subsnumber;
	}
	public void setSubsnumber(String subsnumber) {
		this.subsnumber = subsnumber;
	}
	public String getSubsname() {
		return subsname;
	}
	public void setSubsname(String subsname) {
		this.subsname = subsname;
	}
	public String getSrtypeid() {
		return srtypeid;
	}
	public void setSrtypeid(String srtypeid) {
		this.srtypeid = srtypeid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAcceptstaffno() {
		return acceptstaffno;
	}
	public void setAcceptstaffno(String acceptstaffno) {
		this.acceptstaffno = acceptstaffno;
	}
	public String getSubslevel() {
		return subslevel;
	}
	public void setSubslevel(String subslevel) {
		this.subslevel = subslevel;
	}
	public String getSubscity() {
		return subscity;
	}
	public void setSubscity(String subscity) {
		this.subscity = subscity;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAcceptmode() {
		return acceptmode;
	}
	public void setAcceptmode(String acceptmode) {
		this.acceptmode = acceptmode;
	}
	@Override
	public String toString() {
		return "QryCrmProcessPOJO [serialno=" + serialno + ", urgentid=" + urgentid + ", accepttime=" + accepttime
				+ ", processstate=" + processstate + ", subsnumber=" + subsnumber + ", subsname=" + subsname
				+ ", srtypeid=" + srtypeid + ", fullname=" + fullname + ", acceptstaffno=" + acceptstaffno
				+ ", subslevel=" + subslevel + ", subscity=" + subscity + ", content=" + content + ", acceptmode="
				+ acceptmode + "]";
	}
	
	
	
}
