package com.pi.controller;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.pi.dao.ProductsDAO;
import com.pi.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class SearchCategoryControllerTest {
	
	@Mock
	private ProductsDAO productsDAO;
	
	@InjectMocks
	private SearchCategoryController testObj;

	@Test
	public void getAllCategories_GivenTwoCategories_ShouldReturnTwoCategories() {
		List<String> categories = new ArrayList<String>();
		categories.add("foo");
		categories.add("bar");
		when(productsDAO.getAllCategories()).thenReturn(categories);
		Collection<String> result = testObj.getAllCategories();
		assertThat(result, hasItems("foo", "bar"));
	}

	@Test
	public void getProductsByCategory_GivenProduct_ShouldReturnProduct()  {
		String category = "foo";
		Product productA = new Product();
		Collection<Product> products = new ArrayList<Product>();
		products.add(productA);
		when(productsDAO.getProductsByCategory(category)).thenReturn(products);
		Collection<Product> result = testObj.getCategoryProducts(category);
		verify(productsDAO, times(1)).getProductsByCategory(category);
		assertThat(result, hasItems(productA));
	}

	@Test
	public void getProductsByCategoryAndKeyword_GivenProduct_ShouldReturnProduct()  {
		String category = "foo";
		String keyword = "test";
		Product productA = new Product();
		Collection<Product> products = new ArrayList<Product>();
		products.add(productA);
		when(productsDAO.getProductsByCategoryAndKeyword(category, keyword)).thenReturn(products);
		Collection<Product> result = testObj.getProductsByCategoryAndKeyword(category, keyword);
		verify(productsDAO, times(1)).getProductsByCategoryAndKeyword(category, keyword);
		assertThat(result, hasItems(productA));
	}
}
