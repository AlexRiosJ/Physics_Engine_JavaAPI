package com.rad.classes;

/**
 * The Boundary class is a singleton that will define the boundaries
 * where the particles/bodies will be involved.
 * 
 * @author Alejandro Ríos, Alfredo Rodríguez, Darío Arias.
 */
public class Boundary {
	
	private double top, left, right, bottom;

	private static Boundary instance = null;
	
	/**
	 * Creates just one instance of the class defining the top, left, right and bottom walls
	 * of the canvas where the particles/bodies will be involved.
	 * @param top canvas roof
	 * @param left left wall of the canvas
	 * @param right right wall of the canvas
	 * @param bottom bottom floor of the canvas
	 */
	private Boundary(double top, double left, double right, double bottom) {
		this.top    = top;
		this.left   = left;
		this.right  = right;
		this.bottom = bottom;
	}
	
	/**
	 * Creates just one instance of the class and returns just that instance once.
	 * If the instances is created before it returns the current and only instance.
	 * @param top canvas roof
	 * @param left left wall of the canvas
	 * @param right right wall of the canvas
	 * @param bottom bottom floor of the canvas
	 * @return The instance of the boundary just once.
	 */
	public static Boundary getInstance(double top, double left, double right, double bottom) {
		if(instance == null) return new Boundary(top, left, right, bottom);
		return instance;
	}

	/**
	 * Returns a boolean value depending on whether the body is within the limits of the Boundary.
	 * @param body the body that will be checked
	 * @return A boolean value
	 */
	public boolean outOfBounds(Body body) {
		double x = body.getPosition().getX(), y = body.getPosition().getY();
		if (x < left || x > right || y < bottom || y > top)
			return true;
		return false;
	}
	
	/**
	 * Sets the top value of the Boundary instance.
	 * @param top
	 */
	public void setTop(double top) {
		this.top = top;
	}

	/**
	 * Sets the left value of the Boundary instance.
	 * @param left
	 */
	public void setLeft(double left) {
		this.left = left;
	}

	/**
	 * Sets the right value of the Boundary instance.
	 * @param right
	 */
	public void setRight(double right) {
		this.right = right;
	}

	/**
	 * Sets the bottom value of the Boundary instance.
	 * @param bottom
	 */
	public void setBottom(double bottom) {
		this.bottom = bottom;
	}
	
	/**
	 * Returns the size of Boundary's top roof.
	 * @return A double value of the top roof
	 */
	public double getTop() {
		return top;
	}

	/**
	 * Returns the size of Boundary's left wall.
	 * @return A double value of the left wall
	 */
	public double getLeft() {
		return left;
	}

	/**
	 * Returns the size of Boundary's right wall.
	 * @return A double value of the right wall
	 */
	public double getRight() {
		return right;
	}

	/**
	 * Returns the size of Boundary's bottom floor.
	 * @return A double value of the bottom floor
	 */
	public double getBottom() {
		return bottom;
	}
	
}