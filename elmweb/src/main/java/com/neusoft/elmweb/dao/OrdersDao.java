package com.neusoft.elmweb.dao;

import java.util.List;

import com.neusoft.elmweb.po.Orders;

public interface OrdersDao {
	
	public int saveOrders(Orders orders);
	
    public Orders getOrdersById(Integer orderId);
    
    public List<Orders> listOrdersById(String userId);

}
