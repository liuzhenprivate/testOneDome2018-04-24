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
@Entity(name = "TB_WEBCHATREPLY")
public class WebchatReply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WEBCHATREPLY_ID")
	private long id;

	// 微信id
	@Column(name = "WEBCHAT_ID")
	private long webchatId;

	 
	// 关键词
	@Column(name = "KEYWORDS")
	private String keywords;

	// 回复内容
	@Column(name = "CONTENT")
	private String content;
	/*//有效小时
	@Column(name = "VALID_HOURS")
	private int validHours;*/
	//类型0自动回复 1关注回复',
	@Column(name = "TYPE")
	private int type;
	
	// 失效时间
	@Column(name = "LOSE_TIME")
	private String loseTime;

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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	 

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLoseTime() {
		return loseTime;
	}

	public void setLoseTime(String loseTime) {
		this.loseTime = loseTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public WebchatReply() {
		super();
	}

	@Override
	public String toString() {
		return "WebchatReply [id=" + id + ", webchatId=" + webchatId
				+ ", keywords=" + keywords + ", content=" + content
				 + ", type=" + type
				+ ", loseTime=" + loseTime + ", createTime=" + createTime + "]";
	}

	 
}
