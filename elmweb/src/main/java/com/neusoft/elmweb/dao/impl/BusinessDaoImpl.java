package com.neusoft.elmweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.elmweb.util.DBUtil;
import com.neusoft.elmweb.dao.BusinessDao;
import com.neusoft.elmweb.po.Business;

public class BusinessDaoImpl implements BusinessDao {
	
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;

	@Override
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) {
		
		List<Business> blist = new ArrayList<Business>();
		String sql = "select * from business where orderTypeId = ?";
		conn = DBUtil.getConnection();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, orderTypeId);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Business business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setBusinessImg(rs.getString("businessImg"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
				business.setOrderTypeId(rs.getInt("orderTypeId"));
				business.setStarPrice(rs.getDouble("starPrice"));
				blist.add(business);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs,pstm);
			DBUtil.close();
		}
		
		return blist;
	}

	@Override
	public Business getBusinessById(Integer businessId) {
		String sql = "select * from business where businessId = ?";
		conn = DBUtil.getConnection();
		Business business = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, businessId);
			rs = pstm.executeQuery();
			while(rs.next()) {
				business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setBusinessImg(rs.getString("businessImg"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
				business.setOrderTypeId(rs.getInt("orderTypeId"));
				business.setStarPrice(rs.getDouble("starPrice"));	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs,pstm);
			DBUtil.close();
		}
		
		return business;
	}

}
