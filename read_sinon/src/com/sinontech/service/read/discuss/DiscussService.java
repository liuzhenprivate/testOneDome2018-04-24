package com.sinontech.service.read.discuss;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;


@Service("discussService")
public class DiscussService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("DiscussMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("DiscussMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("DiscussMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("DiscussMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("DiscussMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("DiscussMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("DiscussMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	 * 匹配书籍总评论数
	 */
	public PageData articleCountdiscuss(PageData pd) throws Exception {
		return (PageData)dao.findForObject("DiscussMapper.articleCountdiscuss", pd);
	}
	
	/*
	 * 修改书籍显示人数
	 */
	public void editDisplayReads(PageData pd) throws Exception {
		dao.update("DiscussMapper.editDisplayReads", pd);
	}
	
}

