package com.neusoft.elmweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elmweb.po.Business;
import com.neusoft.elmweb.service.BusinessService;
import com.neusoft.elmweb.service.impl.BusinessServiceImpl;

public class BusinessListController {
	
	BusinessService service = new BusinessServiceImpl();
	
	public Object listBusinessByOrderTypeId(HttpServletRequest request) {
		Integer orderTypeId = Integer.parseInt(request.getParameter("orderTypeId"));
		List<Business> list = service.listBusinessByOrderTypeId(orderTypeId);
		
		return list;
	}
	
	public Object getBusinessById(HttpServletRequest request) {
		Integer businessId = Integer.parseInt(request.getParameter("businessId"));
		Business business = service.getBusinessById(businessId);
		
		return business;
	}

}
