package com.sinontech.service;

import java.util.List;

import com.sinontech.modle.Signset;


public interface SignsetService {
	
	public List<Signset> getSignset( );
	public Signset getSignsetByTimes( int times);
	public List<Signset> getSignsetbonus( );
	public Signset getSignsetById(long id);
	
}
