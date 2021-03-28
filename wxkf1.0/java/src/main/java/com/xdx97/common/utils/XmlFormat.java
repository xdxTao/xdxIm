package com.xdx97.common.utils;

import com.xdx97.bean.WxBean;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class XmlFormat<T> {

//    public  T format(String xmlString){
//
//        if (xmlString.length() < 10){
//            throw new RuntimeException("错误的xml");
//        }
//        xmlString = xmlString.substring(5,xmlString.length()-6);
//        Map<String,Object> map = new HashMap<String, Object>();
//        char[] chars = xmlString.toCharArray();
//        StringBuilder curFiled = new StringBuilder();
//        StringBuilder curContent = new StringBuilder();
//        int flag = 0;
//        for (int i = 0;i < chars.length; i++){
//            if (chars[i] == '<' && flag != 1){
//                continue;
//            }
//            if (chars[i] != '>' && flag != 1){
//                curFiled.append(chars[i]);
//                continue;
//            }else if (flag != 1){
//                flag = 1;
//                continue;
//            }
//
//            if (flag == 1 && chars[i] != '<'){
//                curContent.append(chars[i]);
//                continue;
//            }
//            if (flag == 1 && chars[i] == '<'){
//                map.put(curFiled.toString(), curContent.toString());
//                i += curFiled.length() + 2;
//                curFiled.delete(0,curFiled.length());
//                curContent.delete(0, curContent.length());
//                flag = 0;
//            }
//        }
//        for (String key : map.keySet()){
//            System.out.println("key = "+key +", value = " + map.get(key));
//        }
//        return null;
//    }

    public T Wxformat(String xmlString,T t){

        if (xmlString.length() < 10){
            throw new RuntimeException("错误的xml");
        }
        xmlString = xmlString.replace("<![CDATA[", "");
        xmlString = xmlString.replace("]]>","");

        xmlString = xmlString.substring(5,xmlString.length()-6);
        Map<String,Object> map = new HashMap<String, Object>();
        char[] chars = xmlString.toCharArray();
        StringBuilder curFiled = new StringBuilder();
        StringBuilder curContent = new StringBuilder();
        int flag = 0;
        for (int i = 0;i < chars.length; i++){
            if (chars[i] == '<' && flag != 1){
                continue;
            }
            if (chars[i] != '>' && flag != 1){
                curFiled.append(chars[i]);
                continue;
            }else if (flag != 1){
                flag = 1;
                continue;
            }

            if (flag == 1 && chars[i] != '<'){
                curContent.append(chars[i]);
                continue;
            }
            if (flag == 1 && chars[i] == '<'){
                map.put(curFiled.toString(), curContent.toString());
                i += curFiled.length() + 2;
                curFiled.delete(0,curFiled.length());
                curContent.delete(0, curContent.length());
                flag = 0;
            }
        }
        try {
            Class<?> aClass = t.getClass();
            for (String key : map.keySet()){
                Method method = aClass.getMethod("set" + key, String.class);
                method.invoke(t, map.get(key));
            }
        }catch (Exception e){

        }
        return t;
    }

    public static void main(String[] args) {
        XmlFormat<Object> objectXmlFormat = new XmlFormat<>();
        String xml = "<xml><ToUserName><![CDATA[gh_98de193a18a1]]></ToUserName><FromUserName><![CDATA[o2tmFv6nFgxPzbYt4fqlgvzyaL8s]]></FromUserName><CreateTime>1615894182</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[裂开了]]></Content><MsgId>23134042094363852</MsgId></xml>";

        WxBean wxformat = (WxBean)objectXmlFormat.Wxformat(xml, new WxBean());

        System.out.println(wxformat.getContent());
        System.out.println(wxformat.getCreateTime());
        System.out.println(wxformat.getFromUserName());
        System.out.println(wxformat.getMsgId());
        System.out.println(wxformat.getMsgType());
        System.out.println(wxformat.getToUserName());


//        objectXmlFormat.format("<xml><ToUserName>123</ToUserName><FromUserName>123</FromUserName><CreateTime>1615894182</CreateTime><MsgType>123</MsgType><Content>123</Content><MsgId>23134042094363852</MsgId></xml>");
    }


}
