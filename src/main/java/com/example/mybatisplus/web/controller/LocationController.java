package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.LocationService;
import com.example.mybatisplus.model.domain.Location;


/**
 *
 *  前端控制器
 *
 *
 * @author Kristy
 * @since 2021-06-18
 * @version v1.0
 */
@Controller
@RequestMapping("/api")
public class LocationController {

    private final Logger logger = LoggerFactory.getLogger( LocationController.class );

    @Autowired
    private LocationService locationService;

    /**
     * 描述：返回地点安排
     *
     */
    @RequestMapping(value="/getLocation",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getLocation(@RequestParam("type")Integer type){
        return locationService.getLocation(type);
    }
}

