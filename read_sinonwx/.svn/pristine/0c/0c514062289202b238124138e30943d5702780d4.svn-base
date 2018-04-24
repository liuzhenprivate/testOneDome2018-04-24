package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 页面配置模块详情表
 * @author ljj
 *
 */
@Entity(name = "TB_HTMLMODLE_DETAIL")
public class HtmlModleDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HTMLMODLE_DETAIL_ID")
	private long id;

	@Column(name = "HTMLMODLE_ID")
	private long htmlmodleId;
	
	//主标题
	@Column(name = "TITLE")
	private String title;
	//副标题
	@Column(name = "SUBHEAD")
	private String subhead;
	//内容
	@Column(name = "CONTENT")
	private String content;
	
	//图片路径
	@Column(name = "IMG_URL")
	private String imgUrl;
		
	//图片路径
	@Column(name = "HTML_URL")
	private String htmlUrl;
	
	/*//模板类型1系统页面 2其他
	@Column(name = "HTML_TYPE")
	private int htmlType;*/

	//排序
	@Column(name = "SORT")
	private int sort;
	//书籍ID
	@Column(name = "ARTICLE_ID")
	private long articleID;

	//描述说明
	@Column(name = "MEMO")
	private String memo;
	
	// 创建时间
	@Column(name = "CREATE_TIME")
	private String createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
  
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubhead() {
		return subhead;
	}

	public void setSubhead(String subhead) {
		this.subhead = subhead;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	/*public int getHtmlType() {
		return htmlType;
	}

	public void setHtmlType(int htmlType) {
		this.htmlType = htmlType;
	}*/

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public long getArticleID() {
		return articleID;
	}

	public void setArticleID(long articleID) {
		this.articleID = articleID;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public long getHtmlmodleId() {
		return htmlmodleId;
	}

	public void setHtmlmodleId(long htmlmodleId) {
		this.htmlmodleId = htmlmodleId;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public HtmlModleDetail() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (articleID ^ (articleID >>> 32));
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
//		result = prime * result + htmlType;
		result = prime * result + ((htmlUrl == null) ? 0 : htmlUrl.hashCode());
		result = prime * result + (int) (htmlmodleId ^ (htmlmodleId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((imgUrl == null) ? 0 : imgUrl.hashCode());
		result = prime * result + ((memo == null) ? 0 : memo.hashCode());
		result = prime * result + sort;
		result = prime * result + ((subhead == null) ? 0 : subhead.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HtmlModleDetail other = (HtmlModleDetail) obj;
		if (articleID != other.articleID)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		 
		if (htmlUrl == null) {
			if (other.htmlUrl != null)
				return false;
		} else if (!htmlUrl.equals(other.htmlUrl))
			return false;
		if (htmlmodleId != other.htmlmodleId)
			return false;
		if (id != other.id)
			return false;
		if (imgUrl == null) {
			if (other.imgUrl != null)
				return false;
		} else if (!imgUrl.equals(other.imgUrl))
			return false;
		if (memo == null) {
			if (other.memo != null)
				return false;
		} else if (!memo.equals(other.memo))
			return false;
		if (sort != other.sort)
			return false;
		if (subhead == null) {
			if (other.subhead != null)
				return false;
		} else if (!subhead.equals(other.subhead))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HtmlModleDetail [id=" + id + ", htmlmodleId=" + htmlmodleId
				+ ", title=" + title + ", subhead=" + subhead + ", content="
				+ content + ", imgUrl=" + imgUrl + ", htmlUrl=" + htmlUrl
			    + ", sort=" + sort + ", articleID="
				+ articleID + ", memo=" + memo + ", createTime=" + createTime
				+ "]";
	}

	 
 
}
