package com.sinontech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinontech.dao.impl.SignsetDaoImpl;
import com.sinontech.modle.Signset;
import com.sinontech.service.SignsetService;

@Service("signsetservice")
public class SignsetServiceImpl implements SignsetService{

	@Autowired
	SignsetDaoImpl signsetDaoImpl;

	public Signset getSignsetById(long id){
		return this.signsetDaoImpl.getSignsetById(id);
	}
	public List<Signset> getSignset() {
		return this.signsetDaoImpl.getSignset( );
	}

	public List<Signset> getSignsetbonus(){
		return this.signsetDaoImpl.getSignsetbonus();
	}
	
	public Signset getSignsetByTimes(int times) {
		List<Signset> list =  this.signsetDaoImpl.getSignsetByTimes(  times);
		if(null!=list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	 
	
}
