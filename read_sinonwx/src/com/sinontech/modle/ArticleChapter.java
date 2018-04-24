package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 章节表
 * @author liuzhen
 *
 */
@Entity(name = "TB_ARTICLE_CHAPTER")
public class ArticleChapter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTICLE_CHAPTER_ID")
	private long id;
	
	// 书籍id
	@OneToOne
	@JoinColumn(name = "ARTICLE_ID")
	private Article article;

	// 章节名称
	@Column(name = "CHAPTER_NAME")
	private String chapterName;

	// 章节序号
	@Column(name = "CHAPTER_NO")
	private long chapterNo;
	
	// 是否收费
	@Column(name = "IS_FREE")
	private int isFree;

	// 阅读币
	@Column(name = "CONSUMES")
	private long consumes;

	// 创建时间
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	// 章节字数
	@Column(name = "NUMBER_CHAPTER")
	private long numberChapter;

	// 章节状态
	@Column(name = "CHAPTER_STATE")
	private int chapterState;

	// 修改时间
	@Column(name = "UPDATE_TIME")
	private String updateTime;
	
	// 章节路径
	@Column(name = "CONTENT_URL")
	private String contentUrl;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	 

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public long getChapterNo() {
		return chapterNo;
	}

	public void setChapterNo(long chapterNo) {
		this.chapterNo = chapterNo;
	}

	public int getIsFree() {
		return isFree;
	}

	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}

	public long getConsumes() {
		return consumes;
	}

	public void setConsumes(long consumes) {
		this.consumes = consumes;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public long getNumberChapter() {
		return numberChapter;
	}

	public void setNumberChapter(long numberChapter) {
		this.numberChapter = numberChapter;
	}

	public int getChapterState() {
		return chapterState;
	}

	public void setChapterState(int chapterState) {
		this.chapterState = chapterState;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result
				+ ((chapterName == null) ? 0 : chapterName.hashCode());
		result = prime * result + (int) (chapterNo ^ (chapterNo >>> 32));
		result = prime * result + chapterState;
		result = prime * result + (int) (consumes ^ (consumes >>> 32));
		result = prime * result
				+ ((contentUrl == null) ? 0 : contentUrl.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + isFree;
		result = prime * result
				+ (int) (numberChapter ^ (numberChapter >>> 32));
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
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
		ArticleChapter other = (ArticleChapter) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (chapterName == null) {
			if (other.chapterName != null)
				return false;
		} else if (!chapterName.equals(other.chapterName))
			return false;
		if (chapterNo != other.chapterNo)
			return false;
		if (chapterState != other.chapterState)
			return false;
		if (consumes != other.consumes)
			return false;
		if (contentUrl == null) {
			if (other.contentUrl != null)
				return false;
		} else if (!contentUrl.equals(other.contentUrl))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id != other.id)
			return false;
		if (isFree != other.isFree)
			return false;
		if (numberChapter != other.numberChapter)
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticleChapter [id=" + id + ", article=" + article
				+ ", chapterName=" + chapterName + ", chapterNo=" + chapterNo
				+ ", isFree=" + isFree + ", consumes=" + consumes
				+ ", createTime=" + createTime + ", numberChapter="
				+ numberChapter + ", chapterState=" + chapterState
				+ ", updateTime=" + updateTime + ", contentUrl=" + contentUrl
				+ "]";
	}

	
}
