package com.qf.serviceImpl;

import com.qf.pojo.Student;
import com.qf.service.FileupdownService;
import com.qf.util.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileupdownServiceImpl implements FileupdownService {
    //处理上传的文件
    @Override
    public List getBankListByExcel(InputStream in, String fileName,int class_id) throws Exception {
        System.out.println(class_id);
        List<Student> list = new ArrayList<>();
        //创建excel工作簿
        Workbook work = this.getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                Student student = new Student();
                student.setSname(String.valueOf(row.getCell(0)));
                student.setSsex(String.valueOf(row.getCell(1)));
                student.setSage(String.valueOf(row.getCell(2)));
                double v = NumberUtils.tranPointNum(0, Double.parseDouble(row.getCell(3).toString()));
                student.setSphone((int)v);
                student.setSemail(String.valueOf(row.getCell(4)));
                list.add(student);
            }
        }
        return list;
    }
    //判断文件的格式
    @Override
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }
}
