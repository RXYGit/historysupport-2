package com.yidong.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class IOPOutCallTaskPOJO implements Serializable {
	private static final long serialVersionUID = -6911329268209516829L;
	private String pushDate;
	private String subsNumber;
	private String activityName;
	private String activityid;
	private String acttype;
	private String marketscencename;
	private String activityPriority;
	private String campaignid;
	private String campaignName;
	private String treatmentid;
}
