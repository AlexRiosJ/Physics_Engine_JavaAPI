package com.rad.vf;
import java.lang.Math;

public class Vec2D {
	private double x, y;
	//Constructores
	public Vec2D(double x, double y){
		this.x = x;
		this.y = y;
	}
		
	public Vec2D() {
		this.x = 0;
		this.y = 0;
	}

	//Getters y Setters
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public static double getAngle(Vec2D vecA, Vec2D vecB) {
		//angle in radians
		return Math.atan2(vecB.getY() - vecA.getY(), vecB.getX() - vecA.getX());
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	
	//Métodos de clase
	public static Vec2D sum(Vec2D vecA, Vec2D vecB){
		return new Vec2D(vecA.getX() + vecB.getX(), vecA.getY() + vecB.getY());
	}
		
	public static Vec2D substract(Vec2D vecA, Vec2D vecB){
		return new Vec2D(vecA.getX() - vecB.getX(), vecA.getY() - vecB.getY());
	}
	
	public static Vec2D multiply(Vec2D vector, double scalar){
		return new Vec2D(vector.getX() * scalar, vector.getY() * scalar);
	}
	
	public static double dot(Vec2D vecA, Vec2D vecB) {
		return vecA.getX() * vecB.getX() + vecA.getY() * vecB.getY();
	}
	
	public static double cross(Vec2D vecA, Vec2D vecB) {
		return vecA.getX() * vecB.getY() - vecA.getY() * vecB.getX();
	}
	
	public static double distanceFrom(Vec2D vecA, Vec2D vecB) {
		return vecA.getMagnitude() - vecB.getMagnitude();
	}
	
	public static double distanceFromSquared(Vec2D vecA, Vec2D vecB) {
		return vecA.getSquaredMagnitude() - vecB.getSquaredMagnitude();
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Vec2D)) return false;
		Vec2D foreign=(Vec2D)o;
		return	x == foreign.getX() && y == foreign.getY();
	}
	
	public static Vec2D getNormalized(Vec2D vector) {
		double x     = vector.getX(), y = vector.getY();
		double normX = (x / vector.getMagnitude()), normY = (y / vector.getMagnitude());
		return new Vec2D(normX, normY);
	}
	
	//Métodos de objeto
	
	// POSIBILIDAD DE AÑADIR ESTE MÉTODO DE OBJETO (RIOS)
//	public void normalize() {
//		double m = this.getMagnitude();
//		this.x /= m;
//		this.y /= m;
//	}
	
	public void sum(Vec2D vecB){
		 this.x += vecB.getX();
		 this.y += vecB.getY();
	}
	public double getMagnitude() {
		return Math.sqrt((x * x) + (y * y));		
	}
	
	public double getSquaredMagnitude() {
		return (x * x) + (y * y);
	}
	
	public void substract(Vec2D vecB){
		 this.x -= vecB.getX();
		 this.y -= vecB.getY();
	}
	
	public void multiply(double scalar){
		this.x *= scalar;
		this.y *= scalar;
	}
	
	public void addScaledVector(Vec2D vec, double scale) {
		this.x += vec.getX() * scale;
		this.y += vec.getY() * scale;
	}
	
	public Vec2D clone() {
		Vec2D clone = new Vec2D(x, y);
		return clone;
	}
	
	public String toString() {
		return String.format("Vector at: <%.2f ,%.2f>", x, y);
	}
}
