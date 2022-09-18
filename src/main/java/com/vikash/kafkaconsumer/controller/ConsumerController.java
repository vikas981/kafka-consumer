package com.vikash.kafkaconsumer.controller;

import com.vikash.kafkaconsumer.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

@RestController
public class ConsumerController {


    @Autowired
    private ReportService reportService;

    @GetMapping("/export")
    public ResponseEntity<InputStreamSource> generateExcelData(HttpServletResponse response){
           response.setContentType("application/octet-stream");
           String headerKey = "Content-Disposition";
           String headerValue = "attachment; filename=students.xlsx";
           response.setHeader(headerKey,headerValue);
           ByteArrayInputStream in = reportService.generateStudentExcel();
           return ResponseEntity.ok().body(new InputStreamResource(in));
    }
}
