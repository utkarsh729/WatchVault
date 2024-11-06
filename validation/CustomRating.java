package com.example.utkarsh.watchlist.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CustomRatingLogic.class)
public @interface CustomRating {
	
	
	String message() default "Rating can only be between 0-10";
	
	Class<?> []groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
