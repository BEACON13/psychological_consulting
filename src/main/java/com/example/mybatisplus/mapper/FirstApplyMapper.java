package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.FirstApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.vo.FirstApplyVO;
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
public interface FirstApplyMapper extends BaseMapper<FirstApply> {

    List<FirstApplyVO> getUrgentApply();

    List<FirstApplyVO> getNormalApply();
}
