package com.rad.classes;

public class Body extends Particle {

	public double radius, length, height;

	public Body() {
		super(null, null, null, null, 0, 1);
	}

	public Body(Vec2D position, Vec2D velocity, Vec2D acceleration, Vec2D force, double damp, double mass) {
		super(position, velocity, acceleration, force, damp, mass);
	}

	public Body(double radius, double height, double length, double mass) {
		super(null, null, null, null, 0, mass);
		this.radius = radius;
		this.height = height;
		this.length = length;
	}

	public double getRadius() {
		return radius;
	}
	
	public double getArea() {
		return Math.PI * this.radius * this.radius;
	}
	
	public double getDensity() {
		return this.getMass() / this.getArea();
	}

	public double getHeight() {
		return height;
	}

	public double getLength() {
		return length;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
}
