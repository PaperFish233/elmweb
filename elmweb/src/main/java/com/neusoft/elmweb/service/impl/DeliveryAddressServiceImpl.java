package com.neusoft.elmweb.service.impl;

import java.util.List;

import com.neusoft.elmweb.dao.DeliveryAddressDao;
import com.neusoft.elmweb.dao.impl.DeliveryAddressDaoImpl;
import com.neusoft.elmweb.po.DeliveryAddress;
import com.neusoft.elmweb.service.DeliveryAddressService;

public class DeliveryAddressServiceImpl implements DeliveryAddressService {
	
	DeliveryAddressDao dao = new DeliveryAddressDaoImpl();

	@Override
    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
       
        return dao.listDeliveryAddressByUserId(userId);
    }
	
    @Override
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress) {
        
        return dao.saveDeliveryAddress(deliveryAddress);
    }
    
    @Override
    public int removeDeliveryAddress(Integer daId) {
       
        return dao.removeDeliveryAddress(daId);
    }
    
    @Override
    public DeliveryAddress getDeliveryAddressById(Integer daId) {
        
        return dao.getDeliveryAddressById(daId);
    }
    
    @Override
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress) {
        
        return dao.updateDeliveryAddress(deliveryAddress);
    }

}
