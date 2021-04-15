package com.cpucode.easyexcel;

import com.alibaba.excel.EasyExcel;

/**
 * @author : cpucode
 * @date : 2021/4/15
 * @time : 16:19
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public class ReadTest {
    public static void main(String[] args) {
        // 读取文件路径
        String fileName = "D:\\cpucode.xlsx";

        //调用方法实现读取操作
        EasyExcel.read(fileName, UserData.class,new ExcelListener()).sheet().doRead();
    }
}
