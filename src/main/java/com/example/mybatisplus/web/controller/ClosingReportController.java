package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ClosingReportService;
import com.example.mybatisplus.model.domain.ClosingReport;


/**
 *
 *  前端控制器
 *
 *
 * @author Beacon
 * @since 2021-06-14
 * @version v1.0
 */
@Controller
@RequestMapping("/api/closingReport")
public class ClosingReportController {

    private final Logger logger = LoggerFactory.getLogger( ClosingReportController.class );

    @Autowired
    private ClosingReportService closingReportService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        ClosingReport  closingReport =  closingReportService.getById(id);
        return JsonResponse.success(closingReport);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        closingReportService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateClosingReport(@PathVariable("id") Long  id,ClosingReport  closingReport) throws Exception {
        closingReport.setClosingReportId(id);
        closingReportService.updateById(closingReport);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建ClosingReport
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(ClosingReport  closingReport) throws Exception {
        closingReportService.save(closingReport);
        return JsonResponse.success(null);
    }
}

