package com.ravi.ratingdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.ratingdataservice.model.Rating;
import com.ravi.ratingdataservice.model.UserRating;

@RestController
@RequestMapping("/reating")
public class RatingResource {

	@RequestMapping("/movies/{movieId}")
	public Rating getMovieRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	@RequestMapping("/user/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", 4), 
				new Rating("1235", 3));
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;

	}
}
