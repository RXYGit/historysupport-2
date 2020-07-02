package com.yidong.sqlservice;


import com.yidong.pojo.IOPOutCallTaskPOJO;
import com.yidong.pojo.IOPSelecTaskId;

import java.util.List;

public interface IOPOutCallTaskService {
    //入库（新）前店后厂
	int insertOutCallTask(IOPOutCallTaskPOJO arryIOP);

    //查询配置表 campaignid
    IOPSelecTaskId selectIds(String campaignid);
    //入库预付费欠费外呼，瞬时超套m_iop_outcalltaskinfo
    int insertOutcalltaskinfo(List<IOPOutCallTaskPOJO> arryIOP);
    //更新上面的入库操作，瞬时超套m_iop_outcalltaskinfo
    void updateTaskId( String childTask,String taskid, String pushDate, String campaignid);

}
