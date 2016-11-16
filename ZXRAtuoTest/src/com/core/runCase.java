/**
 * 
 */
package com.core;

import java.util.ArrayList;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicNameValuePair;

import com.common.Utils;
import com.http.HttpClient;
import com.http.HttpUtils;

/**
 * @author NICO
 **@date�� ���ڣ�2016��11��14�� ʱ�䣺����6:00:28
 */
public class runCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String casename;
		String description;
		String exec;
		String domainname;
		String path = null;
		String flag="YES";
		String param=null;
	
		Map<String,ArrayList> data=Utils.getDataFromExcel();
		System.out.println("-------------------------------------"+"��ʼִ�в�������"+"----------------------------------------------");
		for(int i=0;i<data.size();i++){
			ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			ArrayList a=data.get(String.valueOf(i+1));
			for(int j=0;j<a.size();j++){
				if(j<3){
//					System.out.println(a.get(j));
					System.out.print(a.get(j)+"===============");
				}else if(j==3){
					if("NO".equals(a.get(j).toString())){
					flag="NO";
					break;
					}
					flag="YES";
				}else if (j==4) {
					path=a.get(j).toString();
				}else{
					ArrayList al=(ArrayList) a.get(j);
					for(Object str:al){
						String strp;
						if((strp=str.toString()).contains("=")){
							String[] x=strp.split("=");
							params.add(new BasicNameValuePair(x[0],x[1]));
						}else{
							params.add(new BasicNameValuePair(strp,null));
						}
					}
				}
			}
			if(flag.equals("YES")){
			CloseableHttpResponse res=new HttpClient().post(path, params);
			System.out.println(HttpUtils.getStatusCodeFromResp(res));
			System.out.println(HttpUtils.getEntityFromResp(res));
			}else{
				System.out.println("=================��Case'��ִ��===============");
			}
		}
		System.out.println("-------------------------------------"+"���Խ���"+"----------------------------------------------");

	}
	
}
