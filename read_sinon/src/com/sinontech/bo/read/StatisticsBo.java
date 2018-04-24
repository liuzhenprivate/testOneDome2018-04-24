package com.sinontech.bo.read;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.controller.base.BaseController;
import com.sinontech.service.read.consumelog.ConsumelogService;
import com.sinontech.service.read.countday.CountDayService;
import com.sinontech.service.read.countmonth.CountMonthService;
import com.sinontech.service.read.countweek.CountWeekService;
import com.sinontech.service.read.rechargelog.RechargeLogService;
import com.sinontech.service.read.singlog.SingLogService;
import com.sinontech.service.read.user.WXUserService;
import com.sinontech.service.system.user.UserService;
import com.sinontech.util.DateUtil;
import com.sinontech.util.PageData;

@Service("statisticsBo")
public class StatisticsBo extends BaseController {
	@Resource(name = "countmonthService")
	private CountMonthService countmonthService;

	@Resource(name = "countdayService")
	private CountDayService countdayService;

	@Resource(name = "countweekService")
	private CountWeekService countweekService;

	@Resource(name = "wxuserService")
	private WXUserService wxuserService;

	@Resource(name = "rechargelogService")
	private RechargeLogService rechargelogService;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "singlogService")
	private SingLogService singlogService;

	@Resource(name = "consumelogService")
	private ConsumelogService consumelogService;
	
	// 总会员数
	long usersAll = 0l;// 平台总会员数
	long usersFee = 0l;// 收费会员数
	long usersDay = 0l;// 当日
	long userYesterDay = 0l;// 昨天
	long usersWeek = 0l;// 本周
	long usersMonth = 0l; // 本月

	// 累计充值数
	long recharges = 0l;// 总充值数
	long rechargesDay = 0l;// 当日充值
	long rechargesYesterDay = 0l;// 昨日充值
	long rechargesWeek = 0l;// 本周充值
	long rechargesMonth = 0l;// 本月充值

	// 累计充值笔数
	long rechargeTimes = 0l;// 总充值笔数
	long rechargeTimesDay = 0l;// 当日总充值笔数
	long rechargeTimesYesterDay = 0l;// 昨日总充值笔数
	long rechargeTimesWeek = 0l;// 当周总充值笔数
	long rechargeTimesMonth = 0l;// 当月总充值笔数

	// 累计消费额
	long consumes = 0l;// 总消费额
	long consumeDay = 0l;// 每天消费额
	long consumeYesterDay = 0l;// 昨天消费额
	long consumeWeek = 0l;// 每周消费额
	long consumeMonth = 0l;// 每月消费额

	// 消费笔数
	long consumeNumbers = 0l;// 总消费笔数
	long consumeNumberDay = 0l;// 每天消费笔数
	long consumeNumberYesterDay = 0l;// 昨天消费笔数
	long consumeNumberWeek = 0l;// 每周消费笔数
	long consumeNumberMonth = 0l;// 每月消费笔数

	// 总收益
	double income = 0;// 平台收益
	double incomeUp = 0;// 上游收益
	double incomeChannel = 0;// 渠道收益

	// 阅读币
	long bookcurAll = 0l;// 平台总共阅读币
	long bookcurGife = 0l;// 平台赠送的阅读币
	long bookcurRecharge = 0l;// 用户充值兑换的阅读币

	// 渠道统计
	long countSysUsers = 0l; // 渠道个数
	long SysUserFans = 0l; // 渠道粉丝
	long SysUserFansFee = 0l; // 渠道付费粉丝
	long WXFansFeeDay = 0l; // 今天渠道粉丝
	long WXFansFeeMonth = 0l; // 本月渠道付费粉丝
	
	//签到人数和签到获得阅读币统计
	long singLogs = 0l;//渠道总签到人数
	long singLogDay = 0l;//今日签到人数
	long singLogMonth = 0l;//本月签到人数
	long singLogYserterDay = 0l;//昨天签到人数
	
	long singCurrencys = 0l; //累计签到获得阅读币
	long singCurrencyDay = 0l; //今日签到获得阅读币
	long singCurrencysMonth = 0l; //本月签到获得阅读币
	long singCurrencyYserterDay = 0l; //昨天签到获得阅读币
	
	/*
	 * 签到人数和签到获得阅读币统计
	 */
	public PageData singLogData(PageData pd) throws Exception{
		PageData singpd =  singlogService.seachSing(pd);
		PageData singDaypd =  singlogService.seachSingLogDay(pd);
		PageData singMonthpd =  singlogService.seachSingLogMonth(pd);
		PageData singYserterDaypd =  singlogService.seachSingLogYserterDay(pd);
		if(singpd!=null){
			pd.put("RECHARGE_IDS", singpd.get("RECHARGE_IDS").toString());
			pd.put("BOOK_CURRENCYSS", singpd.get("BOOK_CURRENCYSS").toString());
		}else{
			pd.put("RECHARGE_IDS", 0);
			pd.put("BOOK_CURRENCYSS", 0);
		}
		if(singDaypd!=null){
			pd.put("DRECHARGE_ID", singDaypd.get("DRECHARGE_ID").toString());
			pd.put("DBOOK_CURRENCYS", singDaypd.get("DBOOK_CURRENCYS").toString());
		}else{
			pd.put("DRECHARGE_ID", 0);
			pd.put("DBOOK_CURRENCYS", 0);
		}
		if(singMonthpd!=null){
			pd.put("MRECHARGE_ID", singMonthpd.get("MRECHARGE_ID").toString());
			pd.put("MBOOK_CURRENCYS", singMonthpd.get("MBOOK_CURRENCYS").toString());
		}else{
			pd.put("MRECHARGE_ID", 0);
			pd.put("MBOOK_CURRENCYS", 0);
		}
		if(singYserterDaypd!=null){
			pd.put("YDRECHARGE_ID", singYserterDaypd.get("YDRECHARGE_ID").toString());
			pd.put("YDBOOK_CURRENCYS", singYserterDaypd.get("").toString());
		}else{
			pd.put("YDRECHARGE_ID", 0);
			pd.put("YDBOOK_CURRENCYS", 0);
		}
		return pd;
	}
	
	
	/**
	 * 查询当天统计数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public PageData statisticsDayData() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData dayData = countdayService.countDay(pd);
		if (dayData != null) {
			usersDay = Long.parseLong(dayData.get("USERS").toString());// 每天会员数
			rechargesDay = Long.parseLong(dayData.get("RECHARGES").toString());// 当日充值
			rechargeTimesDay = Long.parseLong(dayData.get("RECHARGETIMES").toString()); // 当日总充值笔数
			consumeDay = Long.parseLong(dayData.get("MONEY").toString());// 当日消费额
			consumeNumberDay = Long.parseLong(dayData.get("CONSUMENUMBER").toString());// 当日消费笔数
			pd.put("dayData", dayData);
		}
		pd.put("consumeDay", consumeDay);
		pd.put("consumeNumberDay", consumeNumberDay);
		pd.put("usersDay", usersDay);
		pd.put("rechargesDay", rechargesDay);
		pd.put("rechargeTimesDay", rechargeTimesDay);
		return pd;
	}

	/**
	 * 查询本周统计数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public PageData statisticsWeekData() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData weekData = countweekService.countWeek(pd);
		if (weekData != null) {
			usersWeek = Long.parseLong(weekData.get("USERS").toString()); // 每周会员数
			rechargesWeek = Long.parseLong(weekData.get("RECHARGES").toString()); // 每周充值
			rechargeTimesWeek = Long.parseLong(weekData.get("RECHARGETIMES").toString()); // 每周总充值笔数
			consumeWeek = Long.parseLong(weekData.get("MONEY").toString());// 每周消费额
			consumeNumberWeek = Long.parseLong(weekData.get("CONSUMENUMBER").toString());// 每周消费笔数
			pd.put("weekData", weekData);
		}
		pd.put("consumeWeek", consumeWeek);
		pd.put("consumeNumberWeek", consumeNumberWeek);
		pd.put("usersWeek", usersWeek);
		pd.put("rechargesWeek", rechargesWeek);
		pd.put("rechargeTimesWeek", rechargeTimesWeek);
		return pd;
	}

	/**
	 * 查询当月统计数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public PageData statisticsMonthData() throws Exception {
		PageData pd = new PageData();

		pd = this.getPageData();
		PageData monthData = countmonthService.countMonth(pd);
		if (monthData != null) {
			usersMonth = Long.parseLong(monthData.get("USERS") + "".toString());// 每月会员数
			rechargesMonth = Long.parseLong(monthData.get("RECHARGES").toString()); // 每月充值
			rechargeTimesMonth = Long.parseLong(monthData.get("RECHARGETIMES").toString()); // 每月总充值笔数
			consumeMonth = Long.parseLong(monthData.get("MONEY").toString()); // 每月消费额
			consumeNumberMonth = Long.parseLong(monthData.get("CONSUMENUMBER").toString()); // 每月消费笔数
			pd.put("monthData", monthData);
		}
		pd.put("consumeMonth", consumeMonth);
		pd.put("consumeNumberMonth", consumeNumberMonth);
		pd.put("usersMonth", usersMonth);
		pd.put("rechargesMonth", rechargesMonth);
		pd.put("rechargeTimesMonth", rechargeTimesMonth);
		return pd;
	}

	/**
	 * 统计平台会员总数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public PageData statisticsCountUsers() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData countUsers = countmonthService.countUsersAll(pd);
		if (countUsers != null) {
			usersAll = Long.parseLong(countUsers.get("USERS").toString());// 总人数
			recharges = Long.parseLong(countUsers.get("RECHARGES").toString());// 总充值数
			rechargeTimes = Long.parseLong(countUsers.get("RECHARGETIMES").toString());// 总充值笔数
			consumes = Long.parseLong(countUsers.get("MONEY").toString());// 总消费额
			consumeNumbers = Long.parseLong(countUsers.get("CONSUMENUMBER").toString());// 总消费笔数
			pd.put("countUsers", countUsers);
		}
		pd.put("consumes", consumes);
		pd.put("consumeNumbers", consumeNumbers);
		pd.put("usersAll", usersAll);
		pd.put("recharges", recharges);
		pd.put("rechargeTimes", rechargeTimes);
		return pd;
	}

	/**
	 * 查询昨天的统计数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public PageData statisticsCountYesterdayData() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData countUserdayData = countdayService.countUserdayData(pd);
		PageData seachYesterDayRechargeLog = rechargelogService.seachYesterDayRechargeLog(pd);
		PageData seachRechargeLog = rechargelogService.seachRechargeLog(pd);
		if (countUserdayData != null) {
			userYesterDay = Long.parseLong(countUserdayData.get("USERS").toString()); // 昨日阅读人数
			rechargesYesterDay = Long.parseLong(countUserdayData.get("RECHARGES").toString()); // 昨日充值数
			rechargeTimesYesterDay = Long.parseLong(countUserdayData.get("RECHARGETIMES").toString()); // 昨日充值笔数
			consumeYesterDay = Long.parseLong(countUserdayData.get("MONEY").toString()); // 昨天消费额
			consumeNumberYesterDay = Long.parseLong(countUserdayData.get("CONSUMENUMBER").toString()); // 昨天消费笔数
			pd.put("countUserdayData", countUserdayData);
		}
		if(seachYesterDayRechargeLog != null){
			pd.put("SumUserIds", seachYesterDayRechargeLog.getString("SumUserIds"));//昨日复充人数
		}else{
			pd.put("SumUserIds", 0);//昨日复充人数
		}
		
		if(seachRechargeLog != null){
			pd.put("countUserid", seachRechargeLog.getString("countUserid"));//昨日充值人数
		}else{
			pd.put("countUserid", 0);//昨日充值人数
		}
		pd.put("consumeYesterDay", consumeYesterDay);
		pd.put("consumeNumberYesterDay", consumeNumberYesterDay);
		pd.put("userYesterDay", userYesterDay);
		pd.put("rechargesYesterDay", rechargesYesterDay);
		pd.put("rechargeTimesYesterDay", rechargeTimesYesterDay);
		return pd;
	}

	/**
	 * 统计渠道信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public PageData statisticsSysUser() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData sysUserData = userService.countSysUserData(pd);
		countSysUsers = Long.parseLong(sysUserData.get("SYSUSERS").toString());// 渠道数
		pd.put("countSysUsers", countSysUsers);
		pd.put("sysUserData", sysUserData);
		return pd;
	}

	/**
	 * 统计渠道粉丝和渠道付费粉丝
	 * 
	 * @return
	 * @throws Exception
	 */
	public PageData statisticsUserFans() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData UserFans = wxuserService.countUserFans(pd);
		PageData UserFansFee = wxuserService.countUserFansFee(pd);
		PageData UserFansFeeDay = wxuserService.countUserFansFeeDay(pd);
		PageData UserFansFeeMonth = wxuserService.countUserFansFeeMonth(pd);
		if (UserFans != null) {
			SysUserFans = Long.parseLong(UserFans.get("UsersFans").toString()); // 渠道粉丝
		}
		if (UserFansFee != null) {
			SysUserFansFee = Long.parseLong(UserFansFee.get("UsersFansFee").toString()); // 渠道付费粉丝
		}
		if (UserFansFeeDay != null) {
			WXFansFeeDay = Long.parseLong(UserFansFeeDay.get("UserFansFeeDay").toString()); // 今天渠道付费粉丝
		}
		if (UserFansFeeMonth != null) {
			WXFansFeeMonth = Long.parseLong(UserFansFeeMonth.get("UserFansFeeMonth").toString()); // 本月渠道付费粉丝
		}
		pd.put("SysUserFans", SysUserFans);
		pd.put("SysUserFansFee", SysUserFansFee);
		pd.put("WXFansFeeDay", WXFansFeeDay);
		pd.put("WXFansFeeMonth", WXFansFeeMonth);
		pd.put("UserFans", UserFans);
		pd.put("UserFansFee", UserFansFee);
		pd.put("UserFansFeeDay", UserFansFeeDay);
		pd.put("UserFansFeeMonth", UserFansFeeMonth);
		return pd;
	}

	/**
	 * 签到统计
	 * 
	 * @return
	 * @throws Exception
	 */
	public PageData countSingLogUser() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pdSingLogUser = singlogService.countSingLogUser(pd);
		PageData pdSingLogUserCurrency = singlogService.countSingLogUserCurrency(pd);
		PageData pdSingLogUserCurrencDay = singlogService.countSingLogUserCurrencDay(pd);
		PageData pdSingLogUserCurrencyMonth = singlogService.countSingLogUserCurrencyMonth(pd);
		PageData pdSingLogUserMonth = singlogService.countSingLogUserMonth(pd);

		pd.put("SINGLOGUSERS", pdSingLogUser.get("SINGLOGUSERS"));// 累计签到人数
		pd.put("CURRENCY", pdSingLogUserCurrency.get("CURRENCY"));// 累计签到阅读币
		pd.put("CURRENCYDAY", pdSingLogUserCurrencDay.get("CURRENCYDAY"));// 累每天计签到阅读币和签到人数
		pd.put("SINGUSERSDAY", pdSingLogUserCurrencDay.get("SINGUSERSDAY"));// 每天签到人数
		pd.put("CURRENCYMONTH", pdSingLogUserCurrencyMonth.get("CURRENCYMONTH"));// 累计每月签到阅读币
		pd.put("SINGUSERSMONTH", pdSingLogUserMonth.get("SINGUSERSMONTH"));// 每月签到人数
		return pd;
	}

	/**
	 * 读者 充值/消费 数据统计
	 * 
	 * @return
	 * @throws Exception
	 */
	public PageData countRechargeLogDay(PageData pd) throws Exception {

		PageData pda = rechargelogService.countRechargeLogDay(pd);
		PageData pdb = consumelogService.countConsumelogDay(pd);
		if (pda != null) {
			pd.put("RECHARGENUMBERS", pda.getString("RECHARGENUMBERS"));// 读者总充值笔数
			pd.put("RECHARGEMONEY", pda.getString("RECHARGEMONEY"));// 读者总充值金额
		}
		if (pdb != null) {
			pd.put("CONSUMENUMBERS", pdb.getString("CONSUMENUMBERS"));// 读者总消费笔数
			pd.put("CONSUMEMONEY", pdb.getString("CONSUMEMONEY"));// 读者总消费金额
		}

		pd.put("TIME_RECHARGE", DateUtil.getDay());// 获取当天日期（天）
		pd.put("CREATE_TIME", DateUtil.getDay());
		PageData pd1 = rechargelogService.countRechargeLogDay(pd);
		PageData pd2 = consumelogService.countConsumelogDay(pd);
		if (pd1 != null) {
			pd.put("RECHARGENUMBERS_DAY", pd1.getString("RECHARGENUMBERS"));// 读者当天充值笔数
			pd.put("RECHARGEMONEY_DAY", pd1.getString("RECHARGEMONEY"));// 读者当天充值金额
		}
		if (pd2 != null) {
			pd.put("CONSUMENUMBERS_DAY", pd2.getString("CONSUMENUMBERS"));// 读者当天消费笔数
			pd.put("CONSUMEMONEY_DAY", pd2.getString("CONSUMEMONEY"));// 读者当天消费金额
		}

		pd.put("TIME_RECHARGE", DateUtil.getAfterDayDates("-1"));// 获取前一天日期（天）
		pd.put("CREATE_TIME", DateUtil.getAfterDayDates("-1"));
		PageData pd3 = rechargelogService.countRechargeLogDay(pd);
		PageData pd4 = consumelogService.countConsumelogDay(pd);
		if (pd3 != null) {
			pd.put("RECHARGENUMBERS_YESTERDAY",
					pd3.getString("RECHARGENUMBERS"));// 读者昨天充值笔数
			pd.put("RECHARGEMONEY_YESTERDAY", pd3.getString("RECHARGEMONEY"));// 读者昨天充值金额
		}
		if (pd4 != null) {
			pd.put("CONSUMENUMBERS_YESTERDAY", pd4.getString("CONSUMENUMBERS"));// 读者昨天消费笔数
			pd.put("CONSUMEMONEY_YESTERDAY", pd4.getString("CONSUMEMONEY"));// 读者昨天消费金额
		}

		pd.put("TIME_RECHARGE", DateUtil.getMM());// 获取本月日期（月）
		pd.put("CREATE_TIME", DateUtil.getMM());
		PageData pd5 = rechargelogService.countRechargeLogDay(pd);
		PageData pd6 = consumelogService.countConsumelogDay(pd);
		if (pd5 != null) {
			pd.put("RECHARGENUMBERS_MONTH", pd5.getString("RECHARGENUMBERS"));// 读者本月充值笔数
			pd.put("RECHARGEMONEY_MONTH", pd5.getString("RECHARGEMONEY"));// 读者本月充值金额
		}
		if (pd6 != null) {
			pd.put("CONSUMENUMBER_SMONTH", pd6.getString("CONSUMENUMBERS"));// 读者本月消费笔数
			pd.put("CONSUMEMONEY_MONTH", pd6.getString("CONSUMEMONEY"));// 读者本月消费金额
		}

		return pd;
	}

}
