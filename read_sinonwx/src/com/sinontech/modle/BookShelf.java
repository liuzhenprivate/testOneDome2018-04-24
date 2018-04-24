package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 
 * 用途：书架表
 * @author liuzhen
 * 时间：2018-2-27 上午10:05:05
 */
@Entity(name = "TB_BOOKSHELF")
public class BookShelf {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOKSHELF_ID")
	private long id;
	
	@OneToOne
	@JoinColumn(name = "ARTICLE_ID")
	private Article article;
	
	
	//用户id
	@Column(name="USERID")
	private long userid;
	
	//渠道id
	@Column(name="USER_ID")
	private long user_id;
	
	//章节id
	@OneToOne
	@JoinColumn(name="ARTICLE_CHAPTER_ID")
	private ArticleChapter articleChapter;
	
	//创建时间
	@Column(name="CREATE_TIME")
	private String createTime;
	
	//更新时间
	@Column(name="UPDATE_TIME")
	private String updateTime;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public ArticleChapter getArticleChapter() {
		return articleChapter;
	}

	public void setArticleChapter(ArticleChapter articleChapter) {
		this.articleChapter = articleChapter;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result
				+ ((articleChapter == null) ? 0 : articleChapter.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + (int) (user_id ^ (user_id >>> 32));
		result = prime * result + (int) (userid ^ (userid >>> 32));
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
		BookShelf other = (BookShelf) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (articleChapter == null) {
			if (other.articleChapter != null)
				return false;
		} else if (!articleChapter.equals(other.articleChapter))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id != other.id)
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (user_id != other.user_id)
			return false;
		if (userid != other.userid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookShelf [id=" + id + ", article=" + article + ", userid="
				+ userid + ", user_id=" + user_id + ", articleChapter="
				+ articleChapter + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
}
