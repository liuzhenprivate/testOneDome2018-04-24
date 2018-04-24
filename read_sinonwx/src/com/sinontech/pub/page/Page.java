package com.sinontech.pub.page;

import java.util.List;

/**
 * 分页
 * 
 * @author zwq
 * 
 */
public class Page {
	private List pageList;
	private Long pagecount;
	private Integer pageno;
	private Integer pagenow;

	public Page() {
	};

	public Page(List pageList, Long pagecount) {
		this.pagecount = pagecount;
		this.pageList = pageList;
	};

	public Page(List pageList) {
		this.pageList = pageList;
	}

	public Integer getPageno() {
		return pageno;
	}

	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}

	public List getPageList() {
		return pageList;
	}

	public void setPageList(List pageList) {
		this.pageList = pageList;
	}

	public Long getPagecount() {
		return pagecount;
	}

	public void setPagecount(Long pagecount) {
		this.pagecount = pagecount;
	};

	public Integer getPagenow() {
		return pagenow;
	}

	public void setPagenow(Integer pagenow) {
		this.pagenow = pagenow;
	}

}
