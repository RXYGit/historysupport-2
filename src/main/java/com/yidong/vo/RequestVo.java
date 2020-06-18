package com.yidong.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestVo implements Serializable{
	private static final long serialVersionUID = 1190572096275085176L;
	
	private String userMobile;
	private String beginTime;
	private String endTime;

}
