package com.sinontech.service.read.html;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;


@Service("htmlService")
public class HtmlService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("HtmlMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("HtmlMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("HtmlMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("HtmlMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("HtmlMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("HtmlMapper.findById", pd);
	}
	
	public PageData findMaxPlace(PageData pd)throws Exception{
		return (PageData)dao.findForObject("HtmlMapper.findMaxPlace", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("HtmlMapper.deleteAll", ArrayDATA_IDS);
	}
	/*
	 * 修改页面状态
	 */
	public void editstate(PageData pd) throws Exception {
		dao.update("HtmlMapper.editstate", pd);
	}
	
}
