package com.neusoft.elmweb.service;

import java.util.List;

import com.neusoft.elmweb.po.Business;

public interface BusinessService {

	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);

	public Business getBusinessById(Integer businessId);

}
