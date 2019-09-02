package com.armifella.algolia.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.algolia.search.models.indexing.Query;
import com.algolia.search.models.indexing.SearchResult;
import com.armifella.algolia.dto.Product;
import com.armifella.algolia.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Override
	public List<Product> getContacts(String keyword) throws InterruptedException, ExecutionException, IOException {
		List<Product> productList =  findSuggestions(keyword);
		return productList;
	}

	private void fillData() throws InterruptedException, ExecutionException {
		SearchClient client = DefaultSearchClient.create("LGRVUQI268", "d6b5a2526b847526097c0361fb7488ab");
		SearchIndex<Product> index = client.initIndex("contacts", Product.class);
		
		Product data1 = new Product();
		data1.setName("Bismutol");
		data1.setDescription("Bismutol sin azucar");
		data1.setPrice(50.5);
		
		data1 = new Product();
		data1.setName("Panadol");
		data1.setDescription("Panadol 50gr tableta");
		data1.setPrice(27);
		
		// Async version
		index.saveObjectsAsync(Arrays.asList(data1,data1));
	}
	
	private List<Product> findSuggestions(String keyword) throws InterruptedException, ExecutionException, IOException {
		List<Product> contactList = new ArrayList<>();
		SearchClient client = DefaultSearchClient.create("LGRVUQI268", "d6b5a2526b847526097c0361fb7488ab");
		SearchIndex<Product> index = client.initIndex("products", Product.class);
		
		//Async version
		SearchResult<Product> result = index.searchAsync(new Query(keyword)).get();
		contactList = result.getHits();
		
		client.close();
		
		return contactList;
	}

}
