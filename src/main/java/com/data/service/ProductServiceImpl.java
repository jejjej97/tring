package com.data.service;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.data.dao.MemoDAO;
import com.data.dao.ProductDAO;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Inject
	private ProductDAO dao;
	
}
