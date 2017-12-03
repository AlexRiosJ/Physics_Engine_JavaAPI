package com.rad.classes;

public class NegativeMassException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private double negativeNumber = -1;
	
	public NegativeMassException(double negativeNumber) {
		super("NegativeMassException");
		this.negativeNumber = negativeNumber;
	}
	
	@Override
	public String toString() {
		return getMessage() + ": Negative mass is not possible with a body: " + negativeNumber;
	}
	
}
