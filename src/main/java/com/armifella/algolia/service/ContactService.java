package com.armifella.algolia.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.armifella.algolia.dto.Product;

public interface ContactService {

	public List<Product> getContacts(String keyword) throws InterruptedException, ExecutionException;
	
}
