package com.sinontech.service;

import com.sinontech.modle.CountDay;
import com.sinontech.modle.CountMonth;
import com.sinontech.modle.CountWeek;


public interface CountService {

	public void saveCountDay(CountDay countDay);
	public void updateCountDay(CountDay countDay);
	public CountDay getCountDayByCountDate(long webchatid,String countDay);
	
	public void saveCountMonth(CountMonth countMonth);
	public void updateCountMonth(CountMonth countMonth);
	public CountMonth getCountMonthByCountDate(long webchatid,String countMonth);
	
	
	public void saveCountWeek(CountWeek countWeek);
	public void updateCountWeek(CountWeek countWeek);
	public CountWeek getCountWeekByCountDate(long webchatid,String stratDate,String endDate);
	
	public void addBookCurrency(long channelId,long webchatid, long bookCurrency,long bookCurrencyGift,
			long recharges,long users,long rechargeTimes,long consumes,long money,long consumenumber);
	
}