package com.neusoft.elmweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elmweb.po.Orders;
import com.neusoft.elmweb.service.OrdersService;
import com.neusoft.elmweb.service.impl.OrdersServiceImpl;

public class OrdersController {
	
	OrdersService service = new OrdersServiceImpl();
	
    public Object createOrders(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        Integer businessId = Integer.valueOf(request.getParameter("businessId"));
        Integer daId = Integer.valueOf(request.getParameter("daId"));
        int orderId = service.createOrders(userId, businessId, daId);
        return orderId;
    }
    
    public Object getOrdersById(HttpServletRequest request) {
        Integer orderId = Integer.valueOf(request.getParameter("orderId"));
        Orders orders = service.getOrdersById(orderId);
        return orders;
    }
    
    public Object listOrdersByUserId(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        List<Orders> list = service.listOrdersByUserId(userId);
        return list;
    }

}
