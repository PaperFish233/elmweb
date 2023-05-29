package com.neusoft.elmweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.elmweb.dao.FoodDao;
import com.neusoft.elmweb.po.Food;
import com.neusoft.elmweb.util.DBUtil;

public class FoodDaoImpl implements FoodDao {
	
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;

	@Override
	public List<Food> getFoodByBusinessId(Integer businessId) {
		List<Food> list = new ArrayList<Food>();
		String sql = "select * from food where businessId = ?";
		conn = DBUtil.getConnection();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, businessId);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Food food = new Food();
				food.setFoodId(rs.getInt("foodId"));
				food.setFoodName(rs.getString("foodName"));
				food.setFoodExplain(rs.getString("foodExplain"));
				food.setFoodImg(rs.getString("foodImg"));
				food.setFoodPrice(rs.getDouble("foodPrice"));
				food.setBusinessId(rs.getInt("businessId"));
				
				list.add(food);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs,pstm);
			DBUtil.close();
		}
		
		return list;
	}

}
