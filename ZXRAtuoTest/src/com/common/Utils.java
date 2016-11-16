/**
 * 
 */
package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;


/**
 * @author nico
 * @version 1.0 @date�� ���ڣ�2016��11��7�� ʱ�䣺����10:14:25
 */
public class Utils {
	/**
	 * ���������ת����json
	 * 
	 * @param method
	 *            �ӿڷ���
	 * @param service
	 *            ����
	 * @param params0
	 *            ����
	 * @return
	 */
	public static String getJsonDate(String method, String service, List params0) {
		Gson gson = new Gson();
		// ����UUID
		UUID uuid = UUID.randomUUID();

		LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
		header.put("appVersion", "v8.6.2.0");
		header.put("deviceNumber", "865863027618813");
		header.put("environment", "Release");
		header.put("osType", "Android");
		header.put("userToken", "16F1D6EC5DCDA63FEF0CA923F66B206B");

		// //param ����
		// List param=new ArrayList();
		// param.add(1614);
		// param.add(params0);

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		// ͷ��Ϣһ�㲻��仯
		params.put("header", header);

		// �ɱ����
		params.put("method", method);
		params.put("myParams", params0);
		params.put("service", service);

		// UUID�Զ�����
		params.put("uuid", uuid);
		String result = gson.toJson(params);

		return result;
	}

	/**
	 * MD5
	 * 
	 * @param s
	 * @return
	 */
	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// ���MD5ժҪ�㷨�� MessageDigest ����
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// ʹ��ָ�����ֽڸ���ժҪ
			mdInst.update(btInput);
			// �������
			byte[] md = mdInst.digest();
			// ������ת����ʮ�����Ƶ��ַ�����ʽ
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str).toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ��ȡ�����ļ�ֵ
	 * 
	 * @param key
	 * @return
	 */
	public static String getConfigValue(String key) {
		Properties prop = new Properties();
		String value = null;
		InputStream in = Object.class.getResourceAsStream("/config.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		value = prop.getProperty(key);
		return value;
	}

	public static void setConfigValue(String key, String value) {
		Properties prop = new Properties();

		InputStream in = Object.class.getResourceAsStream("/config.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop.setProperty(key, value);
		try {
			prop.list(new PrintStream(new File(getRootDirectory() + "\\src\\config.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ��ȡ��Ŀ��Ŀ¼
	 * 
	 * @return
	 */
	public static String getRootDirectory() {
		String path = null;
		File file = new File("");
		path = file.getAbsolutePath();
		return path;
	}

	/**
	 * ��ȡexcel����
	 * @return Map
	 */
	public static Map getDataFromExcel() {
		
		Map<String,ArrayList> data=new HashMap<String,ArrayList>();

		short rownum;
		int linenum;
		//��ȡ����
		XSSFWorkbook wb;
		try {
			wb = new XSSFWorkbook(getRootDirectory() + "\\" + "src" + "\\" + "TestCase.xlsx");
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row = sheet.getRow(0);
			rownum=row.getLastCellNum();
			linenum=sheet.getLastRowNum();
//			System.out.println(linenum);
			for(int j=1;j<=linenum;j++)
			{
				row = sheet.getRow(j);
				ArrayList al = new ArrayList();
				ArrayList params=null;
				XSSFCell cell=null;
				//ѭ����ȡexcel��
				params = new ArrayList();
				for(int i=0;i<rownum;i++){
					
					if(i<5){
					cell = row.getCell(i);
					al.add(cell);
					}else{
						cell = row.getCell(i);	
//						System.out.println(cell);
						if(cell!=null){
						params.add(cell);
						}	
					}	
				}
				al.add(params);
				data.put(String.valueOf(j), al);
			}	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	
	public static ResultSet DbHander(){
		String url = "jdbc:mysql://"+"test.56zxr.com"+":"+"4040"+"/"+"hyl_db";
		String USER = "xline_test";
		String PASS = "21232124";
		String DB_URL;
		String sql="SELECT * FROM hyl_address LIMIT 10,20" ;
		ResultSet ResultSet = null;
		System.out.println("Connecting to database...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				Connection conn = DriverManager.getConnection(url,USER,PASS);	
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				System.out.println(rs);
				
				while(rs.next())//�Խ�������б������
				{
				System.out.println("id: "+rs.getString("id"));//ͨ���еı�����������
				System.out.println("����: "+rs.getString("cityName"));
				System.out.println("��ַ: "+rs.getString("addr"));
				System.out.println("����: "+rs.getString("createTime"));
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("�Ҳ������������� ����������ʧ�ܣ�");  
			e.printStackTrace();
		}
	
		return ResultSet;
	}
}
