package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.ConsultantDuty;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.vo.ConsultantDutyVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kristy
 * @since 2021-06-17
 */
@Repository
public interface ConsultantDutyMapper extends BaseMapper<ConsultantDuty> {

    List<ConsultantDutyVO> showFreeTime(@Param("tpID") Integer tpID);

    List<ConsultantDutyVO> showConsultantDuty();
}
