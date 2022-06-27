package com.blogapi.blogapi.exception;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	String resourceName;
	String fieldName;
	Integer fieldValue;
	String fieldValues;
	
	public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue) {
		super(String.format("%s not found with %s : %d",resourceName,fieldName,fieldValue ));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValues) {
		super(String.format("%s not found with %s : %d",resourceName,fieldName,fieldValues ));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValues = fieldValues;
	}
	
}
