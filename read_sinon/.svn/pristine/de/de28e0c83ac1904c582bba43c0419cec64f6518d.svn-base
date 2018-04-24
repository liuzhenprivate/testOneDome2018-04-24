package com.sinontech.service.read.htmlmodledetail;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;


@Service("htmlmodledetailService")
public class HtmlmodleDetailService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("HtmlmodleDetailMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("HtmlmodleDetailMapper.delete", pd);
	}
	
	public void deleteByHTMLMODLE_ID(PageData pd)throws Exception{
		dao.delete("HtmlmodleDetailMapper.deleteByHTMLMODLE_ID", pd);
	}
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("HtmlmodleDetailMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("HtmlmodleDetailMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("HtmlmodleDetailMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("HtmlmodleDetailMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("HtmlmodleDetailMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

