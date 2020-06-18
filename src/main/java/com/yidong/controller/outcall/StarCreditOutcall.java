package com.yidong.controller.outcall;

import com.yidong.pojo.SingleStopOwePOJO;
import com.yidong.pojo.SingleStopOweWritebackPOJO;
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
 * 星级信用欠费外呼，表由白文成提供
 * 王滨提出2020-06-15
 */
@RestController
public class StarCreditOutcall {

    @Autowired
    private SingleStopOweService singleStopOweService;

    @RequestMapping(value = "/findObj/outCall/starCreditOutCall",method = {RequestMethod.POST} )
    public Map<String,Object> starCreditOutCall(@RequestBody Map<String,Object> params){
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
        if (String.valueOf(param)==null||param.isEmpty()) {
            map.put("respCode", "-1");map.put("respDesc", "fail");
            map.put("resultRows",String.valueOf(arrayList.size()));
            map.put("result", arrayList);
            return map;
        }
        String inputCode = String.valueOf(param.get("inputCode"));
        String code = "CMOSSINGLESTOPOWEOUTCALLSTARCREDITCODE";
        if (!code.equals(inputCode)) {
            map.put("respCode", "-1");
            map.put("respDesc", "fail");
            map.put("resultRows",String.valueOf(arrayList.size()));
            map.put("result", arrayList);
            return map;
        }
        //查询日期 例 ：2020-06-03
        String startsdate = LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd "));
        //一个月前的日期
        String lastMonth = LocalDateTime.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd "));
        ////查询记录  m_c_sce_staroutcalllist
        List<SingleStopOwePOJO> startCreditRs = singleStopOweService.selectStarCredit(startsdate,lastMonth);
        List<String> idlist = new ArrayList<>();
        if(startCreditRs.isEmpty()) {
            map.put("respCode", "-1");
            map.put("respDesc", "fail");
            map.put("resultRows", String.valueOf(startCreditRs.size()));
            map.put("result", startCreditRs);
            return map;
        }
        for (SingleStopOwePOJO startCreditR : startCreditRs) {
            idlist.add(startCreditR.getCallId());
        }
        //根据id值批量的更新
        singleStopOweService.updateStartCredit(idlist);

        map.put("respCode", "0");
        map.put("respDesc", "success");
        map.put("resultRows", String.valueOf(startCreditRs.size()));
        map.put("result", startCreditRs);

        return map;
    }

    /**
     * 星级信用欠费外呼，表由白文成提供
     * 王滨提出2020-06-15
     * 修改status状态,00已呼、01待呼、02正呼)
     */
    @RequestMapping(value = "/findObj/outCall/starCreditWriteBack",method = {RequestMethod.POST} )
    public Map<String, Object> singleStopOweWriteback(@RequestBody Map<String, Object> params ){
        Map<String, Object> map = new HashMap<>();
        if (String.valueOf(params)==null||params.isEmpty()||"".equals(params.get("params"))) {map.put("respCode", "-1");map.put("respDesc", "fail");
            return map;
        }
        @SuppressWarnings("unchecked")
        List<SingleStopOweWritebackPOJO> param = (List<SingleStopOweWritebackPOJO>) params.get("params");
        if (param.size()==0||param.isEmpty()||String.valueOf(param)==null) {
            map.put("respCode", "-1");map.put("respDesc", "fail");
            return map;
        }

        for (int i = 0; i < param.size(); i++) {
            @SuppressWarnings("unchecked")
            Map<String,Object> m = (Map<String,Object>) param.get(i);
            if(m.get("callId")==null||"".equals(m.get("callId"))){map.put("respCode", "-1");map.put("respDesc", "fail"); return map;}
            if(m.get("callNo")==null||"".equals(m.get("callNo"))){map.put("respCode", "-1");map.put("respDesc", "fail"); return map;}
            if(m.get("status")==null||"".equals(m.get("status"))){map.put("respCode", "-1");map.put("respDesc", "fail"); return map;}
        }
        //批量更新
        int rows = singleStopOweService.updateStarCredits(param);
        if (rows>=1) {map.put("respCode", "0");map.put("respDesc", "success");
        }else {map.put("respCode", "-1");map.put("respDesc", "fail");return map;}
        return map;
    }

}


