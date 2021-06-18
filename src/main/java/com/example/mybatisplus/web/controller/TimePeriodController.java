package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.TimePeriodService;
import com.example.mybatisplus.model.domain.TimePeriod;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;


/**
 *
 *  前端控制器
 *
 *
 * @author Beacon
 * @since 2021-06-16
 * @version v1.0
 */
@Controller
@RequestMapping("/api")
public class TimePeriodController {

    private final Logger logger = LoggerFactory.getLogger( TimePeriodController.class );

    @Autowired
    private TimePeriodService timePeriodService;

    /**
     * 描述：返回时间段安排
     *
     */
    @RequestMapping(value="/getTimePeriod",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getTimePeriod(){
        return timePeriodService.getTimePeriod();
    }

    /*
     * 插入新时间段
     */
    @RequestMapping(value="admin/insert/timePeriod")
    @ResponseBody
    public JsonResponse insertTimePeriod(@RequestBody Map<String,Object> info){
        TimePeriod tp = new TimePeriod();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
        tp.setStartTime(LocalTime.parse((String) info.get("startTime"),df))
                .setDuration((Integer) info.get("duration"))
                .setWeekday((Integer) info.get("weekday"));

        return timePeriodService.insertTimePeriod(tp)?
                JsonResponse.successMessage("插入成功"):
                JsonResponse.failure("插入出错");
    }

    /*
     * 删除时间段
     *
     */
    @RequestMapping(value="admin/delete/timePeriod")
    @ResponseBody
    public JsonResponse deleteTimePeriod(@RequestParam("tp_id")Long tpId){
        return timePeriodService.deleteTimePeriod(tpId)>0?
                JsonResponse.successMessage("删除成功"):
                JsonResponse.failure("删除失败");
    }

}

