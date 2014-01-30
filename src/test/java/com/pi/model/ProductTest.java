package com.pi.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class ProductTest {

	@Test
	public void getKeywords_GivenTwoWords_ShouldContainKeyword() {
		Product product = new Product();
		product.setTitle("foo bar");
		assertThat(product.getKeywords().contains("foo"), is(true));
	}

	@Test
	public void getKeywords_GivenOneWord_ShouldContainWord() {
		Product product = new Product();
		product.setTitle("foobar");
		assertThat(product.getKeywords().contains("foobar"), is(true));
	}

}
