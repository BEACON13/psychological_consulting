package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import com.example.mybatisplus.mapper.ConsultAppointmentRecordMapper;
import com.example.mybatisplus.model.vo.ConsultAppointmentRecordVO;
import com.example.mybatisplus.service.ConsultAppointmentRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ConsultAppointmentRecordVO> getRecordByConsultantAndStudent(Long CID, Long SID) {
        return cram.getRecordByConsultantAndStudent(CID,SID);
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
    @Override
    public List<ConsultAppointmentRecord> getAllRecordByConsultantID(Long consultantId) {
        QueryWrapper<ConsultAppointmentRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ConsultAppointmentRecord::getCId,consultantId);

        List<ConsultAppointmentRecord> records = baseMapper.selectList(wrapper);
        return records;
    }
     */


    /*
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

     */

    /*
    @Override
    public List<ConsultAppointmentRecord> getAllUnfinishedRecordByConsultantID(Long consultantId) {
        QueryWrapper<ConsultAppointmentRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ConsultAppointmentRecord::getCId,consultantId)
                .eq(ConsultAppointmentRecord::getIsFinished,0);
        List<ConsultAppointmentRecord> unfinishedRecords = baseMapper.selectList(wrapper);
        return unfinishedRecords;
    }

     */

    /*
    @Override
    public List<ConsultAppointmentRecord> getRecordByConsultantAndStudent(Long CID, Long SID) {
        QueryWrapper<ConsultAppointmentRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ConsultAppointmentRecord::getCId,CID)
                .eq(ConsultAppointmentRecord::getSId,SID);
        List<ConsultAppointmentRecord> records = baseMapper.selectList(wrapper);
        return records;
    }

     */

}
