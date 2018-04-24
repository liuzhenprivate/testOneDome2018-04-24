package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 渠道推荐发展用户记录表
 * @author liuzhen
 *
 */
@Entity(name = "TB_EXTENSION_USER")
public class ExtensionUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXTENSION_USER_ID")
	private long id;
	
	//推广id
	@Column(name="EXTENSION_CONTENT_ID")
	private long extensionId;
	
	//用户ID
	@Column(name = "USER_ID")
	private long user_id;
	//关注状态 默认 0未关注 1已关注
	@Column(name = "FOLLOWSTATE")
	private int followstate;
	
	//发展时间
	@Column(name = "CREATE_TIME")
	private String ctime;

	//充值金额
	@Column(name = "RECHARGE")
	private long recharge;
	
	//充值笔数
	@Column(name = "RECHARGE_TIMES")
	private int rechargeTimes;
		
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

	public long getExtensionId() {
		return extensionId;
	}

	public void setExtensionId(long extensionId) {
		this.extensionId = extensionId;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public int getFollowstate() {
		return followstate;
	}

	public void setFollowstate(int followstate) {
		this.followstate = followstate;
	}

	public long getRecharge() {
		return recharge;
	}

	public void setRecharge(long recharge) {
		this.recharge = recharge;
	}

	public int getRechargeTimes() {
		return rechargeTimes;
	}

	public void setRechargeTimes(int rechargeTimes) {
		this.rechargeTimes = rechargeTimes;
	}

	public ExtensionUser() {
		super();
	}

	@Override
	public String toString() {
		return "ExtensionUser [id=" + id + ", extensionId=" + extensionId
				+ ", user_id=" + user_id + ", followstate=" + followstate
				+ ", ctime=" + ctime + ", recharge=" + recharge
				+ ", rechargeTimes=" + rechargeTimes + "]";
	}

	 

}
