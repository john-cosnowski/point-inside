package com.pi.data;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.pi.model.Product;
import com.pi.model.Products;

/**
 * Provides a list of all available product records.  
 * @author johncosnowski
 *
 */
@Component
public class ProductRepository {

	@Autowired
	private ResourceLoader resourceLoader;
	
	private String productsList;
	
	private List<Product> products;
	
	public void init()  {
		Resource resource = resourceLoader.getResource(productsList);
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Products allProducts = objectMapper.readValue(resource.getInputStream(), Products.class);
			products = allProducts.getProducts();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> getProducts()  {
		return products;
	}

	public void setProductsList(String productsList) {
		this.productsList = productsList;
	}
}
