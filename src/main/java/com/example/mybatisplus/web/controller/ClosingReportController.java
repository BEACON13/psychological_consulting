package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.model.vo.ClosingReportVO;
import com.example.mybatisplus.service.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.ClosingReport;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
@RequestMapping("/api")
public class ClosingReportController {

    private final Logger logger = LoggerFactory.getLogger( ClosingReportController.class );

    @Autowired
    private ClosingReportService closingReportService;

    @Autowired
    private ConsultAppointmentRecordService consultAppointmentRecordService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ConsultAppointmentReportService consultAppointmentReportService;

    @Autowired
    private PdfService pdfService;


    /*
     *插入结案报告
     */
    @RequestMapping(value = "/consultant/insert/closingReport")
    @ResponseBody
    public JsonResponse insertClosingReport(@RequestBody Map<String,Object> info) throws Exception {

        //获取sId
        Long sId = Long.parseLong(info.get("sId").toString());

        //填写内容
        ClosingReport report = new ClosingReport();
        report.setCId(Long.parseLong(info.get("cId").toString()))
                .setConsultEffectSelf((String) info.get("consultEffectSelf"))
                .setProblemType((String) info.get("problemType"))
                .setSId(sId);

        //计算学生完成的咨询次数
        int num = consultAppointmentRecordService.countConsultingNum(sId);
        report.setConsultNum(num);

        closingReportService.save(report);

        //修改学生is_qualified字段
        studentService.setUnQualified(sId);

        //去除咨询record中没有finish的
        consultAppointmentRecordService.deleteUndoneRecords(sId);

        return JsonResponse.successMessage("插入完成");
    }


    /*
     * 检查是否允许插入结案报告
     * 检查内容:
     *     1.查看学生is_qualified字段是否为1
     *       因为插入结案报告时会将该字段置为0
     *     2.检查学生最后一次咨询报告的内容是否为“结案”
     */
    @RequestMapping(value = "/consultant/insert/closingReport/allow",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse insertClosingReportQualification(@RequestParam("student_id") Long id){
        return (studentService.isQualified(id) &&
                consultAppointmentReportService.isLastRecordClosed(id)) ?
                JsonResponse.successMessage("可以填写！") :
                JsonResponse.failure("不满足填写要求");
    }

    /*
     * 咨询师获得自己和某个学生咨询的结案报告
     */
    @RequestMapping(value = "/consultant/show/closingReport/stu", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getClosingReportByConAndStu(@RequestParam("stu_name") String stuName){
        Long cId= SecurityUtils.getCurrentUserInfo().getPId();
        return JsonResponse.success(closingReportService
                .getClosingReportByConAndStu(stuName,cId));
    }

    /*
     * 咨询师获得自己撰写的结案报告
     */
    @RequestMapping(value = "/consultant/show/closingReport/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getClosingReportByCon(){
        return JsonResponse.success(closingReportService
                .getClosingReportByCon(SecurityUtils.getCurrentUserInfo().getPId()));
    }

    /*
     * 展示所有结案报告
     */
    @RequestMapping(value = "/admin/show/closingReport/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getAllClosingReport(){
        return JsonResponse.success(closingReportService.getAllClosingReport());
    }

    /*
     * 展示结案报告
     * 根据咨询师姓名
     */
    @RequestMapping(value = "/admin/show/closingReport/con", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getClosingReportByConName(@RequestParam("con_name")String conName){
        return JsonResponse.success(closingReportService.getAllClosingReportByConName(conName));
    }

    /*
     * 展示所有结案报告
     * 根据学生姓名
     */
    @RequestMapping(value = "/admin/show/closingReport/stu", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getClosingReportByStuName(@RequestParam("stu_name")String stuName){
        return JsonResponse.success(closingReportService.getAllClosingReportByStuName(stuName));
    }

    /*
     * 展示所有结案报告
     * 根据咨询师姓名和学生姓名
     */
    @RequestMapping(value = "/admin/show/closingReport/stuAndCon", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getClosingReportByStuName(@RequestParam("stu_name")String stuName, @RequestParam("con_name")String conName){
        return JsonResponse.success(closingReportService.getAllClosingReportByStuAndConName(conName,stuName));
    }

    /**
     * 描述：打印全部结案报告
     *
     */
    @RequestMapping(value = "/admin/printAllPDF")
    @ResponseBody
    public JsonResponse printAllPDF() throws Exception {
        List<ClosingReportVO> allClosingReport = closingReportService.getAllClosingReport();
        return pdfService.generatePDF(allClosingReport);
    }


    /**
     * 描述：根据咨询师姓名打印结案报告
     *
     */
    @RequestMapping(value = "/admin/printPDFByCon")
    @ResponseBody
    public JsonResponse printPDFByCon(@RequestParam("con_name")String conName) throws Exception {
        List<ClosingReportVO> allClosingReportByConName = closingReportService.getAllClosingReportByConName(conName);
        return pdfService.generatePDF(allClosingReportByConName);
    }


    /**
     * 描述：根据学生姓名打印结案报告
     *
     */
    @RequestMapping(value = "/admin/printPDFByStu")
    @ResponseBody
    public JsonResponse printPDFByStu(@RequestParam("stu_name")String stuName) throws Exception {
        List<ClosingReportVO> allClosingReportByStuName = closingReportService.getAllClosingReportByStuName(stuName);
        return pdfService.generatePDF(allClosingReportByStuName);
    }
}
