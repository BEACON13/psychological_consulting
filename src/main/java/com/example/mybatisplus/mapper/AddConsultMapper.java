package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.AddConsult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.vo.AddConsultVO;
import org.springframework.stereotype.Repository;

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
public interface AddConsultMapper extends BaseMapper<AddConsult> {

    List<AddConsultVO> getAllAddConsult();

    List<AddConsultVO> getUnfinishedAddConsult();
}
