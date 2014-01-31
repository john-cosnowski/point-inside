package com.pi.data;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.pi.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class ProductRepositoryTest {

	@Mock
	private ResourceLoader resourceLoader;
	
	@Mock
	private Resource resource;
	
	@InjectMocks
	private ProductRepository testObj;
	
	@Test
	public void getProducts_GivenInitializedProductRepository_ReturnsJsonProductArray() throws Exception {
		String productsJson = "{\"products\":[{\"upc\":\"123\"}]}";
		String resourceName = "test";
		when(resourceLoader.getResource(resourceName)).thenReturn(resource);
		when(resource.getInputStream()).thenReturn(new ByteArrayInputStream(productsJson.getBytes()));
		
		testObj.setProductsList(resourceName);
		testObj.init();
		List<Product> result = testObj.getProducts();
		
		assertThat(result, is(notNullValue()));
		assertThat(result.size(), is(1));
		assertThat(result.get(0).getUpc(), is("123"));
	}

}
