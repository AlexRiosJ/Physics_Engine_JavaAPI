package com.rad.vf;
public class Body extends Particle {

	double radius, height, mass, coefficientOfRestitution;
	
	Body(Vec2D position, Vec2D velocity, Vec2D accel, Vec2D force, double damp, double mass) {
		super(position, velocity, accel, force, damp, mass);
	}
}
