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
 * @version 1.0 @date： 日期：2016年11月7日 时间：上午10:14:25
 */
public class Utils {
	/**
	 * 将请求参数转换成json
	 * 
	 * @param method
	 *            接口方法
	 * @param service
	 *            包名
	 * @param params0
	 *            参数
	 * @return
	 */
	public static String getJsonDate(String method, String service, List params0) {
		Gson gson = new Gson();
		// 生成UUID
		UUID uuid = UUID.randomUUID();

		LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
		header.put("appVersion", "v8.6.2.0");
		header.put("deviceNumber", "865863027618813");
		header.put("environment", "Release");
		header.put("osType", "Android");
		header.put("userToken", "16F1D6EC5DCDA63FEF0CA923F66B206B");

		// //param 数组
		// List param=new ArrayList();
		// param.add(1614);
		// param.add(params0);

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		// 头信息一般不会变化
		params.put("header", header);

		// 可变参数
		params.put("method", method);
		params.put("myParams", params0);
		params.put("service", service);

		// UUID自动生成
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
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
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
	 * 获取配置文件值
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
	 * 获取项目根目录
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
	 * 获取excel数据
	 * @return Map
	 */
	public static Map getDataFromExcel() {
		
		Map<String,ArrayList> data=new HashMap<String,ArrayList>();

		short rownum;
		int linenum;
		//读取数据
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
				//循环读取excel列
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
				
				while(rs.next())//对结果集进行遍历输出
				{
				System.out.println("id: "+rs.getString("id"));//通过列的标号来获得数据
				System.out.println("城市: "+rs.getString("cityName"));
				System.out.println("地址: "+rs.getString("addr"));
				System.out.println("城市: "+rs.getString("createTime"));
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");  
			e.printStackTrace();
		}
	
		return ResultSet;
	}
}
