package com.sinontech.service.read.countmonth;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.DateUtil;
import com.sinontech.util.PageData;


@Service("countmonthService")
public class CountMonthService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("CountMonthMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("CountMonthMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("CountMonthMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CountMonthMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CountMonthMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CountMonthMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CountMonthMapper.deleteAll", ArrayDATA_IDS);
	}
	/*
	 * 查询当月数据
	 */
	public PageData countMonth(PageData pd) throws Exception {
		pd.put("COUNT_MONTH",DateUtil.getMM());
		return (PageData)dao.findForObject("CountMonthMapper.countMonth", pd);
	}
	
	/*
	 *查询用户总数据
	 */
	public PageData countUsersAll(PageData pd) throws Exception {
		return (PageData)dao.findForObject("CountMonthMapper.countUsersAll", pd);
	}
	
	/*
	 * 充值图标数据查询
	 */
	public List<PageData> date(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("CountMonthMapper.date", pd);
	}
	/*
	 * 查询指定渠道总充值数
	 */
	public PageData countMonthqdAll(PageData pd) throws Exception {
		return (PageData)dao.findForObject("CountMonthMapper.countMonthqdAll", pd);
	}
	/*
	 * 统计渠道这个月的充值总额
	 */
	public PageData channelSumToMonth(PageData pd) throws Exception {
		return (PageData)dao.findForObject("CountMonthMapper.channelSumToMonth", pd);
	}
	/*
	 * 指定渠道月充值统计
	 */
	public List<PageData> channelcountMonthlist(Page page) throws Exception {
		return (List<PageData>)dao.findForList("CountMonthMapper.channelcountMonthlistPage", page);
	}
	/*
	 * 统计渠道到当前月份的充值总额
	 */
	public PageData channelMonthSum(PageData pd) throws Exception {
		return (PageData)dao.findForObject("CountMonthMapper.channelMonthSum", pd);
	}
	/*
	 * 统计渠道月充值统计导出到Excel
	 */
	public List<PageData> channelcountMonthlistExcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("CountMonthMapper.channelcountMonthlistExcel", pd);
	}
	/*
	 * 渠道充值图标数据查询
	 */
	public List<PageData> sysuserdate(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("CountMonthMapper.sysuserdate", pd);
	}
	
}
