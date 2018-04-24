package com.sinontech.util.print;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * xls工具类
 * 
 * @author hjn
 * 
 */
public class XlsUtil {
  
	/**
	 * 
	 * @param filePath 读取文件的内容
	 * @param columns 取几列
	 * @return
	 * @throws IOException
	 */
  public static List<String[][]> read(String filePath,int columns) throws IOException {
	    List<String[][]> contents = new ArrayList<String[][]>();  
	    String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
	    InputStream stream = new FileInputStream(filePath);
	    Workbook wb = null;
	    if (fileType.equals("xls")||fileType.equals("XLS")) {
	      wb = new HSSFWorkbook(stream);
	    } else if (fileType.equals("xlsx") || fileType.equals("XLSX")) {
	      wb = new XSSFWorkbook(stream);
	    } else {
	      System.out.println("您输入的excel格式不正确");
	      return null;
	    }
	    Sheet sheet1 = wb.getSheetAt(0);
//	    获取excel当前工作表的总行数  
        int rows = sheet1.getPhysicalNumberOfRows();  
        System.out.println("当前工作簿一共有"+rows+"行 ");  
//      创建当前工作表的存储二维数组  
//      声明每个工作表存储的二维数组对象  
        String[][] row_contents = null;
        row_contents = new String[rows][columns]; 
	    System.out.println(sheet1.getPhysicalNumberOfRows());
	    int n=0;
	    for (Row row : sheet1) {
	    	int k =0;
	      for (Cell cell : row) {
	    	  try {
				row_contents[n][k]=cell.getStringCellValue();
			} catch (Exception e) {
				e.printStackTrace();
				 try {
						row_contents[n][k]=(cell.getNumericCellValue()+"");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
	    	  
	    	 
	    	  
//	        System.out.print(cell.getStringCellValue() + "  ");
	        k++;
	      }
	      n++;
	      contents.add(row_contents);
	      System.out.println("==");
	    }
	    return contents;  
  }

  public static boolean write(String outPath) throws Exception {
    String fileType = outPath.substring(outPath.lastIndexOf(".") + 1, outPath.length());
    System.out.println(fileType);
    // 创建工作文档对象
    Workbook wb = null;
    if (fileType.equals("xls")) {
      wb = new HSSFWorkbook();
    } else if (fileType.equals("xlsx")) {
      wb = new XSSFWorkbook();
    } else {
      System.out.println("您的文档格式不正确！");
      return false;
    }
    // 创建sheet对象
    Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
    // 循环写入行数据
    for (int i = 0; i < 2; i++) {
      Row row = (Row) sheet1.createRow(i);
      // 循环写入列数据
      for (int j = 0; j < 8; j++) {
        Cell cell = row.createCell(j);
        cell.setCellValue("测试" + j);
      }
    }
    // 创建文件流
    OutputStream stream = new FileOutputStream(outPath);
    // 写入数据
    wb.write(stream);
    // 关闭文件流
    stream.close();
    return true;
  }


  public static void main(String[] args) {
	  
   /* try {
      XlsUtil.write("D:" + File.separator + "out.xls");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }*/
    try {
    	List<String[][]> ls =  XlsUtil.read("D:" + File.separator + "1.xlsx",12);
    	for(String[][] l:ls){
    		for(int i=0;i<l.length;i++){
    			for(int k=0;k<12;k++)
    			System.out.println(l[i][k]+"");
    		}
    	}
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
 
  public static boolean write(String outPath,Map<String,List<String> > map) throws Exception {
	    String fileType = outPath.substring(outPath.lastIndexOf(".") + 1, outPath.length());
	    System.out.println(fileType);
	    // 创建工作文档对象
	    Workbook wb = null;
	    if (fileType.equals("xls")) {
	      wb = new HSSFWorkbook();
	    } else if (fileType.equals("xlsx")) {
	      wb = new XSSFWorkbook();
	    } else {
	      System.out.println("您的文档格式不正确！");
	      return false;
	    }
	    // 创建sheet对象
	    Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
	    int n=0;
	    for(List<String> l:map.values()){
	    	Row row = (Row) sheet1.createRow(n);
		      // 循环写入列数据
		      for (int j = 0; j < l.size(); j++) {
		        Cell cell = row.createCell(j);
		        cell.setCellValue(l.get(j));
		      }
		      n++;
	    }
	     
	    // 创建文件流
	    OutputStream stream = new FileOutputStream(outPath);
	    // 写入数据
	    wb.write(stream);
	    // 关闭文件流
	    stream.close();
	    return true;
	  }
}
