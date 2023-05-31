package com.neusoft.elmweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.neusoft.elmweb.dao.BusinessDao;
import com.neusoft.elmweb.dao.CartDao;
import com.neusoft.elmweb.dao.OrderDetailetDao;
import com.neusoft.elmweb.dao.OrdersDao;
import com.neusoft.elmweb.dao.impl.BusinessDaoImpl;
import com.neusoft.elmweb.dao.impl.CartDaoImpl;
import com.neusoft.elmweb.dao.impl.OrderDetailetDaoImpl;
import com.neusoft.elmweb.dao.impl.OrdersDaoImpl;
import com.neusoft.elmweb.po.Business;
import com.neusoft.elmweb.po.Cart;
import com.neusoft.elmweb.po.OrderDetailet;
import com.neusoft.elmweb.po.Orders;
import com.neusoft.elmweb.service.OrdersService;
import com.neusoft.elmweb.util.DBUtil;

public class OrdersServiceImpl implements OrdersService {
	
	BusinessDao businessDao = new BusinessDaoImpl();
	CartDao cartDao = new CartDaoImpl();
    OrdersDao ordersDao = new OrdersDaoImpl();
    OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();

	@Override
	public int createOrders(String userId, Integer businessId, Integer daId) {
		
		int orderId = 0;
        try {
        //DBUtil.beginTransaction();
        //1、查询当前用户购物车中当前商家的所有食品
        Cart cart = new Cart();
        cart.setBusinessId(businessId);
        cart.setUserId(userId);
        List<Cart> cartList = cartDao.listCart(cart);
        //2、查询商家信息（需要使用商家的配送费信息）
        Business business = businessDao.getBusinessById(businessId);
        //3、获取订单总额
        Double ordersTotal = 0.0;  //订单总额
        for(Cart c : cartList) {
            //累计所有食品总价格
            ordersTotal += c.getFood().getFoodPrice()*c.getQuantity();
        }
        //加上配送费
        ordersTotal += business.getDeliveryPrice();
        //3、创建订单，并获取订单编号
        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setBusinessId(businessId);
        orders.setOrderTotal(ordersTotal);
        orders.setDaId(daId);
        orderId = ordersDao.saveOrders(orders);
        //4、处理相关数据：获取订单明细集合
        List<OrderDetailet> orderDetailetList = new ArrayList<>();  //订单明细集合
        for(Cart c : cartList) {
            OrderDetailet od = new OrderDetailet();
            od.setOrderId(orderId);
            od.setFoodId(c.getFoodId());
            od.setQuantity(c.getQuantity());
            orderDetailetList.add(od);
        }
        //5、批量添加订单明细
        orderDetailetDao.saveOrderDetailetBatch(orderDetailetList);

        //6、清空当前用户购物车中当前商家的所有食品
        cartDao.removeCart(cart);
        //DBUtil.commitTransaction();
        } catch (Exception e) {
            orderId = 0;
//            try {
//                DBUtil.rollbackTransaction();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
            e.printStackTrace();
        }
		
		return orderId;
	}

	@Override
	public Orders getOrdersById(Integer orderId) {
		
		return ordersDao.getOrdersById(orderId);
	}

	@Override
	public List<Orders> listOrdersByUserId(String userId) {
		List<Orders> list = new ArrayList<>();
        try {
//            DBUtil.beginTransaction();
            //先查询当前用户所有订单
            list = ordersDao.listOrdersById(userId);
            for(Orders o : list) {
                //在查询每个订单的明细
                List<OrderDetailet> odList = orderDetailetDao.listOrderDetailetByOrderId(o.getOrderId());
                o.setList(odList);
            }
//            DBUtil.commitTransaction();
        } catch (Exception e) {
//            try {
//                DBUtil.rollbackTransaction();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
            e.printStackTrace();
        } finally {
            DBUtil.close(); // 关闭Connection
        }
        return list;
	}

}
