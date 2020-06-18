package com.yidong.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleStopOweWritebackPOJO implements Serializable {

	private static final long serialVersionUID = 6834003163510739924L;
	private String callId;//外呼ID
	private String callNo;//外呼号码
	private String status;//外呼的状态	外呼状态，00已呼、01待呼、02正呼
}
