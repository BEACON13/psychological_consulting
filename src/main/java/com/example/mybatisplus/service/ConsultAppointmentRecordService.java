package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import com.baomidou.mybatisplus.extension.service.IService;

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

    List<ConsultAppointmentRecord> getAllRecordByConsultantID(Long consultantId);

    List<ConsultAppointmentRecord> getRecordNotFilledInByConsultantID(Long consultantId);

    List<ConsultAppointmentRecord> getAllUnfinishedRecordByConsultantID(Long consultantId);

    List<ConsultAppointmentRecord> getRecordByConsultantAndStudent(Long CID,Long SID);

    Map<String,List> getUnfinishedAndNotFilledIn(Long consultantId);

    int finishAppointment(Long consultAppointId);
}
