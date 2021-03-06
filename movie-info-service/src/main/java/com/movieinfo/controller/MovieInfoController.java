package com.movieinfo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieinfo.model.MovieInfo;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

	@RequestMapping("/{movieId}")
	public MovieInfo getMovieInfo(@PathVariable("movieId") String movieId) {
		return new MovieInfo(movieId,"Shawshank","Story of innocent man");
	}
}
