package com.example.mybatisplus.common.utls;


import com.example.mybatisplus.model.vo.ClosingReportVO;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PdfUtil {
    public void createPDF(Document document, PdfWriter writer, List<ClosingReportVO> reports) throws IOException {
        //Document document = new Document(PageSize.A4);
        try {
            document.addTitle("结案报告");
            document.addAuthor("kristy");
            document.addSubject("结案报告");
            document.addKeywords("结案报告");
            document.open();
            PdfPTable table = createTable(writer,reports);
            document.add(table);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public static PdfPTable createTable(PdfWriter writer,List<ClosingReportVO> reports) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(3);//生成一个两列的表格
        PdfPCell cell;
        int size = 20;
        Font font = new Font(BaseFont.createFont("C://Windows//Fonts//simfang.ttf", BaseFont.IDENTITY_H,
                BaseFont.NOT_EMBEDDED));
        for(int i = 0;i<reports.size();i++) {
            cell = new PdfPCell(new Phrase(reports.get(i).getClosingReportId().toString(),font));//结案报告id
            cell.setFixedHeight(size);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(reports.get(i).getStuName(),font));//学生姓名
            cell.setFixedHeight(size);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(reports.get(i).getConsultantName(),font));//咨询师姓名
            cell.setFixedHeight(size);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(reports.get(i).getProblemType(),font));//问题类型
            cell.setFixedHeight(size);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(reports.get(i).getConsultNum().toString(),font));//咨询次数
            cell.setFixedHeight(size);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(reports.get(i).getConsultEffectSelf(),font));//咨询师自评
            cell.setFixedHeight(size);
            table.addCell(cell);
        }
        return table;
    }

}
