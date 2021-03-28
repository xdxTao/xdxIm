package com.xdx97.common.handler;

import com.xdx97.bean.*;
import com.xdx97.common.utils.CurUserUtils;
import com.xdx97.common.utils.JsonUtils;
import com.xdx97.common.utils.WxData;
import com.xdx97.mapper.MsgInfoMapper;
import com.xdx97.mapper.SessionListMapper;
import com.xdx97.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 消息处理
 */
@Async
@Component
public class MessageHandler {

    @Autowired
    private SessionListMapper sessionListMapper;

    @Autowired
    private MsgInfoMapper msgInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Transactional
    public void handler(WxBean wxBean){
        System.out.println(wxBean.toString());
        try {
            // 1、判断用户是否已经存在
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(wxBean.getFromUserName());
            if (userInfo == null){
                Map<String, Object> tmp = WxData.getUserInfo(wxBean.getFromUserName());
                userInfo = new UserInfo();
                userInfo.setId(wxBean.getFromUserName());
                userInfo.setUserName(tmp.get("nickname").toString());
                userInfoMapper.insert(userInfo);
            }
            // 1、判断是否已经和在线客服建立连接
            boolean isConnect = false;

            KfInfo kfInfo = new KfInfo();

            for (String key : CurUserUtils.curKfSessions.keySet()){
                if (CurUserUtils.curKfSessions.get(key).equals(wxBean.getFromUserName())){
                    isConnect = true;
                    kfInfo.setId(key.replace(wxBean.getFromUserName(),""));
                    break;
                }
            }
            // 2、使用合适的算法选择一个客服与该用户建立连接  (这里我只有一个客服我就写死了)
            kfInfo.setId("xdx97");
            // 6、消息持久化
            MsgInfo msgInfo = new MsgInfo();
            msgInfo.setId(UUID.randomUUID().toString());
            msgInfo.setKfId(kfInfo.getId());
            msgInfo.setContent(wxBean.getContent());
            msgInfo.setCreateTime(new Date());
            msgInfo.setKfName("小道仙");
            msgInfo.setUnReadFlag(0);
            msgInfo.setUserName(userInfo.getUserName());
            msgInfo.setUserId(wxBean.getFromUserName());
            msgInfo.setSource(0);
            msgInfoMapper.insertSelective(msgInfo);
            if (!isConnect){
                // 3、判断客服是否和该客户建立连接
                SessionList sessionList = new SessionList();
                sessionList.setKfId(kfInfo.getId());
                sessionList.setUserId(wxBean.getFromUserName());
                sessionList = sessionListMapper.selectOne(sessionList);
                if (sessionList != null && sessionList.getId() != null){
                    // 4、建立连接开启会话
                    if (sessionList.getStatus() != 1){
                        sessionList.setConnectCount(sessionList.getConnectCount() + 1);
                    }
                    sessionList.setStatus(1);
                    if (isConnect){
                        sessionList.setUnReadCount(0);
                    }else {
                        sessionList.setUnReadCount(sessionList.getUnReadCount() + 1);
                    }
                    sessionListMapper.updateByPrimaryKeySelective(sessionList);
                }else {
                    sessionList = new SessionList();
                    // 5、未建立 创建会话
                    sessionList.setStatus(1);
                    sessionList.setKfId(kfInfo.getId());
                    sessionList.setUnReadCount(1);
                    sessionList.setConnectCount(1);
                    sessionList.setListName(userInfo.getUserName());
                    sessionList.setUserId(wxBean.getFromUserName());
                    sessionListMapper.insertSelective(sessionList);
                }
                // 7、判断该客服是否在线
                if (!CurUserUtils.curUsers.containsKey(kfInfo.getId())){
                    // 8、不在线结束  return;
                    return;
                }
            }
            WebSocketMessage webSocketMessage = new WebSocketMessage();
            // 9、在线判断该客服当前选中会话
            if (CurUserUtils.curKfSessions.get(kfInfo.getId()).equals(wxBean.getFromUserName())){
                // 10、当前会话选择 推送会话消息
                webSocketMessage.setStatus(1);
                webSocketMessage.setData(msgInfo);

            }else {
                // 11、非当前会话推送消息列表
                webSocketMessage.setStatus(2);
                List<SessionList> listList = sessionListMapper.select(new SessionList());
                webSocketMessage.setData(listList);
            }
            System.out.println(JsonUtils.objectToJson(webSocketMessage));
            CurUserUtils.webSockets.get(kfInfo.getId()).sendTextMessage(JsonUtils.objectToJson(webSocketMessage));
        }catch (RuntimeException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("接受消息异常。。。");
            e.printStackTrace();
        }

    }

}
