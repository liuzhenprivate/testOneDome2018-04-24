package com.sinontech.service.read.webchatmenu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;


@Service("webchatmenuService")
public class WebchatMenuService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("WebchatMenuMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("WebchatMenuMapper.delete", pd);
	}
	/**
	 * 用途说明：同个父节点删除二级子菜单
	 * @param pd
	 * @throws Exception
	 * 2018年1月30日上午11:31:40
	 * @auther ljj
	 */
	public void deleteByPID(PageData pd)throws Exception{
		dao.delete("WebchatMenuMapper.deleteByPID", pd);
	}
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("WebchatMenuMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("WebchatMenuMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("WebchatMenuMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("WebchatMenuMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("WebchatMenuMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

