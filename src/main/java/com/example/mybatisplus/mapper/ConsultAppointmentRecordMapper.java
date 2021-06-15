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
    List<ConsultAppointmentRecordVO> getRecordsByConsultant(Long c_id);

    List<ConsultAppointmentRecordVO> getRecordNotFilledIn(@Param("id") Long c_id,@Param("date") String date);

    List<ConsultAppointmentRecordVO> getUnfinishedRecords(Long c_id);

    List<ConsultAppointmentRecordVO> getRecordByConsultantAndStudent(@Param("c_id") Long c_id,@Param("s_id") Long s_id);
}
