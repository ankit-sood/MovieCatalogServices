package com.ratingdata.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingdata.model.Ratings;

@RestController
@RequestMapping("/ratings")
public class RatingsDataController {

	@RequestMapping("/{movieId}")
	public Ratings getRatings(@PathVariable("movieId") String movieId) {
		return new Ratings(movieId, 4);
	}
}
