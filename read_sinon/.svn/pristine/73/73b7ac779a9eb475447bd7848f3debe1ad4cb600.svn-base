package com.sinontech.service.read.label;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;


@Service("labelService")
public class LabelService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("LabelMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("LabelMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("LabelMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("LabelMapper.datalist", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("LabelMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("LabelMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("LabelMapper.deleteAll", ArrayDATA_IDS);
	}
	

	/*
	 * 添加前确认是否重复(LABEL_NAME)
	 */
	public PageData seachlabel(PageData pd) throws Exception {
		return (PageData)dao.findForObject("LabelMapper.seachlabel", pd);
	}
	
	/*
	 * 移除标签的分类
	 */
	public void editname(PageData pd) throws Exception {
		dao.update("LabelMapper.editname", pd);
	}
	
	/*
	 * 根据名称查询
	 */
	public List<PageData> listData(String[] LABEL) throws Exception {
		return (List<PageData>)dao.findForList("LabelMapper.listData", LABEL);
	}
	/*
	 * 查询指定书籍的标签
	 */
	public List<PageData> listfindbookid(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("LabelMapper.listfindbookid", pd);
	}
	
}

