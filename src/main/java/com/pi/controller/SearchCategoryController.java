package com.pi.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pi.dao.ProductsDAO;
import com.pi.model.Product;

@Controller
@RequestMapping("/search/category")
public class SearchCategoryController {
	
	private static Logger LOG = LoggerFactory.getLogger("com.pi.controller.SearchCategoryController");
	
	@Autowired
	private ProductsDAO productsDAO;
	
	@RequestMapping(method=RequestMethod.GET, headers={"Accept=application/json"})
	public @ResponseBody Collection<String> getAllCategories()  {
		LOG.info("getAllCategories()");
		return productsDAO.getAllCategories();
	}

	@RequestMapping(value="{category}", method=RequestMethod.GET, headers={"Accept=application/json"})
	public @ResponseBody Collection<Product> getCategoryProducts(@PathVariable("category") String category)  {
		LOG.info(String.format("getCategoryProducts(%s)", category));
		return productsDAO.getProductsByCategory(category);
	}

	@RequestMapping(value="{category}/keyword/{word}", method=RequestMethod.GET, headers={"Accept=application/json"})
	public @ResponseBody Collection<Product> getProductsByCategoryAndKeyword(
			@PathVariable("category") String category, @PathVariable("word") String word)  {
		LOG.info(String.format("getCategoryProducts(%s, %s)", category, word));
		return productsDAO.getProductsByCategoryAndKeyword(category, word);
	}
}
