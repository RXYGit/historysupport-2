package com.yidong.mysqlMapper;


import com.yidong.pojo.IOPOutCallTaskPOJO;
import com.yidong.pojo.IOPSelecTaskId;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOPOutCallTaskDao {
    //入库（新）前店后厂
	int insertOutCallTask(IOPOutCallTaskPOJO arryIOP);
    //查询配置表 campaignid
    IOPSelecTaskId selectIds(@Param("campaignid") String campaignid);
    //入库预付费欠费外呼，瞬时超套m_iop_outcalltaskinfo
    int insertOutCallTaskInfo(@Param("arryIOP")List<IOPOutCallTaskPOJO> arryIOP);
    //瞬时超套 s入库更新m_iop_outcalltaskinfo 中taskId,和childTaskid
    void updateTaskId(@Param("childTask")String childTask, @Param("taskid")String taskid,@Param("pushDate") String pushDate, @Param("campaignid") String campaignid);

}
