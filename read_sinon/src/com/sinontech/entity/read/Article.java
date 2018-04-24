package com.sinontech.entity.read;

/**
 * 书籍表
 *
 */
public class Article {
	private long id;

	// 书籍类目id
	private long articleCategoryId;

	// 书籍编号
	private String articleCode;

	// 书籍名称
	private String articleName;

	// 作者
	private String author;

	// 付费类型(默认0免费 1付费)
	private int feeType;

	// 付费方式(默认0阅读币购买阅读1 VIP免费指定章节免费时间段阅读)
	private int payWay;

	// 总阅读币
	private long payConsumes;

	// 是否热门
	private int isHost;

	// 简介
	private String summary;

	// 书籍总字数
	private long countLetter;

	// 书籍总章节
	private int countChapters;

	// 书籍状态(默认0未上架 1已上架 2下架 -1删除)
	private int state;

	// 创建时间
	private String createTime;

	// 收藏人数
	private int collection;

	// 阅读人数
	private int readers;
	
	//显示阅读人数
	private int displayReaders;
	
	// 购买书籍总阅读币
	private long countConsumes;

	// 频道（0男频1女频）
	private int channelType;

	// 连载状态（0：连载中 1：已完结）
	private int serialState;

	// 书籍封面
	private String bookCover;

	// 推荐指数
	private int recommend;
	
	//书籍类型
	private String category;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	
	public int getDisplayReaders() {
		return displayReaders;
	}

	public void setDisplayReaders(int displayReaders) {
		this.displayReaders = displayReaders;
	}

	 

	public long getArticleCategoryId() {
		return articleCategoryId;
	}

	public void setArticleCategoryId(long articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	 
}
