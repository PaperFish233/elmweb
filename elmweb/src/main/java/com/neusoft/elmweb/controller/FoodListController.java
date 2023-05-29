package com.neusoft.elmweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elmweb.po.Food;
import com.neusoft.elmweb.service.FoodService;
import com.neusoft.elmweb.service.impl.FoodServiceImpl;

public class FoodListController {
	
	FoodService service = new FoodServiceImpl();
	
	public Object getFoodByBusinessId(HttpServletRequest request) {
		Integer businessId = Integer.parseInt(request.getParameter("businessId"));
		List<Food> list = service.getFoodByBusinessId(businessId);
		
		return list;
	}

}
