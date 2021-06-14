package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.FirstVisitReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.vo.FirstVisitReportVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kristy
 * @since 2021-06-14
 */

@Repository
public interface FirstVisitReportMapper extends BaseMapper<FirstVisitReport> {

    List<FirstVisitReportVO> getFVReportsByName(Long id, String stuName);

    List<FirstVisitReportVO> showAllFVReports(Long id);

}
