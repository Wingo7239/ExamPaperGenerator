package com.yw.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yw.dao.CartDao;
import com.yw.domain.Cart;
import com.yw.service.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CartDao cartDao;

	public boolean insertCart(String id, String list) {
		// TODO Auto-generated method stub

		return cartDao.addCart(id, list);
	}

	public String getCart(String id) {
		// TODO Auto-generated method stub
		Cart c = (Cart) cartDao.findById(Cart.class, id);
		if (c != null)
			return c.getList();
		return "[]";
	}

}
