package com.qf.util;

import com.qf.pojo.Student;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

	//解析员工
//	public static List<Staff> parseExcel(InputStream is) {
//		List<Staff> list = new ArrayList<>();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		// 创建单元
//		HSSFWorkbook wb;
//		try {
//			wb = new HSSFWorkbook(is);
//			HSSFSheet sheet = wb.getSheetAt(0);
//			int l = sheet.getLastRowNum();
//			for (int i = 1; i <= l; i++) {
//				HSSFRow hr = sheet.getRow(i);
//				Staff staff=new Staff();
//				staff.setNo(hr.getCell(0).getStringCellValue());
//				staff.setName(hr.getCell(1).getStringCellValue());
//				staff.setDid((int)hr.getCell(2).getNumericCellValue());
//				staff.setSex(hr.getCell(3).getStringCellValue());
//				staff.setPhone(new Double(hr.getCell(4).getNumericCellValue()).intValue()+"");
//				staff.setEmail(hr.getCell(5).getStringCellValue());
//				staff.setQq(new Double(hr.getCell(6).getNumericCellValue()).intValue()+"");
//				staff.setCreatedate(sdf.parse(hr.getCell(7).getStringCellValue()));
//				list.add(staff);
//			}
//			wb.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}
	
	//解析学员
	public static List<Student> parseExcelS(InputStream is) {
		List<Student> list = new ArrayList<>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		// 创建单元
		HSSFWorkbook wb;
		try {
			wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
			int l = sheet.getLastRowNum();
			for (int i = 1; i <= l; i++) {
				HSSFRow hr = sheet.getRow(i);
				Student student=new Student();
				student.setSid((int)hr.getCell(0).getNumericCellValue());
				student.setSname(hr.getCell(1).getStringCellValue());
				student.getUser().setUid((int)hr.getCell(2).getNumericCellValue());
				student.setSsex(hr.getCell(3).getStringCellValue());
				student.setSphone(new Double(hr.getCell(4).getNumericCellValue()).intValue()+"");
				student.setSemail(hr.getCell(5).getStringCellValue());
				student.setSage(hr.getCell(6).getStringCellValue());
				student.getClasses().setClass_id((int)hr.getCell(2).getNumericCellValue());
				list.add(student);
			}
			wb.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
