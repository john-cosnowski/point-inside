package com.pi.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pi.data.ProductRepository;
import com.pi.model.Product;

@Component("keywordsDAO")
public class UncachedKeywordsDAO implements KeywordsDAO {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Collection<String> getAllKeywords() {
		Set<String> keywords = new HashSet<String>();
		for (Product product : productRepository.getProducts())  {
			keywords.addAll(product.getKeywords());
		}
		return keywords;
	}

	@Override
	public Collection<Product> getProductsByKeyword(String keyword) {
		List<Product> products = new ArrayList<Product>();
		for (Product product : productRepository.getProducts())  {
			if (product.getKeywords().contains(keyword)) {
				products.add(product);
			}
		}
		return products;
	}

}
