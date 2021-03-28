package com.xdx97.common.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HttpUtils {


	public static String sendGetForm(String url, Map<String, String> params) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
        	String param = "";
            if (params != null) {
            	Set<Entry<String, String>> set = params.entrySet();
            	for(Entry<String, String> entry : set) {
            		if(param.isEmpty()) {
            			param = entry.getKey() + "=" + entry.getValue();
            		}else {
            			param = param + "&" + entry.getKey() + "=" + entry.getValue();
            		}
            	}
            }
            url = param.isEmpty() ? url : url + "?" + param;
        	HttpGet httpGet = new HttpGet(url);
            try(CloseableHttpResponse response = httpClient.execute(httpGet)){
            	 return EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {

			throw new RuntimeException("发送get请求失败", e);
        }
	}
	
	public static String sendPostForm(String url, Map<String, String> params) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpPost httpPost = new HttpPost(url);
            if (params != null) {
                List<NameValuePair> nameValuePairs = new ArrayList<>();
                for (String key : params.keySet()) {
                	nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs);
                httpPost.setEntity(entity);
            }
            try(CloseableHttpResponse response = httpClient.execute(httpPost)){
            	 return EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {

			throw new RuntimeException("发送post请求失败", e);
        }
	}
	
	public static String sendPostJson(String url, String json) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            try(CloseableHttpResponse response = httpClient.execute(httpPost)){
            	 return EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {

			throw new RuntimeException("发送post请求失败", e);
        }
	}
	
	public static String sendPostJson(String url, Map<String, String> headers, String json) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
			// 浏览器表示
	        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
	        // 传输的类型
	        httpPost.addHeader("Content-Type", "application/json");
	        //追加的头部信息
            headers.forEach((k, v) -> {
            	httpPost.setHeader(k, v);
            });
	        // 执行请求
            try(CloseableHttpResponse response = httpClient.execute(httpPost)){
            	 return EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {

			throw new RuntimeException("发送post请求失败", e);
        }
	}
}
