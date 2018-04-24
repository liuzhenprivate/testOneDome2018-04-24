package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 书籍类目表
 * @author liuzhen
 *
 */
@Entity(name = "TB_ARTICLE_CATEGORY")
public class ArticleCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTICLE_CATEGORY_ID")
	private long id;

	// 书籍类目名称
	@Column(name = "CATEGORY")
	private String gategory;
	//类目类型0女生1男生
	@Column(name = "CATEGORY_TYPE")
	private int ctype;
		
	//图片路径
	@Column(name = "IMGURL")
	private String imgUrl;

	// 书籍类目排序
	@Column(name = "SORT") 
	private int sort;

	// 书籍类目状态
	@Column(name = "STATE")
	private int state;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getGategory() {
		return gategory;
	}
	public void setGategory(String gategory) {
		this.gategory = gategory;
	}

	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public int getCtype() {
		return ctype;
	}
	public void setCtype(int ctype) {
		this.ctype = ctype;
	}
	public ArticleCategory() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "ArticleCategory [id=" + id + ", gategory=" + gategory
				+ ", ctype=" + ctype + ", imgUrl=" + imgUrl + ", sort=" + sort
				+ ", state=" + state + "]";
	}
	 
}
