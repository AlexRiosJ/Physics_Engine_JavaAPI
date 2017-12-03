package com.rad.classes;

public class Boundary {
	
	double top, left, right, bottom;

	public Boundary(double top, double left, double right, double bottom) {
		this.top    = top;
		this.left   = left;
		this.right  = right;
		this.bottom = bottom;
	}

	public boolean outOfBounds(Body b) {
		double x = b.getPosition().getX(), y = b.getPosition().getY();
		if (x < left || x > right || y < bottom || y > top)
			return true;
		return false;
	}
}