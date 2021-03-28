package com.xdx97.controller.wx;

import com.xdx97.bean.WxBean;
import com.xdx97.common.handler.MessageHandler;
import com.xdx97.common.utils.XmlFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/portal")
public class WxHandler {

    @Autowired
    private MessageHandler messageHandler;

    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(
            @RequestParam(name = "signature", required = false) String signature,
            @RequestParam(name = "timestamp", required = false) String timestamp,
            @RequestParam(name = "nonce", required = false) String nonce,
            @RequestParam(name = "echostr", required = false) String echostr) {

        // 1、TODO 对数据校验

        return echostr;
    }

    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@RequestBody String requestBody,
                       @RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce, @RequestParam("openid") String openid,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature) throws InterruptedException {
        // 1、TODO 对数据校验
        XmlFormat<Object> objectXmlFormat = new XmlFormat<>();
        WxBean wxformat = (WxBean)objectXmlFormat.Wxformat(replaceSpecialStr(requestBody), new WxBean());

        // 3、注意如果在5s内没有收到回复，会重试三次，还是失败后就会告知用户，该公众号服务中断
        messageHandler.handler(wxformat);


        return "success";
    }

    // 去除字符串中的空格、回车、换行符、制表符等
    public static String replaceSpecialStr(String str) {
        String repl = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            repl = m.replaceAll("");
        }
        return repl;

    }
}

