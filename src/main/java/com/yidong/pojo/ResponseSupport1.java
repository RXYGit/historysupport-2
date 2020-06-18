package com.yidong.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseSupport1 implements Serializable{

	private static final long serialVersionUID = -6368725104039813556L;
	
	//private String isBound;
	//private Integer resultRows;
	private List<String> result;
	
	
}
