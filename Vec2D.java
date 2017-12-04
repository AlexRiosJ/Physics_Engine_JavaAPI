package com.rad.classes;

import java.lang.Math;

/**
 * This class contains methods for creating and manipulating
 * vectors. Vectors are the basis of all the geometry related operations in the
 * engine. A Vector object is of the form { x: 0, y: 0 }.
 * 
 * @author Alejandro Ríos, Darío Arias, Alfredo Rodriguez
 */
public class Vec2D {
	private double x, y;

	// Constructors
	/**
	 * Constructor which initialize a vector whit values x, y.
	 * 
	 * @param x component of the vector
	 * @param y component of the vector
	 * @return Vec2D a new Vec2D
	 */
	public Vec2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * An empty constructor which initialize whit zero values.
	 * @return Vec2D a new Vec2D
	 */
	public Vec2D() {
		this.x = 0;
		this.y = 0;
	}

	// Getters and Setters
	/**
	 * Set the x component of the vector.
	 * 
	 * @param x component of the vector
	 * @return No return but modifies the x component value of the vector
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Set the y component of the vector.
	 * 
	 * @param y component of the vector
	 * @return No return but modifies the y component value of the vector
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Get the x component of the vector.
	 * @return The x component of the vector
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Get the x component of the vector.
	 * @return The x component of the vector
	 */
	public double getY() {
		return y;
	}

	// Class methods
	/**
	 * Adds the two vectors.
	 * @param vectorA the vector A to be added
	 * @param vectorB the vector B to be added
	 * @return A new vector of vectorA and vectorB added
	 */
	public static Vec2D sum(Vec2D vectorA, Vec2D vectorB) {
		return new Vec2D(vectorA.getX() + vectorB.getX(), vectorA.getY() + vectorB.getY());
	}

	/**
	 * Subtracts  the two vectors.
	 * @param vectorA the vector A to be subtracted
	 * @param vectorB the vector B to be subtracted
	 * @return A new vector of vectorA and vectorB subtracted
	 */
	public static Vec2D substract(Vec2D vectorA, Vec2D vectorB) {
		return new Vec2D(vectorA.getX() - vectorB.getX(), vectorA.getY() - vectorB.getY());
	}

	/**
	 * Multiplies a vector and a scalar.
	 * @param vector the vector that will be multiplied by the scalar
	 * @param scalar the scalar that will multiply each component of the vector
	 * @return A new vector multiplied by scalar
	 */
	public static Vec2D multiply(Vec2D vector, double scalar) {
		return new Vec2D(vector.getX() * scalar, vector.getY() * scalar);
	}

	/**
	 * Returns the dot-product of two vectors.
	 * @param vectorA
	 * @param vectorB
	 * @return The dot product of the two vectors
	 */
	public static double dot(Vec2D vectorA, Vec2D vectorB) {
		return vectorA.getX() * vectorB.getX() + vectorA.getY() * vectorB.getY();
	}

	/**
	 * Returns the cross-product of two vectors.
	 * @param vectorA
	 * @param vectorB
	 * @return The cross product of the two vectors
	 */
	public static double cross(Vec2D vectorA, Vec2D vectorB) {
		return vectorA.getX() * vectorB.getY() - vectorA.getY() * vectorB.getX();
	}

	/**
	 * Returns the magnitude of the distance between the vectors.
	 * @param vectorA
	 * @param vectorB
	 * @return The magnitude of the distance between the vectors
	 */
	public static double distanceFrom(Vec2D vectorA, Vec2D vectorB) {
		return Math.sqrt(Math.pow(vectorA.getX() - vectorB.getX(), 2) + Math.pow(vectorA.getY() - vectorB.getY(), 2));
	}

	/**
	 * Returns the magnitude squared of the distance between the vectors.
	 * @param vectorA
	 * @param vectorB
	 * @return The magnitude squared of the distance between the vectors
	 */
	public static double distanceFromSquared(Vec2D vectorA, Vec2D vectorB) {
		return Math.pow(vectorA.getX() - vectorB.getX(), 2) + Math.pow(vectorA.getY() - vectorB.getY(), 2);
	}

	/**
	 * Returns the vector normalized.
	 * @param vector the vector to be normalized
	 * @return A new Vec2D normalized from the vector in the argument.
	 */
	public static Vec2D getNormalized(Vec2D vector) {
		double x = vector.getX(), y = vector.getY();
		double normX = (x / vector.getMagnitude()), normY = (y / vector.getMagnitude());
		return new Vec2D(normX, normY);
	}

	// Object methods

	/**
	 * Returns a boolean value depending if equals or not the vector in the argument.
	 * @param vector the vector to be compared
	 * @return true or false depending if equals or not the vector in the argument
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Vec2D)) return false;
		Vec2D vector = (Vec2D) o;
		return x == vector.getX() && y == vector.getY();
	}

	/**
	 * Updates the current vector adding the values of each component from the vector in the argument.
	 * @param vector the vector to be added to each component
	 */
	public void sum(Vec2D vector) {
		this.x += vector.getX();
		this.y += vector.getY();
	}

	/**
	 * Updates the current vector subtracting the values of each component from the vector in the argument.
	 * @param vector the vector to be subtracted to each component
	 */
	public void substract(Vec2D vector) {
		this.x -= vector.getX();
		this.y -= vector.getY();
	}

	/**
	 * Returns the magnitude (length) of a vector.
	 * @return The magnitude of the vector
	 */
	public double getMagnitude() {
		return Math.sqrt(x * x + y * y);
	}

	/**
	 * Returns the magnitude (length) of a vector (therefore saving a sqrt operation).
	 * @return The squared magnitude of the vector
	 */
	public double getSquaredMagnitude() {
		return (x * x) + (y * y);
	}

	/**
	 * Multiply the vector with the scalar from the argument.
	 * @param scalar the value of the scalar
	 */
	public void multiply(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
	}

	/**
	 * Add a scaled vector to each component from the vector
	 * @param vector the vector to be scaled
	 * @param scale the value of the scalar
	 */
	public void addScaledVector(Vec2D vector, double scale) {
		this.x += vector.getX() * scale;
		this.y += vector.getY() * scale;
	}

	/**
	 * Returns a new vector with the same values of the current vector.
	 * @return Vec2D the copy of the vector
	 */
	@Override
	public Vec2D clone() {
		Vec2D clone = new Vec2D(x, y);
		return clone;
	}

	/**
	 * Returns a String in the format { x: a, y: b } of the current vector values.
	 * @return String the string of the vector values { x: a, y: b }
	 */
	@Override
	public String toString() {
		return String.format("{ x: %.2f, y: %.2f }", x, y);
	}

}
