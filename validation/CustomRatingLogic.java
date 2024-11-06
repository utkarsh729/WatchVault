package com.example.utkarsh.watchlist.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomRatingLogic implements ConstraintValidator<CustomRating, Float>{
	
	@Override
	public boolean isValid(Float value, ConstraintValidatorContext context) {
//		if(value==null) {
////			// Disable the default constraint violation message
////            context.disableDefaultConstraintViolation();
////            // Set a custom message for null value
////            context.buildConstraintViolationWithTemplate("Rating is empty").addConstraintViolation();
//            return false;
// 
//		}
		if(value>=0.0f && value<=10f) {
			return true;
		}
		return false;
	}
}
