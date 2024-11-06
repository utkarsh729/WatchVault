package com.example.utkarsh.watchlist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.utkarsh.watchlist.entity.Movie;
import com.example.utkarsh.watchlist.repositories.MovieRepository;

@Service   //Factory class
public class DatabaseServices {
	
	@Autowired
	MovieRepository movieRepo;
	@Autowired
	RatingService ratingService;
	public void create(Movie movie) {
	
//		movie.setRating(ratingService.getMovieRating(movie.getTitle()));
		
		Float rating=ratingService.getMovieRating(movie.getTitle());

		if(rating!=0.0) {
			movie.setRating(rating);
		}
		
		if(movie.getPriority()=="") {
			if(movie.getRating()<=4.5f) {
				movie.setPriority("Low");
			}
			if(movie.getRating()>=4.5f && movie.getRating()<=7f) {
				movie.setPriority("Medium");
			}
			else {
				movie.setPriority("High");
			}
		}
		
		
		movieRepo.save(movie);
	}
	
	public List<Movie> getAllMovies(){
		
		return movieRepo.findAll();
		// findAll() will return all the movies in the form of list
	}
	
	public Movie findMovie(Integer id) {
		return movieRepo.findById(id).get();
	}
	
	public void update(Movie movie, Integer id) {
		Movie updateMovie= findMovie(id);
		
		updateMovie.setTitle(movie.getTitle());
		updateMovie.setRating(movie.getRating());
		updateMovie.setPriority(movie.getPriority());
		updateMovie.setComments(movie.getComments());
		
//		movieRepo.save(updateMovie);
		create(updateMovie);
	}
	
	public void deleteItem(Integer id) {

		movieRepo.deleteById(id);
	}
}
