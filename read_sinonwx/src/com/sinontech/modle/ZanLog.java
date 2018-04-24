package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 点赞记录表
 * @author ljj
 *
 */
@Entity(name = "TB_ZANLOG")
public class ZanLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ZANLOG_ID")
	private long id;
	//书籍ID
	@Column(name = "ARTICLE_ID")
	private long articleId;
	//评论ID
	@Column(name = "DISCUSS_ID")
	private long discussId; 
	//读者ID
	@Column(name = "USERID")
	private long userId;
	//几星
	@Column(name = "LEVELS")
	private int levels;
	 
	
	@Column(name = "CREATE_TIME")
	private String createTime;
	//消费阅读币
	@Column(name = "FEE")
	private long fee;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	 

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
 
	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public int getLevels() {
		return levels;
	}

	public void setLevels(int levels) {
		this.levels = levels;
	}

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
 
	public long getDiscussId() {
		return discussId;
	}

	public void setDiscussId(long discussId) {
		this.discussId = discussId;
	}

	public ZanLog() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (articleId ^ (articleId >>> 32));
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + (int) (discussId ^ (discussId >>> 32));
		result = prime * result + (int) (fee ^ (fee >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + levels;
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		ZanLog other = (ZanLog) obj;
		if (articleId != other.articleId)
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (discussId != other.discussId)
			return false;
		if (fee != other.fee)
			return false;
		if (id != other.id)
			return false;
		if (levels != other.levels)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ZanLog [id=" + id + ", articleId=" + articleId + ", discussId="
				+ discussId + ", userId=" + userId + ", levels=" + levels
				+ ", createTime=" + createTime + ", fee=" + fee + "]";
	}

	 
	 
}
