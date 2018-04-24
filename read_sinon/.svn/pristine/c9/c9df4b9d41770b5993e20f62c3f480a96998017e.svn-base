package com.sinontech.service.read.extensionconnect;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;


@Service("extensionconnectService")
public class ExtensionConnectService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ExtensionConnectMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ExtensionConnectMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ExtensionConnectMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ExtensionConnectMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ExtensionConnectMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ExtensionConnectMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ExtensionConnectMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	 * 通过推广链接获取数据
	 */
	public PageData findByURL(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ExtensionConnectMapper.findByURL", pd);
	}
	/*
	 * 使用推广链接匹配
	 */
	public PageData findVisitByURL(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ExtensionConnectMapper.findVisitByURL", pd);
	}
	
	/*
	 * 引导人数+1
	 */
	public void guideeditup(PageData pd) throws Exception {
		dao.update("ExtensionConnectMapper.guideeditup", pd);
	}
	
}

