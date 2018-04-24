package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 书籍评论表
 * @author ljj
 *
 */
@Entity(name = "TB_DISCUSS")
public class Discuss {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DISCUSS_ID")
	private long id;

	// 书籍id
	@Column(name = "ARTICLE_ID")
	private long articleId;

	// 读者ID
	@OneToOne
	@JoinColumn(name = "USERID")
	private UserInfo user;

	// 评论内容
	@Column(name = "CONTENT")
	private String content;

	// 状态0未审核1审核通过2审核失败3删除
	@Column(name = "STATE")
	private int state;
	//几星
	@Column(name = "LEVELS")
	private int levels;
	//赞数
	@Column(name = "ZANS")
	private long zans;
	
	// 平台ID
	@Column(name = "USER_CODE")
	private String userCode;

	// 创建时间
	@Column(name = "CREATE_TIME")
	private String createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	 

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getLevels() {
		return levels;
	}

	public void setLevels(int levels) {
		this.levels = levels;
	}

	public long getZans() {
		return zans;
	}

	public void setZans(long zans) {
		this.zans = zans;
	}

	public Discuss() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (articleId ^ (articleId >>> 32));
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + levels;
		result = prime * result + state;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result
				+ ((userCode == null) ? 0 : userCode.hashCode());
		result = prime * result + (int) (zans ^ (zans >>> 32));
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
		Discuss other = (Discuss) obj;
		if (articleId != other.articleId)
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
		if (id != other.id)
			return false;
		if (levels != other.levels)
			return false;
		if (state != other.state)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		if (zans != other.zans)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Discuss [id=" + id + ", articleId=" + articleId + ", user="
				+ user + ", content=" + content + ", state=" + state
				+ ", levels=" + levels + ", zans=" + zans + ", userCode="
				+ userCode + ", createTime=" + createTime + "]";
	}

	 
	 
	
}
