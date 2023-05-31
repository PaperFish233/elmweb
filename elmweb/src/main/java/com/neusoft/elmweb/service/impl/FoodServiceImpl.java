package com.neusoft.elmweb.service.impl;

import java.util.List;

import com.neusoft.elmweb.dao.FoodDao;
import com.neusoft.elmweb.dao.impl.FoodDaoImpl;
import com.neusoft.elmweb.po.Food;
import com.neusoft.elmweb.service.FoodService;

public class FoodServiceImpl implements FoodService {
	
	FoodDao dao = new FoodDaoImpl();

	@Override
	public List<Food> getFoodByBusinessId(Integer businessId) {
		
		return dao.getFoodByBusinessId(businessId);
	}

}
