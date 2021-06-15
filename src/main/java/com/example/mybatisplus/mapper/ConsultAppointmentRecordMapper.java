package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.vo.ConsultAppointmentRecordVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Beacon
 * @since 2021-06-13
 */
@Repository
public interface ConsultAppointmentRecordMapper extends BaseMapper<ConsultAppointmentRecord> {
    List<ConsultAppointmentRecordVO> getRecordsByConsultant(Long cId);

    List<ConsultAppointmentRecordVO> getRecordNotFilledIn(@Param("id") Long cId,@Param("date") String date);

    List<ConsultAppointmentRecordVO> getUnfinishedRecords(Long cId);

    List<ConsultAppointmentRecordVO> getRecordByConsultantAndStudentID(@Param("c_id") Long cId,@Param("s_id") Long sId);

    List<ConsultAppointmentRecordVO> getRecordByConsultantAndStudent(@Param("c_id") Long cId,@Param("stu_name") String stuName);
}

