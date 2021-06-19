package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.FirstVisitorDuty;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.vo.FirstVisitorDutyVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kristy
 * @since 2021-06-15
 */
@Repository
public interface FirstVisitorDutyMapper extends BaseMapper<FirstVisitorDuty> {

    List<FirstVisitorDutyVO> getAllFVDuty();

    List<FirstVisitorDutyVO> getAvailableFVDuty();

    List<FirstVisitorDutyVO> getAvailableFVDutyInTp(@Param("tp_id") int tpId);

}
