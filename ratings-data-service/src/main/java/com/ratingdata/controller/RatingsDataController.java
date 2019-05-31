package com.ratingdata.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingdata.model.Rating;
import com.ratingdata.model.UserRating;

@RestController
@RequestMapping("/ratings")
public class RatingsDataController {

	@GetMapping("/{movieId}")
	public Rating getRatings(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
	
	@GetMapping("/users/{userId}")
	public UserRating getUserRatings(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserRatings(getRatingsList());
		return userRating;
	}
	
	private List<Rating> getRatingsList(){
		List<Rating> ratings = new ArrayList<>();
		ratings.add(new Rating("100",4));
		ratings.add(new Rating("101",5));
		return ratings;		
	}
}
