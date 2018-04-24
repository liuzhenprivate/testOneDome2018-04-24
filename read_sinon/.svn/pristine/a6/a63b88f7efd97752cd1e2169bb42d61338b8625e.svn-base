package com.sinontech.controller.system.user;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sinontech.controller.base.BaseController;
import com.sinontech.entity.Page;
import com.sinontech.entity.system.Role;
import com.sinontech.service.read.account.AccountService;
import com.sinontech.service.read.consumelog.ConsumelogService;
import com.sinontech.service.read.rechargelog.RechargeLogService;
import com.sinontech.service.read.remittancelog.RemittanceLogService;
import com.sinontech.service.read.singlog.SingLogService;
import com.sinontech.service.read.user.WXUserService;
import com.sinontech.service.read.webchat.WebchatService;
import com.sinontech.service.system.menu.MenuService;
import com.sinontech.service.system.role.RoleService;
import com.sinontech.service.system.user.UserService;
import com.sinontech.util.AppUtil;
import com.sinontech.util.Const;
import com.sinontech.util.DateUtil;
import com.sinontech.util.FileDownload;
import com.sinontech.util.FileUpload;
import com.sinontech.util.GetPinyin;
import com.sinontech.util.Jurisdiction;
import com.sinontech.util.ObjectExcelRead;
import com.sinontech.util.PageData;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.PathUtil;
import com.sinontech.util.Tools;

/**
 * 类名称：UserController 创建人：FH 创建时间：2014年6月28日
 * @version
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	String menuUrl = "user/listUsers.do"; // 菜单地址(权限用)
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name="webchatService")
	private WebchatService webchatService;
	@Resource(name="remittancelogService")
	private RemittanceLogService remittancelogService;
	@Resource(name="singlogService")
	private SingLogService singlogService;
	@Resource(name="consumelogService")
	private ConsumelogService consumelogService;
	@Resource(name="rechargelogService")
	private RechargeLogService rechargelogService;
	@Resource(name="wxuserService")
	private WXUserService wxuserService;
	@Resource(name="accountService")
	private AccountService accountService;
	/**
	 * 保存用户
	 */
	@RequestMapping(value = "/saveU")
	public ModelAndView saveU(PrintWriter out) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		pd.put("USERID", this.get32UUID()); // ID
		pd.put("RIGHTS", ""); // 权限
		pd.put("LAST_LOGIN", ""); // 最后登录时间
		pd.put("IP", ""); // IP
		pd.put("STATUS", "0"); // 状态
		pd.put("SKIN", "default"); // 默认皮肤

		pd.put("PASSWORD", new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASSWORD")).toString());
		pd.put("COMPANY", "欣网卓信");
		pd.put("CREATE_TIME", DateUtil.getTime());
		if (null == userService.findByUId(pd)) {
			if (Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
				userService.saveU(pd);
			} // 判断新增权限
			mv.addObject("msg", "success");
		} else {
			mv.addObject("msg", "failed");
		}
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 判断用户名是否存在
	 */
	@RequestMapping(value = "/hasU")
	@ResponseBody
	public Object hasU() {
		Map<String, String> map = new HashMap<String, String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			if (userService.findByUId(pd) != null) {
				errInfo = "error";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo); // 返回结果
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 判断邮箱是否存在
	 */
	@RequestMapping(value = "/hasE")
	@ResponseBody
	public Object hasE() {
		Map<String, String> map = new HashMap<String, String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try {
			pd = this.getPageData();

			if (userService.findByUE(pd) != null) {
				errInfo = "error";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo); // 返回结果
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 判断编码是否存在
	 */
	@RequestMapping(value = "/hasN")
	@ResponseBody
	public Object hasN() {
		Map<String, String> map = new HashMap<String, String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			if (userService.findByUN(pd) != null) {
				errInfo = "error";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo); // 返回结果
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 修改用户
	 */
	@RequestMapping(value = "/editU")
	public ModelAndView editU() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if (pd.getString("PASSWORD") != null && !"".equals(pd.getString("PASSWORD"))) {
			pd.put("PASSWORD", new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASSWORD")).toString());
		}
		if (Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			userService.editU(pd);
		}
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 去修改用户页面
	 */
	@RequestMapping(value = "/goEditU")
	public ModelAndView goEditU() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		// 顶部修改个人资料
		String fx = pd.getString("fx");

		// System.out.println(fx);

		if ("head".equals(fx)) {
			mv.addObject("fx", "head");
		} else {
			mv.addObject("fx", "user");
		}

		List<Role> roleList = roleService.listAllERRoles(); // 列出所有二级角色
		pd = userService.findByUiId(pd); // 根据ID读取
		mv.setViewName("system/user/user_edit");
		mv.addObject("msg", "editU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);

		return mv;
	}

	/**
	 * 去新增用户页面
	 */
	@RequestMapping(value = "/goAddU")
	public ModelAndView goAddU() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<Role> roleList;

		roleList = roleService.listAllERRoles(); // 列出所有二级角色

		mv.setViewName("system/user/user_edit");
		mv.addObject("msg", "saveU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);

		return mv;
	}

	/**
	 * 显示用户列表(用户组)
	 */
	@RequestMapping(value = "/listUsers")
	public ModelAndView listUsers(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		String USERNAME = pd.getString("USERNAME");

		if (null != USERNAME && !"".equals(USERNAME)) {
			USERNAME = USERNAME.trim();
			pd.put("USERNAME", USERNAME);
		}

		String lastLoginStart = pd.getString("lastLoginStart");
		String lastLoginEnd = pd.getString("lastLoginEnd");

		if (lastLoginStart != null && !"".equals(lastLoginStart)) {
			lastLoginStart = lastLoginStart + " 00:w:00";
			pd.put("lastLoginStart", lastLoginStart);
		}
		if (lastLoginEnd != null && !"".equals(lastLoginEnd)) {
			lastLoginEnd = lastLoginEnd + " 00:00:00";
			pd.put("lastLoginEnd", lastLoginEnd);
		}

		page.setPd(pd);
		List<PageData> userList = userService.listPdPageUser(page); // 列出用户列表
		List<Role> roleList = roleService.listAllERRoles(); // 列出所有二级角色

		mv.setViewName("system/user/user_list");
		mv.addObject("userList", userList);
		mv.addObject("roleList", roleList);
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		return mv;
	}
	
	/**
	 * 显示用户列表(tab方式)
	 */
	@RequestMapping(value = "/listtabUsers")
	public ModelAndView listtabUsers(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData> userList = userService.listAllUser(pd); // 列出用户列表
		mv.setViewName("system/user/user_tb_list");
		mv.addObject("userList", userList);
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		return mv;
	}

	/**
	 * 删除用户
	 */
	@RequestMapping(value = "/deleteU")
	public void deleteU(PrintWriter out) {
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			if (Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
				userService.deleteU(pd);
			}
			out.write("success");
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

	}

	/**
	 * 批量删除
	 */
	@RequestMapping(value = "/deleteAllU")
	@ResponseBody
	public Object deleteAllU() {
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String USER_IDS = pd.getString("USER_IDS");

			if (null != USER_IDS && !"".equals(USER_IDS)) {
				String ArrayUSER_IDS[] = USER_IDS.split(",");
				if (Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
					userService.deleteAllU(ArrayUSER_IDS);
				}
				pd.put("msg", "ok");
			} else {
				pd.put("msg", "no");
			}

			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}

	// ===================================================================================================

	/*
	 * 导出用户信息到EXCEL
	 * @return
	 */
	@RequestMapping(value = "/excel")
	public ModelAndView exportExcel() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			if (Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
				// 检索条件===
				String USERNAME = pd.getString("USERNAME");
				if (null != USERNAME && !"".equals(USERNAME)) {
					USERNAME = USERNAME.trim();
					pd.put("USERNAME", USERNAME);
				}
				String lastLoginStart = pd.getString("lastLoginStart");
				String lastLoginEnd = pd.getString("lastLoginEnd");
				if (lastLoginStart != null && !"".equals(lastLoginStart)) {
					lastLoginStart = lastLoginStart + " 00:00:00";
					pd.put("lastLoginStart", lastLoginStart);
				}
				if (lastLoginEnd != null && !"".equals(lastLoginEnd)) {
					lastLoginEnd = lastLoginEnd + " 00:00:00";
					pd.put("lastLoginEnd", lastLoginEnd);
				}
				// 检索条件===

				Map<String, Object> dataMap = new HashMap<String, Object>();
				List<String> titles = new ArrayList<String>();

				titles.add("用户名"); // 1
				titles.add("编号"); // 2
				titles.add("姓名"); // 3
				titles.add("职位"); // 4
				titles.add("手机"); // 5
				titles.add("邮箱"); // 6
				titles.add("最近登录"); // 7
				titles.add("上次登录IP"); // 8

				dataMap.put("titles", titles);

				List<PageData> userList = userService.listAllUser(pd);
				List<PageData> varList = new ArrayList<PageData>();
				for (int i = 0; i < userList.size(); i++) {
					PageData vpd = new PageData();
					vpd.put("var1", userList.get(i).getString("USERNAME")); // 1
					vpd.put("var2", userList.get(i).getString("NUMBER")); // 2
					vpd.put("var3", userList.get(i).getString("NAME")); // 3
					vpd.put("var4", userList.get(i).getString("ROLE_NAME")); // 4
					vpd.put("var5", userList.get(i).getString("PHONE")); // 5
					vpd.put("var6", userList.get(i).getString("EMAIL")); // 6
					vpd.put("var7", userList.get(i).getString("LAST_LOGIN")); // 7
					vpd.put("var8", userList.get(i).getString("IP")); // 8
					varList.add(vpd);
				}
				dataMap.put("varList", varList);
				ObjectExcelView erv = new ObjectExcelView(); // 执行excel操作
				mv = new ModelAndView(erv, dataMap);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 打开上传EXCEL页面
	 */
	@RequestMapping(value = "/goUploadExcel")
	public ModelAndView goUploadExcel() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/user/uploadexcel");
		return mv;
	}

	/**
	 * 下载模版
	 */
	@RequestMapping(value = "/downExcel")
	public void downExcel(HttpServletResponse response) throws Exception {

		FileDownload.fileDownload(response, PathUtil.getClasspath() + Const.FILEPATHFILE + "Users.xls", "Users.xls");

	}

	/**
	 * 从EXCEL导入到数据库
	 */
	@RequestMapping(value = "/readExcel")
	public ModelAndView readExcel(@RequestParam(value = "excel", required = false) MultipartFile file) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return null;
		}
		if (null != file && !file.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE; // 文件上传路径
			String fileName = FileUpload.fileUp(file, filePath, "userexcel"); // 执行上传

			List<PageData> listPd = (List) ObjectExcelRead.readExcel(filePath, fileName, 2, 0, 0); // 执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet

			/* 存入数据库操作====================================== */
			pd.put("RIGHTS", ""); // 权限
			pd.put("LAST_LOGIN", ""); // 最后登录时间
			pd.put("IP", ""); // IP
			pd.put("STATUS", "0"); // 状态
			pd.put("SKIN", "default"); // 默认皮肤

			List<Role> roleList = roleService.listAllERRoles(); // 列出所有二级角色

			pd.put("ROLE_ID", roleList.get(0).getROLE_ID()); // 设置角色ID为随便第一个
			/**
			 * var0 :编号 var1 :姓名 var2 :手机 var3 :邮箱 var4 :备注
			 */
			for (int i = 0; i < listPd.size(); i++) {
				pd.put("USER_ID", this.get32UUID()); // ID
				pd.put("NAME", listPd.get(i).getString("var1")); // 姓名

				String USERNAME = GetPinyin.getPingYin(listPd.get(i).getString("var1")); // 根据姓名汉字生成全拼
				pd.put("USERNAME", USERNAME);
				if (userService.findByUId(pd) != null) { // 判断用户名是否重复
					USERNAME = GetPinyin.getPingYin(listPd.get(i).getString("var1")) + Tools.getRandomNum();
					pd.put("USERNAME", USERNAME);
				}
				pd.put("BZ", listPd.get(i).getString("var4")); // 备注
				if (Tools.checkEmail(listPd.get(i).getString("var3"))) { // 邮箱格式不对就跳过
					pd.put("EMAIL", listPd.get(i).getString("var3"));
					if (userService.findByUE(pd) != null) { // 邮箱已存在就跳过
						continue;
					}
				} else {
					continue;
				}

				pd.put("NUMBER", listPd.get(i).getString("var0")); // 编号已存在就跳过
				pd.put("PHONE", listPd.get(i).getString("var2")); // 手机号

				pd.put("PASSWORD", new SimpleHash("SHA-1", USERNAME, "123").toString()); // 默认密码123
				if (userService.findByUN(pd) != null) {
					continue;
				}
				userService.saveU(pd);
			}
			/* 存入数据库操作====================================== */

			mv.addObject("msg", "success");
		}

		mv.setViewName("save_result");
		return mv;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}

	
	/**
	 * 
	 * 用途：渠道列表
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-11 下午2:22:54
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(pd.getString("data")!=null){
			String data = pd.getString("data");
			pd.put("data",data);
		}
		String sendtime = String.valueOf(pd.get("sendtime"));
		if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
			String[] times = sendtime.split(" - ");
			String timebegin = times[0];
			String timeend = times[1];
			pd.put("sendTimeBegin", timebegin);
			pd.put("sendTimeEnd", timeend);
		}
		//System.out.println(pd.toString());
		page.setPd(pd);
		List<PageData> varList = userService.list(page); // 列出用户列表

		mv.setViewName("reader/sysuser/user_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		return mv;
	}
	/**
	 * 
	 * 用途：修改渠道状态
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-11 下午3:59:19
	 */
	@RequestMapping(value="/editstate")
	public ModelAndView editstate() throws Exception{
		logBefore(logger, "修改Consumelog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		userService.editstate(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	/**
	 * 
	 * 用途：去渠道详情页面
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-11 下午4:12:24
	 */
	@RequestMapping(value = "/goSysUserDetail")
	public ModelAndView goSysUserDetail() throws Exception {
		logBefore(logger, "去渠道详情页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("reader/sysuser/sysuser_detail");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 
	 * 用途：去渠道信息总览
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-11 下午4:16:38
	 */
	@RequestMapping(value = "/sysuserNewsDetails")
	public ModelAndView sysuserNewsDetails() throws Exception {
		logBefore(logger, "去渠道信息总览");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData upd = userService.findByUiId(pd);				//渠道详细信息
		PageData wpd = webchatService.findWebchatUserId(pd);	//渠道公众微信号信息
		mv.setViewName("reader/sysuser/sysuserNewsDetails");
		mv.addObject("pd", pd);
		mv.addObject("upd", upd);
		mv.addObject("wpd", wpd);
		return mv;
	}
	
	
	/**
	 * 
	 * 用途：去渠道账户信息
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-11 下午5:12:55
	 */
	@RequestMapping(value = "/sysuserAccountDetails")
	public ModelAndView sysuserAccountDetails(Page page) throws Exception {
		logBefore(logger, "去渠道账户信息");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if("-1".equals(pd.getString("STATE"))){
			pd.put("STATE", null);
		}
		String sendtime = String.valueOf(pd.get("sendtime"));
		if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
			String[] times = sendtime.split(" - ");
			String timebegin = times[0];
			String timeend = times[1];
			pd.put("sendTimeBegin", timebegin);
			pd.put("sendTimeEnd", timeend);
		}
		page.setPd(pd);
		PageData upd = accountService.findByUserId(pd);					//渠道账户详细信息
		List<PageData> varList = remittancelogService.sysUserList(page);//获取渠道打款信息
		mv.setViewName("reader/sysuser/sysuserAccountDetails");
		mv.addObject("pd", pd);
		mv.addObject("upd", upd);
		mv.addObject("varList", varList);
		mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		return mv;
	}
	
	/**
	 * 
	 * 用途：导出渠道账户信息
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-12 下午3:45:10
	 */
	@RequestMapping(value="/exportExcelsysuserAccount")
	public ModelAndView exportExcelsysuserAccount(){
		logBefore(logger, "导出sysuserAccount到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
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
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("序号");	//1
			titles.add("结算单号");	//2
			titles.add("申请时间");	//3
			titles.add("账户余额");	//4
			titles.add("提现金额");	//5
			titles.add("累计提现");	//6
			titles.add("付款时间");	//7
			titles.add("打款状态");	//8
			dataMap.put("titles", titles);
			List<PageData>	varOList = remittancelogService.listsysuserAccountexcel(pd);	//列出渠道账户信息
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1",(i+1)+"");	//1
				vpd.put("var2", varOList.get(i).get("REMITTANCE_LOG_ID").toString());	//2
				vpd.put("var3", varOList.get(i).getString("CREATE_TIME"));	//3
				vpd.put("var4", varOList.get(i).get("SURPLUS").toString());	//4
				vpd.put("var5", varOList.get(i).get("MONEY").toString());	//5
				vpd.put("var6",varOList.get(i).get("SUMMONEY").toString());	//6
				vpd.put("var7", varOList.get(i).getString("CHECK_TIME"));	//7
				vpd.put("var8", varOList.get(i).get("STATE").toString());	//8
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	/**
	 * 
	 * 用途：去渠道签到页面
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-12 下午1:46:25
	 */
	@RequestMapping(value = "/sysuserSingDetails")
	public ModelAndView sysuserSingDetails(Page page) throws Exception {
		logBefore(logger, "去渠道签到页面");
		ModelAndView mv = this.getModelAndView();
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
		page.setPd(pd);
		List<PageData> varList = singlogService.listsysuserSing(page);
		PageData pdTotalP = singlogService.sysUserSingTotalP(pd);
		PageData pdTotalC = singlogService.sysUserSingTotalC(pd);
		pd.put("TIME", "");
		PageData pdCount = singlogService.sysUserSing(pd);
		pd.put("TIME", DateUtil.getMM());
		PageData pdMonth = singlogService.sysUserSing(pd);
		pd.put("TIME", DateUtil.getDay());
		PageData pdDay = singlogService.sysUserSing(pd);
		mv.setViewName("reader/sysuser/sysuserSingDetails");
		mv.addObject("pd", pd);
		mv.addObject("varList", varList);
		mv.addObject("pdCount", pdCount);
		mv.addObject("pdDay", pdDay);
		mv.addObject("pdMonth", pdMonth);
		mv.addObject("pdTotalC", pdTotalC);
		mv.addObject("pdTotalP", pdTotalP);
		mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		return mv;
	}
	/**
	 * 
	 * 用途：导出excel渠道签到
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-14 下午1:49:12
	 */
	@RequestMapping(value="/sysuserSingExportExcel")
	public ModelAndView sysuserSingExportExcel(){
		logBefore(logger, "导出excel渠道签到");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
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
		try{
			if(null!=pd.getString("names")){
				String names = new String(pd.getString("names").getBytes("iso-8859-1"),"UTF-8");
				pd.put("names",names);
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("昵称");	//1
			titles.add("渠道ID");	//2
			titles.add("签到日期");	//3
			titles.add("签到阅读币");	//4
			titles.add("签到累计阅读币");	//5
			dataMap.put("titles", titles);
			List<PageData>	varOList = singlogService.listsysuserSingexcel(pd);;	//列出渠道账户信息
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NICKNAME"));	//2
				vpd.put("var2", varOList.get(i).get("USER_ID").toString());	//3
				vpd.put("var3", varOList.get(i).getString("CREATE_TIME"));	//4
				vpd.put("var4", varOList.get(i).get("BOOK_CURRENCY").toString());	//5
				vpd.put("var5",varOList.get(i).get("SBC").toString());	//6
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	/**
	 * 
	 * 用途：去渠道消费页面
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-12 下午2:28:52
	 */
	@RequestMapping(value = "/sysuserConsumeDetails")
	public ModelAndView sysuserConsumeDetails(Page page) throws Exception {
		logBefore(logger, "去渠道消费页面");
		ModelAndView mv = this.getModelAndView();
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
		page.setPd(pd);
		List<PageData> varList = consumelogService.listsysuserConsume(page);
		pd.put("TIME", "");
		PageData pdTotal = consumelogService.sysUserConsume(pd);
		pd.put("TIME", DateUtil.getDay());
		PageData pdDay = consumelogService.sysUserConsume(pd);
		pd.put("TIME", DateUtil.getMM());
		PageData pdMonth = consumelogService.sysUserConsume(pd);
		pd.put("TIME", DateUtil.getAfterDayDates("-1"));
		PageData pdYester = consumelogService.sysUserConsume(pd);
		mv.setViewName("reader/sysuser/sysuserConsumDetails");
		mv.addObject("pd", pd);
		mv.addObject("pdTotal", pdTotal);
		mv.addObject("pdDay", pdDay);
		mv.addObject("pdMonth", pdMonth);
		mv.addObject("pdYester", pdYester);
		mv.addObject("varList", varList);
		mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		return mv;
	}
	/**
	 * 
	 * 用途：导出渠道消费信息excel
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-14 下午2:50:47
	 */
	@RequestMapping(value="/sysuserConsumeExcel")
	public ModelAndView sysuserConsumeExcel(){
		logBefore(logger, "导出渠道消费信息excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
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
		try{
			if(null!=pd.getString("names")){
				String names = new String(pd.getString("names").getBytes("iso-8859-1"),"UTF-8");
				pd.put("names",names);
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("昵称");	//1
			titles.add("渠道ID");	//2
			titles.add("消费日期");	//3
			titles.add("消费内容");	//4
			titles.add("使用阅读币");	//5
			dataMap.put("titles", titles);
			List<PageData>	varOList = consumelogService.listsysuserConsumeExce(pd);;	//列出渠道账户信息
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NICKNAME"));	//2
				vpd.put("var2", varOList.get(i).get("USER_ID").toString());	//3
				vpd.put("var3", varOList.get(i).getString("CREATE_TIME"));	//4
				vpd.put("var4", varOList.get(i).getString("ARTICLE_NAME")+"  "
						+varOList.get(i).getString("CHAPTER_NAME"));		//5
				vpd.put("var5",varOList.get(i).get("CONSUMES").toString());	//6
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：去渠道充值页面
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-12 下午2:29:50
	 */
	@RequestMapping(value = "/sysuserRechargeDetails")
	public ModelAndView sysuserRechargeDetails(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
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
		page.setPd(pd);
		List<PageData> varList = rechargelogService.listsysuserRecharge(page);
		pd.put("TIME", "");
		PageData pdTotal = rechargelogService.sysUserRecharge(pd);
		pd.put("TIME", DateUtil.getDay());
		PageData pdDay = rechargelogService.sysUserRecharge(pd);
		pd.put("TIME", DateUtil.getMM());
		PageData pdMonth = rechargelogService.sysUserRecharge(pd);
		pd.put("TIME", DateUtil.getAfterDayDates("-1"));
		PageData pdYester = rechargelogService.sysUserRecharge(pd);
		mv.setViewName("reader/sysuser/sysuserRechargeDetails");
		mv.addObject("pd", pd);
		mv.addObject("pdTotal", pdTotal);
		mv.addObject("pdDay", pdDay);
		mv.addObject("pdMonth", pdMonth);
		mv.addObject("pdYester", pdYester);
		mv.addObject("varList", varList);
		mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		return mv;
	}
	/**
	 * 
	 * 用途：导出渠道充值列表到excel
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-15 上午11:03:21
	 */
	@RequestMapping(value="/sysuserRechargeExcel")
	public ModelAndView sysuserRechargeExcel(){
		logBefore(logger, "导出渠道充值列表到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
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
		try{
			if(null!=pd.getString("names")){
				String names = new String(pd.getString("names").getBytes("iso-8859-1"),"UTF-8");
				pd.put("names",names);
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("管理渠道");	//1
			titles.add("渠道ID");	//2
			titles.add("充值日期");	//3
			titles.add("VIP状态");	//4
			titles.add("充值类型");	//5
			titles.add("充值额度");	//6
			titles.add("有效期");	//7
			titles.add("充值金额");	//8
			titles.add("获得阅读币");	//9
			titles.add("平台收益");	//10
			titles.add("渠道充值");	//11
			dataMap.put("titles", titles);
			List<PageData>	varOList = rechargelogService.listsysuserRechargeExce(pd);	//列出RechargeLog列表
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("COMPANY"));	//1
				vpd.put("var2", varOList.get(i).get("USER_ID").toString());	//2
				vpd.put("var3", varOList.get(i).getString("CREATE_TIME"));	//3
				vpd.put("var4", varOList.get(i).get("ISVIP").toString());	//4
				vpd.put("var5", varOList.get(i).get("RECHARGE_TYPE").toString());	//5
				vpd.put("var6","--");	//6
				vpd.put("var7", varOList.get(i).getString("EXP_DATE"));	//7
				vpd.put("var8", varOList.get(i).get("MONEY").toString());	//8
				vpd.put("var9", varOList.get(i).get("BOOK_CURRENCY_ALL").toString());	//9
				vpd.put("var10", varOList.get(i).get("INCOME").toString());	//10
				vpd.put("var11", varOList.get(i).get("QMONEY").toString());	//11
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：去渠道读者列表
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-12 下午2:43:13
	 */
	@RequestMapping(value = "/sysuserReadDetails")
	public ModelAndView sysuserReadDetails(Page page) throws Exception {
		logBefore(logger, "去渠道读者列表");
		ModelAndView mv = this.getModelAndView();
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
		page.setPd(pd);
		List<PageData>	varList = wxuserService.listsysuserRead(page);	//列出渠道WXUser列表
		
		pd.put("TIME", "");
		PageData pdTotalRechargeP = rechargelogService.sysUserRechargeP(pd);
		PageData pdTotalRead = wxuserService.sysUserRead(pd);
		PageData pdTotalFans = wxuserService.sysUserFans(pd);
		
		pd.put("TIME", DateUtil.getDay());
		PageData pdDayRechargeP = rechargelogService.sysUserRechargeP(pd);
		PageData pdDayRead = wxuserService.sysUserRead(pd);
		PageData pdDayFans = wxuserService.sysUserFans(pd);
		
		pd.put("TIME", DateUtil.getMM());
		PageData pdMonthRechargeP = rechargelogService.sysUserRechargeP(pd);
		PageData pdMonthRead = wxuserService.sysUserRead(pd);
		PageData pdMonthFans = wxuserService.sysUserFans(pd);
		mv.setViewName("reader/sysuser/sysuserReadDetails");
		mv.addObject("pd", pd);
		mv.addObject("pdTotalRechargeP", pdTotalRechargeP);
		mv.addObject("pdTotalRead", pdTotalRead);
		mv.addObject("pdTotalFans", pdTotalFans);
		mv.addObject("pdDayRechargeP", pdDayRechargeP);
		mv.addObject("pdDayRead", pdDayRead);
		mv.addObject("pdDayFans", pdDayFans);
		mv.addObject("pdMonthRechargeP", pdMonthRechargeP);
		mv.addObject("pdMonthRead", pdMonthRead);
		mv.addObject("pdMonthFans", pdMonthFans);
		mv.addObject("varList", varList);
		mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		return mv;
	}
	/**
	 * 
	 * 用途：导出渠道读者列表到excel
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-15 下午4:12:47
	 */
	@RequestMapping(value="/sysuserReadExcel")
	public ModelAndView sysuserReadExcel(){
		logBefore(logger, "导出渠道读者列表到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
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
		try{
			if(null!=pd.getString("names")){
				String names = new String(pd.getString("names").getBytes("iso-8859-1"),"UTF-8");
				pd.put("names",names);
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("昵称");	//1
			titles.add("平台ID");	//2
			titles.add("渠道ID");	//3
			titles.add("关注状态");	//4
			titles.add("注册日期");	//5
			titles.add("VIP状态");	//6
			titles.add("阅读币余额");	//7
			titles.add("累计充值");	//8
			titles.add("累计阅读币");	//9
			dataMap.put("titles", titles);
			List<PageData>	varOList = wxuserService.listsysuserReadExce(pd);	//列出RechargeLog列表
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NICKNAME"));	//1
				vpd.put("var2", varOList.get(i).get("USER_CODE").toString());	//2
				vpd.put("var3",varOList.get(i).get("USER_ID").toString());	//3
				vpd.put("var4", varOList.get(i).get("FOLLOWSTATE").toString());	//4
				vpd.put("var5", varOList.get(i).getString("CREATE_TIME"));	//5
				vpd.put("var6",varOList.get(i).get("ISVIP").toString());	//6
				vpd.put("var7", varOList.get(i).get("SURPLUS_CURRENCY").toString());	//7
				vpd.put("var8", varOList.get(i).get("RECHARGE_MONEY").toString());	//8
				vpd.put("var9", varOList.get(i).get("CUMULATIVE_CURRENCY").toString());	//9
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	
	
	
	
	/**
	 * 
	 * 用途：去推广统计页面
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-12 下午2:34:54
	 */
	@RequestMapping(value = "/sysuserExtensionDetails")
	public ModelAndView sysuserExtensionDetails(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		mv.setViewName("reader/sysuser/sysuserExtensionDetails");
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		return mv;
	}
	/**
	 * 
	 * 用途：去渠道新增页面
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-22 上午10:01:08
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() throws Exception {
		logBefore(logger, "去渠道新增页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("reader/sysuser/sysuser_add");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}
	/**
	 * 
	 * 用途：新增渠道
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-22 上午11:21:28
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save(PrintWriter out) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("PASSWORD", new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASSWORD")).toString());
		pd.put("CREATE_TIME", DateUtil.getTime());
		pd.put("ROLE_ID", "7dfd8d1f7b6245d283217b7e63eec9b2");
		pd.put("STATUS", "0");
		if (null == userService.findByUId(pd)) {
				userService.save(pd);
			mv.addObject("msg", "success");
		} else {
			mv.addObject("msg", "failed");
		}
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 
	 * @purpose：验证渠道账户是否已经存在
	 * @param pd
	 * @return
	 * @return String
	 * @author liuzhen
	 * @Time：2018-4-17 上午10:20:13
	 */
	@RequestMapping(value = "/checkName")
	@ResponseBody
	public String chackName(){
		PageData pd = new PageData();
		String chack = "false";
		pd = this.getPageData();
		PageData pd1 = null;
		try {
			pd1 = userService.getChackName(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		if(pd1!=null){
			chack = "true";
		}
		return chack;
	}
	/* ===============================权限================================== */
	public Map<String, String> getHC() {
		Subject currentUser = SecurityUtils.getSubject(); // shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>) session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
}
