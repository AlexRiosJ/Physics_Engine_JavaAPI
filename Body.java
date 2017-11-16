package com.rad.vf;

public class Body extends Particle {

	public double radius, height, mass, coefficientOfRestitution;
	
	public Body(double mass,double radius,double coefficient){
		this(null, null, null, null, 0, mass);
		this.mass=mass;
		this.radius=radius;
		this.coefficientOfRestitution=coefficient;
	}
	
	public Body(Vec2D position, Vec2D velocity, Vec2D accel, Vec2D force, double damp, double mass) {
		super(position, velocity, accel, force, damp, mass);
	}

	public double getRadius() {
		return radius;
	}

	public double getHeight() {
		return height;
	}

	public double getMass() {
		return mass;
	}

	public double getCoefficientOfRestitution() {
		return coefficientOfRestitution;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public void setCoefficientOfRestitution(double coefficientOfRestitution) {
		this.coefficientOfRestitution = coefficientOfRestitution;
	}
	
}
