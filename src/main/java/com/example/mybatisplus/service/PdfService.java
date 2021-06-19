package com.example.mybatisplus.service;


import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.vo.ClosingReportVO;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.List;

public interface PdfService {
    public JsonResponse generatePDF(List<ClosingReportVO> reports) throws Exception;

    public PdfPTable generateTemplate(List<ClosingReportVO> reports) throws Exception;

    public void setStyle(PdfPCell cell);
}
