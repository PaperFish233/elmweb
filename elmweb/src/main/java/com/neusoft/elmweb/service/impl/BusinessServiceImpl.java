package com.neusoft.elmweb.service.impl;

import java.util.List;

import com.neusoft.elmweb.dao.BusinessDao;
import com.neusoft.elmweb.dao.impl.BusinessDaoImpl;
import com.neusoft.elmweb.po.Business;
import com.neusoft.elmweb.service.BusinessService;

public class BusinessServiceImpl implements BusinessService{
	
	BusinessDao dao = new BusinessDaoImpl();
	
	@Override
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) {
		
		return dao.listBusinessByOrderTypeId(orderTypeId);
	}

	@Override
	public Business getBusinessById(Integer businessId) {

		return dao.getBusinessById(businessId);
	}

}
