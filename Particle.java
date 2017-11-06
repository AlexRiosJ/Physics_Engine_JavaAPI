package com.rad.vf;

public class Particle {
	private Vec2D position, velocity, acceleration, forceAccum, torque;
	private double damping, inverseMass; // Amortiguación
	
	// CONTROL DE EXCEPCION PORQUE LA MASA NO PUEDE SER 0.
	
	Particle(Vec2D position, Vec2D velocity, Vec2D acceleration, Vec2D forceAcumm, double damping, double mass){
		this.position     = position;
		this.velocity     = velocity;
		this.acceleration = acceleration;
		this.forceAccum   = forceAcumm;
		this.damping      = damping;
		this.inverseMass  = 1.0 / mass;
	}
	
	void integrate(double time) {
		assert(time > 0.0);
		
		// Update linear position.
		position.addScaledVector(velocity, time);
		position.addScaledVector(acceleration, time * time * 0.5);
		
		// Work out the acceleration from the force.
		acceleration.addScaledVector(forceAccum, inverseMass);
		
		// Update linear velocity from the acceleration.
		velocity.addScaledVector(acceleration, time);
		
		// Impose drag.
		velocity.multiply(Math.pow(damping, time));
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


	public double getInverseMass() {
		return inverseMass;
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


	public void setInverseMass(double inverseMass) {
		this.inverseMass = inverseMass;
	}


	public void addForce(Vec2D force) {
		this.forceAccum.setX(this.forceAccum.getX() + force.getX());
		this.forceAccum.setY(this.forceAccum.getY() + force.getY());
		//torque
	}
	
	public void clearAcc() {
		this.forceAccum.setX(0);
		this.forceAccum.setY(0);
	}
	
}
