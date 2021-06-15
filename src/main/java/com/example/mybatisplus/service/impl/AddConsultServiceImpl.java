package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.mapper.StudentMapper;
import com.example.mybatisplus.model.domain.AddConsult;
import com.example.mybatisplus.mapper.AddConsultMapper;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.service.AddConsultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-15
 */
@Service
public class AddConsultServiceImpl extends ServiceImpl<AddConsultMapper, AddConsult> implements AddConsultService {

    @Autowired
    StudentService studentService;
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
}
