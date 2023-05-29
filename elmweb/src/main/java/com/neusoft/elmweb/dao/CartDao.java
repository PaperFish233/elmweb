package com.neusoft.elmweb.dao;

import java.util.List;

import com.neusoft.elmweb.po.Cart;

public interface CartDao {

	public int saveCart(Cart cart);

	public int updateCart(Cart cart);

	public int removeCart(Cart cart);

	public List<Cart> listCart(Cart cart);

}
