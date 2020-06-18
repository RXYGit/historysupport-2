package com.yidong.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OutCallReturnPOJO implements Serializable{
	
	private static final long serialVersionUID = -7389667898913333954L;
	private String taskId;
	private String phoneNum;
	private String crtTime;
	private String sysId;
	private String callResultCode;
	private String answerFlag;
	private String callTypeFlag;
	private String custGroupNo;
	private String ngocSmplId;
	private String cretime;
	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;
}
