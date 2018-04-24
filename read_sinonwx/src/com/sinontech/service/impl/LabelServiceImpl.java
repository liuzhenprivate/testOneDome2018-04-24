package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.LabelDaoImpl;
import com.sinontech.modle.Label;
import com.sinontech.service.LabelService;

@Service("labelservice")
public class LabelServiceImpl implements LabelService {
	@Autowired
	LabelDaoImpl labelDao;
	
	@Override
	public List<Label> getLabelList() {
		return labelDao.getLabelList();
	}
	
	
}
