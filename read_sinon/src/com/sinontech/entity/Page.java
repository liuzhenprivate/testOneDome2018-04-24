package com.sinontech.entity;

import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.util.Tools;

public class Page {

	private int showCount; // 每页显示记录数
	private int totalPage; // 总页数
	private int totalResult; // 总记录数
	private int currentPage; // 当前页
	private int currentResult; // 当前记录起始索引
	private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr; // 最终页面显示的底部翻页导航，详细见：getPageStr();
	private PageData pd = new PageData();

	public Page() {
		try {
			this.showCount = Integer.parseInt(Tools.readTxtFile(Const.PAGE));
		} catch (Exception e) {
			this.showCount = 15;
		}
	}

	public int getTotalPage() {
		if (totalResult % showCount == 0)
			totalPage = totalResult / showCount;
		else
			totalPage = totalResult / showCount + 1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		if (currentPage <= 0)
			currentPage = 1;
		if (currentPage > getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if (totalResult > 0) {
			sb.append("	<div class=\"flipTwoL\">共" + totalResult + "条</div>\n");
			sb.append("<div class=\"flipTwoR clearfix\">");
			sb.append("<div class=\"flipTwoPrev\" onclick=\"nextPage(" + (currentPage - 1) + ")\"><img  src=\"static/read/images/myPic17.png\"/></div><div class=\"flipTwoDe clearfix\">");
			if (totalPage == 1) {
				sb.append("<span class=\"flipTwoDeSpan\">1</span>\n");
				sb.append("<div class=\"flipTwoNext\"><img   src=\"static/read/images/myPic18.png\"   /></div>");
				sb.append("</div>\n");
			} else {
				int showTag = 5;// 分页标签显示数量
				int startTag = 1;
				if (currentPage > showTag) {
					startTag = currentPage - 1;
				}
				int endTag = startTag + showTag - 1;
				for (int i = startTag; i <= totalPage && i <= endTag; i++) {
					if (currentPage == i){
						sb.append("<span class=\"flipTwoDeSpan\">" + i + "</span>\n");
					}else{
						sb.append("	<span style=\"cursor:pointer;\"><a onclick=\"nextPage(" + i + ")\">" + i + "</a></span>\n");
					}
				}
				if(totalPage>5){
					sb.append("...");
					sb.append("	<span style=\"cursor:pointer;\"><a onclick=\"nextPage(" + totalPage + ")\">"+ totalPage +"</a></span>\n");
				}
				sb.append("</div>/n");
				sb.append("<div class=\"flipTwoNext\" onclick=\"nextPage(" + (currentPage + 1) + ")\" ><img src=\"static/read/images/myPic18.png\"   /></div>");
				sb.append("<div class=\"flipTwoSkip\">跳至</div>");
				sb.append("<div class=\"clearfix flipTwoInp\">");
				sb.append("<input  id=\"toGoPage\"/>");
				sb.append("</div>");
				sb.append("<div class=\"flipTwoSkip\">页</div>");
				sb.append("<div class=\"flipTwoSure\" onclick=\"toTZ();\" >确定</div>");
				sb.append("</div>");
			}
			 

		 
			sb.append("<script type=\"text/javascript\">\n");

			// 换页函数
			sb.append("function nextPage(page){");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("		else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("		url = url + page + \"&" + (entityOrField ? "showCount" : "page.showCount") + "=" + showCount + "\";\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("		url = url + page + \"&" + (entityOrField ? "showCount" : "page.showCount") + "=" + showCount + "\";\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");

			// 调整每页显示条数
			sb.append("function changeCount(value){");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("		else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("		url = url + \"1&" + (entityOrField ? "showCount" : "page.showCount") + "=\"+value;\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"1&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("		url = url + \"&" + (entityOrField ? "showCount" : "page.showCount") + "=\"+value;\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");

			// 跳转函数
			sb.append("function toTZ(){");
			sb.append("var toPaggeVlue = document.getElementById(\"toGoPage\").value;");
			sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("nextPage(toPaggeVlue);");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {

		this.showCount = showCount;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getShowCount();
		if (currentResult < 0)
			currentResult = 0;
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

	public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}

}
