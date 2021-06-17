package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import com.example.mybatisplus.mapper.ConsultAppointmentRecordMapper;
import com.example.mybatisplus.model.vo.ConsultAppointmentRecordVO;
import com.example.mybatisplus.service.ConsultAppointmentRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-13
 */
@Service
public class ConsultAppointmentRecordServiceImpl extends ServiceImpl<ConsultAppointmentRecordMapper, ConsultAppointmentRecord> implements ConsultAppointmentRecordService {

    @Autowired
    ConsultAppointmentRecordMapper cram;



    /*
    根据咨询师id获取其所有的咨询记录
     */
    @Override
    public List<ConsultAppointmentRecordVO> getAllRecordByConsultantID(Long consultantId) {

        return cram.getRecordsByConsultant(consultantId);

    }


    /*
    今天及之前
    未完成
    即未填报
    */
    @Override
    public List<ConsultAppointmentRecordVO> getRecordNotFilledInByConsultantID(Long consultantId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        String date=simpleDateFormat.format(new Date());

        return cram.getRecordNotFilledIn(consultantId,date);
    }

    @Override
    public List<ConsultAppointmentRecordVO> getAllUnfinishedRecordByConsultantID(Long consultantId) {

        return cram.getUnfinishedRecords(consultantId);
    }


    @Override
    public List<ConsultAppointmentRecordVO> getRecordByConsultantAndStudent(Long cId, String stuName) {
        return cram.getRecordByConsultantAndStudent(cId,stuName);
    }

    @Override
    public List<ConsultAppointmentRecordVO> getRecordByConsultantAndStudentID(Long cId, Long sId) {
        return cram.getRecordByConsultantAndStudentID(cId,sId);
    }



    @Override
    public Map<String, List> getUnfinishedAndNotFilledIn(Long consultantId) {
        Map<String,List> map=new HashMap<>();
        map.put("未完成",getAllUnfinishedRecordByConsultantID(consultantId));
        map.put("未填报",getRecordNotFilledInByConsultantID(consultantId));
        return map;
    }

    /*
    当填写报告之后，调用该service，将咨询记录设置为已完成
     */
    @Override
    public int finishAppointment(Long consultAppointId) {
        UpdateWrapper<ConsultAppointmentRecord> wrapper = new UpdateWrapper();
        wrapper.lambda()
                .eq(ConsultAppointmentRecord::getConsultAppointId,consultAppointId)
                .set(ConsultAppointmentRecord::getIsFinished,1);
        return baseMapper.update(null,wrapper);
    }

    /*
    获取特定学生咨询的数目
    */
    @Override
    public int countConsultingNum(Long sId) {
        QueryWrapper<ConsultAppointmentRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ConsultAppointmentRecord::getSId,sId)
                .eq(ConsultAppointmentRecord::getIsFinished,1);
        return baseMapper.selectCount(wrapper);
    }

    @Override
    public List<ConsultAppointmentRecordVO> getStuRecord(Long id) {

        return cram.getRecordByStu(id);
    }

    /**
     * 描述：根据学生ID获取记录，查看学生是否有资格申请咨询
     *
     */
    @Override
    public List<ConsultAppointmentRecord> getRecordsByStuId(Long SID) {
        QueryWrapper<ConsultAppointmentRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ConsultAppointmentRecord::getSId,SID).eq(ConsultAppointmentRecord::getIsFinished,0);
        List<ConsultAppointmentRecord> consultAppointmentRecords = cram.selectList(wrapper);

        return consultAppointmentRecords;
    }

    /*
    删除某学生还没有进行的咨询记录
     */
    @Override
    public void deleteUndoneRecords(Long sId) {
        UpdateWrapper<ConsultAppointmentRecord> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(ConsultAppointmentRecord::getSId,sId)
                .eq(ConsultAppointmentRecord::getIsFinished,0)
                .set(ConsultAppointmentRecord::getIsDeleted,1);

        baseMapper.update(null,wrapper);
    }


    /**
     * 描述：心理助理查看所有预约记录（包括各咨询师、各学生）
     *
     */
    @Override
    public JsonResponse showAllRecords() {
        List<ConsultAppointmentRecordVO> consultAppointmentRecordVOS = cram.showAllRecords();

        return JsonResponse.success(consultAppointmentRecordVOS,"success!");
    }


    /**
     * 描述：心理助理根据咨询师姓名查看咨询预约记录
     *
     */
    @Override
    public JsonResponse showRecordsByConName(String ConName) {
        List<ConsultAppointmentRecordVO> consultAppointmentRecordVOS = cram.showRecordsByConName(ConName);
        return JsonResponse.success(consultAppointmentRecordVOS,"success!");
    }


    /**
     * 描述：心理助理根据学生姓名查看咨询预约记录
     *
     */
    @Override
    public JsonResponse showRecordsByStuName(String StuName) {
        List<ConsultAppointmentRecordVO> consultAppointmentRecordVOS = cram.showRecordsByStuName(StuName);
        return JsonResponse.success(consultAppointmentRecordVOS,"success!");
    }



    /**
     * 描述：心理助理查看所有未完成的预约记录（包括各咨询师、各学生）
     *
     */
    @Override
    public JsonResponse showUnfinishedRecords() {
        List<ConsultAppointmentRecordVO> consultAppointmentRecordVOS = cram.showUnfinishedRecords();
        return JsonResponse.success(consultAppointmentRecordVOS,"success!");
    }


    /**
     * 描述：心理助理根据咨询师的姓名查看所有未完成的预约记录
     *
     */
    @Override
    public JsonResponse showUnfinishedRecordsByConName(String ConName) {
        List<ConsultAppointmentRecordVO> consultAppointmentRecordVOS = cram.showUnfinishedRecordsByConName(ConName);
        return JsonResponse.success(consultAppointmentRecordVOS,"success!");
    }


    /**
     * 描述：心理助理根据学生的姓名查看所有未完成的预约记录
     *
     */
    @Override
    public JsonResponse showUnfinishedRecordsByStuName(String StuName) {
        List<ConsultAppointmentRecordVO> consultAppointmentRecordVOS = cram.showUnfinishedRecordsByStuName(StuName);
        return JsonResponse.success(consultAppointmentRecordVOS,"success!");
    }


    /**
     * 描述：心理助理修改某条预约记录
     *
     */
    @Override
    public JsonResponse manageRecords(Map form) {
        Long carID = Long.parseLong(form.get("car_id").toString());
        Long sID = Long.parseLong(form.get("s_id").toString());
        Integer tpID = (Integer)form.get("tp_id");
        Long lID = Long.parseLong(form.get("l_id").toString());
        Long cID = Long.parseLong(form.get("c_id").toString());
        LocalDate date = LocalDate.parse((String)form.get("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        UpdateWrapper<ConsultAppointmentRecord> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(ConsultAppointmentRecord::getConsultAppointId,carID)
                .set(ConsultAppointmentRecord::getSId,sID)
                .set(ConsultAppointmentRecord::getTpId,tpID)
                .set(ConsultAppointmentRecord::getLocationId,lID)
                .set(ConsultAppointmentRecord::getCId,cID)
                .set(ConsultAppointmentRecord::getDate,date);
        cram.update(null,wrapper);
        return null;
    }


}
