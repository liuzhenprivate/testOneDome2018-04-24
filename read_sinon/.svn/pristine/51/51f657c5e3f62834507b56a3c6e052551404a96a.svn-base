package com.sinontech.service.read.articlechaptertemp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinontech.dao.DaoSupport;
import com.sinontech.entity.Page;
import com.sinontech.util.DateUtil;
import com.sinontech.util.PageData;


@Service("articlechaptertempService")
public class ArticleChapterTempService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ArticleChapterTempMapper.save", pd);
	}
	
	
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ArticleChapterTempMapper.findById", pd);
	}
	
	/*
	 * 序号加1
	 */
	public void upnextno(PageData pd) throws Exception {
		dao.update("ArticleChapterTempMapper.upnextno", pd);
	}


	/*
	 * 查询指定书籍的第一章
	 */
	public PageData findByStart(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ArticleChapterTempMapper.findByStart", pd);
	}
	/*
	 * 查询指定书籍的最后一章
	 */
	public PageData findByEnd(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ArticleChapterTempMapper.findByEnd", pd);
	}



	public List<PageData> listAll(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("ArticleChapterTempMapper.listAll", pd);
	}


	/*
	 * 删除临时表数据
	 */
	public void delAll(PageData pd) throws Exception {
		dao.delete("ArticleChapterTempMapper.delAll", pd);
	}
	
}

