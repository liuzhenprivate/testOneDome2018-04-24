package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 榜单表详情表
 * 
 * @author liuzhen
 * 
 */
@Entity(name = "TB_BOARD_DETAIL")
public class BoardDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOARD_DETAIL_ID")
	private long id;
	
	//榜单主键id
	@Column(name = "BOARD_ID")
	private long boardId;
	
	// 书籍id
	@Column(name = "ARTICLE_ID")
	private long articleId;

	// 榜单类型
	@Column(name = "BOARD_TYPE")
	private int boardType;

	// 排序
	@Column(name = "SORT")
	private int sort;

	// 创建时间
	@Column(name = "CREATE_TIME")
	private String createTime;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public int getBoardType() {
		return boardType;
	}

	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	

	@Override
	public String toString() {
		return "BoardDetail [id=" + id + ", boardId=" + boardId
				+ ", articleId=" + articleId + ", boardType=" + boardType
				+ ", sort=" + sort + ", createTime=" + createTime + "]";
	}

}
