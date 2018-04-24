package com.sinontech.service.read.htmlmodle;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;


@Service("htmlmodleService")
public class HtmlmodleService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("HtmlmodleMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("HtmlmodleDetailMapper.deleteByHTMLMODLE_ID", pd);
		dao.delete("HtmlmodleMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("HtmlmodleMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("HtmlmodleMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("HtmlmodleMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("HtmlmodleMapper.findById", pd);
	}
	
	public PageData getMaxPd(PageData pd)throws Exception{
		return (PageData)dao.findForObject("HtmlmodleMapper.getMaxPd", pd);
	}
	public PageData findByHtmlIdName(PageData pd)throws Exception{
		return (PageData)dao.findForObject("HtmlmodleMapper.findByHtmlIdName", pd);
	}
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("HtmlmodleMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

