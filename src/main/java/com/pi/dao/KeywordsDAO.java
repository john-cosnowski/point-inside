package com.pi.dao;

import java.util.Collection;

import com.pi.model.Product;

public interface KeywordsDAO {
	
	Collection<String> getAllKeywords();
	
	Collection<Product> getProductsByKeyword(String keyword);

}
