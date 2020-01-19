package com.ravi.movieinfoservice.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/movie")
public class MoviesService {

	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		return new Movie(movieId, "Hello Brother");
	}
}
