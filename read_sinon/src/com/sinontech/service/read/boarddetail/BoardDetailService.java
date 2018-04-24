package com.sinontech.service.read.boarddetail;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;



@Service("boarddetailService")
public class BoardDetailService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("BoardDetailMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("BoardDetailMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("BoardDetailMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("BoardDetailMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("BoardDetailMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("BoardDetailMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("BoardDetailMapper.deleteAll", ArrayDATA_IDS);
	}
	/*
	 * 查询书籍类目
	 */
	public PageData seachboarddetailCATEGORY(PageData pd) throws Exception {
		return (PageData)dao.findForObject("BoardDetailMapper.seachboarddetailCATEGORY", pd);
	}
	/*
	 * 查询手动添加书籍
	 */
	public List<PageData> addBooksListAll(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("BoardDetailMapper.addBooksListAll", pd);
	}
	/*
	 * 手动添加前确认添加的书籍是否已经存在
	 */
	public PageData seachContrast(PageData pd) throws Exception {
		return (PageData)dao.findForObject("BoardDetailMapper.seachContrast", pd);
	}
	/*
	 * 查询排序的最后一个
	 */
	public PageData seachNextSort(PageData pd) throws Exception {
		return (PageData)dao.findForObject("BoardDetailMapper.seachNextSort", pd);
	}
	/*
	 * 所有排序加length 
	 */
	public void goUp(PageData pd) throws Exception {
		dao.update("BoardDetailMapper.goUp", pd);
	}
	/*
	 * 删除榜单详情数据（自动匹配的除外）
	 */
	public void delIsNotRuleOne(PageData pd) throws Exception {
		dao.delete("BoardDetailMapper.delIsNotRuleOne", pd);
	}
	/*
	 * 查询榜单详情里面手动添加和手动修改的数据
	 */
	public List<PageData> seachIdType(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("BoardDetailMapper.seachIdType", pd);
	}
	/*
	 * 更新手动修改和手动添加的排序
	 */
	public void updateInit(PageData pd) throws Exception {
		dao.update("BoardDetailMapper.updateInit", pd);
	}
	
}

