package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
/**
 * 书籍阅读记录表
 * @author ljj
 *
 */
@Entity(name = "TB_ARTICLELOG")
public class ArticleLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTICLELOG_ID")
	private long id;
	//书籍ID
	@OneToOne
	@JoinColumn(name = "ARTICLE_ID")
	private Article article;
	//章节ID
	/*@OneToOne
	@JoinColumn(name = "ARTICLE_CHAPTER_ID")
	private ArticleChapter articleChapter;*/
	@Column(name = "ARTICLE_CHAPTER_ID")
	private long articleChapterId;
	//书籍名称
	@Column(name = "ARTICLE_NAME")
	private String articleName;
	//章节名称
	@Column(name = "CHAPTER_NAME")
	private String chapterName;
	
	//读者ID
	@Column(name = "USERID")
	private long userId;
	//渠道表主键
	@Column(name = "USER_ID")
	private long channelId;
	//微信公众
	@Column(name = "WEBCHAT_ID")
	private long webchatId;
	
	@Column(name = "CREATE_TIME")
	private String createTime;
	//消费阅读币
	@Column(name = "FEE")
	private long fee;
	
	//更新时间
	@Column(name = "UPDATE_TIME")
	private String updateTime;
	
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
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
 
	public ArticleLog() {
		super();
	}
	
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result
				+ (int) (articleChapterId ^ (articleChapterId >>> 32));
		result = prime * result
				+ ((articleName == null) ? 0 : articleName.hashCode());
		result = prime * result + (int) (channelId ^ (channelId >>> 32));
		result = prime * result
				+ ((chapterName == null) ? 0 : chapterName.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + (int) (fee ^ (fee >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
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
		ArticleLog other = (ArticleLog) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (articleChapterId != other.articleChapterId)
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
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (fee != other.fee)
			return false;
		if (id != other.id)
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (userId != other.userId)
			return false;
		if (webchatId != other.webchatId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticleLog [id=" + id + ", article=" + article
				+ ", articleChapterId=" + articleChapterId + ", articleName="
				+ articleName + ", chapterName=" + chapterName + ", userId="
				+ userId + ", channelId=" + channelId + ", webchatId="
				+ webchatId + ", createTime=" + createTime + ", fee=" + fee
				+ ", updateTime=" + updateTime + "]";
	}

	 
}