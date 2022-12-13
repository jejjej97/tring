package com.data.controller;


import com.data.service.ProductService;
import com.tring.domain.ProductVO;
import com.user.service.UserService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Handles requests for the application home page.
 */
@CrossOrigin
@RestController
public class ProductController
{

	@Inject
	private ProductService pservice;
	@Inject
	private UserService uservice;
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
//////////////////////////////////////////////// 회의 데이터 ////////////////////////////////////////////////
	
	//상품 검색 리스트 출력
	@RequestMapping(value = "/tring_data_productSearchList", method = RequestMethod.POST)
    public Map<String, Object> productSearchList(@RequestBody ProductVO mvo) throws Exception {
		
		//요청 {}

    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	return result;
    }

	//상품 상세 정보 출력
	@RequestMapping(value = "/tring_data_productDetail", method = RequestMethod.POST)
    public Map<String, Object> productDetail(@RequestBody ProductVO mvo) throws Exception {
		
		//요청 {}

    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	return result;
    }
	
	//상품 구매하기
	@RequestMapping(value = "/tring_data_productBuy", method = RequestMethod.POST)
    public Map<String, Object> productBuy(@RequestBody ProductVO mvo) throws Exception {
		
		//요청 {}

    	final Map<String, Object> result = new HashMap<String, Object>();
    	
    	return result;
    }
}