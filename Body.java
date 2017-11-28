package com.rad.vf;

public class Body extends Particle {

	public double radius, length, height, coefficientOfRestitution;

	public Body() {
		super(null, null, null, null, 0, 1);
	}

	public Body(Vec2D position, Vec2D velocity, Vec2D accel, Vec2D force, double damp, double mass) {
		super(position, velocity, accel, force, damp, mass);
	}
	
	public Body(double radius,double height, double length, double mass) {
		super(null, null, null, null, 0, mass);
		this.radius=radius;
		this.height=height;
		this.length=length;
	}

	public double getRadius() {
		return radius;
	}

	public double getHeight() {
		return height;
	}

	public double getLength() {
		return length;
	}

	public double getCoefficientOfRestitution() {
		return coefficientOfRestitution;
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

	public void setCoefficientOfRestitution(double coefficientOfRestitution) {
		this.coefficientOfRestitution = coefficientOfRestitution;
	}
	

}