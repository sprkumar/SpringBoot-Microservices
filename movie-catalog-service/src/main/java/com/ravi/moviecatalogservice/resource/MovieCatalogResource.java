package com.ravi.moviecatalogservice.resource;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ravi.moviecatalogservice.model.CatalogItem;
import com.ravi.moviecatalogservice.model.Movie;
import com.ravi.moviecatalogservice.model.Rating;
import com.ravi.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating ratings = restTemplate.getForObject("http://localhost:9092/reating/user/" + userId,
				UserRating.class);

		return ratings.getUserRating().stream().map(rating -> {
			// for each movieId call movie info service and get details
			Movie movie = restTemplate.getForObject("http://localhost:9091/movie/" + rating.getMovieId(), Movie.class);
			// put all together
			return new CatalogItem(movie.getName(), "salman khan and Arbaaz khan", rating.getRating());
		}).collect(Collectors.toList());

	}

}
/*
 Alternative
 // Async way call
  WebClient way Movie movie = webClientBuilder.build()
  .get()
  .uri("http://localhost:8082/movies/"+ rating.getMovieId())
  .retrieve()
  .bodyToMono(Movie.class)
  
  .block(); // adding .block() again sysn call
 */

// Spring cloud use client side discovery i.e Discovery server (client side discovery mean mediator between client and server1,2,3....)
