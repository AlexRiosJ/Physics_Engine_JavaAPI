
package com.rad.classes;

public class Particle {
	private Vec2D position, velocity, acceleration, forceAccum, torque;
	private double damping, mass; // Amortiguación

	// CONTROL DE EXCEPCION PORQUE LA MASA NO PUEDE SER 0.

	public Particle(Vec2D position, Vec2D velocity, Vec2D acceleration, Vec2D totalForce, double damp, double mass) {
		this.position = position;
		this.velocity = velocity;
		this.acceleration = acceleration;
		this.forceAccum = totalForce;
		this.damping = damp;
		this.mass = mass;
	}

	public void updateConstSpeed(double time) {
		// Update linear position.
		position.addScaledVector(velocity, time);
	}

	public void updateConstAcc(double time) {
		position.addScaledVector(velocity, time);
		// Update linear velocity from the acceleration.
		velocity.addScaledVector(acceleration, time);

		// Work out the acceleration from the force.
		/*
		 * acceleration.addScaledVector(forceAccum,inverseMass); // Impose drag.
		 * velocity.multiply(Math.pow(damping, time));
		 */
	}

	public Vec2D getPosition() {
		return position;
	}

	public Vec2D getVelocity() {
		return velocity;
	}

	public Vec2D getAcceleration() {
		return acceleration;
	}

	public Vec2D getForceAccum() {
		return forceAccum;
	}

	public Vec2D getTorque() {
		return torque;
	}

	public double getDamping() {
		return damping;
	}

	public double getMass() {
		return mass;
	}

	public void setPosition(Vec2D position) {
		this.position = position;
	}

	public void setVelocity(Vec2D velocity) {
		this.velocity = velocity;
	}

	public void setAcceleration(Vec2D acceleration) {
		this.acceleration = acceleration;
	}

	public void setForceAccum(Vec2D forceAccum) {
		this.forceAccum = forceAccum;
	}

	public void setTorque(Vec2D torque) {
		this.torque = torque;
	}

	public void setDamping(double damping) {
		this.damping = damping;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public void addForce(Vec2D force) {
		this.forceAccum.setX(this.forceAccum.getX() + force.getX());
		this.forceAccum.setY(this.forceAccum.getY() + force.getY());
		// torque
	}

	public void clearAcc() {
		this.forceAccum.setX(0);
		this.forceAccum.setY(0);
	}

	public String toString() {
		return this.position.toString();
	}

}
