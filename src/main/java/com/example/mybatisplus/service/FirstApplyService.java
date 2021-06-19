package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.FirstApply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.vo.FirstApplyVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-14
 */
public interface FirstApplyService extends IService<FirstApply> {
    int insertFirstApply(FirstApply firstApply);

    List<FirstApply> getFirstApplyByStu(Long sId);

    List<FirstApplyVO> getUrgentApply();

    List<FirstApplyVO> getNormalApply();

    int finishFirstApply(Long firstApplyId);
}
