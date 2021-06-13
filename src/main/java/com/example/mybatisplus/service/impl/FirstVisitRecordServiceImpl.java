package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.model.domain.FirstVisitRecord;
import com.example.mybatisplus.mapper.FirstVisitRecordMapper;
import com.example.mybatisplus.service.FirstVisitRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-13
 */
@Service
public class FirstVisitRecordServiceImpl extends ServiceImpl<FirstVisitRecordMapper, FirstVisitRecord> implements FirstVisitRecordService {

    @Autowired
    FirstVisitRecordMapper firstVisitRecordMapper;

    @Override
    public JsonResponse showRecord() {

        Long id = SecurityUtils.getUserInfo().getId();
        QueryWrapper<FirstVisitRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FirstVisitRecord::getFvId,id).eq(FirstVisitRecord::getIsFinished,0);
        List<FirstVisitRecord> firstVisitRecords = firstVisitRecordMapper.selectList(wrapper);


        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.format(formatter);
        QueryWrapper<FirstVisitRecord> wrapper2 = new QueryWrapper<>();
        wrapper2.lambda().eq(FirstVisitRecord::getFvId,id).le(FirstVisitRecord::getDate,date).eq(FirstVisitRecord::getIsFinished,0);
        List<FirstVisitRecord> firstVisitRecords2 = firstVisitRecordMapper.selectList(wrapper2);

        //System.out.println(firstVisitRecords);
        //System.out.println(firstVisitRecords2);
        Map<String,List> map = new HashMap();
        map.put("未完成",firstVisitRecords);
        map.put("未填报",firstVisitRecords2);
        return JsonResponse.success(map,"完成！");
    }
}
