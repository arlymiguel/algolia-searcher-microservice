package com.armifella.algolia.rest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armifella.algolia.dto.Product;
import com.armifella.algolia.service.ContactService;

@RestController
@RequestMapping("/contacts-searcher")
public class SearcherRestController {

	
	private ContactService contactService;
	
	
	@Autowired
	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}


	@CrossOrigin("*")
	@GetMapping(path = "/{keyword}")
	public List<Product> getContacts(@PathVariable(value = "keyword")String keyword) throws InterruptedException, ExecutionException , IOException {
		System.out.println("###keyword :" + keyword);
		return contactService.getContacts(keyword);
	}
	
	
}
