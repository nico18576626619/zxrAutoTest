package com.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.portable.InputStream;

import com.common.Utils;

import org.apache.http.NameValuePair;
public class HttpClient {
	/*
	 * 发送get请求
	 */
	public void get() {
		// 创建httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建HttpGet对象
		HttpGet httpGet = new HttpGet(
				"http://hitao.top/login/getUserInfo?callback=jQuery1113010979214845289098_1476887717003&_=1476887717013");
		CloseableHttpResponse response;
		try {
			response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			System.out.println("--------------------------------------");
			// 打印响应状态
			// System.out.println(response.getStatusLine());
			if (entity != null) {
				// 打印响应内容
				System.out.println(EntityUtils.toString(entity));

			}
			System.out.println("------------------------------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * 发送post请求
	 * 
	 */

	public String post(String jsonstr) throws ParseException, IOException {
		String url = "http://test.oss.hyl.56zxr.com/hyl/swap/listTowingVehiclePage.do";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		StringEntity entity;
		HttpEntity entity1 = null;
		try {
			entity = new StringEntity(jsonstr, "utf-8");
			// httppost.setHeader("Cookie","JSESSIONID=EA574D9827604F074D420FB7C480F086");
			httppost.setEntity(entity);

			entity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");

			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
//			System.out.println("输出Cookie:" + new CookieUtils(response).getCookie());
			System.out.println(response.getStatusLine().getStatusCode());

			// System.out.println("cookie:"+ response.containsHeader("Cookie"));

			try {
				entity1 = response.getEntity();
				if (entity1 != null) {
					System.out.println("--------------------------------------");
					System.out.println("Response content: " + EntityUtils.toString(entity1, "UTF-8"));

					System.out.println("--------------------------------------" + response.getHeaders("Set-Cookie"));

				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return EntityUtils.toString(entity1, "UTF-8");

	}

	/*
	 * 通过参数队列形式传递参数
	 * 
	 */
	public CloseableHttpResponse post(String path, ArrayList<BasicNameValuePair> params) {
		// 拼接URL
		String url = HttpUtils.splitJointUrl(path);
		System.out.println(url);

		CloseableHttpResponse response = null;
		HttpEntity entity1 = null;

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Cookie","JSESSIONID="+Utils.getConfigValue("Cookie"));
		UrlEncodedFormEntity uefEntity;
		
		try {
			uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
			httppost.setEntity(uefEntity);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		try {
			response = httpclient.execute(httppost);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;

	}

}
