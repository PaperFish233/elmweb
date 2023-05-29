package com.neusoft.elmweb.service;

import java.util.List;

import com.neusoft.elmweb.po.Food;

public interface FoodService {

	public List<Food> getFoodByBusinessId(Integer businessId);

}
