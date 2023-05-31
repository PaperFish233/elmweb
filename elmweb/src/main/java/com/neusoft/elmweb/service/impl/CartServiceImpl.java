package com.neusoft.elmweb.service.impl;

import java.util.List;

import com.neusoft.elmweb.dao.CartDao;
import com.neusoft.elmweb.dao.impl.CartDaoImpl;
import com.neusoft.elmweb.po.Cart;
import com.neusoft.elmweb.service.CartService;

public class CartServiceImpl implements CartService {
	
	CartDao dao = new CartDaoImpl();

	@Override
	public int saveCart(Cart cart) {
		
		return dao.saveCart(cart);
	}

	@Override
	public int updateCart(Cart cart) {
		
		return dao.updateCart(cart);
	}

	@Override
	public int removeCart(Cart cart) {
		
		return dao.removeCart(cart);
	}

	@Override
	public List<Cart> listCart(Cart cart) {
		
		return dao.listCart(cart);
	}
	
	

}
