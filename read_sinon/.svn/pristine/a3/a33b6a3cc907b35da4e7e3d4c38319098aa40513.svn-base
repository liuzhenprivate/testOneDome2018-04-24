package com.sinontech.util.print;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUtils
{
  public static Object getObjectAttribute(HttpSession session, String objectName)
  {
    return session.getAttribute(objectName);
  }

  public static void setObjectAttribute(HttpSession session, String objectName, Object object)
  {
    session.setAttribute(objectName, object);
  }

  public static void removeObjectAttribute(HttpSession session, String objectName)
  {
    session.removeAttribute(objectName);
  }

  public static Object getObjectAttribute(HttpServletRequest request, String objectName)
  {
    return request.getSession().getAttribute(objectName);
  }

  public static void setObjectAttribute(HttpServletRequest request, String objectName, Object object)
  {
    request.getSession().setAttribute(objectName, object);
  }

  public static void removeObjectAttribute(HttpServletRequest request, String objectName)
  {
    request.getSession().removeAttribute(objectName);
  }
  public static PrintWriter getPrintWriter(HttpServletResponse response){
		PrintWriter printWriter = null;
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return printWriter;
	}
  public static PrintWriter getJsonPrintWriter(HttpServletResponse response){
		PrintWriter printWriter = null;
		try {
		    response.setContentType("application/json; charset=utf-8");  
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return printWriter;
	}
}