package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.AddConsult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.vo.AddConsultVO;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-17
 */
public interface AddConsultService extends IService<AddConsult> {
    JsonResponse addConsultTP(Map form);

    List<AddConsultVO> getAllAddConsult();

    List<AddConsultVO> getUnfinishedAddConsult();

    int finishAdd(Long addConsultId);
}
