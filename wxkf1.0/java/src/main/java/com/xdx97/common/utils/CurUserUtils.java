package com.xdx97.common.utils;

import com.xdx97.bean.KfInfo;
import com.xdx97.bean.UserInfo;
import com.xdx97.controller.websocket.WebSocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用来存放当前客服的用户信息
 */
public class CurUserUtils {
    // 存放当前的 webSocket
    public static Map<String, WebSocket> webSockets = new ConcurrentHashMap<>();
    // 当前登录客服
    public static Map<String, KfInfo> curUsers = new ConcurrentHashMap<>();
    // 当前在线会话 key = 客服id+用户id（openId）
//    public static Map<String, UserInfo> curSessions = new ConcurrentHashMap<>();
    // 当前在线坐席正在会话的用户 key：kf_id   value：openId
    public static Map<String, String> curKfSessions = new ConcurrentHashMap<>();

}
