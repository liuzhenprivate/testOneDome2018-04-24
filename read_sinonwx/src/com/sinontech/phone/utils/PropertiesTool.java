package com.sinontech.phone.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 操作properties文件
 * @author Administrator
 *
 */
public class PropertiesTool{
 
    private Properties propertie;
    private String path;
    private FileInputStream inputFile;
    private FileOutputStream outputFile;
   
   /**
    * 构造器
    * 用途：新建一个properties文件
    * 调用setValue（key，value）添加节点
    * 调用saveAsFile（路径，描述）方法保存
    */
    public PropertiesTool(){
        propertie = new Properties();
    }
   
   /**
    * 构造器（输入路径）
    * @param filePath properties文件路径
    * 调用setValue（key，value）添加节点
    * 调用getValue（key）获取节点的值
    * 调用clear（）方法清空properties文件
    * 调用saveFile（）保存
    */
    public PropertiesTool(String mypath){
//    String filePath = System.getProperty("user.dir");
	String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""));	//项目路径
	filePath =   filePath.replace("file:/", "");
//    System.out.println(filePath);
    filePath=filePath+"/"+mypath;
	System.out.println(filePath);
     path = filePath;
        propertie = new Properties();
        try {
            inputFile = new FileInputStream(filePath);
            propertie.load(inputFile);
            inputFile.close();
        } catch (FileNotFoundException ex){
            System.out.println("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");
            ex.printStackTrace();
        } catch (IOException ex){
            System.out.println("装载文件--->失败!");
            ex.printStackTrace();
        }
    }
   
   /**
    * 通过key值获取value
    * @param key
    * @return
    */
    public String getValue(String key){
        if(propertie.containsKey(key)){
            String value = propertie.getProperty(key);//得到某一属性的值
            //System.out.println(value+"====");
            /*try {
				value = new String(value
						.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
            return value;
        }
        else
            return "";
    }
   
   /**
    * 清空properties文件
    *
    */
    public void clear(){
        propertie.clear();
    }
   
   /**
    * 输入key和value进行添加节点 ------构造器（输入路径）实例化时候 调用
    * @param key
    * @param value
    */
    public void setValue(String key, String value){
        propertie.setProperty(key, value);
    }
    
    /**
     * 保存properties文件
     * @param fileName
     * @param description
     */
     public void saveFile(String description){
         try {
             outputFile = new FileOutputStream(path);
             propertie.store(outputFile, description);
             outputFile.close();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException ioe){
             ioe.printStackTrace();
         }
     }
   
   /**
    * 另存为properties文件
    * @param fileName
    * @param description
    */
    public void saveAsFile(String fileName, String description){
        try {
            outputFile = new FileOutputStream(fileName);
            propertie.store(outputFile, description);
            outputFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}