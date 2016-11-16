/**
 * 
 */
package com.http;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

/**
 * @author NICO
 **@date�� ���ڣ�2016��11��12�� ʱ�䣺����11:01:33
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
