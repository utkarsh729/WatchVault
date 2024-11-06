package com.example.utkarsh.watchlist.entity;

import com.example.utkarsh.watchlist.validation.CustomPriority;
import com.example.utkarsh.watchlist.validation.CustomRating;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private Integer id;
	
	@Column
	@NotBlank(message="Please Enter the title") // validating here ..but we toh validate at controller and view side also..otherwise our
	//application will break
	private String title;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@CustomRating
	private Float rating;
	
	@CustomPriority
	private String priority;
	
	@Size(max = 50, message = "At most 50 characters are allowed")
	private String comments;
	// comments ko sirf humko view side pr he validate krna hai kyuki backend side pr phele he humne handle kr liya hai
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		if(rating==null) {
			this.rating=5.0f;
		}
		else {
			this.rating = rating;
		}
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
