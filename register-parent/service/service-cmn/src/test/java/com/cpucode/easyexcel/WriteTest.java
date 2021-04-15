package com.cpucode.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/4/15
 * @time : 15:49
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public class WriteTest {
    public static void main(String[] args) {
        //构建数据list集合
        List<UserData> list = new ArrayList();

        for (int i=0;i<10;i++) {
            UserData data = new UserData();

            data.setUid(i);
            data.setUsername("cpucode" + i);

            list.add(data);
        }

        //设置excel文件路径和文件名称
        String fileName = "D:\\cpucode.xlsx";

        //调用方法实现写操作
        EasyExcel.write(fileName, UserData.class)
                .sheet("用户信息")
                .doWrite(list);
    }
}
