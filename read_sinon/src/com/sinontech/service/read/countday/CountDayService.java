package com.sinontech.service.read.countday;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.DateUtil;
import com.sinontech.util.PageData;


@Service("countdayService")
public class CountDayService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("CountDayMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("CountDayMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("CountDayMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CountDayMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CountDayMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CountDayMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CountDayMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	 * 默认查询当天数据
	 */
	public PageData countDay(PageData pd) throws Exception {
		pd.put("COUNT_DAY",DateUtil.getDay());
		return (PageData)dao.findForObject("CountDayMapper.countDay", pd);
	}
	
	/*
	 * 查询昨天数据
	 */
	public PageData countUserdayData(PageData pd) throws Exception {
		pd.put("COUNT_DAY", DateUtil.getAfterDayDates("-1"));
		return (PageData)dao.findForObject("CountDayMapper.countUserdayData", pd);
	}
	
	/*
	 * 统计startTime到endTime时间之内的总充值数
	 */
	public PageData sumTimeAllData(Page page) throws Exception {
		return (PageData)dao.findForObject("CountDayMapper.sumTimeAllData", page);
	}
	
	
	/*
	 * 统计startTime到endTime时间之内每天的数据
	 */
	public List<PageData> countTimeData(Page page) throws Exception {
		return (List<PageData>)dao.findForList("CountDayMapper.countTimeDatalistPage", page);
	}
	
	/*
	 * 充值图标数据查询
	 */
	public List<PageData> date(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("CountDayMapper.date", pd);
	}
	/*
	 * 查询指定渠道当天数据
	 */
	public PageData countDayqdAll(PageData pd) throws Exception {
		return (PageData)dao.findForObject("CountDayMapper.countDayqdAll", pd);
	}
	/*
	 * 统计指定渠道充值日报
	 */
	public List<PageData> channelcountDaylist(Page page) throws Exception {
		return (List<PageData>)dao.findForList("CountDayMapper.channelcountDaylistPage", page);
	}
	/*
	 * 统计渠道到今天的充值总额
	 */
	public PageData channelSumToDay(PageData pd) throws Exception {
		return (PageData)dao.findForObject("CountDayMapper.channelSumToDay", pd);
	}
	/*
	 * 统计指定渠道充值日报导出到Excel
	 */
	public List<PageData> channelcountDaylistExcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("CountDayMapper.channelcountDaylistExcel", pd);
	}
	/*
	 * 渠道充值图标数据查询
	 */
	public List<PageData> sysuserdate(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("CountDayMapper.sysuserdate", pd);
	}
	/*
	 * 导出充值统计
	 */
	public List<PageData> countTimeDataoutExcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("CountDayMapper.countTimeDataoutExcel", pd);
	}
	
}
