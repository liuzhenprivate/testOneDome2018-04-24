package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 书籍表
 * @author liuzhen
 *
 */
@Entity(name = "TB_ARTICLE")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTICLE_ID")
	private long id;

	// 书籍类目id
	@OneToOne
	@JoinColumn(name = "ARTICLE_CATEGORY_ID")
	private ArticleCategory articleCategory;

	// 书籍编号
	@Column(name = "ARTICLE_CODE")
	private String articleCode;

	// 书籍名称
	@Column(name = "ARTICLE_NAME")
	private String articleName;

	// 作者
	@Column(name = "AUTHOR")
	private String author;

	// 付费类型(默认0免费 1付费)
	@Column(name = "FEE_TYPE")
	private int feeType;

	// 付费方式(默认0阅读币购买阅读1 VIP免费指定章节免费时间段阅读)
	@Column(name = "PAY_WAY")
	private int payWay;

	// 总阅读币
	@Column(name = "PAY_CONSUMES")
	private long payConsumes;

	// 是否热门
	@Column(name = "IS_HOT")
	private int isHost;

	// 简介
	@Column(name = "SUMMARY")
	private String summary;

	// 书籍总字数
	@Column(name = "COUNT_LETTER")
	private long countLetter;

	// 书籍总章节
	@Column(name = "COUNT_CHAPTERS")
	private int countChapters;

	// 书籍状态(默认0未上架 1已上架 2下架 -1删除)
	@Column(name = "STATE")
	private int state;

	// 创建时间
	@Column(name = "CREATE_TIME")
	private String createTime;

	// 收藏人数
	@Column(name = "COLLECTION")
	private int collection;

	// 阅读人数
	@Column(name = "READERS")
	private int readers;
	
	//显示阅读人数
	@Column(name = "DISPLAY_READERS")
	private int displayReaders;
	
	// 购买书籍总阅读币
	@Column(name = "COUNT_CONSUMES")
	private long countConsumes;

	// 频道（0男频1女频）
	@Column(name = "CHANNEL_TYPE")
	private int channelType;

	// 连载状态（0：连载中 1：已完结）
	@Column(name = "SERIAL_STATE")
	private int serialState;

	// 书籍封面
	@Column(name = "BOOK_COVER")
	private String bookCover;

	// 推荐指数
	@Column(name = "RECOMMEND")
	private int recommend;
	
	@Column(name = "HTTPURL")
	private String httpUrl;
	
	//书籍充值总额
	@Column(name = "RECHARGES")
	private long rrecharges;
	
	//书籍评分
	@Column(name = "SCORE")
	private double score;
	
	//书籍付费人数
	@Column(name = "FEEPOPLES")
	private long feePoples;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}

	public String getArticleCode() {
		return articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getFeeType() {
		return feeType;
	}

	public void setFeeType(int feeType) {
		this.feeType = feeType;
	}

	public int getPayWay() {
		return payWay;
	}

	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}

	public long getPayConsumes() {
		return payConsumes;
	}

	public void setPayConsumes(long payConsumes) {
		this.payConsumes = payConsumes;
	}

	public int getIsHost() {
		return isHost;
	}

	public void setIsHost(int isHost) {
		this.isHost = isHost;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public long getCountLetter() {
		return countLetter;
	}

	public void setCountLetter(long countLetter) {
		this.countLetter = countLetter;
	}

	public int getCountChapters() {
		return countChapters;
	}

	public void setCountChapters(int countChapters) {
		this.countChapters = countChapters;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getCollection() {
		return collection;
	}

	public void setCollection(int collection) {
		this.collection = collection;
	}

	public int getReaders() {
		return readers;
	}

	public void setReaders(int readers) {
		this.readers = readers;
	}

	public int getDisplayReaders() {
		return displayReaders;
	}

	public void setDisplayReaders(int displayReaders) {
		this.displayReaders = displayReaders;
	}

	public long getCountConsumes() {
		return countConsumes;
	}

	public void setCountConsumes(long countConsumes) {
		this.countConsumes = countConsumes;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public int getSerialState() {
		return serialState;
	}

	public void setSerialState(int serialState) {
		this.serialState = serialState;
	}

	public String getBookCover() {
		return bookCover;
	}

	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	
	public long getRrecharges() {
		return rrecharges;
	}

	public void setRrecharges(long rrecharges) {
		this.rrecharges = rrecharges;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public long getFeePoples() {
		return feePoples;
	}

	public void setFeePoples(long feePoples) {
		this.feePoples = feePoples;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((articleCategory == null) ? 0 : articleCategory.hashCode());
		result = prime * result
				+ ((articleCode == null) ? 0 : articleCode.hashCode());
		result = prime * result
				+ ((articleName == null) ? 0 : articleName.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result
				+ ((bookCover == null) ? 0 : bookCover.hashCode());
		result = prime * result + channelType;
		result = prime * result + collection;
		result = prime * result + countChapters;
		result = prime * result
				+ (int) (countConsumes ^ (countConsumes >>> 32));
		result = prime * result + (int) (countLetter ^ (countLetter >>> 32));
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + displayReaders;
		result = prime * result + (int) (feePoples ^ (feePoples >>> 32));
		result = prime * result + feeType;
		result = prime * result + ((httpUrl == null) ? 0 : httpUrl.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + isHost;
		result = prime * result + (int) (payConsumes ^ (payConsumes >>> 32));
		result = prime * result + payWay;
		result = prime * result + readers;
		result = prime * result + recommend;
		result = prime * result + (int) (rrecharges ^ (rrecharges >>> 32));
		long temp;
		temp = Double.doubleToLongBits(score);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + serialState;
		result = prime * result + state;
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
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
		Article other = (Article) obj;
		if (articleCategory == null) {
			if (other.articleCategory != null)
				return false;
		} else if (!articleCategory.equals(other.articleCategory))
			return false;
		if (articleCode == null) {
			if (other.articleCode != null)
				return false;
		} else if (!articleCode.equals(other.articleCode))
			return false;
		if (articleName == null) {
			if (other.articleName != null)
				return false;
		} else if (!articleName.equals(other.articleName))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookCover == null) {
			if (other.bookCover != null)
				return false;
		} else if (!bookCover.equals(other.bookCover))
			return false;
		if (channelType != other.channelType)
			return false;
		if (collection != other.collection)
			return false;
		if (countChapters != other.countChapters)
			return false;
		if (countConsumes != other.countConsumes)
			return false;
		if (countLetter != other.countLetter)
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (displayReaders != other.displayReaders)
			return false;
		if (feePoples != other.feePoples)
			return false;
		if (feeType != other.feeType)
			return false;
		if (httpUrl == null) {
			if (other.httpUrl != null)
				return false;
		} else if (!httpUrl.equals(other.httpUrl))
			return false;
		if (id != other.id)
			return false;
		if (isHost != other.isHost)
			return false;
		if (payConsumes != other.payConsumes)
			return false;
		if (payWay != other.payWay)
			return false;
		if (readers != other.readers)
			return false;
		if (recommend != other.recommend)
			return false;
		if (rrecharges != other.rrecharges)
			return false;
		if (Double.doubleToLongBits(score) != Double
				.doubleToLongBits(other.score))
			return false;
		if (serialState != other.serialState)
			return false;
		if (state != other.state)
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", articleCategory=" + articleCategory
				+ ", articleCode=" + articleCode + ", articleName="
				+ articleName + ", author=" + author + ", feeType=" + feeType
				+ ", payWay=" + payWay + ", payConsumes=" + payConsumes
				+ ", isHost=" + isHost + ", summary=" + summary
				+ ", countLetter=" + countLetter + ", countChapters="
				+ countChapters + ", state=" + state + ", createTime="
				+ createTime + ", collection=" + collection + ", readers="
				+ readers + ", displayReaders=" + displayReaders
				+ ", countConsumes=" + countConsumes + ", channelType="
				+ channelType + ", serialState=" + serialState + ", bookCover="
				+ bookCover + ", recommend=" + recommend + ", httpUrl="
				+ httpUrl + ", rrecharges=" + rrecharges + ", score=" + score
				+ ", feePoples=" + feePoples + "]";
	}
	
}