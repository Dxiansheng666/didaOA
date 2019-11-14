package com.qf.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileupdownService {
    /**
     * 处理上传的文件
     * @param in
     * @param fileName
     * @return
     */
    public List getBankListByExcel(InputStream in, String fileName,int class_id) throws Exception;
    /*
    判断文件的格式
     */
    public Workbook getWorkbook(InputStream inStr, String fileName) throws IOException, Exception;
}
