package com.neusoft.elmweb.dao;

import java.util.List;

import com.neusoft.elmweb.po.Food;

public interface FoodDao {

	public List<Food> getFoodByBusinessId(Integer businessId);

}
