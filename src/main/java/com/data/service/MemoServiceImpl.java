package com.data.service;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.data.dao.MemoDAO;


@Service
public class MemoServiceImpl implements MemoService{
	
	@Inject
	private MemoDAO dao;
	
}
