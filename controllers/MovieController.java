package com.example.utkarsh.watchlist.controllers;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.utkarsh.watchlist.entity.Movie;
import com.example.utkarsh.watchlist.services.DatabaseServices;

import jakarta.validation.Valid;

@RestController
public class MovieController {

	@Autowired
	DatabaseServices databaseService;
	
	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchListForm(@RequestParam(required = false) Integer id){
		
		System.out.println(id);
		
		String viewName="watchlistItemForm";
		
		// object is parent of all type
		Map<String, Object> model =new HashMap<>();
		
//		Movie dummyMovie=new Movie();
//		dummyMovie.setTitle("DummyMovie");
//		dummyMovie.setRating(0);
//		dummyMovie.setPriority("default");
//		dummyMovie.setComments("dummyMovie"); 
		
//		for put default values
		
//		model.put("watchlistItem", dummyMovie);
		
		if(id==null) {
			model.put("watchlistItem", new Movie());
		}
		else {		
			model.put("watchlistItem", databaseService.findMovie(id));
		}
//		if(id==null) {
//			model.put("movie", new Movie());
//		}
//		else {		
//			model.put("movie", databaseService.findMovie(id));
//		}
		
		// ModelAndView return two things model(data) and view(html page)
		return new ModelAndView(viewName, model);
	}
	
	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistForm(@Valid @ModelAttribute("watchlistItem") Movie movie, BindingResult bindingResult) {
//	public ModelAndView submitWatchlistForm(@Valid Movie movie, BindingResult bindingResult) {
		
		//we are validating movie on backend and we have to validate watchlistItem on frontend 
		// modelattribute is use to map command object with another object ..here we mapping with watchlistItem
// if we take mapping variable and backend command object reference different so we have explicitly tell spring to map both with @ModelAttribute
	
		// BindingResult is use to catch error it is same as try catch
		if(bindingResult.hasErrors()) {  // till here we have catched the error 
			// if errors are there..redisplay the page with message
			return new ModelAndView("watchlistItemForm");

		}
		
		Integer id=movie.getId();
		if(id==null) {
			databaseService.create(movie);
		}
		else {
			databaseService.update(movie,id);
		}
		
		RedirectView rd=new RedirectView();
		rd.setUrl("/watchlist"); 
		// as you press submit button it will redirect to watchlist
		
		return new ModelAndView(rd);
	}
	
	@GetMapping("/watchlist") 
	public ModelAndView getWatchlist() {
//		public ModelAndView getWatchlist(@RequestParam(required = false) Integer id) {
		// you can delete movie from watch list in this way also but this is not considered as good practice 
		// because here you are handling delete mapping in get mapping ..not good
		
//		if(id!=null) {
//			databaseService.deleteItem(id);
//		}
		
		String viewName="watchlist";
		Map<String, Object> model=new HashMap<>();
		
//		model.put("watchlistRows", databaseService.getAllMovies());
		List<Movie> movieList=databaseService.getAllMovies();
		model.put("watchlistRows",movieList);
		
		model.put("noOfMovies", movieList.size());
		return new ModelAndView(viewName, model);
		
	}
	
//	@DeleteMapping("/watchlist/delete") will not work @DeleteMapping: Requires a true HTTP DELETE request, which an HTML form cannot produce.
	
	
	@PostMapping("/watchlist/delete")
	public RedirectView deleteWatchlistItem(@RequestParam Integer id) {
		
	    databaseService.deleteItem(id);

	    RedirectView redirectView = new RedirectView();
	    redirectView.setUrl("/watchlist");
	    return redirectView;
	}

	
	
	
	
	
}
