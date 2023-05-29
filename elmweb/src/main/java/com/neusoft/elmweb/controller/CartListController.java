package com.neusoft.elmweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elmweb.po.Cart;
import com.neusoft.elmweb.service.CartService;
import com.neusoft.elmweb.service.impl.CartServiceImpl;

public class CartListController {
	
	CartService service = new CartServiceImpl();
	
	public Object saveCart(HttpServletRequest request) {
	
        Cart cart = new Cart();
        cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
        cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
        cart.setUserId(request.getParameter("userId"));
        
        int result = service.saveCart(cart);
		
		return result;
	}
	
	public Object updateCart(HttpServletRequest request) {
		
        Cart cart = new Cart();
        cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
        cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
        cart.setUserId(request.getParameter("userId"));
        cart.setQuantity(Integer.valueOf(request.getParameter("quantity")));
        
        int result = service.updateCart(cart);
		
		return result;
	}
	
	public Object removeCart(HttpServletRequest request) {
		
        Cart cart = new Cart();
        cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
        cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
        cart.setUserId(request.getParameter("userId"));
        
        int result = service.removeCart(cart);
		
		return result;
	}
	
	public Object listCart(HttpServletRequest request) {
		
        Cart cart = new Cart();
        
        if(request.getParameter("businessId") != null) {
        	cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
        }
        
        cart.setUserId(request.getParameter("userId"));
        
        List<Cart> cartResult = service.listCart(cart);
		
		return cartResult ;
	}

}
