package com.sinontech.service.read.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.DateUtil;
import com.sinontech.util.PageData;


@Service("wxuserService")
public class WXUserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("WXUserMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("WXUserMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("WXUserMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("WXUserMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("WXUserMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("WXUserMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("WXUserMapper.deleteAll", ArrayDATA_IDS);
	}

	/*
	 * 统计渠道粉丝
	 */
	public PageData countUserFans(PageData pd) throws Exception {
		return (PageData)dao.findForObject("WXUserMapper.countUserFans", pd);
	}
	/*
	 * 渠道付费粉丝
	 */
	public PageData countUserFansFee(PageData pd) throws Exception {
		return (PageData)dao.findForObject("WXUserMapper.countUserFansFee", pd);
	}
	/*
	 * 统计今天付费用户数
	 */
	public PageData countUserFansFeeDay(PageData pd) throws Exception {
		pd.put("CREATE_TIME",DateUtil.getDay());
		return (PageData)dao.findForObject("WXUserMapper.countUserFansFeeDay", pd);
	}
	
	/*
	 * 统计本月付费用户数
	 */
	public PageData countUserFansFeeMonth(PageData pd) throws Exception {
		pd.put("CREATE_TIME",DateUtil.getMM());
		return (PageData)dao.findForObject("WXUserMapper.countUserFansFeeMonth", pd);
	}
	/*
	 * 获取指定平台id的用户的渠道信息
	 */
	public List<PageData> listUser(Page page) throws Exception {
		return (List<PageData>)dao.findForList("WXUserMapper.UserlistPage", page);
	}
	/*
	 * 读者管理：获取指定读者的渠道
	 */
	public List<PageData> readLists(Page page) throws Exception {
		return (List<PageData>)dao.findForList("WXUserMapper.readlist", page);
	}
	
	/*
	 * 渠道读者列表
	 */
	public List<PageData> listsysuserRead(Page page) throws Exception {
		return (List<PageData>)dao.findForList("WXUserMapper.sysuserReadlistPage", page);
	}
	/*
	 * 导出渠道读者列表到excel
	 */
	public List<PageData> listsysuserReadExce(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("WXUserMapper.listsysuserReadExce", pd);
	}
	/*
	 * 渠道读者 累计/当月/当天 信息
	 */
	public PageData sysUserRead(PageData pd) throws Exception {
		return (PageData)dao.findForObject("WXUserMapper.sysUserRead", pd);
	}
	/*
	 * 渠道粉丝 累计/当月/当天 信息
	 */
	public PageData sysUserFans(PageData pd) throws Exception {
		return (PageData)dao.findForObject("WXUserMapper.sysUserFans", pd);
	}
	/*
	 * 累计/当月/当天 读者 数
	 */
	public PageData countUsers(PageData pd) throws Exception {
		return (PageData)dao.findForObject("WXUserMapper.countUsers", pd);
	}
	
	
	/**
	 * 
	 * 用途：渠道端
	 * @author 刘振
	 * List<PageData>
	 * 时间:2018-1-23 下午1:39:06
	 */
	
	/*
	 * 渠道端：渠道读者列表
	 */
	public List<PageData> listsysuserReadChannel(Page page) throws Exception {
		return (List<PageData>)dao.findForList("WXUserMapper.sysuserReadChannellistPage", page);
	}
	
	/*
	 * 渠道端：渠道读者 累计/当月/当天 信息
	 */
	public PageData sysUserReadChannel(PageData pd) throws Exception {
		return (PageData)dao.findForObject("WXUserMapper.sysUserReadChannel", pd);
	}
	/*
	 * 渠道端：渠道粉丝 累计/当月/当天 信息
	 */
	public PageData sysUserFansChannel(PageData pd) throws Exception {
		return (PageData)dao.findForObject("WXUserMapper.sysUserFansChannel", pd);
	}
	/*
	 * 渠道端：导出渠道读者列表到excel
	 */
	public List<PageData> ReadExceChannel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("WXUserMapper.ReadExceChannel", pd);
	}

	/*
	 * 渠道端：渠道读者基本信息
	 */
	public PageData findByIdChannel(PageData pd) throws Exception {
		return (PageData)dao.findForObject("WXUserMapper.findByIdChannel", pd);
	}
	/*
	 * 渠道端：统计渠道付费读者
	 */
	public PageData countUsersumfee(PageData pd) throws Exception {
		return (PageData)dao.findForObject("WXUserMapper.countUsersumfee", pd);
	}
	/*
	 * 统计 付费用户数
	 */
	public PageData countUserPay(PageData pd) throws Exception {
		return (PageData)dao.findForObject("WXUserMapper.countUserPay", pd);
	}
	/*
	 * 统计 累计/当月/当天 渠道粉丝
	 */
	public PageData countFeeUsers(PageData pd) throws Exception {
		return (PageData)dao.findForObject("WXUserMapper.countFeeUsers", pd);
	}

	public PageData countUsersChannel(PageData pd) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
