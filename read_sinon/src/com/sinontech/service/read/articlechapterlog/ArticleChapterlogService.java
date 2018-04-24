package com.sinontech.service.read.articlechapterlog;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.DateUtil;
import com.sinontech.util.PageData;


@Service("articlechapterlogService")
public class ArticleChapterlogService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ArticleChapterlogMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ArticleChapterlogMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ArticleChapterlogMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ArticleChapterlogMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ArticleChapterlogMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ArticleChapterlogMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ArticleChapterlogMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	 * 查询书籍总数据
	 */
	public PageData bookcountdata(PageData pd) throws Exception {
		pd.put("CREATE_TIME", "");
		return (PageData)dao.findForObject("ArticleChapterlogMapper.bookcountdata", pd);
	}
	
	/*
	 * 查询书籍昨天数据
	 */
	public PageData bookcountdatayd(PageData pd) throws Exception {
		pd.put("CREATE_TIME", DateUtil.getAfterDayDates("-1"));
		return (PageData)dao.findForObject("ArticleChapterlogMapper.bookcountdata", pd);
	}
	/*
	 * 查询书籍当天数据
	 */
	public PageData bookcountdatad(PageData pd) throws Exception {
		pd.put("CREATE_TIME", DateUtil.getDay());
		return (PageData)dao.findForObject("ArticleChapterlogMapper.bookcountdata", pd);
	}
	/*
	 * 查询书籍当月数据
	 */
	public PageData bookcountdatam(PageData pd) throws Exception {
		pd.put("CREATE_TIME", DateUtil.getMM());
		return (PageData)dao.findForObject("ArticleChapterlogMapper.bookcountdata", pd);
	}
	
	/*
	 * 读者阅读记录
	 */
	public List<PageData> listUser(Page page) throws Exception {
		return (List<PageData>)dao.findForList("ArticleChapterlogMapper.listUserlistPage", page);
	}

}

