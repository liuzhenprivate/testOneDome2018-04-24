package com.sinontech.phone.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.criterion.PropertiesSubqueryExpression;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinontech.phone.utils.HtmlPaseUtils.ResultModel;
import com.sinontech.phone.utils.PhoneInfoParseTool.PhoneInfo.PhoneInfoBean;

public class PhoneInfoParseTool {
	private static List<Map<String, String>> info = new ArrayList<Map<String, String>>();
	/**
	 * 用到的文件
	 */
	private static String areainfoName = "areainfo.properties";
	private static String quhaoName = "quhao.txt";
	public static void main(String[] args) throws UnsupportedEncodingException {
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""));	//项目路径
		filePath =   filePath.replace("file:/", "");
		System.out.println("filePath="+filePath);
		
		 PhoneInfos findByPhoneSubInfo = phoneHandleMain("1335714");
		 System.out.println(findByPhoneSubInfo);

		// String propertiesString = getPropertiesString("1515819");
		// System.out.println(propertiesString);
		// modifyConfigField("123678", "456");
//		try {
//			writerProperties(areainfoName, "33", "ff");
//			// String propertiesString = getPropertiesString("4");
//			// System.out.println(propertiesString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		String propertiesInfo = getPropertiesInfo("44");
//		System.out.println(propertiesInfo);

	}
	/**
	 * 爬取数据：号码主方法调用，注意号码里有新的特殊字符里要在这数组里加
	 * 
	 * @param phone
	 * @return [0] parea [1]ptype [2]phone
	 * @see String[] resultMain = PhoneInfoParseTool.getResultMain(phone); if
	 *      (resultMain==null) { continue; } String parea = resultMain[0];
	 *      String ptype = resultMain[1]; phone = resultMain[2];
	 */
	public static PhoneInfos getResultMain(String phone) {
		PhoneInfos infos = new PhoneInfos();
		String[] fuhaoArr = new String[] { "，", "-", "、", "/", " ", "()", "|",
				",", "；", "\\" };
		String phoneFactory = phoneFactory(phone, fuhaoArr);
		if (!phoneFactory.equals("")) {
			Map<String, Map<String, String>> pythonPhoneInfo = getPythonPhoneInfo(phoneFactory);
			if (pythonPhoneInfo != null) {
				Map<String, String> map = pythonPhoneInfo.get(phoneFactory);
				// 获取地市和运营商
				infos.setParea(map.get("parea"));
				infos.setPtype(map.get("ptype"));
				infos.setPhone(map.get("phone"));
				return infos;
			}
		}
		System.out.println("号码有误，解析失败");
		return null;
	}

	/**
	 * 号码处理类
	 * 
	 * @param phone
	 * @param array
	 * @return
	 */
	public static String phoneFactory(String phone, String[] array) {
		boolean flag = false;
		for (int i = 0; i < array.length; i++) {
			String arr = array[i];
			if (phone.contains(arr)) {
				int index = phone.indexOf(arr);
				if (phone.startsWith("0") || phone.startsWith("1")
						|| phone.startsWith("5")) {
					if (phone.startsWith("0") && index < 7) {
						phone = phone.replace(arr, "");
					}
					if (phone.startsWith("1") && index < 11) {
						phone = phone.replace(arr, "");
					}
					if (phone.startsWith("5") && index < 6) {
						phone = phone.replace(arr, "");
					}
				}
			}
			if (phone.contains(arr)) {
				StringBuffer sb = new StringBuffer();
				if (arr.contains("|")) {
					arr = "\\|";
				} else {
					phone = phone.replace(arr, "|");
				}
				String[] split = phone.split("\\|");
				for (int j = 0; j < split.length; j++) {
					phone = split[j];
					if (phone.equals("")) {
						return "";
					}
					if (phone.length() >= 7) {
						phone = getNumbers(phone);
						phone = phone.trim();
						if (phone.startsWith("0") || phone.startsWith("1")
								|| phone.startsWith("5")) {
							if (phone.startsWith("0") && phone.length() < 7) {
								return "";
							} else if (phone.startsWith("1")
									&& phone.length() < 11) {
								return "";
							} else if (phone.startsWith("5")
									&& phone.length() < 7) {
								return "";
							}
							if (phone.substring(0, 2).equals("00")
									|| phone.substring(0, 2).equals("01")
									&& phone.length() > 7) {
								phone = phone.substring(1, phone.length());
							}
							if (phone.startsWith("5") && phone.length() >= 7) {
								phone = "0" + phone;
							}
							sb.append(phone + "|");
						}
					}
				}
				phone = sb.toString();
				return phone;
			}
			flag = false;
		}
		if (flag == false) {
			return phone;
		}
		return "";
	}

	/**
	 * 该方法适用爬虫（多号码间特殊符号处理+号码判断+号码详细信息获取方法）
	 * 列（05594526952、13395580306）、（055、94526952、13395580306）
	 * （（055）94526952、13395580306）
	 * 
	 * @param phone
	 * @param array
	 *            特殊符号(,|、……)
	 * @return
	 */
	public static Map<String, Map<String, String>> getPythonPhoneInfo(
			String phone) {
		PhoneInfos infos = new PhoneInfos();

		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		String oldphone = phone;
		if (phone.contains("|")) {
			StringBuffer sb = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			StringBuffer sb3 = new StringBuffer();
			String[] split = phone.split("\\|");
			for (int j = 0; j < split.length; j++) {
				String string = split[j];
				infos = PhoneInfoParseTool.getHanderResultInfo(string);
				if (infos != null) {
					if (!infos.getParea().contains("null")
							&& !infos.getPtype().contains("null")
							&& !infos.getPhone().contains("null")
							&& infos != null) {
						sb.append(infos.getParea()).append("|");
						sb2.append(infos.getPtype()).append("|");
						sb3.append(infos.getPhone()).append("|");
					}
				}

			}
			if (infos == null) {
				return null;
			} else {
				if (!infos.getParea().contains("null")
						&& !infos.getPtype().contains("null")
						&& !infos.getPhone().contains("null")) {
					infos.setParea(sb.toString());
					infos.setPtype(sb2.toString());
					infos.setPhone(sb3.toString());
				} else {
					return null;
				}
			}

		} else {
			infos = getHanderResultInfo(phone);
			if (infos == null) {
				return null;
			} else {
				if (infos.getParea().contains("null")
						|| infos.getPtype().contains("null")
						|| infos.getPhone().contains("null")) {
					return null;
				}

			}
		}
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("parea", infos.getParea());
		infoMap.put("ptype", infos.getPtype());
		infoMap.put("phone", infos.getPhone());
		map.put(oldphone, infoMap);
		return map;
	}

	/**
	 * 号码判断+号码详细信息获取方法
	 * 
	 * @param phone
	 * @return [0]=parea：地市 [1]=ptype：运营商
	 * 
	 */
	public static PhoneInfos getHanderResultInfo(String phone) {
		PhoneInfos infos = new PhoneInfos();
		phone = phoneFormatHandler(phone);
		if (!phone.equals("")) {
			infos = PhoneInfoParseTool.phoneHandleMain(phone);
			if (infos != null) {
				return infos;
			}
		}
		return null;
	}

	/**
	 * 基本正确号码判断
	 * 
	 * @param phone
	 * @return
	 */
	public static String phoneFormatHandler(String phone) {
		phone = phone.replace("，", "");
		phone = phone.replace("、", "");
		phone = phone.replace("(", "");
		phone = phone.replace(")", "");
		phone = phone.replace("-", "");
		phone = phone.replace("*", "");
		phone = phone.replace(" ", "");
		phone = phone.trim();
		if (phone.equals("")) {
			return "";
		}
		phone = getNumbers(phone);
		if (phone.length() >= 7) {
			if (phone.startsWith("0") || phone.startsWith("1")
					|| phone.startsWith("5")) {
				if (phone.startsWith("0") && phone.length() < 7) {
					return "";
				} else if (phone.startsWith("1") && phone.length() < 11) {
					return "";
				} else if (phone.startsWith("5") && phone.length() < 7) {
					return "";
				}
				if (phone.substring(0, 2).equals("00") && phone.length() > 7) {
					phone = phone.substring(1, phone.length());
				}
				if (phone.substring(0, 2).equals("01") && phone.length() > 11) {
					phone = phone.substring(1, phone.length());
				}
				if (phone.startsWith("5") && phone.length() >= 7) {
					phone = "0" + phone;
				}
				return phone;
			}
		}
		return "";
	}

	/**
	 * 号码地市、运营商区分（手机号四种查询方式，优先级别查询）
	 * 
	 * @param phone
	 * @return[0]=parea地市 [1]=ptype运营商
	 */
	@SuppressWarnings("unused")
	public static PhoneInfos phoneHandleMain(String phone) {
		PhoneInfos infos = new PhoneInfos();
		phone = getNumbers(phone);
		phone = phone.trim();
		if (!phone.startsWith("0") && phone.length() >= 7) {
			// 从地市文件里查找
			String phoneSub = phone.substring(0, 7);
			infos = findByPhoneSubInfo(phoneSub);
			if (infos != null) {
				if (!infos.getParea().contains("null")
						&& !infos.getPtype().contains("null")
						&& !infos.getPhone().contains("null")) {
					infos.setPhone(phone);
					return infos;
				}
			}
			// 从138wap网站查找
			infos = getPhoneInfoBy138WAP(phone);
			if (infos != null) {
				if (infos.getMsg() == null) {
					phoneSub = phone.substring(0, 7);
					String parea = infos.getParea();
					String ptype = infos.getPtype();
					int insertAreaInfo = insertPhoneSubInfo(phoneSub, parea,
							ptype);
					if (insertAreaInfo > 0) {
						System.out.println("该号段" + phoneSub + "不存在，已添加成功！！！");
					}
					return infos;
				}
			}
			// 从138web网站查找
			infos = getPhoneInfoBy138Web(phone);
			if (infos != null) {
				if (infos.getMsg() == null) {
					phoneSub = phone.substring(0, 7);
					String parea = infos.getParea();
					String ptype = infos.getPtype();
					int insertAreaInfo = insertPhoneSubInfo(phoneSub, parea,
							ptype);
					if (insertAreaInfo > 0) {
						System.out.println("该号段" + phoneSub + "不存在，已添加成功！！！");
					}
					return infos;
				}
			}
			// 从api查找
			PhoneInfoBean parsePhoneArea = getPhoneInfoByApicloud(phone);
			if (parsePhoneArea != null) {
				infos.setParea(parsePhoneArea.getProvince() + ""
						+ parsePhoneArea.getCity());
				infos.setPtype(parsePhoneArea.getOperator());
				infos.setPhone(phone);
				return infos;
			}
			if (infos != null) {
				if (infos.getMsg() != null && infos.getMsg().equals("接口读取错误")) {
					System.out.println("138接口读取失败，，，，" + phone);
					HtmlPaseUtils.sleepThead();
					phoneHandleMain(phone);
				}
			}
		} else {
			if (phone.startsWith("0") && phone.length() > 3) {
				try {
					String[] fixedPhoneByQuhao = getFixedPhoneByPhone(phone);
					if (fixedPhoneByQuhao != null) {
						String string1 = fixedPhoneByQuhao[0];
						String string3 = fixedPhoneByQuhao[2];
						infos.setParea(string3 + string1);
						infos.setPtype("电信");
						infos.setPhone(phone);
						return infos;
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else {
				return null;
			}
		}
		return null;
	}



	/**
	 * 读取号段信息
	 * 
	 * @param phoneSub
	 * @return
	 */
	public static PhoneInfos findByPhoneSubInfo(String phoneSub) {
		PhoneInfos infos = new PhoneInfos();
		String result = "";
		phoneSub = phoneSub.substring(0, 7);
		result = getPropertiesInfo(phoneSub);
		System.out.println("===="+result);
		// 判断是否已经存在
		if (!StringUtils.isEmpty(result)) {
			String[] split = result.split("\\|");
			String phonesub = split[0];
			String parea = null;
			parea =  split[1] ;
			String ptype = null;
			ptype = split[2] ;
			infos.setPhone(phonesub);
			infos.setParea(parea);
			infos.setPtype(ptype);

			return infos;
		}
		return null;
	}

	public static String getPropertiesInfo(String key) {
		PropertiesTool rc = new PropertiesTool(areainfoName);
		// 测试从文件读取
		String databaseName= rc.getValue(key);
		
		if (!StringUtils.isEmpty(databaseName)) {
			databaseName =decode1(databaseName);
			return databaseName;
		}else{
			System.out.println("该" + key + "在资源文件里不存在！！！");
			return "";
		}
	}

	/**
	 * 读取Properties信息 方法1
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropertiesBundle(String phoneSub) {
		ResourceBundle r = ResourceBundle.getBundle("areainfo");
		String result = "";
		try {
			if (r.containsKey(phoneSub)) {
				result = new String(r.getString(phoneSub)
						.getBytes("ISO-8859-1"), "utf-8");
			} else {
				System.out.println("该" + phoneSub + "在资源文件里不存在！！！");
				return "";
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 读取Properties信息 方法2
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropertiesString(String key) {
		Properties pros = new Properties();
		String value = "";
		InputStreamReader isr = null;
		InputStream is = null;
		try {
			is = PhoneInfoParseTool.class.getResourceAsStream("/"
					+ areainfoName);
			isr = new InputStreamReader(is, "UTF-8");
			pros.load(isr);
			value = (String) pros.get(key);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return value;
	}

	/**
	 * 插入号段信息
	 * 
	 * @param phoneSub
	 * @param parea
	 * @param ptype
	 * @return
	 */
	public static int insertPhoneSubInfo(String phoneSub, String parea,
			String ptype) {
		StringBuilder sb = new StringBuilder();
		parea=encode(parea);
		ptype=encode(ptype);
		sb.append(phoneSub).append("|").append(parea).append("|").append(ptype)
				.append("\r\n");
		try {
			writerProperties(areainfoName, phoneSub, sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	/**
	 * 编码
	 * @param s
	 * @return
	 */
	public static String encode(String s) {
	    StringBuilder sb = new StringBuilder(s.length() * 3);
	    for (char c : s.toCharArray()) {
	        if (c < 256) {
	            sb.append(c);
	        } else {
	            sb.append("\\u");
	            sb.append(Character.forDigit((c >>> 12) & 0xf, 16));
	            sb.append(Character.forDigit((c >>> 8) & 0xf, 16));
	            sb.append(Character.forDigit((c >>> 4) & 0xf, 16));
	            sb.append(Character.forDigit((c) & 0xf, 16));
	        }
	    }
	    return sb.toString();
	}
	static final Pattern reUnicode = Pattern.compile("\\\\u([0-9a-zA-Z]{4})");
	/**
	 * 解码
	 * @param s
	 * @return
	 */
	public static String decode1(String s) {
	    Matcher m = reUnicode.matcher(s);
	    StringBuffer sb = new StringBuffer(s.length());
	    while (m.find()) {
	        m.appendReplacement(sb,
	                Character.toString((char) Integer.parseInt(m.group(1), 16)));
	    }
	    m.appendTail(sb);
	    return sb.toString();
	}
	// 写入Properties信息
	public static void writerProperties(String propertiesName, String pKey,
			String pValue) throws IOException {
		Properties pps = new Properties();
		InputStreamReader in = new InputStreamReader(
				PhoneInfoParseTool.class.getResourceAsStream("/"
						+ propertiesName), "UTF-8");
		// 从输入流中读取属性列表（键和元素对）
		pps.load(in);
		pps.setProperty(pKey, pValue);
//		String path = System.getProperty("user.dir");
		String path = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""));	//项目路径
		path =   path.replace("file:/", "");
		
		// String path="";
		path +=  propertiesName;
		// path="D:/areainfo.properties";
		System.out.println(path);
		FileOutputStream fs = new FileOutputStream(path);
		OutputStreamWriter out = new OutputStreamWriter(fs, "UTF-8");
		pps.store(out, "Update " + pKey + " name");
		in.close();
		fs.flush();
		out.flush();
		out.close();
		fs.close();
	}

	/**
	 * …（TXT方式）插入数据到地市文件
	 * 
	 * @param phoneSub
	 * @param parea
	 * @param ptype
	 */
	public static int insertAreaInfo(String phoneSub, String parea, String ptype) {
		String phoneSubPath = getPhoneSubPath();
		 BufferedWriter out = null;   
	        try {   
	        	StringBuffer sb = new StringBuffer();
	        	sb.append(phoneSub).append("|").append(parea).append("|").append(ptype).append("\r\n");
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(phoneSubPath, true),"UTF-8"));   
	            out.write(sb.toString());   
	        } catch (Exception e) {   
	            e.printStackTrace(); 
	            return -1;
	        } finally {   
	            try {   
	                if(out != null){
	                    out.close();   
	                }
	            } catch (IOException e) {   
	                e.printStackTrace();   
	            }   
	    }   
	        return 1;
		// 判断是否已经存在
//		FileWriter createFile;
//		try {
//			createFile = new FileWriter(phoneSubPath, true);
//			createFile.append(phoneSub).append("|").append(parea).append("|")
//					.append(ptype).append("\r\n");
//			createFile.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//			return -1;
//		}
//		return 1;
	}

	/**
	 * …（TXT方式）查找号段在地市表里是否存在
	 * 
	 * @param phoneSub
	 * @return
	 */
	public static PhoneInfos findByPhoneSub(String phoneSub) {
		PhoneInfos infos = new PhoneInfos();
		String phoneSubPath = getPhoneSubPath();
		List<String> txtLineRed = getTxtLineRed(phoneSubPath);
		for (int i = 0; i < txtLineRed.size(); i++) {
			String string = txtLineRed.get(i);
			// 判断是否已经存在
			if (string.contains(phoneSub)) {
				String[] split = string.split("\\|");
				String phonesub = split[0];
				String parea = split[1];
				String ptype = split[2];
				infos.setPhone(phonesub);
				infos.setParea(parea);
				infos.setPtype(ptype);
				return infos;
			}
		}
		return null;
	}

	/**
	 * 得到号段库路径
	 * 
	 * @return
	 */
	public static String getPhoneSubPath() {
		File directory = new File("");
		String courseFile = "";
		try {
			courseFile = directory.getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		courseFile = courseFile + areainfoName;
		File file = new File(courseFile);
		if (!file.exists()) {
			file.mkdir();
		}
		return courseFile;
	}

	/**
	 * 138wap网站查询方式获取手机号相关信息
	 * 
	 * @param phone
	 * @return
	 */
	public static PhoneInfos getPhoneInfoBy138WAP(String phone) {
		PhoneInfos infos = new PhoneInfos();
		String path = "http://m.ip138.com/mobile.asp?mobile=" + phone;
		ResultModel model = HtmlPaseUtils.readHtmlAsUTF8(path);
		String readHtml = model.getResult();
		if (model.getCode() == HtmlPaseUtils.ERR_CODE) {
			System.err.println("接口读取错误：getPhoneInfoBy138WAP…………" + phone);
			infos.setMsg("接口读取错误");
			return infos;
		}
		Document parse = Jsoup.parse(readHtml);
		Elements element = parse.select("tr").select("span");
		if (element.size() > 0) {
			for (int i = 0; i < element.size(); i++) {
				String myphone = element.get(0).text();
				if (!myphone.equals("验证手机号有误")) {
					String parea = element.get(1).text();
					String ptype = element.get(2).text();
					if (parea != null && ptype != null) {
						infos.setPhone(myphone);
						infos.setParea(parea);
						infos.setPtype(ptype);
						return infos;

					}
				}
			}
		}
		return null;
	}

	/**
	 * 138web网站查询方式获取手机号相关信息
	 * 
	 * @param phone
	 * @return
	 */
	public static PhoneInfos getPhoneInfoBy138Web(String phone) {
		PhoneInfos infos = new PhoneInfos();
		String path = "http://www.ip138.com:8080/search.asp?mobile=" + phone
				+ "&action=mobile";
		ResultModel model = HtmlPaseUtils.readHtmlAsGBK(path);
		String readHtml = model.getResult();
		if (model.getCode() == HtmlPaseUtils.ERR_CODE) {
			System.err.println("接口读取错误：getPhoneInfoBy138Web…………" + phone);
			infos.setMsg("接口读取错误");
			return infos;
		}
		Document parse = Jsoup.parse(readHtml);
		Elements element = parse.select("td[class=tdc2]");
		if (element.size() > 0) {
			for (int i = 0; i < element.size(); i++) {
				String myphone = element.get(0).text();
				if (!myphone.equals("验证手机号有误")) {
					String parea = element.get(1).text();
					String ptype = element.get(2).text();
					if (parea != null && ptype != null) {
						infos.setPhone(myphone);
						infos.setParea(parea);
						infos.setPtype(ptype);
						return infos;
					}
				} else {
					return null;
				}
			}
		}
		return null;
	}

	/**
	 * 接口方式查询手机号详细信息
	 * 
	 * @param phone
	 * @return
	 */
	public static PhoneInfoBean getPhoneInfoByApicloud(String phone) {
		StringBuffer sb = new StringBuffer("");
		if ("" != phone && phone != null && phone.length() > 7) {
			String myurl = "http://apicloud.mob.com/v1/mobile/address/query?key=14af9360bd5a0&phone="
					+ phone.trim();
			URL url;
			try {
				url = new URL(myurl);
				URLConnection connection = url.openConnection();
				HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
				connection
						.setRequestProperty("User-Agent",
								"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						httpURLConnection.getInputStream(), "utf-8"));
				String s = "";
				while ((s = br.readLine()) != null) {
					sb.append(s + "\r\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		Gson gson = new Gson();
		Type type = new TypeToken<PhoneInfo>() {
		}.getType();
		PhoneInfo phoneInfo = gson.fromJson(sb.toString(), type);
		PhoneInfoBean result2 = phoneInfo.getResult();
		if (result2 != null) {
			return result2;
		}
		return null;
	}

	/**
	 * 获取本项目文件路径
	 * 
	 * @param name
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String getTxtPath(String name)
			throws UnsupportedEncodingException {
		String path = PhoneInfoParseTool.class.getClassLoader().getResource(name)
				.getPath();
		String decode = URLDecoder.decode(path, "UTF-8");
		decode = decode.substring(1, decode.length());
		return decode;
	}

	/**
	 * 固话列表查询
	 * 
	 * @param path
	 * @return
	 */
	private static List<Map<String, String>> getFixedPhoneInfo()
			throws UnsupportedEncodingException {
		List<Map<String, String>> txtSplit = getTxtSplitCharacter(getTxtPath(quhaoName));
		return txtSplit;
	}

	/**
	 * 固话列表查询
	 * 
	 * @param path
	 * @return
	 */
	public static String[] getFixedPhoneByPhone(String phone)
			throws UnsupportedEncodingException {
		List<Map<String, String>> txtSplit = getFixedPhoneInfo();
		String[] str = new String[3];
		for (int i = 0; i < txtSplit.size(); i++) {
			String string = txtSplit.get(i).get("quhao");
			if (phone.startsWith(string)) {
				String city = txtSplit.get(i).get("city");
				String quhao = txtSplit.get(i).get("quhao");
				String provice = txtSplit.get(i).get("provice");
				str[0] = city;
				str[1] = quhao;
				str[2] = provice;
				break;
			}
		}
		return str;
	}

	/**
	 * 区号txt文件条件单 行读取
	 * 
	 * @param path
	 * @return
	 */

	private static List<Map<String, String>> getTxtSplitCharacter(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		InputStreamReader read;
		String line;
		Map<String, String> map = null;
		try {
			try {
				read = new InputStreamReader(new FileInputStream(file), "gbk");
				reader = new BufferedReader(read);
				try {
					while ((line = reader.readLine()) != null) {
						String[] lines = line.split("\\|");
						if (lines.length < 2) {
							continue;
						}
						map = new HashMap<String, String>();
						String city = lines[0];
						String quhao = lines[1];
						String provice = lines[2];
						map.put("city", city);
						map.put("quhao", quhao);
						map.put("provice", provice);
						info.add(map);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return info;

	}

	public class PhoneInfo {
		public String msg;
		public String retCode;
		public PhoneInfoBean result;

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getRetCode() {
			return retCode;
		}

		public void setRetCode(String retCode) {
			this.retCode = retCode;
		}

		public PhoneInfoBean getResult() {
			return result;
		}

		public void setResult(PhoneInfoBean result) {
			this.result = result;
		}

		public class PhoneInfoBean {
			public String city;
			public String cityCode;
			public String mobileNumber;
			public String operator;
			public String province;
			public String zipCode;

			public String getCity() {
				return city;
			}

			public void setCity(String city) {
				this.city = city;
			}

			public String getCityCode() {
				return cityCode;
			}

			public void setCityCode(String cityCode) {
				this.cityCode = cityCode;
			}

			public String getMobileNumber() {
				return mobileNumber;
			}

			public void setMobileNumber(String mobileNumber) {
				this.mobileNumber = mobileNumber;
			}

			public String getOperator() {
				return operator;
			}

			public void setOperator(String operator) {
				this.operator = operator;
			}

			public String getProvince() {
				return province;
			}

			public void setProvince(String province) {
				this.province = province;
			}

			public String getZipCode() {
				return zipCode;
			}

			public void setZipCode(String zipCode) {
				this.zipCode = zipCode;
			}

		}
	}

	public static class PhoneInfos {
		public String msg;

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		/**
		 * @return 号码
		 */
		public String phone;
		/**
		 * @return 地市
		 */
		public String parea;
		/**
		 * @return 运营商
		 */
		public String ptype;

		public PhoneInfos() {
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getParea() {
			return parea;
		}

		public void setParea(String parea) {
			this.parea = parea;
		}

		public String getPtype() {
			return ptype;
		}

		public void setPtype(String ptype) {
			this.ptype = ptype;
		}

		@Override
		public String toString() {
			return "PhoneInfos [msg=" + msg + ", phone=" + phone + ", parea="
					+ parea + ", ptype=" + ptype + "]";
		}

	}

	/**
	 * 判断是否是号码
	 * 
	 * @param mobiles
	 * @return
	 */
	private static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	// 截取数字
	private static String getNumbers(String content) {
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(content);
		return m.replaceAll("").trim();
	}

	/**
	 * txt单行读取
	 * 
	 * @param path
	 * @return
	 */
	public static List<String> getTxtLineRed(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		InputStreamReader read;
		String line;
		List<String> list = new ArrayList<String>();
		try {
			try {
				read = new InputStreamReader(new FileInputStream(file), "utf-8");
				reader = new BufferedReader(read);
				try {
					while ((line = reader.readLine()) != null) {
						list.add(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return list;

	}

}
