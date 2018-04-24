package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.HtmlModleDetailDaoImpl;
import com.sinontech.modle.HtmlModleDetail;
import com.sinontech.service.HtmlModleDetailService;

@Service("htmlmodledetailservice")
public class HtmlModleDetailServiceImpl implements HtmlModleDetailService {
	@Autowired
	HtmlModleDetailDaoImpl htmlModleDetailDaoImpl;

	@Override
	public List<HtmlModleDetail> gethtmlModleDetailByHtmlmodleId(
			long htmlmodleId) {
		return this.htmlModleDetailDaoImpl.gethtmlModleDetailByHtmlmodleId(htmlmodleId);
	}

	 
	
	 
	
}
