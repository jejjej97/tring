package com.data.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.Product.mappers";

}
