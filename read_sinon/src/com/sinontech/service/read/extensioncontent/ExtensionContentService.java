package com.sinontech.service.read.extensioncontent;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;


@Service("extensioncontentService")
public class ExtensionContentService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ExtensionContentMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ExtensionContentMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ExtensionContentMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ExtensionContentMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ExtensionContentMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ExtensionContentMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ExtensionContentMapper.deleteAll", ArrayDATA_IDS);
	}
	/*
	 * 通过CREATE_TIME获取数据
	 */
	public PageData findByTime(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ExtensionContentMapper.findByTime", pd);
	}
	/*
	 *  修改URL
	 */
	public void editURL(PageData pd) throws Exception {
		dao.update("ExtensionContentMapper.editURL", pd);
	}
	/*
	 * 删除推广
	 */
	public void del(PageData pd) throws Exception {
		dao.update("ExtensionContentMapper.del", pd);
	}
	/*
	 * 修改推广
	 */
	public void extensionedit(PageData pd) throws Exception {
		dao.update("ExtensionContentMapper.extensionedit", pd);
	}
	/*
	 * 渠道详情页面查询推广内容
	 */
	public List<PageData> ExtensionDetailslist(Page page) throws Exception {
		return (List<PageData>)dao.findForList("ExtensionContentMapper.ExtensionDetailslistPage", page);
	}
	
}

