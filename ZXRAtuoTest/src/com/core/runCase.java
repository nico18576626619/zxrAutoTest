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
 **@date： 日期：2016年11月14日 时间：下午6:00:28
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
		System.out.println("-------------------------------------"+"开始执行测试用例"+"----------------------------------------------");
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
				System.out.println("=================本Case'不执行===============");
			}
		}
		System.out.println("-------------------------------------"+"测试结束"+"----------------------------------------------");

	}
	
}
