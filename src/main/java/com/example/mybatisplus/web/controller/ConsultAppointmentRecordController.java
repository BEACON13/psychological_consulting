package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.model.domain.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ConsultAppointmentRecordService;
import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;

import java.util.List;
import java.util.Map;


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
     * 描述：获得某咨询师的所有记录
     *
     */
    @RequestMapping(value = "/appoint/record/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getAllRecord(){
        Person consultant = SecurityUtils.getCurrentUserInfo();
        List records = consultAppointmentRecordService
                .getAllRecordByConsultantID(consultant.getPId());
        return JsonResponse.success(records);
    }

    /**
     * 描述：查看某心理咨询师已进行但未填写的所有咨询
     * 即已经进行了咨询活动但是没有填写咨询报告的咨询记录
     */
    @RequestMapping(value = "/appoint/record/notfilled", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getRecordNotFilledIn(){
        Person consultant = SecurityUtils.getCurrentUserInfo();
        List records = consultAppointmentRecordService
                .getRecordNotFilledInByConsultantID(consultant.getPId());
        return JsonResponse.success(records);
    }

    /**
     * 描述：查看某心理咨询师未完成的咨询
     * 不论日期
     */
    @RequestMapping(value = "/appoint/record/unfinished", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getUnfinishedRecord(){
        Person consultant = SecurityUtils.getCurrentUserInfo();
        List records = consultAppointmentRecordService
                .getAllUnfinishedRecordByConsultantID(consultant.getPId());
        return JsonResponse.success(records);
    }


    /**
     * 描述：查看某心理咨询师与某学生共同参与的咨询记录
     * 不论日期
     */
    @RequestMapping(value = "/appoint/record/constu", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getUnfinishedRecord(@RequestParam("studentId") Long sId){
        Person consultant = SecurityUtils.getCurrentUserInfo();
        List records = consultAppointmentRecordService
                .getRecordByConsultantAndStudent(consultant.getPId(),sId);
        return JsonResponse.success(records);
    }

    /**
     * 描述：获取未完成的和未填写的
     * 以map封装
     */
    @RequestMapping(value = "/appoint/record/show", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getUnfinishedAndNotFilled(){
        Person consultant = SecurityUtils.getCurrentUserInfo();
        Map map = consultAppointmentRecordService
                .getUnfinishedAndNotFilledIn(consultant.getPId());
        return JsonResponse.success(map);
    }



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

