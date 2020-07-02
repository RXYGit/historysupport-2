package com.yidong.controller.outcall;

import com.yidong.pojo.PrepaidAndInstantRequerPOJO;
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
 * 预付费欠费外呼  瞬时超套外呼
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
       // List<Map<String,Object>> campaignidList = (List<Map<String,Object>>) params.get("params");
        Map<String,String>  maptaskId = (Map<String, String>) params.get("params");
        String taskId = maptaskId.get("taskId");
        if (String.valueOf(taskId)==null||"".equals(taskId)){
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
        List<PrepaidAndInstantSuperPOJO> resultPrepaid = singleStopOwe.selectPrepaidAndInstantSuper(taskId,nsdate,lastMonth);
        if(resultPrepaid.isEmpty()) {
            map.put("respCode", "-1");
            map.put("respDesc", "fail");
            map.put("resultRows", String.valueOf(resultPrepaid.size()));
            map.put("result", resultPrepaid);
            return map;
        }
        //更改状态 更改ext1
        List<String> idList = new ArrayList<>();
        for (PrepaidAndInstantSuperPOJO prepaidAndInstantSuperPOJO : resultPrepaid) {
            idList.add(prepaidAndInstantSuperPOJO.getId());
        }
        //根据id值批量的更新ext1为“1”
        singleStopOwe.updatePrepaipushstatus(idList);

        map.put("respCode", "0");
        map.put("respDesc", "success");
        map.put("resultRows", String.valueOf(resultPrepaid.size()));
        map.put("result", resultPrepaid);
        return map;
    }





    /**
     * 预付费欠费外呼  瞬时超套外呼 外呼后的状态回写的接口 例如ext1=“03”或“06”
     */
    @RequestMapping(value = "/findObj/outCall/prepaidAndInstantSuperWback",method = {RequestMethod.POST} )
    public Map<String, Object> prepaidAndInstantSuperWriteback(@RequestBody Map<String, Object> params ) {
        Map<String, Object> map = new HashMap<>();
        if (String.valueOf(params) == null || params.isEmpty() || "".equals(params.get("params"))) {
            map.put("respCode", "-1");map.put("respDesc", "fail");
            return map;
        }
        @SuppressWarnings("unchecked")
        List<PrepaidAndInstantRequerPOJO> param = (List<PrepaidAndInstantRequerPOJO>) params.get("params");
        if (param.size() == 0 || String.valueOf(param) == null) {
            map.put("respCode", "-1");map.put("respDesc", "fail");
            return map;
        }

        for (int i = 0; i < param.size(); i++) {
            @SuppressWarnings("unchecked")
            Map<String, Object> m = (Map<String, Object>) param.get(i);
            if (m.get("id") == null || "".equals(m.get("id"))) {
                map.put("respCode", "-1");map.put("respDesc", "fail");
                return map;
            }
            if (m.get("campaignid") == null || "".equals(m.get("campaignid"))) {
                map.put("respCode", "-1"); map.put("respDesc", "fail");
                return map;
            }
            if (m.get("pushstatus") == null || "".equals(m.get("pushstatus"))) {
                map.put("respCode", "-1"); map.put("respDesc", "fail");
                return map;
            }
        }
        //批量更新
        int rows = singleStopOwe.updateprepaidExt1Status(param);
        if (rows >= 1) {
            map.put("respCode", "0"); map.put("respDesc", "success");
        } else {
            map.put("respCode", "-1"); map.put("respDesc", "fail");
            return map;
        }
        return map;
    }
}
