package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.HtmlModleDaoImpl;
import com.sinontech.modle.Html;
import com.sinontech.modle.HtmlModle;
import com.sinontech.service.HtmlModleService;

@Service("htmlmodleservice")
public class HtmlModleServiceImpl implements HtmlModleService {
	@Autowired
	HtmlModleDaoImpl htmlModleDaoImpl;
	
	
	 
	@Override
	public void updateHtml(Html html){
		this.htmlModleDaoImpl.updateHtml(html);
	}
	
	@Override
	public List<HtmlModle> getHtmlModleByPlaceType(int placeType) {
		return this.htmlModleDaoImpl.getHtmlModleByPlaceType(placeType);
	}

	@Override
	public Html findHtmlById(long id) {
		return this.htmlModleDaoImpl.findHtmlById(id);
	}

	@Override
	public HtmlModle findHtmlDetailById(long id) {
		return this.htmlModleDaoImpl.findHtmlDetailById(id);
	}

	@Override
	public Html findHtmlByPlacType(int placType) {
		Html html =  this.htmlModleDaoImpl.findHtmlByPlacType(placType);
		if(null!=html){
			html.setPvs(html.getPvs()+1);
			this.htmlModleDaoImpl.updateHtml(html);
		}
		return html;
	}

	@Override
	public List<HtmlModle> getHtmlModleByHtmlId(long htmlId) {
		return this.htmlModleDaoImpl.getHtmlModleByHtmlId(htmlId);
	}
	
	 
	
}
