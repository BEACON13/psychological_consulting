package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.mapper.FirstVisitorDutyMapper;
import com.example.mybatisplus.mapper.StudentMapper;
import com.example.mybatisplus.model.domain.*;
import com.example.mybatisplus.mapper.FirstVisitRecordMapper;
import com.example.mybatisplus.model.vo.FirstVisitRecordVO;
import com.example.mybatisplus.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-13
 */
@Service
public class FirstVisitRecordServiceImpl extends ServiceImpl<FirstVisitRecordMapper, FirstVisitRecord> implements FirstVisitRecordService {

    @Autowired
    FirstVisitRecordMapper firstVisitRecordMapper;
    @Autowired
    FirstVisitorDutyMapper firstVisitorDutyMapper;
    @Autowired
    StudentService studentService;
    @Autowired
    TimePeriodService timePeriodService;
    @Autowired
    LocationService locationService;
    @Autowired
    ConsultApplyService consultApplyService;


    /**
     * 描述：显示该初访员所有未完成的预约
     *
     */
    @Override
    public JsonResponse showRecords() {
        //搜索未完成的预约
        Long id = SecurityUtils.getUserInfo().getId();
        List<FirstVisitRecordVO> firstVisitRecordVOS = firstVisitRecordMapper.showRecords(id);

        //搜索未填报的预约
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.format(formatter);
        QueryWrapper<FirstVisitRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FirstVisitRecord::getFvId,id).le(FirstVisitRecord::getDate,date).eq(FirstVisitRecord::getIsFinished,0);
        List<FirstVisitRecord> firstVisitRecords = firstVisitRecordMapper.selectList(wrapper);
        List<Long> ids = new ArrayList<>();
        for ( FirstVisitRecord firstVisitRecord : firstVisitRecords
             ) {
            ids.add(firstVisitRecord.getFvrId());
        }

        //System.out.println(firstVisitRecordVOS);
        //System.out.println(ids);
        Map<String,List> map = new HashMap();
        map.put("unfinished",firstVisitRecordVOS);
        map.put("undo",ids);

        return JsonResponse.success(map,"success!");

    }

    /**
     * 描述：显示该初访员所有预约
     *
     */
    @Override
    public JsonResponse showAllRecords() {
        Long id = SecurityUtils.getUserInfo().getId();
        List<FirstVisitRecordVO> firstVisitRecordVOS = firstVisitRecordMapper.showAllRecords(id);

        //System.out.println(firstVisitRecordVOS);
        return JsonResponse.success(firstVisitRecordVOS,"success!");
    }

    /**
     * 描述：该初访员根据学生姓名搜索是否有属于自己的初访记录
     *
     */
    @Override
    public JsonResponse getRecordsByName(String stuName) {
        Long id = SecurityUtils.getUserInfo().getId();
        List<FirstVisitRecordVO> firstVisitRecordVOS = firstVisitRecordMapper.getRecordsByName(id,stuName);
        return JsonResponse.success(firstVisitRecordVOS,"success!");
    }

    /**
     * 描述：学生查看初访预约记录
     *
     */
    @Override
    public JsonResponse getFVRecord() {
        Long id = SecurityUtils.getCurrentStudentInfo().getSId();
        List<FirstVisitRecordVO> firstVisitRecordVOS = firstVisitRecordMapper.getFVRecord(id);
        return JsonResponse.success(firstVisitRecordVOS,"success!");
    }

    /**
     * 描述：学生提前一天取消初访预约
     *
     */
    @Override
    public JsonResponse manageFVRecord(Long fvrId) {
        FirstVisitRecord firstVisitRecord = firstVisitRecordMapper.selectById(fvrId);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.format(formatter);

        Period p = Period.between(date,firstVisitRecord.getDate());
        long days = p.getDays();

        if(days < 1){
            return JsonResponse.failure("时间小于1天,不可取消预约!");
        }else{
            /**
             * 预约信息被逻辑删除
             */
            UpdateWrapper<FirstVisitRecord> wrapper = new UpdateWrapper<>();
            wrapper.lambda().set(FirstVisitRecord::getIsDeleted,1).eq(FirstVisitRecord::getFvrId,fvrId);
            firstVisitRecordMapper.update(null,wrapper);
            /**
             * 将初访员排班置为1
             */
            UpdateWrapper<FirstVisitorDuty> wrapper2 = new UpdateWrapper<>();
            wrapper2.lambda().set(FirstVisitorDuty::getIsAvailable,1).eq(FirstVisitorDuty::getFvId,firstVisitRecord.getFvId())
                    .eq(FirstVisitorDuty::getTpId,firstVisitRecord.getTpId());
            firstVisitorDutyMapper.update(null,wrapper2);
        }

        return JsonResponse.successMessage("取消预约成功!");
    }

    /*
     * 中心管理员查看所有预约记录
     */
    @Override
    public List<FirstVisitRecordVO> getAllRecordsAdmin() {
        return firstVisitRecordMapper.getAllRecordAdmin();
    }

    /*
     * 中心管理员审核初访申请后，进行插入操作
     */
    @Override
    public int insertFVRecord(FirstVisitRecord record) {
        //给学生邮件系统发送邮件
        Student stu = studentService.getById(record.getSId());
        TimePeriod tp = timePeriodService.getById(record.getTpId());
        Location l = locationService.getById(record.getLocationId());

        //计算初访日期
        LocalDate date = this.getFirstVisitDate(record.getTpId());

        //插入到记录中
        record.setDate(date);

        //填写邮件并发送
//        String toUser = stu.getCode() + "@stu.scu.edu.cn";
//        String text = stu.getName() + "同学你好，你已成功预约初访，请于"+date.toString()+"，即周"+tp.getWeekday().toString()
//                +"的"+tp.getStartTime().toString()+"到达"+l.getLocationName()+"进行初访咨询。请准时参加，谢谢！";
//        consultApplyService.sendMessage(toUser,text);

        return firstVisitRecordMapper.insert(record);
    }

    /*
     * 中心管理员通过初访员姓名查看初访记录
     *
     */
    @Override
    public List<FirstVisitRecordVO> getRecordByFirstVisitorName(String name) {

        return firstVisitRecordMapper.getRecordByFVName(name);
    }

    /*
     * 中心管理员通过初访员姓名查看未完成的初访记录
     *
     */
    @Override
    public List<FirstVisitRecordVO> getRecordByFirstVisitorNameUnfinished(String name) {
        return firstVisitRecordMapper.getRecordByFVNameUnfinished(name);
    }

    /*
     * 中心管理员通过学生姓名查看初访记录
     *
     */
    @Override
    public List<FirstVisitRecordVO> getRecordByStuName(String name) {
        return firstVisitRecordMapper.getRecordByStuName(name);
    }

    /*
     * 计算初访日期
     */
    @Override
    public LocalDate getFirstVisitDate(int tpId) {
        //获取当前日期
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.format(formatter);

        //获取初访预约的时间段信息
        TimePeriod timePeriod = timePeriodService.getById(tpId);

        //预约在星期几
        int FVWeekday=timePeriod.getWeekday();

        //今天是星期几
        int todayWeekday = date.getDayOfWeek().getValue();

        //计算初访日期
        LocalDate firstVisitDate;
        if(FVWeekday > todayWeekday)
            firstVisitDate = date.plusDays(FVWeekday - todayWeekday);
        else
            firstVisitDate = date.plusDays(7 - todayWeekday + FVWeekday);

        return firstVisitDate;
    }

    /*
     * 中心管理员获得所有的未完成记录
     */
    @Override
    public List<FirstVisitRecordVO> getUnfinishedRecord() {
        return firstVisitRecordMapper.getUnfinishedRecord();
    }

    /**
     * 描述：获取学生是否拥有申请初访资格
     *
     */
    @Override
    public List<FirstVisitRecord> getRecordByStudent(Long sId) {
        QueryWrapper<FirstVisitRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FirstVisitRecord::getSId,sId);
        return baseMapper.selectList(wrapper);
    }

}
