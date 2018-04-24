package com.sinontech.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.CountDayDaoImpl;
import com.sinontech.dao.impl.CountMonthDaoImpl;
import com.sinontech.dao.impl.CountWeekDaoImpl;
import com.sinontech.modle.CountDay;
import com.sinontech.modle.CountMonth;
import com.sinontech.modle.CountWeek;
import com.sinontech.pub.utils.DateUtil;
import com.sinontech.service.CountService;
/**
 * 
 * @author ljj
 *
 */
@Service("countservice")
public class CountServiceImpl implements CountService{

	@Autowired
	CountMonthDaoImpl countMonthDaoImpl;
	@Autowired
	CountWeekDaoImpl countWeekDaoImpl;
	@Autowired
	CountDayDaoImpl countDayDaoImpl;
	
	public CountDay getCountDayByCountDate(long webchatid, String countDay) {
		return this.countDayDaoImpl.getCountDayByCountDate(webchatid, countDay);
	}
	public CountMonth getCountMonthByCountDate(long webchatid, String countMonth) {
		return this.countMonthDaoImpl.getCountMonthByCountDate(webchatid, countMonth);
	}
	public CountWeek getCountWeekByCountDate(long webchatid, String stratDate,
			String endDate) {
		return this.countWeekDaoImpl.getCountWeekByCountDate(webchatid, stratDate, endDate);
	}
	public void saveCountDay(CountDay countDay) {
		this.countDayDaoImpl.saveCountDay(countDay);
	}
	public void saveCountMonth(CountMonth countMonth) {
		this.countMonthDaoImpl.saveCountMonth(countMonth);
	}
	public void saveCountWeek(CountWeek countWeek) {
		this.countWeekDaoImpl.saveCountWeek(countWeek);
	}
	public void updateCountDay(CountDay countDay) {
		this.countDayDaoImpl.updateCountDay(countDay);
	}
	public void updateCountMonth(CountMonth countMonth) {
		this.countMonthDaoImpl.updateCountMonth(countMonth);
	}
	public void updateCountWeek(CountWeek countWeek) {
		this.countWeekDaoImpl.updateCountWeek(countWeek);
	}
	/**
	 * channelId 渠道ID
	 * webchatid 微信ID
	 * bookCurrency 充值的阅读币
	 * bookCurrencyGift 赠送的阅读币
	 * recharges  充值的金额
	 * users 会员总数
	 * rechargeTimes 充值的笔数
	 * consumes 消费的阅读币数
	 * money  消费的人民币
	 * consumenumber 消费的笔数
	 */
	public void addBookCurrency(long channelId,long webchatid, long bookCurrency,long bookCurrencyGift,
			long recharges,long users,long rechargeTimes,long consumes,long money,long consumenumber) {
		String time = DateUtil.getStringNow();
		String month = DateUtil.getStringMonth();
		String day = DateUtil.getStringShortNow(); 
		String sunday = DateUtil.getSundayOfThisWeek();
		String monday = DateUtil.getMondayOfThisWeek();
		CountDay countday = this.countDayDaoImpl.getCountDayByCountDate(webchatid, day);
		if(null!=countday){
			countday.setBookCurrency(bookCurrency+countday.getBookCurrency());
			countday.setBookCurrencyGift(bookCurrencyGift+countday.getBookCurrencyGift());
			countday.setUsers(countday.getUsers()+users);
			countday.setRecharges(recharges+countday.getRecharges());
			countday.setRechargeTimes(countday.getRechargeTimes()+rechargeTimes);
			countday.setConsumes(countday.getConsumes()+consumes);
			countday.setMoney(countday.getMoney()+money);
			countday.setConsumenumber(countday.getConsumenumber()+consumenumber);
			countday.setUpdateTime(time);
			this.countDayDaoImpl.updateCountDay(countday);
		}else{
			countday = new CountDay();
			countday.setChannelId(channelId);
			countday.setBookCurrency(bookCurrency );
			countday.setBookCurrencyGift(bookCurrencyGift );
			countday.setUsers( users);
			countday.setRecharges(recharges );
			countday.setUpdateTime(time);
			countday.setWebchatId(webchatid);
			countday.setRechargeTimes(rechargeTimes);
			countday.setConsumes(consumes);
			countday.setMoney(money);
			countday.setConsumenumber(consumenumber);
			countday.setCreateTime(time);
			countday.setCountDay(day);
			this.countDayDaoImpl.saveCountDay(countday);
		}
		CountWeek week = this.countWeekDaoImpl.getCountWeekByCountDate(webchatid, monday, sunday);
		if(null!=week){
			week.setBookCurrency(bookCurrency+week.getBookCurrency());
			week.setBookCurrencyGift(bookCurrencyGift+week.getBookCurrencyGift());
			week.setUsers(week.getUsers()+users);
			week.setRecharges(recharges+week.getRecharges());
			week.setRechargeTimes(week.getRechargeTimes()+rechargeTimes);
			week.setConsumes(week.getConsumes()+consumes);
			week.setMoney(week.getMoney()+money);
			week.setConsumenumber(week.getConsumenumber()+consumenumber);
			week.setRechargeTimes(week.getRechargeTimes()+rechargeTimes);
			week.setConsumes(week.getConsumes()+consumes);
			week.setMoney(week.getMoney()+money);
			week.setConsumenumber(week.getConsumenumber()+consumenumber);
			week.setUpdateTime(time);
			this.countWeekDaoImpl.updateCountWeek(week);
		}else{
			week = new CountWeek();
			week.setChannelId(channelId);
			week.setBookCurrency(bookCurrency );
			week.setBookCurrencyGift(bookCurrencyGift );
			week.setUsers( users);
			week.setRecharges(recharges );
			week.setUpdateTime(time);
			week.setWebchatId(webchatid);
			week.setRechargeTimes(rechargeTimes);
			week.setConsumes(consumes);
			week.setMoney(money);
			week.setConsumenumber(consumenumber);
			week.setCreateTime(time);
			week.setStratDate(monday);
			week.setEndDate(sunday);
			this.countWeekDaoImpl.saveCountWeek(week);
		}
		CountMonth cmonth  = this.countMonthDaoImpl.getCountMonthByCountDate(webchatid, month);
		if(null!=cmonth){
			cmonth.setBookCurrency(bookCurrency+cmonth.getBookCurrency());
			cmonth.setBookCurrencyGift(bookCurrencyGift+cmonth.getBookCurrencyGift());
			cmonth.setUsers(cmonth.getUsers()+users);
			cmonth.setRecharges(recharges+cmonth.getRecharges());
			cmonth.setRechargeTimes(cmonth.getRechargeTimes()+rechargeTimes);
			cmonth.setConsumes(cmonth.getConsumes()+consumes);
			cmonth.setMoney(cmonth.getMoney()+money);
			cmonth.setConsumenumber(cmonth.getConsumenumber()+consumenumber);
			cmonth.setUpdateTime(time);
			this.countMonthDaoImpl.updateCountMonth(cmonth);
		}else{
			cmonth =new CountMonth();
			cmonth.setChannelId(channelId);
			cmonth.setBookCurrency(bookCurrency );
			cmonth.setBookCurrencyGift(bookCurrencyGift );
			cmonth.setUsers( users);
			cmonth.setRecharges(recharges );
			cmonth.setUpdateTime(time);
			cmonth.setWebchatId(webchatid);
			cmonth.setRechargeTimes(rechargeTimes);
			cmonth.setConsumes(consumes);
			cmonth.setMoney(money);
			cmonth.setConsumenumber(consumenumber);
			cmonth.setCreateTime(time);
			cmonth.setCountMonth(month);
			this.countMonthDaoImpl.saveCountMonth(cmonth);
			
		}
		
	}
	 

	 
	  
	
	
}
