package com.sinontech.pub.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 * @author zwq
 */
public class IConstants {
	public static String TEST_PHONE="15355016647";
	
	/**
	 * 获取短信验证码类型
	 */
	public static String SMS_LOGIN_CODE="sms_login_code";
	public static String SMS_REGISTER_CODE="sms_register_code";
	
	/**
	 * 订购/赠送铃音类型
	 */
	public static Integer RING_ORDER_REMSG=1;
	public static Integer RING_GIFT_RETMSG=2;
	
	/**
	 * 后台使用
	 */
	public static String LOGIN_USER ="login_user";
	public static String ADMIN_USER ="admin_user";
	public static String ADMIN_ACTIVITY ="admin_activity";
	
	/**
	 * 表名
	 */
	public static String TABLE_ACTIVITY="activity_zj";
	public static String TABLE_RING="ring_zj";
	public static String TABLE_LOG="log_zj";
	public static String TABLE_USERINFO="userinfo_zj";
	public static String TABLE_AWARD="award_zj";
	public static String TABLE_AWARDMENU="awardmenu_zj";
	public static String TABLE_PLAY="play_zj";
	public static String TABLE_ORDER="order_zj";
	public static String TABLE_TYPE="type_zj";
	public static String TABLE_VOTE="vote_zj";
	public static String TABLE_GAME="game_zj";
	
	/**
	 * 日志操作类型
	 */
	 public static final String LOG_LOGIN = "1";
	 public static final String LOG_ORDER_RING = "2";
	 public static final String LOG_GIFT_RING = "3";
	 public static final String LOG_REGISTER = "4";
	 public static final String LOG_SHARE = "5";
	 
	 
	 
	 /**
	  * 描述
	  */
	 public static final String LOGIN_DAY = "login_day";
	 public static final String LOGIN_ALL = "login_all";
	 public static final String ORDER_RING = "order_ring";
	 public static final String ORDER_BOX = "order_box";
	 public static final String GIFT_RING = "gift_ring";
	 public static final String SYSTEM_INTEGRAL = "system_integral";
	 public static final String GAME ="game";
	 public static final String PLAY_VOTE ="play_vote";
	 public static final String SHARE ="share";
	 public static final String REGISTER ="register";
	 
	
	 /**
	  * 渠道
	  */
	 public static final Integer TYPE_WEB = 0;//WEB
	 public static final Integer TYPE_WAP = 1;//WAP
	 public static final Integer TYPE_WEIXIN = 2;//WEIXIN
	 public static final Integer TYPE_SYSTEM = 3;//SYSTEM
	 public static final Integer TYPE_JMS = 4;//JMS
	 
	 public static Map<Integer,String> ORDER_TYPES(){
	        Map<Integer,String> map = new HashMap<Integer,String>();
	        map.put(TYPE_WEB, "web");
	        map.put(TYPE_WAP, "wap");
	        map.put(TYPE_WEIXIN, "weixin");
	        map.put(TYPE_SYSTEM, "system");
	        map.put(TYPE_JMS, "jms");
	        return map;
	    }
	 /**
	  * 奖品信息
	  */
	 public static final Integer AWARD_THANK=0;
	 public static final Integer AWARD_ONE_CHANCE=2;
	 
	 
	 /**
	  * 投票类型
	  */
	 public static final Integer PLAY_VOTE_VOTE=1;
	 public static final Integer PLAY_VOTE_FLOWER=2;
	 public static final Integer PLAY_VOTE_EGG=3;
	 public static final Integer PLAY_VOTE_MSG=4;
}
