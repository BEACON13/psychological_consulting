package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ConsultAppointmentReportService;
import com.example.mybatisplus.model.domain.ConsultAppointmentReport;


/**
 *
 *  前端控制器
 *
 *
 * @author Beacon
 * @since 2021-06-14
 * @version v1.0
 */
@Controller
@RequestMapping("/api/consultAppointmentReport")
public class ConsultAppointmentReportController {

    private final Logger logger = LoggerFactory.getLogger( ConsultAppointmentReportController.class );

    @Autowired
    private ConsultAppointmentReportService consultAppointmentReportService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        ConsultAppointmentReport  consultAppointmentReport =  consultAppointmentReportService.getById(id);
        return JsonResponse.success(consultAppointmentReport);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        consultAppointmentReportService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateConsultAppointmentReport(@PathVariable("id") Long  id,ConsultAppointmentReport  consultAppointmentReport) throws Exception {
        consultAppointmentReport.setCarId(id);
        consultAppointmentReportService.updateById(consultAppointmentReport);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建ConsultAppointmentReport
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(ConsultAppointmentReport  consultAppointmentReport) throws Exception {
        consultAppointmentReportService.save(consultAppointmentReport);
        return JsonResponse.success(null);
    }
}

