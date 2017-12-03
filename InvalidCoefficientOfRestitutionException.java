package com.rad.classes;

public class InvalidCoefficientOfRestitutionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private double wrongNumber = -1;
	
	public InvalidCoefficientOfRestitutionException(double wrongNumber) {
		super("InvalidCoefficientOfRestitutionException");
		this.wrongNumber = wrongNumber;
	}
	
	@Override
	public String toString() {
		return getMessage() + ": Coeffient of restitution must be between 0 and 1: " + wrongNumber;
	}
	
}
