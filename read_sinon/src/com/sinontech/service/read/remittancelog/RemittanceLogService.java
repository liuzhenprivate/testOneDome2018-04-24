package com.sinontech.service.read.remittancelog;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;


@Service("remittancelogService")
public class RemittanceLogService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("RemittanceLogMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("RemittanceLogMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("RemittanceLogMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("RemittanceLogMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("RemittanceLogMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("RemittanceLogMapper.findById", pd);
	}
	/**
	 * 用途说明：计算打款金额总和
	 * @param pd
	 * @return
	 * @throws Exception
	 * 2018年1月18日下午5:32:06
	 * @auther ljj
	 */
	public PageData sumAll(PageData pd)throws Exception{
		return (PageData)dao.findForObject("RemittanceLogMapper.sumAll", pd);
	}
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("RemittanceLogMapper.deleteAll", ArrayDATA_IDS);
	}
	/*
	 * 获取渠道打款信息
	 */
	public List<PageData> sysUserList(Page page) throws Exception {
		return (List<PageData>)dao.findForList("RemittanceLogMapper.sysUserList", page);
	}
	
	/*
	 * 列出渠道账户信息
	 */
	public List<PageData> listsysuserAccountexcel(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("RemittanceLogMapper.listsysuserAccountexcel", pd);
	}
	
}
