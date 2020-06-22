package com.yidong.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrepaidAndInstantSuperPOJO implements Serializable {

    private static final long serialVersionUID = -8902006251730532902L;
    private String id;
    private String subsNumber;
    private String campaignid;
    private String treatmentid;
}
