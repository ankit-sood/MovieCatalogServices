package com.moviecatalog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moviecatalog.model.CatalogItem;
import com.moviecatalog.model.MovieInfo;
import com.moviecatalog.model.UserRating;

@Service
public class MovieCatalogService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<CatalogItem> getCatalog(String userId){
		//get all the rated movies
		UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratings/users/"+userId, UserRating.class);
		//For each movie ID, call movie info service and get details
		List<CatalogItem> catalogs = userRating.getUserRatings().stream().map(rating ->{
			MovieInfo movieInfo = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), MovieInfo.class);
			return new CatalogItem(movieInfo.getName(), movieInfo.getDesc(), rating.getRating());
		}).collect(Collectors.toList());
		return catalogs;
	}
}
