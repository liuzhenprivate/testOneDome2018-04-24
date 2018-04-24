package com.sinontech.service.read.labelcategory;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;


@Service("labelcategoryService")
public class LabelCategoryService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("LabelCategoryMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("LabelCategoryMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("LabelCategoryMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("LabelCategoryMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("LabelCategoryMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("LabelCategoryMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("LabelCategoryMapper.deleteAll", ArrayDATA_IDS);
	}
	/*
	 * 通过类名获取数据
	 */
	public PageData findCategoryName(PageData pd) throws Exception {
		return (PageData)dao.findForObject("LabelCategoryMapper.findCategoryName", pd);
	}
	
	/*
	 * 删除标签时删除标签类关联表
	 */
	public void deleteCategoryLabelid(PageData pd) throws Exception {
		dao.delete("LabelCategoryMapper.deleteCategoryLabelid", pd);
	}
	
}

