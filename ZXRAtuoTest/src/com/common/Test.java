/**
 * 
 */
package com.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.http.CookieUtils;
import com.http.HttpClient;
import com.http.HttpUtils;

/**
 * @author nico
 * @version 1.0
 **@date： 日期：2016年11月7日 时间：上午10:25:46
 */
public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidFormatException 
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub
//		List param=new ArrayList();
//		param.add(1614);
//		Map<String,String> p=new HashMap<String,String>();
//		p.put("environment", "Release");
//		p.put("osType", "Android");
//		p.put("versionNo", "862");
//		param.add(p);
		
//		String str=Utils.getJsonDate("queryById", "com.zxr.xline.service.UserProfileService", param);
		
//		
//		String str="phone=13332345678&password=7654321";
//		System.out.println(new HttpClient().post(str));		
//		new HttpClient().post(str);
		
//		String url=HttpUtils.splitJointUrl("test.oss.hyl.56zxr.com","8080","/hyl/home/index.do");
//		System.out.println(url);
//		
//		System.out.println(Utils.getConfigValue("domain"));
//		System.out.println(Utils.getRootDirectory());
		
//		System.out.println(HttpUtils.splitJointUrl("/ddd/aaa"));
		/*
		ArrayList<BasicNameValuePair> list=new ArrayList<BasicNameValuePair>();
		list.add(new BasicNameValuePair("truckRequireRouteId", ""));
		list.add(new BasicNameValuePair("keySearch", ""));
		list.add(new BasicNameValuePair("pag", "1"));
		list.add(new BasicNameValuePair("rows", "20"));
		CloseableHttpResponse resp=new HttpClient().post("/hyl/swapCompany/queryCompanyList.do", list);
		System.out.println(HttpUtils.getStatusCodeFromResp(resp));
		System.out.println(HttpUtils.getEntityFromResp(resp));
//		System.out.println(CookieUtils.getCookie(resp));
 * */

		Map al=Utils.getDataFromExcel();
		System.out.println(al);	
	}
	
}
