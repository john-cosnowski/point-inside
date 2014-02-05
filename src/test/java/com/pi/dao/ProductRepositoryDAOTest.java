package com.pi.dao;

import java.util.Collection;

import org.mockito.Mock;

import com.pi.data.ProductRepository;
import com.pi.model.Product;

public class ProductRepositoryDAOTest {

	@Mock
	protected ProductRepository productRepository;

	public ProductRepositoryDAOTest() {
		super();
	}

	protected Product buildProduct(String category) {
		Product product = new Product();
		product.setCategory(category);
		return product;
	}

	protected Product buildProduct(String category, String upc) {
		Product product = new Product();
		product.setCategory(category);
		product.setUpc(upc);
		return product;
	}

	protected Product buildProduct(String category, String title, String upc) {
		Product product = new Product();
		product.setCategory(category);
		product.setTitle(title);
		product.setUpc(upc);
		return product;
	}

	protected boolean containsUpc(Collection<Product> products, String upc) {
		for (Product product : products)  {
			if (upc.equals(product.getUpc())) {
				return true;
			}
		}
		return false;
	}

}