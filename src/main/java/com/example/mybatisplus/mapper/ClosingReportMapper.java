package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.ClosingReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.vo.ClosingReportVO;
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
public interface ClosingReportMapper extends BaseMapper<ClosingReport> {
    List<ClosingReportVO> getClosingReportByConAndStu(@Param("stu_name") String stu_name,@Param("c_id") Long cId);

    List<ClosingReportVO> getClosingReportByCon(@Param("c_id") Long cId);

    List<ClosingReportVO> getAllClosingReport();

    List<ClosingReportVO> getAllClosingReportByConName(@Param("con_name") String conName);

    List<ClosingReportVO> getAllClosingReportByStuName(@Param("stu_name") String stuName);
}
