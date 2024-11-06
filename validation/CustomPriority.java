package com.example.utkarsh.watchlist.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomPriorityLogic.class )  // @Constraint annotation tells ..jo hum annotation bana rahe uske kisse validate kr rahe 
public @interface CustomPriority {
	
	String message() default "Priority can be either Low | Medium | High";
	
	Class<?> []groups() default {};
	Class<? extends Payload>[] payload() default {};
	 
	// payload is used to implement the siverity
	}

