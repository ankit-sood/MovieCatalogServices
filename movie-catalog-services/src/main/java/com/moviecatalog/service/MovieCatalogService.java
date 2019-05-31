package com.moviecatalog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.moviecatalog.model.CatalogItem;
import com.moviecatalog.model.MovieInfo;
import com.moviecatalog.model.Ratings;

@Service
public class MovieCatalogService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	public List<CatalogItem> getCatalog(String userId){
		//get all the rated movies
		List<Ratings> ratings = getRatingsList();
		
		WebClient.Builder builder = WebClient.builder(); 
		//For each movie ID, call movie info service and get details
		List<CatalogItem> catalogs = ratings.stream().map(rating ->{
			//MovieInfo movieInfo = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), MovieInfo.class);
			MovieInfo movieInfo = webClientBuilder.build().get()
														  .uri("http://localhost:8082/movies/"+rating.getMovieId())
														  .retrieve()
														  .bodyToMono(MovieInfo.class)
														  .block();
			return new CatalogItem(movieInfo.getName(), movieInfo.getDesc(), rating.getRating());
		}).collect(Collectors.toList());
		return catalogs;
	}
	
	private List<Ratings> getRatingsList(){
		List<Ratings> ratings = new ArrayList<>();
		ratings.add(new Ratings("100",4));
		ratings.add(new Ratings("101",5));
		return ratings;		
	}
}
