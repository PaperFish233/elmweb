package com.neusoft.elmweb.dao;

import java.util.List;

import com.neusoft.elmweb.po.Business;

public interface BusinessDao {

	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);

	public Business getBusinessById(Integer businessId);
	
}
