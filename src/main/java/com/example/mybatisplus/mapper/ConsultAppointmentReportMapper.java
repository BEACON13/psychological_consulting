package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.ConsultAppointmentReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.vo.ConsultAppointmentReportVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Beacon
 * @since 2021-06-14
 */

@Repository
public interface ConsultAppointmentReportMapper extends BaseMapper<ConsultAppointmentReport> {

    List<ConsultAppointmentReportVO> getRecordByConAndStu(@Param("c_id") Long cId,@Param("stu_name") String stuName);

    List<ConsultAppointmentReportVO> getRecordByCon(@Param("c_id") Long cId);
}
