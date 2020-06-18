package com.yidong.orcaleMapperThree;

import com.yidong.pojo.MysqlPOJO;
import org.apache.ibatis.annotations.Param;

public interface OrcaleMapperThree {
    MysqlPOJO findtest(String serialno);
}
