package com.xdx97.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 封装静态变量
 */
@Configuration
public class StaticInfo {

    public static String wxGrantType;
    @Value("${wx.grant-type}")
    public void setWxGrantType(String wxGrantType){
        StaticInfo.wxGrantType = wxGrantType;
    }

    public static String wxAppid;
    @Value("${wx.appid}")
    public void setWxAppid(String wxAppid){
        StaticInfo.wxAppid = wxAppid;
    }

    public static String wxSecret;
    @Value("${wx.secret}")
    public void setWxSecret(String wxSecret){
        StaticInfo.wxSecret = wxSecret;
    }

}
