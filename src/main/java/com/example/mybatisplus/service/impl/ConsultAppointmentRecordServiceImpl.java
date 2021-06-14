package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import com.example.mybatisplus.mapper.ConsultAppointmentRecordMapper;
import com.example.mybatisplus.service.ConsultAppointmentRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

    @Override
    public List<ConsultAppointmentRecord> getAllRecordByConsultantID(Long consultantId) {
        QueryWrapper<ConsultAppointmentRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ConsultAppointmentRecord::getCId,consultantId);

        List<ConsultAppointmentRecord> records = baseMapper.selectList(wrapper);
        return records;
    }

    /*
    今天及之前
    未完成
    即未填报
    */
    @Override
    public List<ConsultAppointmentRecord> getRecordNotFilledInByConsultantID(Long consultantId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        String date=simpleDateFormat.format(new Date());

        QueryWrapper<ConsultAppointmentRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ConsultAppointmentRecord::getCId,consultantId)
                .le(ConsultAppointmentRecord::getDate,date)
                .eq(ConsultAppointmentRecord::getIsFinished,0);

        List<ConsultAppointmentRecord> records = baseMapper.selectList(wrapper);
        return records;
    }

    @Override
    public List<ConsultAppointmentRecord> getAllUnfinishedRecordByConsultantID(Long consultantId) {
        QueryWrapper<ConsultAppointmentRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ConsultAppointmentRecord::getCId,consultantId)
                .eq(ConsultAppointmentRecord::getIsFinished,0);
        List<ConsultAppointmentRecord> unfinishedRecords = baseMapper.selectList(wrapper);
        return unfinishedRecords;
    }

    @Override
    public List<ConsultAppointmentRecord> getRecordByConsultantAndStudent(Long CID, Long SID) {
        QueryWrapper<ConsultAppointmentRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ConsultAppointmentRecord::getCId,CID)
                .eq(ConsultAppointmentRecord::getSId,SID);
        List<ConsultAppointmentRecord> records = baseMapper.selectList(wrapper);
        return records;
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

}
