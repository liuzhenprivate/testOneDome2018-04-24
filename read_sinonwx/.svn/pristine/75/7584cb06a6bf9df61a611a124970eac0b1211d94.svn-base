package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 收藏记录详情表
 * @author ljj
 *
 */
@Entity(name = "TB_COLLECTIONLOG")
public class Collectionlog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COLLECTIONLOG_ID")
	private long id;

	@Column(name = "WEBCHAT_ID")
	private long webchatId;

	@Column(name = "ARTICLE_ID")
	private long articleId;

	@Column(name = "USERID")
	private long userid;
	 
	// 创建时间
	@Column(name = "CREATE_TIME")
	private String createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
 

	public long getWebchatId() {
		return webchatId;
	}

	public void setWebchatId(long webchatId) {
		this.webchatId = webchatId;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Collectionlog() {
		super();
	}

	@Override
	public String toString() {
		return "Collectionlog [id=" + id + ", webchatId=" + webchatId
				+ ", articleId=" + articleId + ", userid=" + userid
				+ ", createTime=" + createTime + "]";
	}

}
