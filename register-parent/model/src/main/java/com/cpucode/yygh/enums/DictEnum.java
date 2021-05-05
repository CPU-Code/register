package com.cpucode.yygh.enums;

/**
 * @author : cpucode
 * @date : 2021/5/5
 * @time : 16:19
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public enum DictEnum {
    HOSTYPE("Hostype", "医院等级"),
    CERTIFICATES_TYPE("CertificatesType", "证件类型"),
    ;

    private String dictCode;
    private String msg;

    DictEnum(String dictCode, String msg) {
        this.dictCode = dictCode;
        this.msg = msg;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
