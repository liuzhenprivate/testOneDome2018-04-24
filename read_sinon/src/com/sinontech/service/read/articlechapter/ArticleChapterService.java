package com.sinontech.service.read.articlechapter;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.DateUtil;
import com.sinontech.util.PageData;


@Service("articlechapterService")
public class ArticleChapterService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ArticleChapterMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ArticleChapterMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ArticleChapterMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ArticleChapterMapper.datalistPage", page);
	}
	
	/*
	 * 去章节批量编辑页面
	 */
	public List<PageData> chapterBatchlist(Page page) throws Exception {
		return (List<PageData>)dao.findForList("ArticleChapterMapper.chapterBatchlistPage", page);
	}
	/*
	*列表
	*/
	public List<PageData> listchapter(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ArticleChapterMapper.chapterlistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ArticleChapterMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ArticleChapterMapper.findById", pd);
	}
	
	/*
	 * 统计数据前获取统计书籍信息
	 */
	public PageData findByIdState(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ArticleChapterMapper.findByIdState", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ArticleChapterMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	 * 删除对应书籍的所有章节
	 */
	public void deleteArticleId(PageData pd) throws Exception {
		dao.delete("ArticleChapterMapper.deleteArticleId", pd);
	}
	
	/*
	 * 查询指定书籍的章节数据
	 */
	public PageData countChapter(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ArticleChapterMapper.countChapter", pd);
	}
	
	/*
	 * 序号为空，查找指定书籍章节的最后一个序号
	 */
	public PageData nextArticleChapter(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ArticleChapterMapper.nextArticleChapter", pd);
	}
	
	/*
	 * 序号加1
	 */
	public void upnextno(PageData pd) throws Exception {
		dao.update("ArticleChapterMapper.upnextno", pd);
	}
	
	/*
	 * 添加文件保存路径
	 */
	public void editUrl(PageData pd) throws Exception {
		dao.update("ArticleChapterMapper.editUrl", pd);
	}

	public PageData nextArticleChapter1(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ArticleChapterMapper.nextArticleChapter1", pd);
	}
	
	/*
	 * 修改章节价格
	 */
	public void editmoneny(PageData pd) throws Exception {
		dao.update("ArticleChapterMapper.editmoneny", pd);
	}
	/*
	 * 修改章节状态 
	 */
	public void update(PageData pd) throws Exception {
		dao.update("ArticleChapterMapper.update", pd);
	}
	/*
	 * 序号加
	 */
	public void editNoPlus(PageData pd) throws Exception {
		dao.update("ArticleChapterMapper.editNoPlus", pd);
	}
	/*
	 * 序号减
	 */
	public void editNoReduce(PageData pd) throws Exception {
		dao.update("ArticleChapterMapper.editNoReduce", pd);
	}
	/*
	 * 修改章节价格
	 */
	public void updateConsums(PageData pd) throws Exception {
		dao.update("ArticleChapterMapper.updateConsums", pd);
	}
	
	/*
	 * 根据推广连接查询章节
	 */
	public List<PageData> extensionchapter(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("ArticleChapterMapper.extensionchapter", pd);
	}
	/*
	 * 推广详情页面章节查询
	 */
	public List<PageData> seachExtensionchapter(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("ArticleChapterMapper.seachExtensionchapter", pd);
	}
	/*
	 * 通过章节id查询所要推广的章节
	 */
	public PageData extensionchapterid(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ArticleChapterMapper.extensionchapterid", pd);
	}
	/*
	 * 通过排序号和书籍id查询章节
	 */
	public PageData findByNo(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ArticleChapterMapper.findByNo", pd);
	}
	/*
	 * 生成渠道推广链接时查询强制关注章节
	 */
	public List<PageData> extensionlist(Page page) throws Exception {
		return (List<PageData>)dao.findForList("ArticleChapterMapper.extensionlist", page);
	}
	/*
	 * 生成推广链接时查询推广章节数
	 */
	public PageData countindexchapter(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ArticleChapterMapper.countindexchapter", pd);
	}
	/*
	 * 通过书籍id查询章节的最后一章的序号
	 */
	public PageData findByARTICLEID(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ArticleChapterMapper.findByARTICLEID", pd);
	}
	/*
	 * 批量添加（把中间表数据添加到章节表）
	 */
	public void savetemp(PageData pd) throws Exception {
		dao.save("ArticleChapterMapper.savetemp", pd);
	}
	/*
	 * 书籍详情页面，改变章节状态
	 */
	public void editstate(PageData pd) throws Exception {
		dao.update("ArticleChapterMapper.editstate", pd);
	}
	/*
	 * 批量编辑书籍章节价格
	 */
	public void editMoneys(PageData pd) throws Exception {
		dao.update("ArticleChapterMapper.editMoneys", pd);
	}
	/*
	 * 修改章节价格签前查询是否有足够的章节
	 */
	public List<PageData> findBylimit(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("ArticleChapterMapper.findBylimit", pd);
	}
	/*
	 * 查询推广书籍章节
	 */
	public List<PageData> listchapterExtension(Page page) throws Exception {
		return (List<PageData>)dao.findForList("ArticleChapterMapper.listchapterExtensionlistPage", page);
	}
	/*
	 * 按章节数查询章节id
	 */
	public PageData findByChapters(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ArticleChapterMapper.findByChapters", pd);
	}
	
	/*
	 * 查询可强制关注的章节
	 */
	public List<PageData> seachExtensionForceChapter(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("ArticleChapterMapper.seachExtensionForceChapter", pd);
	}
	
}
