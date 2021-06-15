package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.mapper.StudentMapper;
import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import com.example.mybatisplus.model.domain.FirstVisitRecord;
import com.example.mybatisplus.mapper.FirstVisitRecordMapper;
import com.example.mybatisplus.model.vo.FirstVisitRecordVO;
import com.example.mybatisplus.service.FirstVisitRecordService;
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

        System.out.println(firstVisitRecordVOS);
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
            UpdateWrapper<FirstVisitRecord> wrapper = new UpdateWrapper<>();
            wrapper.lambda().set(FirstVisitRecord::getIsDeleted,1).eq(FirstVisitRecord::getFvrId,fvrId);
            firstVisitRecordMapper.update(null,wrapper);
            /**
             * 将初访员排班置为1
             */
        }

        return JsonResponse.successMessage("取消预约成功!");
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
