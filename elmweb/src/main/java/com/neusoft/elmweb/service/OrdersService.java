package com.neusoft.elmweb.service;

import java.util.List;

import com.neusoft.elmweb.po.Orders;

public interface OrdersService {
	
	public int createOrders(String userId,Integer businessId,Integer daId);
	
    public Orders getOrdersById(Integer orderId);
    
    public List<Orders> listOrdersByUserId(String userId);

}
