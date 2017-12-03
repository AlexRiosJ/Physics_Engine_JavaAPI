package com.rad.classes;

import java.lang.Math;

/**
 * This class contains methods for creating and manipulating
 * vectors. Vectors are the basis of all the geometry related operations in the
 * engine. A Vector object is of the form { x: 0, y: 0 }.
 * 
 * @author Alfredo Rodríguez, Alejandro Ríos, Darío Arias
 */
public class Vec2D {
	private double x, y;

	// Constructors
	/**
	 * Constructor which initialize a vector whit values x, y.
	 * 
	 * @param double
	 *            x component, y component
	 * @return Vec2D a new Vec2D
	 */
	public Vec2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * An empty constructor which initialize whit zero values.
	 * 
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
	 * @param x
	 * @return No return but modifies the x component value of the vector
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Set the y component of the vector.
	 * 
	 * @param y
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
	 * @param vectorA
	 * @param vectorB
	 * @return A new vector of vectorA and vectorB added
	 */
	public static Vec2D sum(Vec2D vectorA, Vec2D vectorB) {
		return new Vec2D(vectorA.getX() + vectorB.getX(), vectorA.getY() + vectorB.getY());
	}

	/**
	 * Subtracts  the two vectors.
	 * @param vectorA
	 * @param vectorB
	 * @return A new vector of vectorA and vectorB subtracted
	 */
	public static Vec2D substract(Vec2D vectorA, Vec2D vectorB) {
		return new Vec2D(vectorA.getX() - vectorB.getX(), vectorA.getY() - vectorB.getY());
	}

	/**
	 * Multiplies a vector and a scalar.
	 * @param vector
	 * @param scalar
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
	 * Returns the magnitude of the distance bet
	 * @param vectorA
	 * @param vectorB
	 * @return
	 */
	public static double distanceFrom(Vec2D vectorA, Vec2D vectorB) {
		return Math.sqrt(Math.pow(vectorA.getX() - vectorB.getX(), 2) + Math.pow(vectorA.getY() - vectorB.getY(), 2));
	}

	/**
	 * Retorna un número real con la distancia entre dos vectores al cuadrado.
	 * Ahorra tiempo de ejecución.
	 * 
	 * @param Vec2D
	 *            vector1, Vec2D vector2
	 * @return double distancia cuadrada
	 */
	public static double distanceFromSquared(Vec2D vectorA, Vec2D vectorB) {
		return Math.pow(vectorA.getX() - vectorB.getX(), 2) + Math.pow(vectorA.getY() - vectorB.getY(), 2);
	}

	/**
	 * Retorna un vector normalizado.
	 * 
	 * @param Vec2D
	 *            vector a normalizar
	 * @return Vec2D vector normalizado
	 */
	public static Vec2D getNormalized(Vec2D vector) {
		double x = vector.getX(), y = vector.getY();
		double normX = (x / vector.getMagnitude()), normY = (y / vector.getMagnitude());
		return new Vec2D(normX, normY);
	}

	// Métodos de objeto

	/**
	 * Retorna si un vector es igual al actual.
	 * 
	 * @param Vec2D
	 *            another,
	 * @return boolean true o false
	 */
	public boolean equals(Vec2D another) {
		return x == another.getX() && y == another.getY();
	}

	/**
	 * Actualiza el vector con los valores de la suma de los elementos x,y de un
	 * vector B.
	 * 
	 * @param Vec2D
	 *            vectorB
	 * @return nada porque los cambios son internos
	 */
	public void sum(Vec2D vectorB) {
		this.x += vectorB.getX();
		this.y += vectorB.getY();
	}

	/**
	 * Actualiza el vector con los valores de la resta de los elementos x,y de un
	 * vector B.
	 * 
	 * @param Vec2D
	 *            vectorB
	 * @return nada porque los cambios son internos
	 */
	public void substract(Vec2D vectorB) {
		this.x -= vectorB.getX();
		this.y -= vectorB.getY();
	}

	/**
	 * Retorna la magnitud del vector actual.
	 * 
	 * @return double magnitud
	 */
	public double getMagnitude() {
		return Math.sqrt(x * x + y * y);
	}

	/**
	 * Retorna la magnitud cuadrada del vector actual, ahorrando tiempo de
	 * ejecución.
	 * 
	 * @return double magnitud cuadrada
	 */
	public double getSquaredMagnitude() {
		return (x * x) + (y * y);
	}

	/**
	 * Actualiza el vector con los valores de la multiplicación de los elementos x,y
	 * por un escalar.
	 * 
	 * @param double
	 *            scalar
	 * @return nada porque los cambios son internos
	 */
	public void multiply(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
	}

	/**
	 * Actualiza el vector con los valores de la suma de los elementos x,y de un
	 * vector B, los cuales fueron multiplicados por un escalar.
	 * 
	 * @param Vec2D
	 *            vector, double scale
	 * @return nada porque los cambios son internos
	 */
	public void addScaledVector(Vec2D vector, double scale) {
		this.x += vector.getX() * scale;
		this.y += vector.getY() * scale;
	}

	/**
	 * Retorna un vector con los mismos valores que el actual.
	 * 
	 * @return Vec2D vector clonado
	 */
	public Vec2D clone() {
		Vec2D clone = new Vec2D(x, y);
		return clone;
	}

	/**
	 * Retorna una cadena con la posición en formato <x,y> del vector.
	 * 
	 * @return String con las componentes del vector
	 */
	public String toString() {
		return String.format("{ x: %.2f, y: %.2f }", x, y);
	}

}
