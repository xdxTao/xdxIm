package com.xdx97.controller;

import com.xdx97.bean.MsgInfo;
import com.xdx97.bean.SessionList;
import com.xdx97.bean.WxMsg;
import com.xdx97.common.bean.AjaxResult;
import com.xdx97.common.utils.HttpUtils;
import com.xdx97.common.utils.JsonUtils;
import com.xdx97.common.utils.WxData;
import com.xdx97.mapper.MsgInfoMapper;
import com.xdx97.mapper.SessionListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 消息相关
 */
@RestController
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private MsgInfoMapper msgInfoMapper;

    @Autowired
    private SessionListMapper sessionMapper;
    /**
     * 消息列表
     *
     * @param openId
     */
    @GetMapping("/list")
    public AjaxResult<?> sendMsg(@RequestParam String openId){
        // 消息列表
        MsgInfo msgInfo = new MsgInfo();
        msgInfo.setUserId(openId);
        List<MsgInfo> msgInfos = msgInfoMapper.select(msgInfo);
        // 清空会话
        SessionList sessionList = new SessionList();
        sessionList.setUserId(openId);
        sessionList.setUnReadCount(0);
        sessionMapper.updateByOpenIdSelective(sessionList);
        return AjaxResult.success(msgInfos);
    }

    /**
     * 发送消息
     */
    @PostMapping("/send")
    public AjaxResult<?> sendMsg(@RequestBody MsgInfo msgInfo){
        // 1、消息持久化
        msgInfo.setId(UUID.randomUUID().toString());
        msgInfo.setCreateTime(new Date());
        msgInfo.setUnReadFlag(0);
        msgInfo.setSource(1);
        msgInfoMapper.insertSelective(msgInfo);

        // 2、发送消息到微信
        Map<String,String> content = new HashMap<>();
        content.put("content", msgInfo.getContent().substring(0, msgInfo.getContent().length()-1));
        WxMsg wxMsg = new WxMsg();
        wxMsg.setTouser(msgInfo.getUserId());
        wxMsg.setMsgtype("text");
        wxMsg.setText(content);
        System.out.println(JsonUtils.objectToJson(wxMsg));

        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+ WxData.getToken();
        String s = HttpUtils.sendPostJson(url, JsonUtils.objectToJson(wxMsg));
        System.out.println(s);
        return AjaxResult.success();
    }
}
