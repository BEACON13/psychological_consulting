package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.vo.ConsultAppointmentRecordVO;
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
}
