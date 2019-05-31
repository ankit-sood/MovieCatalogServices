package com.moviecatalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviecatalog.model.CatalogItem;
import com.moviecatalog.service.MovieCatalogService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private MovieCatalogService movieCatalogService;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		return movieCatalogService.getCatalog(userId);
		//return Collections.singletonList(new CatalogItem("Prison Break", "Story of an Inncocent man", 4));
	}

}
