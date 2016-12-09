package com.yw.dao.impl;

import org.springframework.stereotype.Repository;

import com.yw.dao.CartDao;
import com.yw.domain.Cart;


@Repository("cartDao")
public class CartDaoImpl extends BasicDaoImpl implements CartDao{

	public boolean addCart(String id, String list) {
		// TODO Auto-generated method stub
		
		Cart cart = (Cart) findById(Cart.class, id);
		if(cart == null){
			add(new Cart(id,list));
		}
		else{
			cart.setList(list);
			updateObject(cart);
		}
		return true;
	}
 
}
