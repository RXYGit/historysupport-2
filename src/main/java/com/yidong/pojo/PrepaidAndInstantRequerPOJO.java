package com.yidong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrepaidAndInstantRequerPOJO implements Serializable {
    private static final long serialVersionUID = 2329493776963734420L;
    private String id;
    private String campaignid;
    private String pushstatus;
}
