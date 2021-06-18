package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.model.domain.AddConsult;
import com.example.mybatisplus.mapper.AddConsultMapper;
import com.example.mybatisplus.model.vo.AddConsultVO;
import com.example.mybatisplus.service.AddConsultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-17
 */
@Service
public class AddConsultServiceImpl extends ServiceImpl<AddConsultMapper, AddConsult> implements AddConsultService {

    @Autowired
    AddConsultMapper addConsultMapper;
    /**
     * 描述：咨询师申请追加时间段
     *
     */
    @Override
    public JsonResponse addConsultTP(Map form) {
        Long SId = Long.parseLong(form.get("s_id").toString());
        Long CId = SecurityUtils.getUserInfo().getId();
        Integer tpId = (Integer)form.get("tp_id");
        Integer times = (Integer)form.get("times");

        AddConsult a = new AddConsult();
        a.setSId(SId).setCId(CId).setTpId(tpId).setTpId(tpId).setTimes(times);
        addConsultMapper.insert(a);
        return JsonResponse.successMessage("追加成功!");
    }

    /*
     * 管理员查看所有申请预约记录
     */
    @Override
    public List<AddConsultVO> getAllAddConsult() {
        return addConsultMapper.getAllAddConsult();
    }

    /*
     * 管理员查看所有未完成的申请预约记录
     */
    @Override
    public List<AddConsultVO> getUnfinishedAddConsult() {
        return addConsultMapper.getUnfinishedAddConsult();
    }


    /*
     * 完成追加咨询请求
     */
    @Override
    public int finishAdd(Long addConsultId) {
        UpdateWrapper<AddConsult> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(AddConsult::getAddCId,addConsultId)
                .set(AddConsult::getIsFinished,1);
        return baseMapper.update(null,wrapper);
    }
}
