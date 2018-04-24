package com.sinontech.controller.read.channel;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinontech.controller.base.BaseController;
import com.sinontech.entity.Page;
import com.sinontech.entity.system.User;
import com.sinontech.service.read.account.AccountService;
import com.sinontech.service.read.rechargelog.RechargeLogService;
import com.sinontech.service.read.remittancelog.RemittanceLogService;
import com.sinontech.service.system.user.UserService;
import com.sinontech.util.Const;
import com.sinontech.util.DateUtil;
import com.sinontech.util.Jurisdiction;
import com.sinontech.util.PageData;

@Controller
@RequestMapping(value="/channelaccount")
public class ChannelAccountController extends BaseController{

	@Resource(name="accountService")
	private AccountService accountService;
	@Resource(name="remittancelogService")
	private RemittanceLogService remittancelogService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name="rechargelogService")
	private RechargeLogService rechargelogService;
	
	@RequestMapping(value="/presentRecordtoadd")
	public ModelAndView presentRecordtoadd(){
		logBefore(logger, "去申请提现页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Const.SESSION_USER);
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				PageData userpd = userService.findByUiId(pd);
				mv.addObject("user", userpd);
				PageData account = accountService.findByUserId(pd);	//根据ID读取
				mv.setViewName("readerchannel/account/presentRecordadd");
				mv.addObject("msg", "edit");
				mv.addObject("account", account);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	@RequestMapping(value="/savepresentRecord")
	public ModelAndView savepresentRecord(){
		logBefore(logger, "申请提现");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Const.SESSION_USER);
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				PageData userpd = userService.findByUiId(pd);
				long SURPLUS = Long.parseLong(String.valueOf(userpd.get("SURPLUS")));
				double money = Double.parseDouble(String.valueOf(pd.get("TXMONEY")));
				long moneyl = (long) (money*100);
				//提现大于当前可体现额度并且大于10元
				if(moneyl<=SURPLUS && moneyl>=1000){
					PageData account = accountService.findByUserId(pd);	//根据ID读取
					pd.put("ACCOUNT_NAME", account.get("ACCOUNT_NAME"));
					pd.put("CARD_NUM", account.get("CARD_NUM"));
					pd.put("OPEN_CARD", account.get("OPEN_CARD"));
					pd.put("MONEY", moneyl);
					pd.put("CREATE_TIME", DateUtil.getTime());
					pd.put("STATE", 0);
					this.remittancelogService.save(pd);
					
					userpd.put("SURPLUS", SURPLUS-moneyl);
					this.userService.editMoney(userpd);
				}
				
				mv.addObject("msg","success");
				mv.setViewName("save_result");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	@RequestMapping(value="/presentRecord")
	public ModelAndView presentRecord(Page page){
		logBefore(logger, "去提现详情页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Const.SESSION_USER);
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				List<PageData> allrecharge = this.rechargelogService.sumAll(pd);
				page.setPd(pd);
				List<PageData> varlist = this.remittancelogService.list(page);
				mv.setViewName("readerchannel/account/presentRecord");
				mv.addObject("varList", varlist);
				//昨天的日期
				String yesday = DateUtil.getAfterDayDates("-1");
				//当月月份YYYY-MM
				String month = DateUtil.getMM();
				PageData monthpd = new PageData();
				monthpd.put("USER_ID", uid);
				monthpd.put("MONTH", month);
				List<PageData> monthrs = rechargelogService.sumAll(monthpd);
				PageData daypd = new PageData();
				daypd.put("USER_ID", uid);
				daypd.put("sendTimeBegin", yesday);
				daypd.put("sendTimeEnd", DateUtil.getDay());
				List<PageData> dayrs = rechargelogService.sumAll(daypd);
				if(null!=monthrs && monthrs.size()>0){
					mv.addObject("monthrs", monthrs.get(0));
				}
				if(null!=dayrs && dayrs.size()>0){
					mv.addObject("dayrs", dayrs.get(0));
				}
				if(null!=allrecharge && allrecharge.size()>0){
					mv.addObject("recharge", allrecharge.get(0));
				}
				PageData todaypd = new PageData();
				todaypd.put("USER_ID", uid);
				todaypd.put("sendTimeBegin", DateUtil.getDay());
				List<PageData> todayrs = rechargelogService.sumAll(todaypd);
				if(null!=todayrs && todayrs.size()>0){
					mv.addObject("today", todayrs.get(0));
				}
			}
			
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	@RequestMapping(value="/index")
	public ModelAndView index(){
		logBefore(logger, "去收款信息页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Const.SESSION_USER);
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				PageData account = this.accountService.findByUserId(pd);
				if(null!=account){
					mv.setViewName("readerchannel/account/account");
					mv.addObject("account", account);
				}else{
					mv.setViewName("readerchannel/account/account_add");
				}
			}
			
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/saveaccount")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Account");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		if(null!=user){
			Long uid = user.getUSER_ID();
			pd.put("USER_ID", uid);
			pd.put("CREATE_TIME", DateUtil.getTime());	 
			accountService.save(pd);
		}
		return new ModelAndView("redirect:index");
	}
	
	@RequestMapping(value="/goeditaccount")
	public ModelAndView goeditaccount(){
		logBefore(logger, "去修改Account页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Const.SESSION_USER);
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				PageData account = accountService.findByUserId(pd);	//根据ID读取
				mv.setViewName("readerchannel/account/account_edit");
				mv.addObject("msg", "edit");
				mv.addObject("account", account);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	@RequestMapping(value="/editaccount")
	public ModelAndView editaccount(){
		logBefore(logger, "去修改Account页面");
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			accountService.edit(pd);	//根据ID读取
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return new ModelAndView("redirect:index");
	}	
}
