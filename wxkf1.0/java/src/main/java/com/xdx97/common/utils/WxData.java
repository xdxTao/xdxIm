package com.xdx97.common.utils;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取微信数据
 */
public class WxData {

    private static Map<String,Object> map = new HashMap<>();

    // 获取 access_token
    public static String getToken(){

        if (map != null && !map.isEmpty()){
            if (new Date().getTime() - Long.parseLong(map.get("expire").toString()) <= 1000 * 7000){
                return map.get("token").toString();
            }
        }
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + StaticInfo.wxGrantType + "&appid="+StaticInfo.wxAppid+"&secret="+StaticInfo.wxSecret;
        String s = HttpUtils.sendGetForm(url, null);
        Map<String, Object> tmpMap = JsonUtils.jsonToMap(s);
        map.put("token",tmpMap.get("access_token").toString());
        map.put("expire",new Date().getTime());
        return map.get("token").toString();
    }

    // 获取用户数据
    public static Map<String, Object> getUserInfo(String openId){
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+getToken()+"&openid="+openId+"&lang=zh_CN";
        String s = HttpUtils.sendGetForm(url, null);
        Map<String, Object> tmpMap = JsonUtils.jsonToMap(s);
        return tmpMap;
    }

}
