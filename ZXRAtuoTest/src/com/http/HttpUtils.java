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
 **@date�� ���ڣ�2016��11��12�� ʱ�䣺����11:36:42
 */
public class HttpUtils {
	
	/**
	 * ƴ��url
	 * @param domainname ����
	 * @param port �˿ں�
	 * @param path ·��
	 * @return url
	 */
	public static String splitJointUrl(String domainname,String port,String path){
		if(port==null||port=="80"){
			return "http://"+domainname+path;
		}
		return "http://"+domainname+":"+port+path;
	}
	
	/**
	 * ƴ��url  �����Ͷ˿ڴ�config�ļ��л�ȡ
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
	 * ��ȡresponse ����״̬��
	 * @param resp
	 * @return
	 */
	public static int getStatusCodeFromResp(CloseableHttpResponse resp){
		int statuscode=0;
		statuscode=resp.getStatusLine().getStatusCode();
		return statuscode;
	}
	
	/**
	 * ��ȡresponse ����ʵ��
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
