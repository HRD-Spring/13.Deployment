package com.demo.dao.layer;

import java.util.List;

import com.demo.pojo.Products;

public interface ProductsDAO {
	public List<Products> getAllProducts();
	public List<Object[]> getAllProductsSQL();
	
	public boolean deleteProductById(String id);
	public Products getProductByProductId(String id);
}
