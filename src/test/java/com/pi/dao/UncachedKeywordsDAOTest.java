package com.pi.dao;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.hasItems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.pi.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class UncachedKeywordsDAOTest extends ProductRepositoryDAOTest {
	
	@InjectMocks
	private UncachedKeywordsDAO testObj;
	
	@Before
	public void setup()  {
		List<Product> products = new ArrayList<Product>();
		products.add(buildProduct("test", "foo bar", "1"));
		products.add(buildProduct("test", "foo", "2"));
		products.add(buildProduct("test", "bar bell", "3"));
		when(productRepository.getProducts()).thenReturn(products);
	}

	@Test
	public void getAllKeywords_GivenKeywords_ReturnsOnlySingleItemPerKeyword() {
		Collection<String> result = testObj.getAllKeywords();
		assertThat(result.size(), is(3));
		assertThat(result, hasItems("foo", "bar", "bell"));
	}

	@Test
	public void getProductsByKeyword_GivenBarKeyword_ReturnTwoProducts()  {
		Collection<Product> result = testObj.getProductsByKeyword("bar");
		assertThat(result.size(), is(2));
		assertThat(containsUpc(result, "1"), is(true));
		assertThat(containsUpc(result, "2"), is(false));
		assertThat(containsUpc(result, "3"), is(true));
	}

	@Test
	public void getProductsByKeyword_GivenBellKeyword_ReturnOneProduct()  {
		Collection<Product> result = testObj.getProductsByKeyword("bell");
		assertThat(result.size(), is(1));
		assertThat(containsUpc(result, "1"), is(false));
		assertThat(containsUpc(result, "2"), is(false));
		assertThat(containsUpc(result, "3"), is(true));
	}
}
