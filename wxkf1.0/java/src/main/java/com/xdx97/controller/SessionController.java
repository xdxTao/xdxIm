package com.xdx97.controller;

import com.xdx97.bean.MsgInfo;
import com.xdx97.bean.SessionList;
import com.xdx97.common.bean.AjaxResult;
import com.xdx97.mapper.MsgInfoMapper;
import com.xdx97.mapper.SessionListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 会话相关
 */
@RestController
@RequestMapping("/session")
public class SessionController {


    @Autowired
    private SessionListMapper sessionMapper;

    @Autowired
    private MsgInfoMapper msgInfoMapper;

    /**
     * 会话列表
     *
     * @param kfId  客服id
     * @param status 连接状态
     */
    @GetMapping("/list")
    public AjaxResult<?> sessionList(@RequestParam String kfId, @RequestParam Integer status){

        SessionList sessionList = new SessionList();
        sessionList.setStatus(status);
        sessionList.setKfId(kfId);
        List<SessionList> select = sessionMapper.select(sessionList);
        return AjaxResult.success(select);
    }

    /**
     * 切换会话
     */
    @PostMapping("/change")
    public AjaxResult<?> changeSession(@RequestBody Map<String, Object> params){

        // TODO 切换当前会话

        // 获取会话数据
        MsgInfo msgInfo = new MsgInfo();
        List<MsgInfo>  msgInfos = msgInfoMapper.select(msgInfo);
        return AjaxResult.success(msgInfos);
    }

    /**
     * 结束会话
     */
    @GetMapping("/close")
    public AjaxResult<?> closeSession(@RequestParam String userId){

        SessionList sessionList = new SessionList();
        sessionList.setUserId(userId);
        sessionList.setStatus(0);
        sessionMapper.updateByOpenIdSelective(sessionList);
        return AjaxResult.success();
    }



}
