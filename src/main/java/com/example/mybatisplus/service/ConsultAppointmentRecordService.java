package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.vo.ConsultAppointmentRecordVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-13
 */
public interface ConsultAppointmentRecordService extends IService<ConsultAppointmentRecord> {

    List<ConsultAppointmentRecordVO> getAllRecordByConsultantID(Long consultantId);

    List<ConsultAppointmentRecordVO> getRecordNotFilledInByConsultantID(Long consultantId);

    List<ConsultAppointmentRecordVO> getAllUnfinishedRecordByConsultantID(Long consultantId);

    List<ConsultAppointmentRecordVO> getRecordByConsultantAndStudent(Long cId,String stuName);

    List<ConsultAppointmentRecordVO> getRecordByConsultantAndStudentID(Long cId,Long sId);

    Map<String,List> getUnfinishedAndNotFilledIn(Long consultantId);

    int finishAppointment(Long consultAppointId);

    int countConsultingNum(Long sId);

    List<ConsultAppointmentRecordVO> getStuRecord(Long id);

    List<ConsultAppointmentRecord> getRecordsByStuId(Long SID);

    void deleteUndoneRecords(Long sId);

    JsonResponse showAllRecords();

    JsonResponse showRecordsByConName(String ConName);

    JsonResponse showRecordsByStuName(String StuName);

    JsonResponse showUnfinishedRecords();

    JsonResponse showUnfinishedRecordsByConName(String ConName);

    JsonResponse showUnfinishedRecordsByStuName(String StuName);
}

