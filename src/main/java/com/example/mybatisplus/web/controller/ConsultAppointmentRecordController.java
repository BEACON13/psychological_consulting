package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ConsultAppointmentRecordService;
import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;


/**
 *
 *  前端控制器
 *
 *
 * @author Beacon
 * @since 2021-06-13
 * @version v1.0
 */
@Controller
@RequestMapping("/api/consultAppointmentRecord")
public class ConsultAppointmentRecordController {

    private final Logger logger = LoggerFactory.getLogger( ConsultAppointmentRecordController.class );

    @Autowired
    private ConsultAppointmentRecordService consultAppointmentRecordService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        ConsultAppointmentRecord  consultAppointmentRecord =  consultAppointmentRecordService.getById(id);
        return JsonResponse.success(consultAppointmentRecord);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        consultAppointmentRecordService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateConsultAppointmentRecord(@PathVariable("id") Long  id,ConsultAppointmentRecord  consultAppointmentRecord) throws Exception {
        consultAppointmentRecord.setConsultAppointId(id);
        consultAppointmentRecordService.updateById(consultAppointmentRecord);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建ConsultAppointmentRecord
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(ConsultAppointmentRecord  consultAppointmentRecord) throws Exception {
        consultAppointmentRecordService.save(consultAppointmentRecord);
        return JsonResponse.success(null);
    }
}

