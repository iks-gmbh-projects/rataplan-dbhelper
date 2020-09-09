package de.iks.rataplan.exceptions;

import org.springframework.http.HttpStatus;

public class DBHelperException extends RuntimeException {

	private static final long serialVersionUID = 5919419690106310306L;
	protected HttpStatus status = HttpStatus.BAD_REQUEST;

	public DBHelperException(String message) {
		this(message, null);
	}

	public DBHelperException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpStatus getHttpStatus() {
		return this.status;
	}
}
