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

import com.pi.dao.KeywordsDAO;
import com.pi.model.Product;

@Controller
@Path("/search/keyword")
@Produces(APPLICATION_JSON)
public class SearchKeywordController {

	private static Logger LOG = LoggerFactory.getLogger("com.pi.controller.SearchKeywordController");
	
	@Autowired
	private KeywordsDAO keywordsDAO;
	
	@GET
	public Collection<String> getAllKeywords()  {
		LOG.info(String.format("SearchKeywordController.getAllKeywords()"));
		return keywordsDAO.getAllKeywords();
	}
	
	@Path("{word}")
	public Collection<Product> getProductsByKeyword(@PathParam("word") String word)  {
		LOG.info(String.format("SearchKeywordController.getProductsByKeyword(%s)", word));
		return keywordsDAO.getProductsByKeyword(word);
	}

}
