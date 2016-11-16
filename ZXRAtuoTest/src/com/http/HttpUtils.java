/**
 * 
 */
package com.http;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.common.Utils;

/**
 * @author NICO
 **@date： 日期：2016年11月12日 时间：上午11:36:42
 */
public class HttpUtils {
	
	/**
	 * 拼接url
	 * @param domainname 域名
	 * @param port 端口号
	 * @param path 路径
	 * @return url
	 */
	public static String splitJointUrl(String domainname,String port,String path){
		if(port==null||port=="80"){
			return "http://"+domainname+path;
		}
		return "http://"+domainname+":"+port+path;
	}
	
	/**
	 * 拼接url  域名和端口从config文件中获取
	 * @param path
	 * @return
	 */
	public static String splitJointUrl(String path){
		String domainname=Utils.getConfigValue("domain");
		String port=Utils.getConfigValue("port");
		if(port==null||port=="80"){
			return "http://"+domainname+path;
		}
		return "http://"+domainname+":"+port+path;
	}
	
	/**
	 * 获取response 返回状态码
	 * @param resp
	 * @return
	 */
	public static int getStatusCodeFromResp(CloseableHttpResponse resp){
		int statuscode=0;
		statuscode=resp.getStatusLine().getStatusCode();
		return statuscode;
	}
	
	/**
	 * 获取response 返回实体
	 * @param resp
	 * @return
	 */
	public static String getEntityFromResp(CloseableHttpResponse resp){
		String Entity=null;
		try {
			Entity=EntityUtils.toString(resp.getEntity(), "UTF-8");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Entity;
	}
	
	
		
}
