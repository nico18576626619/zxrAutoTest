/**
 * 
 */
package com.http;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

/**
 * @author NICO
 **@date： 日期：2016年11月12日 时间：上午11:01:33
 */
public class getCookie {
	CloseableHttpResponse res;



	/**
	 * @param res
	 */
	
	public String getCookie(CloseableHttpResponse res) {
		String cookie = null;
		Header[] header=res.getAllHeaders();
		for(Header h:header){
			if(h.getName().equals("Set-Cookie")){
				cookie=h.getValue().substring(0,h.getValue().indexOf(";"));
			}
		}
		return cookie;
	}
	
	
	

}
