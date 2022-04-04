package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.vo.ClosingReportVO;
import com.example.mybatisplus.service.PdfService;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {
    SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
    String formatTimeStr = format.format(new Date());
    SimpleDateFormat format2 =  new SimpleDateFormat("yyyyMMddHHmmss");
    String formatTimeStr2 = format2.format(new Date());
    private Integer size = 20;


    @Override
    public JsonResponse generatePDF(List<ClosingReportVO> reports) throws Exception{

        // 生成文件位置
        String filename = "D:/" + formatTimeStr2 + ".pdf";
        Document document = new Document(PageSize.A4);  // 生成是A4纸
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.addTitle("这是PDF标题");
        document.open();

        PdfPTable table = generateTemplate(reports);
        document.add(table);
        document.close();
        //log.info("生成PDF成功! 生成路径为:{}! 生成时间为:{}", filename, formatTimeStr2);

        return JsonResponse.successMessage("生成PDF成功！");
    }

    /**
     * 生成模板内容
     * @return
     */
    @Override
    public PdfPTable generateTemplate(List<ClosingReportVO> reports) throws Exception{

        Font font = new Font(BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED));
        font.setSize(7);
        PdfPTable table = new PdfPTable(16);
        // 结案报告标题  第一行
        PdfPCell cell1 = new PdfPCell(new Phrase("结案报告", font));
        cell1.setColspan(18);
        setStyle(cell1);
        table.addCell(cell1);

        // 结案报告ID: 第二行
        PdfPCell cell2 = new PdfPCell(new Phrase("结案报告ID:", font));
        cell2.setColspan(2);
        setStyle(cell2);
        table.addCell(cell2);

        // 学生姓名 第二行
        PdfPCell cell3 = new PdfPCell(new Phrase("学生姓名:", font));
        cell3.setColspan(2);
        setStyle(cell3);
        table.addCell(cell3);

        // 咨询师姓名 第二行
        PdfPCell cell4 = new PdfPCell(new Phrase("咨询师姓名:", font));
        cell4.setColspan(2);
        setStyle(cell4);
        table.addCell(cell4);

        // 问题类型: 第二行
        PdfPCell cell5 = new PdfPCell(new Phrase("问题类型:", font));
        cell5.setColspan(2);
        setStyle(cell5);
        table.addCell(cell5);

        // 咨询次数 第二行
        PdfPCell cell6 = new PdfPCell(new Phrase("咨询次数:",font));
        cell6.setColspan(2);
        setStyle(cell6);
        table.addCell(cell6);

        // 咨询师自评 第二行
        PdfPCell cell7 = new PdfPCell(new Phrase("咨询师自评:", font));
        cell7.setColspan(8);
        setStyle(cell7);
        table.addCell(cell7);

        // 根据自己业务封装数据
        for (ClosingReportVO r : reports) {
            PdfPCell cell19 = new PdfPCell(new Phrase(r.getClosingReportId().toString(), font));
            cell19.setColspan(2);    // 占几列
            cell19.setRowspan(1);    // 占几行
            setStyle(cell19);
            table.addCell(cell19);

            PdfPCell cell20 = new PdfPCell(new Phrase(r.getStuName(), font));
            cell20.setColspan(2);    // 占几列
            cell20.setRowspan(1);    // 占几行
            setStyle(cell20);
            table.addCell(cell20);

            PdfPCell cell21 = new PdfPCell(new Phrase(r.getConsultantName(), font));
            cell21.setColspan(2);    // 占几列
            cell21.setRowspan(1);    // 占几行
            setStyle(cell21);
            table.addCell(cell21);

            PdfPCell cell22 = new PdfPCell(new Phrase(r.getProblemType(), font));
            cell22.setColspan(2);    // 占几列
            cell22.setRowspan(1);    // 占几行
            setStyle(cell22);
            table.addCell(cell22);

            PdfPCell cell23 = new PdfPCell(new Phrase(r.getConsultNum().toString(), font));
            cell23.setColspan(2);    // 占几列
            cell23.setRowspan(1);    // 占几行
            setStyle(cell23);
            table.addCell(cell23);

            PdfPCell cell24 = new PdfPCell(new Phrase(r.getConsultEffectSelf(), font));
            cell24.setColspan(8);    // 占几列
            cell24.setRowspan(1);    // 占几行
            setStyle(cell24);
            table.addCell(cell24);
        }

        return table;
    }

    /**
     * 设置样式
     * @param cell
     */
    @Override
    public void setStyle(PdfPCell cell){
        cell.setFixedHeight(size);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
    }

}
