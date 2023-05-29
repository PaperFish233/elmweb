package com.neusoft.elmweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.neusoft.elmweb.dao.OrdersDao;
import com.neusoft.elmweb.po.Business;
import com.neusoft.elmweb.po.Orders;
import com.neusoft.elmweb.util.DBUtil;

public class OrdersDaoImpl implements OrdersDao {
	
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	@Override
	public int saveOrders(Orders orders) {
		int result = 0;
		String sql = "insert into orders values(null,?,?,?,?,?,0)";
		con = DBUtil.getConnection();
        try {
        	pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, orders.getUserId());
            pst.setInt(2, orders.getBusinessId());
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = formatter.format(calendar.getTime());
            pst.setString(3, datetime);
            pst.setDouble(4, orders.getOrderTotal());
            pst.setInt(5, orders.getDaId());
            pst.executeUpdate();
            //获取自增长列值
            rs = pst.getGeneratedKeys();
            if(rs.next()){
                result = rs.getInt(1);
            } 
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DBUtil.close(rs,pst);
            DBUtil.close();
        }
        return result;
	}

	@Override
	public Orders getOrdersById(Integer orderId) {
		Orders orders = null;
        StringBuffer sql = new StringBuffer();
        sql.append(" select o.*, ");
        sql.append(" b.businessId bbusinessId, ");
        sql.append(" b.businessName bbusinessName, ");
        sql.append(" b.deliveryPrice bdeliveryPrice ");
        sql.append(" from orders o left join business b on o.businessId=b.businessId ");
        sql.append(" where o.orderId=? ");
        try {
            con = DBUtil.getConnection();
            //设置返回自增长列值
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, orderId);
            rs = pst.executeQuery();
            if(rs.next()){
                orders = new Orders();
                orders.setOrderId(rs.getInt("orderId"));
                orders.setUserId(rs.getString("userId"));
                orders.setBusinessId(rs.getInt("businessId"));
                orders.setOrderDate(rs.getString("orderDate"));
                orders.setOrderTotal(rs.getDouble("orderTotal"));
                orders.setOrderState(rs.getInt("orderState"));
                Business business = new Business();
                business.setBusinessId(rs.getInt("bbusinessId"));
                business.setBusinessName(rs.getString("bbusinessName"));
                business.setDeliveryPrice(rs.getDouble("bdeliveryPrice"));
                orders.setBusiness(business);
            } 
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DBUtil.close(rs,pst);
            DBUtil.close();
        }
        return orders;
	}

	@Override
	public List<Orders> listOrdersById(String userId) {
		List<Orders> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append(" select o.*, ");
        sql.append(" b.businessId bbusinessId, ");
        sql.append(" b.businessName bbusinessName, ");
        sql.append(" b.deliveryPrice bdeliveryPrice ");
        sql.append(" from orders o left join business b on o.businessId=b.businessId ");
        sql.append(" where o.userId=?");
        try {
            con = DBUtil.getConnection();
            //设置返回自增长列值
            pst = con.prepareStatement(sql.toString());
            pst.setString(1, userId);
            rs = pst.executeQuery();
            while(rs.next()){
                Orders o = new Orders();
                o.setOrderId(rs.getInt("orderId"));
                o.setUserId(rs.getString("userId"));
                o.setBusinessId(rs.getInt("businessId"));
                o.setOrderDate(rs.getString("orderDate"));
                o.setOrderTotal(rs.getDouble("orderTotal"));
                o.setDaId(rs.getInt("daId"));
                o.setOrderState(rs.getInt("orderState"));
                Business business = new Business();
                business.setBusinessId(rs.getInt("bbusinessId"));
                business.setBusinessName(rs.getString("bbusinessName"));
                business.setDeliveryPrice(rs.getDouble("bdeliveryPrice"));
                o.setBusiness(business);
                list.add(o);
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
