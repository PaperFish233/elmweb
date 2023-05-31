package com.neusoft.elmweb.dao;

import java.util.List;

import com.neusoft.elmweb.po.OrderDetailet;

public interface OrderDetailetDao {
	
	public int saveOrderDetailetBatch(List<OrderDetailet> list);
	
    public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId);

}
