package com.cpucode.yygh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.yygh.common.exception.YyghException;
import com.cpucode.yygh.common.helper.JwtHelper;
import com.cpucode.yygh.common.result.ResultCodeEnum;
import com.cpucode.yygh.model.user.UserInfo;
import com.cpucode.yygh.user.mapper.UserInfoMapper;
import com.cpucode.yygh.user.service.UserInfoService;
import com.cpucode.yygh.vo.user.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/4/25
 * @time : 23:14
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 会员登录
     */
    @Override
    public Map<String, Object> loginUser(LoginVo loginVo) {
        //从loginVo获取输入的手机号，和验证码
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();

        //判断手机号和验证码是否为空
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }

        //校验校验验证码
        String mobleCode = redisTemplate.opsForValue().get(phone);
        if(!code.equals(mobleCode)) {
            throw new YyghException(ResultCodeEnum.CODE_ERROR);
        }

        //手机号已被使用
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);

        //获取会员
        UserInfo userInfo = baseMapper.selectOne(queryWrapper);

        //第一次使用这个手机号登录
        if(null == userInfo) {
            //添加信息到数据库
            userInfo = new UserInfo();

            userInfo.setName("");
            userInfo.setPhone(phone);
            userInfo.setStatus(1);

            this.save(userInfo);
        }

        //校验是否被禁用
        if(userInfo.getStatus() == 0) {
            throw new YyghException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        //不是第一次，直接登录
        //返回登录信息
        //返回登录用户名
        //返回token信息
        Map<String, Object> map = new HashMap<>();
        String name = userInfo.getName();

        if(StringUtils.isEmpty(name)) {
            name = userInfo.getNickName();
        }
        if(StringUtils.isEmpty(name)) {
            name = userInfo.getPhone();
        }

        map.put("name", name);

        //jwt生成token字符串
        String token = JwtHelper.createToken(userInfo.getId(), name);
        map.put("token",token);

        return map;
    }


    /**
     * 据openid判断
     * @param openid
     * @return
     */
    @Override
    public UserInfo selectWxInfoOpenId(String openid) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        UserInfo userInfo = baseMapper.selectOne(queryWrapper);

        return userInfo;
    }
}
