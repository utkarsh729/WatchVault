package com.example.utkarsh.watchlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.utkarsh.watchlist.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
}
