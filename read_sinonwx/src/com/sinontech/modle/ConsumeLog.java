package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 阅读消费记录表
 * @author ljj
 *
 */
@Entity(name = "TB_CONSUMELOG")
public class ConsumeLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONSUMELOG_ID")
	private long id;
	//读者ID
	@Column(name = "USERID")
	private long userId;
	//渠道表主键
	@Column(name = "USER_ID")
	private long channelId;
	//微信公众
	@Column(name = "WEBCHAT_ID")
	private long webchatId;
	//书籍ID
	@Column(name = "ARTICLE_ID")
	private long articleId;
	//章节ID
	@Column(name = "ARTICLE_CHAPTER_ID")
	private long articleChapterId;
	//书籍名称
	@Column(name = "ARTICLE_NAME")
	private String articleName;
	//章节名称
	@Column(name = "CHAPTER_NAME")
	private String chapterName;
	@Column(name = "CREATE_TIME")
	private String createTime;
	//消费金额
	@Column(name = "MONEY")
	private long money;
	//消费阅读币
	@Column(name = "CONSUMES")
	private long consumes;
	//消费类型 0购买章节 1购买书籍
	@Column(name = "CONSUMES_TYPE")
	private long consumeType;
	
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

	public long getChannelId() {
		return channelId;
	}

	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public long getArticleChapterId() {
		return articleChapterId;
	}

	public void setArticleChapterId(long articleChapterId) {
		this.articleChapterId = articleChapterId;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public long getConsumes() {
		return consumes;
	}

	public void setConsumes(long consumes) {
		this.consumes = consumes;
	}

	public long getWebchatId() {
		return webchatId;
	}

	public void setWebchatId(long webchatId) {
		this.webchatId = webchatId;
	}
 
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
 
	public long getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(long consumeType) {
		this.consumeType = consumeType;
	}

	public ConsumeLog() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (articleChapterId ^ (articleChapterId >>> 32));
		result = prime * result + (int) (articleId ^ (articleId >>> 32));
		result = prime * result
				+ ((articleName == null) ? 0 : articleName.hashCode());
		result = prime * result + (int) (channelId ^ (channelId >>> 32));
		result = prime * result
				+ ((chapterName == null) ? 0 : chapterName.hashCode());
		result = prime * result + (int) (consumeType ^ (consumeType >>> 32));
		result = prime * result + (int) (consumes ^ (consumes >>> 32));
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (money ^ (money >>> 32));
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + (int) (webchatId ^ (webchatId >>> 32));
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
		ConsumeLog other = (ConsumeLog) obj;
		if (articleChapterId != other.articleChapterId)
			return false;
		if (articleId != other.articleId)
			return false;
		if (articleName == null) {
			if (other.articleName != null)
				return false;
		} else if (!articleName.equals(other.articleName))
			return false;
		if (channelId != other.channelId)
			return false;
		if (chapterName == null) {
			if (other.chapterName != null)
				return false;
		} else if (!chapterName.equals(other.chapterName))
			return false;
		if (consumeType != other.consumeType)
			return false;
		if (consumes != other.consumes)
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id != other.id)
			return false;
		if (money != other.money)
			return false;
		if (userId != other.userId)
			return false;
		if (webchatId != other.webchatId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConsumeLog [id=" + id + ", userId=" + userId + ", channelId="
				+ channelId + ", webchatId=" + webchatId + ", articleId="
				+ articleId + ", articleChapterId=" + articleChapterId
				+ ", articleName=" + articleName + ", chapterName="
				+ chapterName + ", createTime=" + createTime + ", money="
				+ money + ", consumes=" + consumes + ", consumeType="
				+ consumeType + "]";
	}

	 
}