package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.ConsultApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.vo.ConsultApplyVO;
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
public interface ConsultApplyMapper extends BaseMapper<ConsultApply> {

    List<ConsultApplyVO> showAllApplies();

    List<ConsultApplyVO> showApplyByStuName(@Param("stuName") String stuName);

    List<ConsultApplyVO> showUnfinishedApplies();

    List<ConsultApplyVO> showUnfinishedApplyByStuName(@Param("stuName") String stuName);
}

