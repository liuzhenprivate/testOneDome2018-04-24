package com.sinontech.service.read.consumelog;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.DateUtil;
import com.sinontech.util.PageData;


@Service("consumelogService")
public class ConsumelogService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ConsumelogMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ConsumelogMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ConsumelogMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ConsumelogMapper.datalistPage", page);
	}
	/*
	 * 消费管理列表
	 */
	public List<PageData> sumlist(Page page) throws Exception {
		return (List<PageData>)dao.findForList("ConsumelogMapper.sumlistPage", page);
	}
	
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ConsumelogMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ConsumelogMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ConsumelogMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	 * 通过userid获取当天数据
	 */
	public PageData countConsumelogDay(PageData pd) throws Exception {
		pd.put("CREATE_TIME",DateUtil.getDay());
		return (PageData)dao.findForObject("ConsumelogMapper.countConsumelogDay", pd);
	}
	
	/*
	 * 获取消费数据
	 */
	public PageData pdConsumelog(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ConsumelogMapper.pdConsumelog", pd);
	}
	
	/*
	 * 渠道消费列表
	 */
	public List<PageData> listsysuserConsume(Page page) throws Exception {
		return (List<PageData>)dao.findForList("ConsumelogMapper.sysuserConsumelistPage", page);
	}
	
	/*
	 * 导出渠道消费信息excel
	 */
	public List<PageData> listsysuserConsumeExce(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("ConsumelogMapper.listsysuserConsumeExce", pd);
	}
	/*
	 * 渠道消费信息  当月/当天/昨天
	 */
	public PageData sysUserConsume(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ConsumelogMapper.sysUserConsume", pd);
	}
	/*
	 * 渠道读者消费记录信息
	 */
	public List<PageData> sysUserReadConsumelist(Page page) throws Exception {
		return (List<PageData>)dao.findForList("ConsumelogMapper.sysUserReadConsumelistPage", page);
	}
	/*
	 * 渠道读者消费信息  当月/当天/昨天
	 */
	public PageData sysUserpdConsumelog(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ConsumelogMapper.sysUserpdConsumelog", pd);
	}
	/*
	 * 导出渠道读者消费记录信息到Excel
	 */
	public List<PageData> listsysUserReadConsumeExce(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("ConsumelogMapper.listsysUserReadConsumeExce", pd);
	}

	/*
	 * 读者管理：读者 累计/月/当天/前一天 消费记录
	 */
	public PageData readerChapter(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ConsumelogMapper.readerChapter", pd);
	}
	
	/*
	 * 读者管理：读者消费记录
	 */
	public List<PageData> listUser(Page page) throws Exception {
		return (List<PageData>)dao.findForList("ConsumelogMapper.listUser", page);
	}
	/*
	 * 读者消费excel导出
	 */
	public List<PageData> listUserexcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("ConsumelogMapper.listUserexcel", pd);
	}
	
	/*******渠道端********/
	
	/*
	 * 渠道读者消费记录信息
	 */
	public List<PageData> channelReadConsumelist(Page page) throws Exception {
		return (List<PageData>)dao.findForList("ConsumelogMapper.channelReadConsumelistPage", page);
	}
	/*
	 * 渠道读者消费信息  当月/当天/昨天
	 */
	public PageData channelpdConsumelog(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ConsumelogMapper.channelpdConsumelog", pd);
	}
	/*
	 * 导出渠道读者消费记录信息到Excel
	 */
	public List<PageData> channelReadConsumeExce(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("ConsumelogMapper.channelReadConsumeExce", pd);
	}
	
	

}
