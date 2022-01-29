package com.devsuperior.movieflix.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * Serial versin UID.
	 */
	private static final long serialVersionUID = 1L; 
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
