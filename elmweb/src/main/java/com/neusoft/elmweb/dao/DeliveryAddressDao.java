package com.neusoft.elmweb.dao;

import java.util.List;

import com.neusoft.elmweb.po.DeliveryAddress;

public interface DeliveryAddressDao {
	
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
	
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress);
    
    public int removeDeliveryAddress(Integer daId);
    
    public DeliveryAddress getDeliveryAddressById(Integer daId);
    
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress);

}
