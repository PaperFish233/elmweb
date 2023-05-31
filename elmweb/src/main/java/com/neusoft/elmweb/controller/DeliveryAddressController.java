package com.neusoft.elmweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elmweb.po.DeliveryAddress;
import com.neusoft.elmweb.service.DeliveryAddressService;
import com.neusoft.elmweb.service.impl.DeliveryAddressServiceImpl;

public class DeliveryAddressController {
	
	DeliveryAddressService service = new DeliveryAddressServiceImpl();
	
	public Object listDeliveryAddressByUserId(HttpServletRequest request) {
	        String userId = request.getParameter("userId");
	        List<DeliveryAddress> list = service.listDeliveryAddressByUserId(userId);
	        return list;
	    }
	 
    public Object saveDeliveryAddress(HttpServletRequest request) {
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setContactName(request.getParameter("contactName"));
        deliveryAddress.setContactSex(Integer.valueOf(request.getParameter("contactSex")));
        deliveryAddress.setContactTel(request.getParameter("contactTel"));
        deliveryAddress.setAddress(request.getParameter("address"));
        deliveryAddress.setUserId(request.getParameter("userId"));
        int result = service.saveDeliveryAddress(deliveryAddress);
        return result;
    }
    
    public Object removeDeliveryAddress(HttpServletRequest request) {
        Integer daId = Integer.valueOf(request.getParameter("daId"));
        int result = service.removeDeliveryAddress(daId);
        return result;
    }
    
    public Object getDeliveryAddressById(HttpServletRequest request) {
        Integer daId = Integer.valueOf(request.getParameter("daId"));
        DeliveryAddress deliveryAddress = service.getDeliveryAddressById(daId);
        return deliveryAddress;
    }
    
    public Object updateDeliveryAddress(HttpServletRequest request) {
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setDaId(Integer.valueOf(request.getParameter("daId")));
        deliveryAddress.setContactName(request.getParameter("contactName"));
        deliveryAddress.setContactSex(Integer.valueOf(request.getParameter("contactSex")));
        deliveryAddress.setContactTel(request.getParameter("contactTel"));
        deliveryAddress.setAddress(request.getParameter("address"));
        deliveryAddress.setUserId(request.getParameter("userId"));
        int result = service.updateDeliveryAddress(deliveryAddress);
        return result;
    }

}
