package com.market.service.exception;

import java.io.Serializable;
import java.util.function.Supplier;

public class ResourceNotFoundException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	  public ResourceNotFoundException(String message) {
	    super(message);
	  }

	  static public Supplier<? extends RuntimeException> supply() {
	    return () -> new ResourceNotFoundException("Resource not found.");
	  }

	  static public Supplier<? extends RuntimeException> supply(String message) {
	    return () -> new ResourceNotFoundException("Resource not found: " + message);
	  }
}
