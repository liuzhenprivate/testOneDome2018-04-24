package com.sinontech.controller.read.channel.sysuserread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sinontech.service.read.rechargelog.RechargeLogService;
import com.sinontech.service.read.user.WXUserService;
import com.sinontech.util.Const;
import com.sinontech.util.DateUtil;
import com.sinontech.util.Jurisdiction;
import com.sinontech.util.PageData;
import com.sinontech.util.ObjectExcelView;

/**
 * 类名称：ChannelUserController 创建人：刘振创建时间：2018年01月23日
 * @version
 */
@Controller
@RequestMapping(value = "/channelsysuserread")
public class ChannelUserController extends BaseController {

	String menuUrl = "user/listUsers.do"; // 菜单地址(权限用)
	@Resource(name="rechargelogService")
	private RechargeLogService rechargelogService;
	@Resource(name="wxuserService")
	private WXUserService wxuserService;
	/**
	 * 渠道端
	 */
	
	/**
	 * 
	 * 用途：去渠道读者列表
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-23 上午10:10:19
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception {
		logBefore(logger, "渠道端：去渠道读者列表");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String sendtime = String.valueOf(pd.get("sendtime"));
		if (null != sendtime && !"".equals(sendtime)
				&& !"null".equals(sendtime)) {
			String[] times = sendtime.split(" - ");
			String timebegin = times[0];
			String timeend = times[1];
			pd.put("sendTimeBegin", timebegin);
			pd.put("sendTimeEnd", timeend);
		}
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		try {
			if (null != user) {
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				if("-1".equals(pd.getString("FOLLOWSTATE"))){
					pd.put("FOLLOWSTATE", "");
				}
				page.setPd(pd);
				List<PageData> varList = wxuserService.listsysuserReadChannel(page); // 列出渠道WXUser列表
				
				
				pd.put("TIME", "");
				PageData pdTotalRechargeP = wxuserService.countUserPay(pd);
				PageData pdTotalRead = wxuserService.sysUserReadChannel(pd);
				PageData pdTotalFans = wxuserService.sysUserFansChannel(pd);

				pd.put("TIME", DateUtil.getDay());
				PageData pdDayRechargeP = rechargelogService
						.sysUserRechargeChannel(pd);
				PageData pdDayRead = wxuserService.sysUserReadChannel(pd);
				PageData pdDayFans = wxuserService.sysUserFansChannel(pd);

				pd.put("TIME", DateUtil.getMM());
				PageData pdMonthRechargeP = rechargelogService
						.sysUserRechargeChannel(pd);
				PageData pdMonthRead = wxuserService.sysUserReadChannel(pd);
				PageData pdMonthFans = wxuserService.sysUserFansChannel(pd);

				mv.setViewName("readerchannel/sysuser/sysuserread_list");
				mv.addObject("pd", pd);
				mv.addObject("pdTotalRechargeP",pdTotalRechargeP);
				mv.addObject("pdTotalRead", pdTotalRead);
				mv.addObject("pdTotalFans", pdTotalFans);
				mv.addObject("pdDayRechargeP", pdDayRechargeP);
				mv.addObject("pdDayRead", pdDayRead);
				mv.addObject("pdDayFans", pdDayFans);
				mv.addObject("pdMonthRechargeP", pdMonthRechargeP);
				mv.addObject("pdMonthRead", pdMonthRead);
				mv.addObject("pdMonthFans", pdMonthFans);
				mv.addObject("varList", varList);
			}
			mv.addObject("pd", pd);
//			System.out.println(pd.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 
	 * 用途：渠道端：导出渠道读者列表到excel
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-23 下午1:36:30
	 */
	@RequestMapping(value="/ReadExcel")
	public ModelAndView ReadExcel(){
		logBefore(logger, "渠道端：导出渠道读者列表到excel");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String sendtime = String.valueOf(pd.get("sendtime"));
		if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
			String[] times = sendtime.split(" - ");
			String timebegin = times[0];
			String timeend = times[1];
			pd.put("sendTimeBegin", timebegin);
			pd.put("sendTimeEnd", timeend);
		}
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		try {
			if (null != user) {
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				if(null!=pd.getString("names")){
					String names = new String(pd.getString("names").getBytes("iso-8859-1"),"UTF-8");
					pd.put("names",names);
				}
				Map<String,Object> dataMap = new HashMap<String,Object>();
				List<String> titles = new ArrayList<String>();
				titles.add("昵称");	//1
				titles.add("读者ID");	//3
				titles.add("关注状态");	//4
				titles.add("注册日期");	//5
				titles.add("VIP状态");	//6
				titles.add("阅读币余额");	//7
				titles.add("累计充值");	//8
				titles.add("累计阅读币");	//9
				dataMap.put("titles", titles);
				List<PageData>	varOList = wxuserService.ReadExceChannel(pd);	//列出RechargeLog列表
				List<PageData> varList = new ArrayList<PageData>();
				for(int i=0;i<varOList.size();i++){
					PageData vpd = new PageData();
					vpd.put("var1", varOList.get(i).getString("NICKNAME"));	//1
					vpd.put("var2",varOList.get(i).get("USERID").toString());	//3
					vpd.put("var3", varOList.get(i).get("FOLLOWSTATE").toString());	//4
					vpd.put("var4", varOList.get(i).getString("CREATE_TIME"));	//5
					vpd.put("var5",varOList.get(i).get("ISVIP").toString());	//6
					vpd.put("var6", varOList.get(i).get("SURPLUS_CURRENCY").toString());	//7
					vpd.put("var7", varOList.get(i).get("RECHARGE_MONEY").toString());	//8
					vpd.put("var8", varOList.get(i).get("CUMULATIVE_CURRENCY").toString());	//9
					varList.add(vpd);
				}
				dataMap.put("varList", varList);
				ObjectExcelView erv = new ObjectExcelView();
				mv = new ModelAndView(erv,dataMap);
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：渠道端：去渠道读者详情页面
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-23 下午2:29:34
	 */
	@RequestMapping(value="/UserReadDetail")
	public ModelAndView UserReadDetail(){
		logBefore(logger, "去UserReadDetail页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("readerchannel/sysuser/UserReadDetail");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	
	
	/* ===============================权限================================== */
	public Map<String, String> getHC() {
		Subject currentUser = SecurityUtils.getSubject(); // shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>) session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
}
