package com.sinontech.service.read.singlog;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.DateUtil;
import com.sinontech.util.PageData;


@Service("singlogService")
public class SingLogService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("SingLogMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("SingLogMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("SingLogMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("SingLogMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("SingLogMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("SingLogMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("SingLogMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	 * 统计累计签到人数
	 */
	public PageData countSingLogUser(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SingLogMapper.countSingLogUser", pd);
	}
	
	/*
	 * 统计累计阅读币
	 */
	public PageData countSingLogUserCurrency(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SingLogMapper.countSingLogUserCurrency", pd);
	}
	
	/*
	 * 统计每天累计签到阅读币
	 */
	public PageData countSingLogUserCurrencDay(PageData pd) throws Exception {
		pd.put("CREATE_TIME", DateUtil.getDay());
		return (PageData)dao.findForObject("SingLogMapper.countSingLogUserCurrencDay", pd);
	}
	
	/*
	 * 统计每月累计签到阅读币
	 */
	public PageData countSingLogUserCurrencyMonth(PageData pd) throws Exception {
		pd.put("SIGN_MONTH", DateUtil.getMM());
		return (PageData)dao.findForObject("SingLogMapper.countSingLogUserCurrencyMonth", pd);
	}
	
	/*
	 * 统计每月累计签到人数
	 */
	public PageData countSingLogUserMonth(PageData pd) throws Exception {
		pd.put("SIGN_MONTH", DateUtil.getMM());
		return (PageData)dao.findForObject("SingLogMapper.countSingLogUserMonth", pd);
	}
	
	/*
	 * 通过uuid获取签到的数据
	 */
	public PageData readsSingsMonth(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SingLogMapper.readsSingsMonth", pd);
	}
	
	/*
	 * 总签到统计
	 */
	public PageData seachSing(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SingLogMapper.seachSing", pd);
	}
	
	/*
	 * 今天签到统计
	 */
	public PageData seachSingLogDay(PageData pd) throws Exception {
		pd.put("CREATE_TIME",DateUtil.getDay());
		return (PageData)dao.findForObject("SingLogMapper.seachSingLogDay", pd);
	}
	/*
	 * 本月签到统计
	 */
	public PageData seachSingLogMonth(PageData pd) throws Exception {
		pd.put("CREATE_TIME",DateUtil.getMM());
		return (PageData)dao.findForObject("SingLogMapper.seachSingLogMonth", pd);
	}
	/*
	 * 昨天签到统计
	 */
	public PageData seachSingLogYserterDay(PageData pd) throws Exception {
		pd.put("CREATE_TIME", DateUtil.getAfterDayDates("-1"));
		return (PageData)dao.findForObject("SingLogMapper.seachSingLogYserterDay", pd);
	}
	/*
	 * 读者管理：读者 累计/月/当天/前一天 签到记录
	 */
	public PageData readerSing(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SingLogMapper.readerSing", pd);
	}
	
	/*
	 * 读者管理：获取当天签到的渠道和是否签到
	 */
	public PageData readerSingDay(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SingLogMapper.readerSingDay", pd);
	}
	/*
	 * 读者管理：读者签到记录
	 */
	public List<PageData> listUser(Page page) throws Exception {
		return (List<PageData>)dao.findForList("SingLogMapper.listUserlistPage", page);
	}
	
	/*
	 * 读者管理：签到记录excel导出
	 */
	public List<PageData> listReadexcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("SingLogMapper.listReadexcel", pd);
	}
	
	/*
	 * 渠道签到列表
	 */
	public List<PageData> listsysuserSing(Page page) throws Exception {
		return (List<PageData>)dao.findForList("SingLogMapper.listsysuserSinglistPage", page);
	}
	/*
	 * 获取渠道 累计/月/当天/ 签到记录
	 */
	public PageData sysUserSing(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SingLogMapper.sysUserSing", pd);
	}
		
	/*
	 * 获取渠道累计签到人数
	 */
	public PageData sysUserSingTotalP(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SingLogMapper.sysUserSingTotalP", pd);
	}
	/*
	 * 获取渠道累计签到次数
	 */
	public PageData sysUserSingTotalC(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SingLogMapper.sysUserSingTotalC", pd);
	}
	/*
	 * 导出excel渠道签到
	 */
	public List<PageData> listsysuserSingexcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("SingLogMapper.listsysuserSingexcel", pd);
	}
	/*
	 * 渠道读者签到列表
	 */
	public List<PageData> sysUserReadSignlist(Page page) throws Exception {
		return (List<PageData>)dao.findForList("SingLogMapper.sysUserReadSignlistPage", page);
	}
	/*
	 * 渠道读者 累计/月/当天/前一天 签到记录
	 */
	public PageData sysUserReadSing(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SingLogMapper.sysUserReadSing", pd);
	}
	/*
	 * 渠道读者：获取当前渠道是否签到
	 */
	public PageData sysUserReadSingDay(PageData pd) throws Exception {
		pd.put("TIME", DateUtil.getDay());
		return (PageData)dao.findForObject("SingLogMapper.sysUserReadSingDay", pd);
	}
	/*
	 * 导出渠道读者签到记录到Excel
	 */
	public List<PageData> listsysUserReadSingexcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("SingLogMapper.listsysUserReadSingexcel", pd);
	}
	
	/******渠道端******/
	
	/*
	 * 渠道读者签到列表
	 */
	public List<PageData> channelReadSignlistlistPage(Page page) throws Exception {
		return (List<PageData>)dao.findForList("SingLogMapper.channelReadSignlistPage", page);
	}
	/*
	 * 渠道读者 累计/月/当天/前一天 签到记录
	 */
	public PageData channelReadSing(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SingLogMapper.channelReadSing", pd);
	}
	/*
	 * 渠道读者：获取当前渠道是否签到
	 */
	public PageData channelReadSingDay(PageData pd) throws Exception {
		pd.put("TIME", DateUtil.getDay());
		return (PageData)dao.findForObject("SingLogMapper.channelReadSingDay", pd);
	}
	/*
	 * 导出渠道读者签到记录到Excel
	 */
	public List<PageData> channelUserReadSingexcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("SingLogMapper.channelUserReadSingexcel", pd);
	}
	/*
	 * 当前日期签到获取的总阅读币
	 */
	public PageData countSum(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SingLogMapper.countSum", pd);
	}
	
}
