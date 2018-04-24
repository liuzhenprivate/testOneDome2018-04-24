package com.sinontech.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.ArticleLabelDaoImpl;
import com.sinontech.modle.ArticleLabel;
import com.sinontech.modle.Label;
import com.sinontech.service.ArticleLabelService;

@Service("articleLabelService")
public class ArticleLabelServiceImpl implements ArticleLabelService {
	
	@Autowired
	ArticleLabelDaoImpl articleLabelDaoImpl;
	
	@Override
	public List<ArticleLabel> varListLabel(long articleId){
		return this.articleLabelDaoImpl.varListLabel(articleId);
	}
}
