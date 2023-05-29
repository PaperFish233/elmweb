package com.neusoft.elmweb.service;

import java.util.List;

import com.neusoft.elmweb.po.Cart;

public interface CartService {

	public int saveCart(Cart cart);

	public int updateCart(Cart cart);

	public int removeCart(Cart cart);

	public List<Cart> listCart(Cart cart);

}
