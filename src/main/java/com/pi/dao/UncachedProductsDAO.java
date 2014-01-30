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

@Component("productsDAO")
public class UncachedProductsDAO implements ProductsDAO {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Collection<String> getAllCategories() {
		Set<String> allCategories = new HashSet<String>();
		for (Product product : productRepository.getProducts())  {
			allCategories.add(product.getCategory());
		}
		return allCategories;
	}

	@Override
	public Collection<Product> getProductsByCategory(String category) {
		List<Product> products = new ArrayList<Product>();
		for (Product product : productRepository.getProducts())  {
			if (category.equals(product.getCategory()))  {
				products.add(product);
			}
		}
		return products;
	}

	@Override
	public Collection<Product> getProductsByCategoryAndKeyword(String category, String keyword) {
		List<Product> products = new ArrayList<Product>();
		for (Product product : productRepository.getProducts())  {
			if (category.equals(product.getCategory()) && product.getKeywords().contains(keyword))  {
				products.add(product);
			}
		}
		return products;
	}

}
