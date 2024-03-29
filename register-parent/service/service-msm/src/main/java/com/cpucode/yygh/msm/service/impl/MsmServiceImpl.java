package com.cpucode.yygh.msm.service.impl;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.cpucode.yygh.msm.service.MsmService;

import com.cpucode.yygh.vo.msm.MsmVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

import java.util.Set;

/**
 * @author : cpucode
 * @date : 2021/4/26
 * @time : 11:46
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(String phone, String code) {
        //判断手机号是否为空
        if(StringUtils.isEmpty(phone)) {
            return false;
        }

        //生产环境请求地址：app.cloopen.com
        String serverIp = "app.cloopen.com";

        //请求端口
        String serverPort = "8883";
        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        String accountSId = "8a216da878d4250201790c592a1c14e9";
        String accountToken = "2d720bdd3f2e434e9914053fd7c29d5e";
        //请使用管理控制台中已创建应用的APPID
        String appId = "8a216da878d4250201790c592af614ef";

        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);

        String to = phone;
        String templateId = "1";
        String[] datas = {code, "3"};


        HashMap<String, Object> result = sdk.sendTemplateSMS(to, templateId, datas);

        if ("000000".equals(result.get("statusCode"))) {
            //正常返回输出data包体信息（map）
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();

            for (String key : keySet) {
                Object object = data.get(key);
                System.out.println(key + " = " + object);
            }

            return true;
        } else {
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));

            return false;
        }
    }

    /**
     * mq使用发送短信
     * @param msmVo
     * @return
     */
    @Override
    public boolean send(MsmVo msmVo) {
        if(!StringUtils.isEmpty(msmVo.getPhone())) {
            String code = (String)msmVo.getParam().get("code");

            return this.send(msmVo.getPhone(), code);
        }

        return false;
    }


}

