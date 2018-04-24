package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 渠道推荐详情表
 * @author liuzhen
 *
 */
@Entity(name = "TB_EXTENSION_CONTENT")
public class ExtensionContent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXTENSION_CONTENT_ID")
	private long id;
	
	//渠道id
	@Column(name="USER_ID")
	private long user_id;
	
	//推广封面
	@Column(name = "COVER")
	private String cover;
	
	//书籍ID
	@OneToOne
	@JoinColumn(name = "ARTICLE_ID")
	private Article article;
	
	//书籍名称
	@Column(name = "ARTICLE_NAME")
	private String articleName;
	
	//可以免费浏览的章节数
	@Column(name = "ARTICLE_CHAPTERS")
	private int articleChapters;
	
	//创建时间
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	//强制关注章节ID
	@Column(name = "FORCE_CHAPTER")
	private long forceChapter;
	
	//推广连接
	@Column(name = "CONTENT_URL")
	private String contentUrl;
	
	//推广标题
	@Column(name = "TITLE")
	private String title;
	
	//关注人数
	@Column(name = "FOLLOW")
	private int follow;
	
	//引导人数
	@Column(name = "GUIDE")
	private int guide;
	
	//充值金额
	@Column(name = "RECHARGE")
	private long recharge;
	
	//充值人数
	@Column(name = "RECHARGE_PEOPLES")
	private int rechargePoples;
	
	//充值笔数
	@Column(name = "RECHARGE_TIMES")
	private int rechargeTimes;
	
	//收益
	@Column(name = "PROFIT")
	private long profit;
	
	//样式名称
	@Column(name = "CSSS")
	private String csss;
	
	//下方点击标题
	@Column(name = "CLICK_TITLE")
	private String clickTitle;
	
	//下方点击样式
	@Column(name = "CLICK_TITLE_CSS")
	private String clickTitleCss;
	
	//推广状态（0默认  1删除）
	@Column(name = "STATE")
	private int state;
	
	//渠道名称
	@Column(name = "NAME")
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public int getArticleChapters() {
		return articleChapters;
	}

	public void setArticleChapters(int articleChapters) {
		this.articleChapters = articleChapters;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public long getForceChapter() {
		return forceChapter;
	}

	public void setForceChapter(long forceChapter) {
		this.forceChapter = forceChapter;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getFollow() {
		return follow;
	}

	public void setFollow(int follow) {
		this.follow = follow;
	}

	public int getGuide() {
		return guide;
	}

	public void setGuide(int guide) {
		this.guide = guide;
	}

	public long getRecharge() {
		return recharge;
	}

	public void setRecharge(long recharge) {
		this.recharge = recharge;
	}

	public int getRechargePoples() {
		return rechargePoples;
	}

	public void setRechargePoples(int rechargePoples) {
		this.rechargePoples = rechargePoples;
	}

	public int getRechargeTimes() {
		return rechargeTimes;
	}

	public void setRechargeTimes(int rechargeTimes) {
		this.rechargeTimes = rechargeTimes;
	}

	public long getProfit() {
		return profit;
	}

	public void setProfit(long profit) {
		this.profit = profit;
	}

	public String getCsss() {
		return csss;
	}

	public void setCsss(String csss) {
		this.csss = csss;
	}

	public String getClickTitle() {
		return clickTitle;
	}

	public void setClickTitle(String clickTitle) {
		this.clickTitle = clickTitle;
	}

	public String getClickTitleCss() {
		return clickTitleCss;
	}

	public void setClickTitleCss(String clickTitleCss) {
		this.clickTitleCss = clickTitleCss;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	  

	@Override
	public String toString() {
		return "ExtensionContent [id=" + id + ", user_id=" + user_id
				+ ", cover=" + cover + ", article=" + article
				+ ", articleName=" + articleName + ", articleChapters="
				+ articleChapters + ", createTime=" + createTime
				+ ", forceChapter=" + forceChapter + ", contentUrl="
				+ contentUrl + ", title=" + title + ", follow=" + follow
				+ ", guide=" + guide + ", recharge=" + recharge
				+ ", rechargePoples=" + rechargePoples + ", rechargeTimes="
				+ rechargeTimes + ", profit=" + profit + ", csss=" + csss
				+ ", clickTitle=" + clickTitle + ", clickTitleCss="
				+ clickTitleCss + ", state=" + state + ", name=" + name + "]";
	}
	
	
	
}
