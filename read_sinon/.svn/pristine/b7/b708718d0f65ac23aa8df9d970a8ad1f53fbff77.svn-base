package com.sinontech.controller.system.timer;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.sinontech.service.read.user.WXUserService;
import com.sinontech.util.Logger;
import com.sinontech.util.PageData;

/**
 * 用途说明：定时器每月1号0点将所有用户的签到次数置为0
 * @author LJJ
 *
 */
public class SignTimesJob extends QuartzJobBean{
	protected static final Logger logger = Logger.getLogger(SignTimesJob.class);
	private WXUserService wxuserService;

	public WXUserService getWxuserService() {
		return wxuserService;
	}

	
	public void setWxuserService(WXUserService wxuserService) {
		this.wxuserService = wxuserService;
	}
	/**
	 * 用途说明：定时器每月1号0点将所有用户的签到次数置为0
	 * @param  
	 * 2017-8-31上午09:36:56
	 * @auther LJJ
	 */
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		logger.info("开始更新签到次数为0");
		PageData pd = new PageData();
		try {
			//this.wxuserService.editSigntimes(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
