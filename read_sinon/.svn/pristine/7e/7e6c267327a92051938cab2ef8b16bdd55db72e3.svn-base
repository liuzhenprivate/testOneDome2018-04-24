package com.sinontech.service.read.categorylabel;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;



@Service("categorylabelService")
public class CategoryLabelService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("CategoryLabelMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("CategoryLabelMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("CategoryLabelMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CategoryLabelMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CategoryLabelMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CategoryLabelMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CategoryLabelMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	 * 
	 */
	public List<PageData> listData(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("CategoryLabelMapper.listData", pd);
	}
	
	/*
	 * 移除标签分类
	 */
	public void delLabelId(String LABEL_ID) throws Exception {
		dao.update("CategoryLabelMapper.delLabelId", LABEL_ID);
	}
	
	/*
	 * 给标签添加标签类前判断是否已经存在
	 */
	public PageData findByLabelandCategoryId(PageData pd) throws Exception {
		return (PageData)dao.findForObject("CategoryLabelMapper.findByLabelandCategoryId", pd);
	}
	
}

