package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 搜索关键词配置表
 * @author ljj
 *
 */
@Entity(name = "TB_SEARCHKEY")
public class SearchKey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEARCHKEY_ID")
	private long id;
	//默认0热门搜索 1历史搜索
	@Column(name = "SEARCH_TYPE")
	private int searchType;
	//关键词
	@Column(name = "KEYWORD")
	private String keyword;
	//搜索时间
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	public long getId() {
		return id;
	}
  

	public int getSearchType() {
		return searchType;
	}


	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}


	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SearchKey() {
		super();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + searchType;
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
		SearchKey other = (SearchKey) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id != other.id)
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (searchType != other.searchType)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "SearchKey [id=" + id + ", searchType=" + searchType
				+ ", keyword=" + keyword + ", createTime=" + createTime + "]";
	}
 
 
	 
}
