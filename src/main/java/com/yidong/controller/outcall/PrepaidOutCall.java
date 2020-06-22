package com.yidong.controller.outcall;

import com.yidong.pojo.PrepaidAndInstantSuperPOJO;
import com.yidong.sqlservice.SingleStopOweService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预付费欠费外呼  瞬时超套欠费外呼
 * 查询推送接口
 * 2020-06-22
 * 入库接口复用多波次前店后厂接口 表  m_iop_outcalltask
 */
@RestController
public class PrepaidOutCall {

    @Autowired
    private SingleStopOweService singleStopOwe;

    @RequestMapping(value = "/findObj/outCall/prepaidAndInstantSuper",method = {RequestMethod.POST} )
    public Map<String,Object> prepaidCall(@RequestBody Map<String, Object> params){
        Map<String, Object> map = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        if (String.valueOf(params)==null||params.isEmpty()||"".equals(params.get("params"))) {
            map.put("respCode", "-1");map.put("respDesc", "fail");
            map.put("resultRows",String.valueOf(arrayList.size()));
            map.put("result", arrayList);
            return map;
        }
        @SuppressWarnings("unchecked")
        Map<String, String> param = (Map<String, String>) params.get("params");
        String campaignid = String.valueOf(param.get("campaignid"));
        if (campaignid==null||"".equals(campaignid)){
            map.put("respCode", "-1");map.put("respDesc", "fail");
            map.put("resultRows",String.valueOf(arrayList.size()));
            map.put("result", arrayList);
            return map;
        }
        //查询日期 例 ：2020-06-03
        String nsdate = LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd "));
        //一个月前的日期
        String lastMonth = LocalDateTime.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd "));

        //查询记录
        List<PrepaidAndInstantSuperPOJO> resultPrepaid = singleStopOwe.selectPrepaidAndInstantSuper(campaignid,nsdate,lastMonth);
        if(resultPrepaid.isEmpty()) {
            map.put("respCode", "-1");
            map.put("respDesc", "fail");
            map.put("resultRows", String.valueOf(resultPrepaid.size()));
            map.put("result", resultPrepaid);
            return map;
        }
        map.put("respCode", "0");
        map.put("respDesc", "success");
        map.put("resultRows", String.valueOf(resultPrepaid.size()));
        map.put("result", resultPrepaid);

        return map;

    }
}
