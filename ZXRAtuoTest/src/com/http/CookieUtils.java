/**
 * 
 */
package com.http;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import com.common.Utils;

/**
 * @author NICO
 **@date�� ���ڣ�2016��11��12�� ʱ�䣺����11:01:33
 */
public class CookieUtils {
//	CloseableHttpResponse res;
//
//	/**
//	 * @param response
//	 */
//	public CookieUtils(CloseableHttpResponse response) {
//		// TODO Auto-generated constructor stub
//		this.res=response;
//	}

	/**
	 * �ӵ�½�����л�ȡSet-Cookie
	 * @param res
	 * @return
	 */
	public static String getCookie(CloseableHttpResponse res){
		String cookie = null;
		Header[] header=res.getAllHeaders();
		for(Header h:header){
			if(h.getName().equals("Set-Cookie")){
				cookie=h.getValue().substring(0,h.getValue().indexOf(";"));
			}
		}
		String str=null;
		str=cookie.substring(cookie.indexOf("="), cookie.length());
		Utils.setConfigValue("Cookie", str);
		return cookie;
	}
}
