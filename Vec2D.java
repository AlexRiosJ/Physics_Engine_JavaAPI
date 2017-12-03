package com.rad.classes;

import java.lang.Math;

/**
 * Esta clase contiene los elementos escenciales de un vector, así como
 * operaciones vectoriales.
 * 
 * @author Alfredo Rodríguez, Alejandro Ríos, Darío Arias
 */
public class Vec2D {
	private double x, y;

	// Constructores
	/**
	 * Constructor que inicializa un vector en valores x, y.
	 * 
	 * @param double
	 *            x, y
	 * @return Vec2D
	 */
	public Vec2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor vacío que inicializa en ceros.
	 * 
	 * @return Vec2D
	 */
	public Vec2D() {
		this.x = 0;
		this.y = 0;
	}

	// Getters y Setters
	/**
	 * Cambia el valor en x del vector
	 * 
	 * @param double
	 *            valor en x
	 * @return No devuelve nada porque las modificaciones son internas
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Cambia el valor en y del vector
	 * 
	 * @param double
	 *            valor en y
	 * @return No devuelve nada porque las modificaciones son internas.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Retorna el valor en x del vector.
	 * 
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Retorna el valor en y del vector.
	 * 
	 * @return double valor en y
	 */
	public double getY() {
		return y;
	}

	// Métodos de clase
	/**
	 * Retorna un nuevo vector inicializado con el resultado de la suma de dos
	 * vectores.
	 * 
	 * @param Vec2D
	 *            vector1, Vec2D vector2
	 * @return Vec2D vector con resultado de suma
	 */
	public static Vec2D sum(Vec2D vectorA, Vec2D vectorB) {
		return new Vec2D(vectorA.getX() + vectorB.getX(), vectorA.getY() + vectorB.getY());
	}

	/**
	 * Retorna un nuevo vector inicializado con el resultado de la resta de dos
	 * vectores.
	 * 
	 * @param Vec2D
	 *            vector1, Vec2D vector2
	 * @return Vec2D vector con resultado de resta
	 */
	public static Vec2D substract(Vec2D vectorA, Vec2D vectorB) {
		return new Vec2D(vectorA.getX() - vectorB.getX(), vectorA.getY() - vectorB.getY());
	}

	/**
	 * Retorna un nuevo vector inicializado con el resultado de la multiplicación de
	 * un vector por un escalar.
	 * 
	 * @param Vec2D
	 *            vector a multiplicar, double escalar
	 * @return Vec2D vector multiplicado
	 */
	public static Vec2D multiply(Vec2D vector, double scalar) {
		return new Vec2D(vector.getX() * scalar, vector.getY() * scalar);
	}

	/**
	 * Retorna un escalar con el resultado del producto punto de dos vectores.
	 * 
	 * @param Vec2D
	 *            vector1, Vec2D vector2
	 * @return double resultado producto punto
	 */

	public static double dot(Vec2D vectorA, Vec2D vectorB) {
		return vectorA.getX() * vectorB.getX() + vectorA.getY() * vectorB.getY();
	}

	/**
	 * Retorna un nuevo vector inicializado con el resultado del producto cruz de
	 * dos vectores.
	 * 
	 * @param Vec2D
	 *            vector1, Vec2D vector2
	 * @return Vec2D resultado producto cruz
	 */
	public static double cross(Vec2D vectorA, Vec2D vectorB) {
		return vectorA.getX() * vectorB.getY() - vectorA.getY() * vectorB.getX();
	}

	/**
	 * Retorna un número real con la distancia entre dos vectores.
	 * 
	 * @param Vec2D
	 *            vector1, Vec2D vector2
	 * @return double distancia
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
		return String.format("Vector at: <%.2f ,%.2f>", x, y);
	}

}
