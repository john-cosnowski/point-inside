package com.pi.controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pi.dao.ProductsDAO;
import com.pi.model.Product;

@Controller
@Path("/search/category")
@Produces(APPLICATION_JSON)
public class SearchCategoryController {
	
	private static Logger LOG = LoggerFactory.getLogger("com.pi.controller.SearchCategoryController");
	
	@Autowired
	private ProductsDAO productsDAO;
	
	@GET
	public Collection<String> getAllCategories()  {
		LOG.info("getAllCategories()");
		return productsDAO.getAllCategories();
	}

	@GET
	@Path("{category}")
	public Collection<Product> getCategoryProducts(@PathParam("category") String category)  {
		LOG.info(String.format("getCategoryProducts(%s)", category));
		return productsDAO.getProductsByCategory(category);
	}

	@GET
	@Path("{category}/keyword/{word}")
	public Collection<Product> getProductsByCategoryAndKeyword(
			@PathParam("category") String category, @PathParam("word") String word)  {
		LOG.info(String.format("getCategoryProducts(%s, %s)", category, word));
		return productsDAO.getProductsByCategoryAndKeyword(category, word);
	}
}
