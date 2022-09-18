package com.vikash.kafkaconsumer.service.impl;

import com.vikash.kafkaconsumer.model.Student;
import com.vikash.kafkaconsumer.repository.StudentRepository;
import com.vikash.kafkaconsumer.service.ReportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public ByteArrayInputStream generateStudentExcel() {
        List<String> columns = Arrays.asList("ID","FIRST_NAME","LAST_NAME","PHONE_NUMBER");
        List<Student> students = studentRepository.getAllStudents();
        if(!CollectionUtils.isEmpty(students)){
            try(
                    Workbook workbook = new XSSFWorkbook();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
             ){
                Sheet sheet = workbook.createSheet("Student");
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontName("Arial");
                headerFont.setColor(IndexedColors.BLACK.getIndex());
                headerFont.setFontHeightInPoints((short) 12);

                DataFormat format = workbook.createDataFormat();

                CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFont(headerFont);
                headerStyle.setBorderTop(BorderStyle.THIN);
                headerStyle.setBorderRight(BorderStyle.THIN);
                headerStyle.setBorderRight(BorderStyle.THIN);
                headerStyle.setBorderBottom(BorderStyle.THIN);
                headerStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setBorderTop(BorderStyle.THIN);
                cellStyle.setBorderRight(BorderStyle.THIN);
                cellStyle.setBorderRight(BorderStyle.THIN);
                cellStyle.setBorderBottom(BorderStyle.THIN);
                cellStyle.setShrinkToFit(true);


                Row headerRow = sheet.createRow(0);

                for(int i=0;i<columns.size();i++){
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns.get(i));
                    cell.setCellStyle(headerStyle);
                }
                int rowIndex = 1;
                for(Student student : students){
                    Row rowData = sheet.createRow(rowIndex++);
                    Cell cell1 = rowData.createCell(0);
                    cell1.setCellValue(student.getId());
                    cell1.setCellStyle(cellStyle);

                    Cell cell2 = rowData.createCell(1);
                    cell2.setCellValue(student.getFirstName());
                    cell2.setCellStyle(cellStyle);

                    Cell cell3 = rowData.createCell(2);
                    cell3.setCellValue(student.getLastName());
                    cell3.setCellStyle(cellStyle);

                    Cell cell4 = rowData.createCell(3);
                    cell4.setCellValue(student.getPhoneNumber());
                    cell4.setCellStyle(cellStyle);

                }
                // column size according to value
                for (int i=0;i<columns.size();i++) {
                    sheet.autoSizeColumn(i);

                }
                workbook.write(out);
               return new ByteArrayInputStream(out.toByteArray());
            }catch (Exception e){

            }
        }
        return null;
    }
}
