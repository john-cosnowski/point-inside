package com.pi.dao;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.pi.data.ProductRepository;
import com.pi.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class UncachedProductsDAOTest {
	
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private UncachedProductsDAO testObj;

	@Test
	public void getAllCategories_GivenTwoCategories_ShouldReturnTwoCategories() {
		List<Product> products = new ArrayList<Product>();
		products.add(buildProduct("foo"));
		products.add(buildProduct("bar"));
		when(productRepository.getProducts()).thenReturn(products);
		Collection<String> result = testObj.getAllCategories();
		assertThat(result, hasItems("foo", "bar"));
	}

	@Test
	public void getAllCategories_GivenDuplicateCategories_ShouldReturnUniqueCategories() {
		List<Product> products = new ArrayList<Product>();
		products.add(buildProduct("foo"));
		products.add(buildProduct("bar"));
		products.add(buildProduct("foo"));
		products.add(buildProduct("bar"));
		when(productRepository.getProducts()).thenReturn(products);
		Collection<String> result = testObj.getAllCategories();
		assertThat(result, hasItems("foo", "bar"));
		assertThat(result.size(), is(2));
	}

	@Test
	public void getProductsByCategory_GivenMoreThanOneCategoryOfProduct_ShouldReturnOnlyCategoryProducts()  {
		List<Product> products = new ArrayList<Product>();
		products.add(buildProduct("foo", "11"));
		products.add(buildProduct("bar", "44"));
		products.add(buildProduct("foo", "88"));
		products.add(buildProduct("bar", "99"));
		when(productRepository.getProducts()).thenReturn(products);
		Collection<Product> result = testObj.getProductsByCategory("foo");
		assertThat(containsUpc(result, "11"), is(true));
		assertThat(containsUpc(result, "99"), is(false));
		assertThat(result.size(), is(2));
		
	}

	@Test
	public void getProductsByCategoryAndKeyword_GivenMoreOneCategoryKeywordOfProduct_ShouldReturnOneProducts()  {
		List<Product> products = new ArrayList<Product>();
		products.add(buildProduct("foo", "test me", "11"));
		products.add(buildProduct("bar", "leave me alone", "44"));
		products.add(buildProduct("foo", "leave me alone", "88"));
		products.add(buildProduct("bar", "test me", "99"));
		when(productRepository.getProducts()).thenReturn(products);
		Collection<Product> result = testObj.getProductsByCategoryAndKeyword("foo", "test");
		assertThat(containsUpc(result, "11"), is(true));
		assertThat(containsUpc(result, "88"), is(false));
		assertThat(result.size(), is(1));
		
	}
	
	private Product buildProduct(String category)  {
		Product product = new Product();
		product.setCategory(category);
		return product;
	}
	
	private Product buildProduct(String category, String upc)  {
		Product product = new Product();
		product.setCategory(category);
		product.setUpc(upc);
		return product;
	}
	
	private Product buildProduct(String category, String title,String upc)  {
		Product product = new Product();
		product.setCategory(category);
		product.setTitle(title);
		product.setUpc(upc);
		return product;
	}
	private boolean containsUpc(Collection<Product> products, String upc)  {
		for (Product product : products)  {
			if (upc.equals(product.getUpc())) {
				return true;
			}
		}
		return false;
	}
}
