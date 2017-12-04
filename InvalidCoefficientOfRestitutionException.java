package com.rad.classes;

/**
 * This Class extends RuntimeException because of the management of the
 * coefficient of restitution value.
 * 
 * @author Alejandro Ríos, Darío Arias, Alfredo Rodriguez
 */
public class InvalidCoefficientOfRestitutionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private double wrongNumber = -1;

	/**
	 * Construct an {@link InvalidCoefficientOfRestitutionException} with the value that cause the exception.
	 * @param wrongNumber the value that cause the exception
	 */
	public InvalidCoefficientOfRestitutionException(double wrongNumber) {
		super("InvalidCoefficientOfRestitutionException");
		this.wrongNumber = wrongNumber;
	}
	
	/**
	 * Returns a String with the message of the exception.
	 */
	@Override
	public String toString() {
		return getMessage() + ": Coeffient of restitution must be between 0 and 1: " + wrongNumber;
	}
	
}
