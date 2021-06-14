package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.FirstApply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-14
 */
public interface FirstApplyService extends IService<FirstApply> {
    public int insertFirstApply(FirstApply firstApply);
}
