package com.xdx97.controller;


import com.xdx97.bean.KfInfo;
import com.xdx97.bean.UserInfo;
import com.xdx97.common.bean.AjaxResult;
import com.xdx97.common.utils.CurUserUtils;
import com.xdx97.mapper.KfInfoMapper;
import com.xdx97.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户相关
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private KfInfoMapper kfInfoMapper;

    /**
     * @param kfName 客服名称
     */
    @GetMapping("/login")
    public AjaxResult<?> login(@RequestParam String kfName){

        KfInfo query = new KfInfo();
        query.setKfName(kfName);
        List<KfInfo> kfInfos = kfInfoMapper.select(query);
        if (kfInfos != null && !kfInfos.isEmpty()){
            KfInfo kfInfo = kfInfos.get(0);
            CurUserUtils.curUsers.put(kfInfo.getId(), kfInfo);
            return AjaxResult.success(kfInfo);
        }
        return AjaxResult.failure("登录失败");
    }

    /**
     * @param kfId 客服名称
     */
    @PostMapping("/loginOut")
    public AjaxResult<?> loginOut(@RequestBody Map<String, Object> params){

        String kfId = params.get("kfId").toString();
        CurUserUtils.webSockets.remove(kfId);
        CurUserUtils.curUsers.remove(kfId);
        CurUserUtils.curKfSessions.remove(kfId);

        return AjaxResult.success("登录成功");
    }

}
