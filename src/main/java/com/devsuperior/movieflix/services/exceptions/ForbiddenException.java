package com.devsuperior.movieflix.services.exceptions;

public class ForbiddenException extends RuntimeException {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	public ForbiddenException( String message ) {
		super( message );
	}
}
