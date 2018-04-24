package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 榜单表
 * 
 * @author liuzhen
 * 
 */
@Entity(name = "TB_BOARD")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOARD_ID")
	private long id;

	// 榜单排序规则
	@Column(name = "BOARD_NO")
	private int boardNo;

	// 榜单名
	@Column(name = "BOARD_NAME")
	private String boardNmae;

	// 榜单图标
	@Column(name = "BOARD_IMG")
	private String boardImg;

	// 创建时间
	@Column(name = "CREATE_TIME")
	private String createTime;

	// 榜单排序
	@Column(name = "SORT")
	private int sort;
	
	@Column(name = "CLICK_NUMBER")
	private long clickNumber;
	
	
	public long getClickNumber() {
		return clickNumber;
	}

	public void setClickNumber(long clickNumber) {
		this.clickNumber = clickNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardNmae() {
		return boardNmae;
	}

	public void setBoardNmae(String boardNmae) {
		this.boardNmae = boardNmae;
	}

	public String getBoardImg() {
		return boardImg;
	}

	public void setBoardImg(String boardImg) {
		this.boardImg = boardImg;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((boardImg == null) ? 0 : boardImg.hashCode());
		result = prime * result
				+ ((boardNmae == null) ? 0 : boardNmae.hashCode());
		result = prime * result + boardNo;
		result = prime * result + (int) (clickNumber ^ (clickNumber >>> 32));
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + sort;
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
		Board other = (Board) obj;
		if (boardImg == null) {
			if (other.boardImg != null)
				return false;
		} else if (!boardImg.equals(other.boardImg))
			return false;
		if (boardNmae == null) {
			if (other.boardNmae != null)
				return false;
		} else if (!boardNmae.equals(other.boardNmae))
			return false;
		if (boardNo != other.boardNo)
			return false;
		if (clickNumber != other.clickNumber)
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id != other.id)
			return false;
		if (sort != other.sort)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", boardNo=" + boardNo + ", boardNmae="
				+ boardNmae + ", boardImg=" + boardImg + ", createTime="
				+ createTime + ", sort=" + sort + ", clickNumber="
				+ clickNumber + "]";
	}
	
	
}
