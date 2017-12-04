package com.rad.classes;

/**
 * This Class extends RuntimeException because of the 
 * management of the particle mass value.
 * 
 * @author Alejandro Ríos, Darío Arias, Alfredo Rodriguez
 */
public class NegativeMassException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private double negativeNumber = -1;
	
	/**
	 * Construct an {@link NegativeMassException} with the value that cause the exception.
	 * @param wrongNumber the value that cause the exception
	 */
	public NegativeMassException(double negativeNumber) {
		super("NegativeMassException");
		this.negativeNumber = negativeNumber;
	}
	
	/**
	 * Returns a String with the message of the exception.
	 */
	@Override
	public String toString() {
		return getMessage() + ": Negative mass is not possible with a body: " + negativeNumber;
	}
	
}
