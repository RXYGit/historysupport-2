package com.yidong.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IOPSelecTaskId implements Serializable {
    private static final long serialVersionUID = 6268618021709949421L;
    private String id;
    private String taskId;
    private String childTask;
}
