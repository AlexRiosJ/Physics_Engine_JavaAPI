package com.rad.classes;

/**
 * The Body class contains methods for creating and manipulating body
 * models. A Body is a rigid body that can be simulated by an engine.
 * 
 * @author Alejandro Ríos, Darío Arias, Alfredo Rodriguez
 */
public class Body extends Particle {

	public double radius, length, height;

	/**
	 * Initializes a newly created {@code Body} object with null elements 
	 * but mass with value 1.
	 */
	public Body() {
		super(null, null, null, null, 0, 1);
	}

	/**
	 * Initializes a newly created {@code Body} object setting the elements from the parameters.
	 * @param position a position vector
	 * @param velocity a velocity vector
	 * @param acceleration an acceleration vector
	 * @param force a force vector
	 * @param damp a damp value
	 * @param mass a mass value
	 */
	public Body(Vec2D position, Vec2D velocity, Vec2D acceleration, Vec2D force, double damp, double mass) {
		super(position, velocity, acceleration, force, damp, mass);
	}

	/**
	 * Initializes a newly created {@code Body} object setting the elements from the parameters.
	 * @param radius the radius of the circle
	 * @param height the height in case the Body is a rectangle
	 * @param length the length in case the Body is a rectangle
	 * @param mass the mass value
	 */
	public Body(double radius, double height, double length, double mass) {
		super(null, null, null, null, 0, mass);
		this.radius = radius;
		this.height = height;
		this.length = length;
	}

	/**
	 * Returns the radius value of the Body.
	 * @return The value of the Body's radius.
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Returns the height value of the Body.
	 * @return The value of the Body's height.
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Returns the length value of the Body.
	 * @return The value of the Body's length.
	 */
	public double getLength() {
		return length;
	}
	
	/**
	 * Returns the area value of the circle.
	 * @return The value of the Body's area if it is a circle.
	 */
	public double getArea() {
		return Math.PI * this.radius * this.radius;
	}

	/**
	 * Returns the density value of the Body.
	 * @return The value of the Body's density.
	 */
	public double getDensity() {
		return this.getMass() / this.getArea();
	}

	/**
	 * Sets the value of the Body's radius.
	 * @param radius the value of the radius
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Sets the value of the Body's length.
	 * @param radius the value of the length
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * Sets the value of the Body's height.
	 * @param radius the value of the height
	 */
	public void setHeight(double height) {
		this.height = height;
	}

}
