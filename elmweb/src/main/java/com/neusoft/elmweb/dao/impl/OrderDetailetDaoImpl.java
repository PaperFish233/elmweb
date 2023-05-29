package com.neusoft.elmweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.elmweb.dao.OrderDetailetDao;
import com.neusoft.elmweb.po.Food;
import com.neusoft.elmweb.po.OrderDetailet;
import com.neusoft.elmweb.util.DBUtil;

public class OrderDetailetDaoImpl implements OrderDetailetDao {
	
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	@Override
	public int saveOrderDetailetBatch(List<OrderDetailet> list) {
		int result = 0;
        StringBuffer stringBuffer = new StringBuffer("insert into orderDetailet(orderId,foodId,quantity) values");
        for(OrderDetailet od : list) {
            stringBuffer.append("("+od.getOrderId()+","+od.getFoodId()+","+od.getQuantity()+"),");
        }
        //去掉SQL语句中的最后一个逗号
        String sql = stringBuffer.toString().substring(0,stringBuffer.toString().length()-1);
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            result = pst.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DBUtil.close(pst);
            DBUtil.close();
        }
        return result;
	}

	@Override
	public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId) {
		List<OrderDetailet> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append(" select o.*, ");
        sql.append(" f.foodId ffoodId, ");
        sql.append(" f.foodName ffoodName, ");
        sql.append(" f.foodPrice ffoodPrice ");
        sql.append(" from orderDetailet o left join food f on o.foodId=f.foodId ");
        sql.append(" where o.orderId=? ");
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, orderId);
            rs = pst.executeQuery();
            while(rs.next()) {
                OrderDetailet od = new OrderDetailet();
                od.setOdId(rs.getInt("odId"));
                od.setOrderId(rs.getInt("orderId"));
                od.setFoodId(rs.getInt("foodId"));
                od.setQuantity(rs.getInt("quantity"));
                Food food = new Food();
                food.setFoodId(rs.getInt("ffoodId"));
                food.setFoodName(rs.getString("ffoodName"));
                food.setFoodPrice(rs.getDouble("ffoodPrice"));
                od.setFood(food);
                list.add(od);
            }
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DBUtil.close(rs,pst);
            DBUtil.close();
        }
        return list;
	}

}
