package com.example.utkarsh.watchlist.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RatingService {
// if movie is present on imdb no matter what rating user has entered ..it will display the imdb rating only
//	String apiUrl="https://omdbapi.com/?t=_moviename_&apikey=fc8e741";  yaha pr string banane se ak he movie ki rating sab movie mai aayengi
	// this is wrong ..for eg you submit Tiger then api url according to function https://omdbapi.com/?t=Tiger&apikey=fc8e741
	// but when you submit another movie Gadar api url will not change it will fetch the tiger rating for gadar
	
	public  float getMovieRating(String title) {
		
		try {
			// try fetch moving rating by calling omdb api
			RestTemplate restTemplate=new RestTemplate();  // request
			// RestTemplate act as web client/postman/web browser to call the api
			
			// will call apiurl +title
			String apiUrl="https://omdbapi.com/?t=_moviename_&apikey=fc8e741";
			apiUrl=apiUrl.replace("_moviename_", title);
			
			//we will get the json object 
			// response entity is used to fetch the object of any type ..it is wrapper
			//here we are fetching ObjectNode type object which is up of json in heirarchy 
			ResponseEntity<ObjectNode> responseEntity=restTemplate.getForEntity(apiUrl, ObjectNode.class);  // response
			// getForEntity takes two types of parameter ..first api url and type of object
			
			ObjectNode jsonObject=responseEntity.getBody();  // json ki body nikal li hai yaha pr humne
			
			// we will get whole details of movie ..but we want only the imdb rating so for that
			System.out.println(jsonObject.path("imdbRating").asText());
			
			return (float)jsonObject.path("imdbRating").asDouble();   // if movie not found it will return 0.0 by default
			
		}
		catch(Exception e) {
			// if movie not present on imdb it will return the rating input by the user
			System.out.println("Either movie name is not available or api service is down"+e.getMessage());
			
			return 0.0f;
		}
	}
}
