package com.sinontech.service.read.noticelog;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;


@Service("noticelogService")
public class NoticeLogService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("NoticeLogMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("NoticeLogMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("NoticeLogMapper.edit", pd);
	}
	/**
	 * 用途说明：修改状态
	 * @param pd
	 * @throws Exception
	 * 2017年11月28日下午5:17:04
	 * @auther ljj
	 */
	public void editstate(PageData pd)throws Exception{
		dao.update("NoticeLogMapper.editstate", pd);
	}
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("NoticeLogMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("NoticeLogMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("NoticeLogMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("NoticeLogMapper.deleteAll", ArrayDATA_IDS);
	}
	/*
	 * 渠道公告列表
	 */
	public List<PageData> sysUserlist(Page page) throws Exception {
		return (List<PageData>)dao.findForList("NoticeLogMapper.sysUserlistPage", page);
	}

	
}

