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

import com.pi.dao.KeywordsDAO;
import com.pi.model.Product;

@Controller
@RequestMapping("/search/keyword")
public class SearchKeywordController {

	private static Logger LOG = LoggerFactory.getLogger("com.pi.controller.SearchKeywordController");
	
	@Autowired
	private KeywordsDAO keywordsDAO;
	
	@RequestMapping(method=RequestMethod.GET, headers={"Accept=application/json"})
	public @ResponseBody Collection<String> getAllKeywords()  {
		LOG.info(String.format("SearchKeywordController.getAllKeywords()"));
		return keywordsDAO.getAllKeywords();
	}
	
	@RequestMapping(value="{word}", method=RequestMethod.GET, headers={"Accept=application/json"})
	public @ResponseBody Collection<Product> getProductsByKeyword(@PathVariable("word") String word)  {
		LOG.info(String.format("SearchKeywordController.getProductsByKeyword(%s)", word));
		return keywordsDAO.getProductsByKeyword(word);
	}

}
