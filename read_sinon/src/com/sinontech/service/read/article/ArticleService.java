package com.sinontech.service.read.article;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.PageData;


@Service("articleService")
public class ArticleService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ArticleMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ArticleMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ArticleMapper.edit", pd);
	}
	
	/*
	 * 修改书籍阅读人数
	 */
	public void updateReads(PageData pd) throws Exception {
		dao.update("ArticleMapper.updateReads", pd);
	}
	
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ArticleMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ArticleMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ArticleMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ArticleMapper.deleteAll", ArrayDATA_IDS);
	}

	/*
	* 下架修改
	*/
	public void editState(PageData pd)throws Exception{
		dao.update("ArticleMapper.editState", pd);
	}
	
	/*
	 * 获取最后一条书籍数据
	 */
	public PageData findNext() throws Exception {
		PageData pd = new PageData();
		return (PageData)dao.findForObject("ArticleMapper.findNext", pd);
	}
	
	/*
	 * 添加书籍
	 */
	public void addBook(PageData pd) throws Exception {
		dao.save("ArticleMapper.addBook", pd);
	}
	/*
	 * 修改封面
	 */
	public void editfile(PageData pd) throws Exception {
		dao.update("ArticleMapper.editfile", pd);
	}
	
	/*
	 *书籍下架 
	 */
	public void bacthAllStatedown(String[] arrayDATA_IDS) throws Exception {
		dao.update("ArticleMapper.bacthAllStatedown", arrayDATA_IDS);
	}
	/*
	 *书籍上架 
	 */
	public void bacthAllStateup(String[] arrayDATA_IDS) throws Exception {
		dao.update("ArticleMapper.bacthAllStateup", arrayDATA_IDS);
	}
	
	/*
	 * 匹配书籍
	 */
	public List<PageData> matchingBook(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("ArticleMapper.matchingBook", pd);
	}
	
	/*
	 * 统计指定书籍的所有章节章节，更新书籍总章节
	 */
	public void updateChapternum(PageData pd) throws Exception {
		dao.update("ArticleMapper.updateChapternum",pd);
	}

	/*
	 * 添加章节时修改书籍总字数
	 */
	public void upchapterNumber(PageData pd) throws Exception {
		dao.update("ArticleMapper.upchapterNumber",pd);
	}
	/*
	 * 统计指定书籍的所有章节价格，更新书籍总价
	 */
	public void updateSum(PageData pd) throws Exception {
		dao.update("ArticleMapper.updateSum",pd);
	}
	
	/********渠道端
	 * @throws Exception *********/
	
	/*
	 * 推广书籍（书籍列表）
	 */
	public List<PageData> extensionBooklistPage(Page page) throws Exception {
		return (List<PageData>)dao.findForList("ArticleMapper.extensionBooklistPage", page);
	}
	/*
	 * 根据书籍名称查询书籍详情
	 */
	public PageData findByName(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ArticleMapper.findByName", pd);
	}
	
	/*
	 * 定时器自动更新
	 * 按榜单类型排序查询书籍
	 */
	public List<PageData> seachBoardSortArticle(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("ArticleMapper.seachBoardSortArticle", pd);
	}

}
