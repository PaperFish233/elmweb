package com.neusoft.elmweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.elmweb.dao.CartDao;
import com.neusoft.elmweb.po.Business;
import com.neusoft.elmweb.po.Cart;
import com.neusoft.elmweb.po.Food;
import com.neusoft.elmweb.util.DBUtil;

public class CartDaoImpl implements CartDao {
	
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;

	@Override
	public int saveCart(Cart cart) {
		String sql = "insert into cart values(null,?,?,?,1)";
		conn = DBUtil.getConnection();
		int result = 0;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, cart.getFoodId());
			pstm.setInt(2, cart.getBusinessId());
			pstm.setString(3, cart.getUserId());
			result = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null,pstm);
			DBUtil.close();
		}
		
		return result;
	}

	@Override
	public int updateCart(Cart cart) {
		String sql = "update cart set quantity=? where foodId=? and businessId=? and userId=?";
		conn = DBUtil.getConnection();
		int result = 0;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, cart.getQuantity());
			pstm.setInt(2, cart.getFoodId());
			pstm.setInt(3, cart.getBusinessId());
			pstm.setString(4, cart.getUserId());
			
			result = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null,pstm);
			DBUtil.close();
		}
		
		return result;
	}

	@Override
	public int removeCart(Cart cart) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from cart where businessId=? and userId=? ");
		if(cart.getFoodId()!=null) {
			sql.append("and foodId=?");
		}
		conn = DBUtil.getConnection();
		int result = 0;
		try {
			pstm = conn.prepareStatement(sql.toString());
			
			pstm.setInt(1, cart.getBusinessId());
			pstm.setString(2, cart.getUserId());
			if(cart.getFoodId()!=null) {
				pstm.setInt(3, cart.getFoodId());
			}
			
			result = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null,pstm);
			DBUtil.close();
		}
		
		return result;
	}

	@Override
	public List<Cart> listCart(Cart cart) {
		List<Cart> list = new ArrayList<Cart>();		
		StringBuffer sql = new StringBuffer();
		sql.append(" select c.*,f.foodId ffoodId,f.foodName,f.foodImg,f.foodPrice,b.businessName,b.deliveryPrice "
				+ "FROM cart c LEFT JOIN food f ON c.foodId = f.foodId LEFT JOIN business b "
				+ "ON c.businessId = b.businessId WHERE c.userId = ? ");
		if(cart.getBusinessId()!=null) {
			sql.append("AND c.businessId = ?");
		}
		
		conn = DBUtil.getConnection();
		try {
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, cart.getUserId());
			if(cart.getBusinessId()!=null) {
				pstm.setInt(2, cart.getBusinessId());
			}
			rs = pstm.executeQuery();
			while(rs.next()) {
				Cart c = new Cart();
				c.setCartId(rs.getInt("cartId"));
                c.setFoodId(rs.getInt("foodId"));
                c.setBusinessId(rs.getInt("businessId"));
                c.setUserId(rs.getString("userId"));
                c.setQuantity(rs.getInt("quantity"));
                
                Food f = new Food();
                f.setFoodId(rs.getInt("ffoodId"));
                f.setFoodName(rs.getString("foodName"));
                f.setFoodImg(rs.getString("foodImg"));
                f.setFoodPrice(rs.getDouble("foodPrice"));
                c.setFood(f);
                
                Business b = new Business();
                b.setBusinessName(rs.getString("businessName"));
                b.setDeliveryPrice(rs.getDouble("deliveryPrice"));
                c.setBusiness(b);
                
                list.add(c);
				
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
