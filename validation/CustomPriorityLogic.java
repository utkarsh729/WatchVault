package com.example.utkarsh.watchlist.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomPriorityLogic implements ConstraintValidator<CustomPriority, String>{
	// it takes two parameter "annotation_names" and type of field jispe annotation use kr rahe
	
	// we have to implement isValid method i.e abstract
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		value=value.trim();
	
		if(value.equalsIgnoreCase("low") || value.equalsIgnoreCase("medium") || value.equalsIgnoreCase("high") || value.equals("")){
			return true;
		}
		return false;
	}
}	
