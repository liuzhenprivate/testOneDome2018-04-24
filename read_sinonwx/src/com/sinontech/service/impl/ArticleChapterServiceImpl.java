package com.sinontech.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.ArticleChapterDaoImpl;
import com.sinontech.modle.ArticleChapter;
import com.sinontech.service.ArticleChapterService;

/**
 * 
 * @author ljj
 *
 */
@Service("articleChapterservice")
public class ArticleChapterServiceImpl implements ArticleChapterService{

	@Autowired
	ArticleChapterDaoImpl articleChapterDaoImpl;

	@Override
	public void updateArticleChapter(ArticleChapter articleChapter) {
		this.articleChapterDaoImpl.updateArticleChapter(articleChapter);
	}

	@Override
	public List<ArticleChapter> getArticleChapterByarticleId(long articleid) {
		return this.articleChapterDaoImpl.getArticleChapterByarticleId(articleid);
	}

	@Override
	public ArticleChapter getArticleChapterFirstByarticleId(long articleid) {
		return this.articleChapterDaoImpl.getArticleChapterFirstByarticleId(articleid);
	}
	
	@Override
	public ArticleChapter getArticleChapterById(long articlechapterid) {
		return this.articleChapterDaoImpl.getArticleChapterById(articlechapterid);
	}
	@Override
	public List<ArticleChapter> visitSeachChapter(long articleId, int articleChpaters) {
		return this.articleChapterDaoImpl.visitSeachChapter(articleId,articleChpaters);
	}
	@Override
	public ArticleChapter seachNextChapter(long articleId, long articleChapterId) {
		return this.articleChapterDaoImpl.seachNextChapter(articleId,articleChapterId);
	}
	@Override
	public int countDownChapter(long articleId, long articleChapterId) {
		return this.articleChapterDaoImpl.countDownChapter(articleId,articleChapterId);
	}

	@Override
	public ArticleChapter readNextArticleChapter(long articleId,long chapterNo) {
		return this.articleChapterDaoImpl.readNextArticleChapter(articleId,chapterNo);
	}

	@Override
	public ArticleChapter readUpperArticleChapter(long articleId,long chapterNo) {
		return this.articleChapterDaoImpl.readUpperArticleChapter(articleId,chapterNo);
	}

	 
}