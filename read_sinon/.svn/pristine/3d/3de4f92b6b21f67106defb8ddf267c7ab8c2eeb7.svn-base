package com.sinontech.service.read.rechargelog;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.DateUtil;
import com.sinontech.util.PageData;


@Service("rechargelogService")
public class RechargeLogService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("RechargeLogMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("RechargeLogMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("RechargeLogMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("RechargeLogMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("RechargeLogMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("RechargeLogMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("RechargeLogMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	 * ListData列表
	 */
	public List<PageData> listData(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("RechargeLogMapper.datalistPd", pd);
	}
	
	public List<PageData> sumAll(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("RechargeLogMapper.sumAll", pd);
	}
	/*
	 * 通过userid获取当天数据
	 */
	public PageData countRechargeLogDay(PageData pd) throws Exception {
		return (PageData)dao.findForObject("RechargeLogMapper.countRechargeLogDay", pd);
	}

	public PageData seachYesterDayRechargeLog(PageData pd) throws Exception {
		pd.put("TIME_RECHARGE", DateUtil.getAfterDayDates("-1"));
		return (PageData)dao.findForObject("RechargeLogMapper.seachYesterDayRechargeLog", pd);
	}
	
	/*
	 * 昨日充值人数
	 */
	public PageData seachRechargeLog(PageData pd) throws Exception {
		pd.put("TIME_RECHARGE", DateUtil.getAfterDayDates("-1"));
		return (PageData)dao.findForObject("RechargeLogMapper.seachRechargeLog", pd);
	}
	/*
	 * 获取指定读者充值金额和充值笔数（指定时间）
	 */
	public PageData rechargelogData(PageData pd) throws Exception {
		return (PageData)dao.findForObject("RechargeLogMapper.rechargelogData", pd);
	}
	/*
	 * 指定id读者充值列表
	 */
	public List<PageData> listRead(Page page) throws Exception {
		return (List<PageData>)dao.findForList("RechargeLogMapper.ReadlistPage", page);
	}
	/*
	 * 导出读者消费excel
	 */
	public List<PageData> listReadexcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("RechargeLogMapper.listReadexcel", pd);
	}
	/*
	 * 充值管理导出excel
	 */
	public List<PageData> listexcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("RechargeLogMapper.listexcel", pd);
	}
	/*
	 * 渠道充值列表
	 */
	public List<PageData> listsysuserRecharge(Page page) throws Exception {
		return (List<PageData>)dao.findForList("RechargeLogMapper.sysuserRechargelistPage", page);
	}
	/*
	 * 渠道充值信息   累计/当月/当天/昨天
	 */
	public PageData sysUserRecharge(PageData pd) throws Exception {
		return (PageData)dao.findForObject("RechargeLogMapper.sysUserRecharge", pd);
	}
	/*
	 * 导出渠道充值列表到excel
	 */
	public List<PageData> listsysuserRechargeExce(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("RechargeLogMapper.listsysuserRechargeExce", pd);
	}
	
	
	/*
	 * 渠道付费人数  累计/当月/当天
	 */
	public PageData sysUserRechargeP(PageData pd) throws Exception {
		return (PageData)dao.findForObject("RechargeLogMapper.sysUserRechargeP", pd);
	}
	
	/*
	 * 渠道读者充值信息 累计/当月/当天/昨天
	 */
	public PageData sysUserrechargelogData(PageData pd) throws Exception {
		return (PageData)dao.findForObject("RechargeLogMapper.sysUserrechargelogData", pd);
	}
	/*
	 * 渠道读者充值列表
	 */
	public List<PageData> sysUserlistRechargelog(Page page) throws Exception {
		return (List<PageData>)dao.findForList("RechargeLogMapper.sysUserRechargeloglistPage", page);
	}
	/*
	 * 导出 渠道读者充值列表到excel
	 */
	public List<PageData> listsysUserReadexcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("RechargeLogMapper.listsysUserReadexcel", pd);
	}
	
	/**
	 * ========================》
	 * 渠道端
	 */
	
	/*
	 * 渠道端：渠道付费人数  累计/当月/当天
	 */
	public PageData sysUserRechargeChannel(PageData pd) throws Exception {
		return (PageData)dao.findForObject("RechargeLogMapper.sysUserRechargeChannel", pd);
	}
	
	/*
	 * 渠道读者充值列表
	 */
	public List<PageData> channelRechargelog(Page page) throws Exception {
		return (List<PageData>)dao.findForList("RechargeLogMapper.channelRechargeloglistPage", page);
	}
	
	/*
	 * 渠道读者充值信息 累计/当月/当天/昨天
	 */
	public PageData channelrechargelog(PageData pd) throws Exception {
		return (PageData)dao.findForObject("RechargeLogMapper.channelrechargelog", pd);
	}
	/*
	 * 导出 渠道读者充值列表到excel
	 */
	public List<PageData> channelrechargelogexcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("RechargeLogMapper.channelrechargelogexcel", pd);
	}
	/*
	 * 渠道充值信息 累计/当月/当天/昨天
	 */
	public PageData channelrechargelogsum(PageData pd) throws Exception {
		return (PageData)dao.findForObject("RechargeLogMapper.channelrechargelogsum", pd);
	}
	
	/*
	 * 充值信息 累计/当月/当天/昨天
	 */
	public PageData rechargeCount(PageData pd) throws Exception {
		return (PageData)dao.findForObject("RechargeLogMapper.rechargeCount", pd);
	}
	/*
	 * 导出充值记录到excel
	 */
	public List<PageData> listsysuserRechargeExcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("RechargeLogMapper.listsysuserRechargeExcel", pd);
	}
	
	
}

