/**
 * 
 */
package com.http;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import com.common.Utils;

/**
 * @author NICO
 **@date： 日期：2016年11月12日 时间：上午11:01:33
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
	 * 从登陆请求中获取Set-Cookie
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
