package com.pi.dao;

import java.util.Collection;

import com.pi.model.Product;

public interface ProductsDAO {

	Collection<String> getAllCategories();
	
	Collection<Product> getProductsByCategory(String category);
	
	Collection<Product> getProductsByCategoryAndKeyword(String category, String keyword);
}
