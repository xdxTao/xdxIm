package com.xdx97.controller.websocket;


import com.xdx97.bean.KfInfo;
import com.xdx97.bean.SessionList;
import com.xdx97.bean.UserInfo;
import com.xdx97.common.utils.CurUserUtils;
import com.xdx97.common.utils.SpringContextUtil;
import com.xdx97.mapper.KfInfoMapper;
import com.xdx97.mapper.SessionListMapper;
import com.xdx97.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/websocket/{kfId}/{userId}")
//此注解相当于设置访问URL
public class WebSocket {

    @Autowired
    private KfInfoMapper kfInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    private Session session;

    private String kfId;

    @OnOpen
    public void onOpen(Session session,@PathParam(value="kfId")String kfId, @PathParam(value="userId")String userId) {
        this.kfId = kfId;
        this.session = session;
        CurUserUtils.webSockets.put(kfId,this);
        CurUserUtils.curKfSessions.put(kfId,userId);
        if (kfInfoMapper == null){
            this.kfInfoMapper = (KfInfoMapper) SpringContextUtil.getBean("kfInfoMapper");
        }
        KfInfo kfInfo = kfInfoMapper.selectByPrimaryKey(kfId);
        CurUserUtils.curUsers.put(kfId, kfInfo);
        System.out.println("客服上线，当前客服数：" + CurUserUtils.curKfSessions.size());
    }

    @OnClose
    public void onClose() {

        CurUserUtils.webSockets.remove(this.kfId);
        CurUserUtils.curKfSessions.remove(this.kfId);
        CurUserUtils.curUsers.remove(this.kfId);
        System.out.println("客服下线，当前客服数：" + CurUserUtils.curKfSessions.size());
    }

    @OnMessage
    public void onMessage(String message) {

    }

    // 此为广播消息
//    public void sendAllMessage(String message) {
//        for(WebSocket webSocket : webSockets) {
//            System.out.println("【websocket消息】广播消息:"+message);
//            try {
//                webSocket.session.getAsyncRemote().sendText(message);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    // 此为单点消息 (发送文本)
    public void sendTextMessage(String message) {
        try {
            session.getBasicRemote().sendText(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 此为单点消息 (发送对象)
//    public void sendObjMessage(String sessionId, Object message) {
//        Session session = CurPool.sessionPool.get(sessionId);
//        if (session != null) {
//            try {
////                session.getAsyncRemote().sendObject(message);
//                session.getBasicRemote().sendText(JsonUtils.objectToJson(message));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

}