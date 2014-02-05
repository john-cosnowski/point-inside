package com.pi.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.pi.dao.KeywordsDAO;
import com.pi.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class SearchKeywordControllerTest {
	
	@Mock
	private KeywordsDAO keywordsDAO;

	@InjectMocks
	private SearchKeywordController testObj;
	
	@Test
	public void getAllKeywords_GienThreeKeywords_ReturnsThreeKeywords() {
		Collection<String> keywords = new ArrayList<String>();
		keywords.add("foo");
		keywords.add("bar");
		keywords.add("bell");
		when(keywordsDAO.getAllKeywords()).thenReturn(keywords);
		Collection<String> result = testObj.getAllKeywords();
		assertThat(result.size(), is(3));
		assertThat(result, hasItems("foo", "bar", "bell"));
	}

	@Test
	public void test()  {
		String keyword = "test";
		Product productA = new Product();
		Product productB = new Product();
		Collection<Product> products = new ArrayList<Product>();
		products.add(productA);
		products.add(productB);
		when(keywordsDAO.getProductsByKeyword(keyword)).thenReturn(products);
		Collection<Product> result = testObj.getProductsByKeyword(keyword);
		assertThat(result.size(), is(2));
	}
}
