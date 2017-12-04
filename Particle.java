
package com.rad.classes;

/**
 * The Particle class contains methods for creating and manipulate
 * the values from a physic particle to do calculus with position, velocity
 * force vectors, etc.
 * 
 * @author Alejandro Ríos, Alfredo Rodríguez, Darío Arias.
 */
public class Particle {
	private Vec2D position, velocity, acceleration, forceAccum, torque;
	private double damping, mass;

	/**
	 * Initializes a newly created {@code Particle} object with the elements from the parameters.
	 * @param position position vector
	 * @param velocity velocity vector
	 * @param acceleration acceleration vector
	 * @param totalForce totalForce vector
	 * @param damp damp value
	 * @param mass mass value
	 */
	public Particle(Vec2D position, Vec2D velocity, Vec2D acceleration, Vec2D totalForce, double damp, double mass) {
		setPosition(position);
		setVelocity(velocity);
		setAcceleration(acceleration);
		setForceAccum(totalForce);
		setDamping(damp);
		setMass(mass);
	}

	/**
	 * Updates the position vector respect the change in time
	 * @param time time value
	 */
	public void updateConstSpeed(double time) {
		position.addScaledVector(velocity, time);
	}

	/**
	 * Updates the velocity and position vector respect the change in time value
	 * @param time
	 */
	public void updateConstAcc(double time) {
		position.addScaledVector(velocity, time);
		velocity.addScaledVector(acceleration, time);
	}

	/**
	 * Returns the current position vector from the particle.
	 * @return A Vec2D of the current position
	 */
	public Vec2D getPosition() {
		return position;
	}

	/**
	 * Returns the current velocity vector from the particle.
	 * @return A Vec2D of the current velocity
	 */
	public Vec2D getVelocity() {
		return velocity;
	}

	/**
	 * Returns the current acceleration vector from the particle.
	 * @return A Vec2D of the current acceleration
	 */
	public Vec2D getAcceleration() {
		return acceleration;
	}

	/**
	 * Returns the current forceAccum vector from the particle.
	 * @return A Vec2D of the current forceAccum
	 */
	public Vec2D getForceAccum() {
		return forceAccum;
	}

	/**
	 * Returns the current torque vector from the particle.
	 * @return A Vec2D of the current torque
	 */
	public Vec2D getTorque() {
		return torque;
	}

	/**
	 * Returns the current damping value from the particle.
	 * @return A double value of the current damping
	 */
	public double getDamping() {
		return damping;
	}

	/**
	 * Returns the current mass value from the particle.
	 * @return A double value of the current mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * Sets the position vector of the particle.
	 * @param Vec2D of the particles's position
	 */
	public void setPosition(Vec2D position) {
		this.position = position;
	}

	/**
	 * Sets the velocity vector of the particle.
	 * @param Vec2D of the particles's velocity
	 */
	public void setVelocity(Vec2D velocity) {
		this.velocity = velocity;
	}

	/**
	 * Sets the acceleration vector of the particle.
	 * @param Vec2D of the particles's acceleration
	 */
	public void setAcceleration(Vec2D acceleration) {
		this.acceleration = acceleration;
	}

	/**
	 * Sets the forceAccum vector of the particle.
	 * @param Vec2D of the particles's forceAccum
	 */
	public void setForceAccum(Vec2D forceAccum) {
		this.forceAccum = forceAccum;
	}

	/**
	 * Sets the torque vector of the particle.
	 * @param Vec2D of the particles's torque
	 */
	public void setTorque(Vec2D torque) {
		this.torque = torque;
	}

	/**
	 * Sets the damping value of the particle.
	 * @param double value of the particles's damping
	 */
	public void setDamping(double damping) {
		this.damping = damping;
	}

	/**
	 * Sets the mass value of the particle.
	 * @param mass value of the particles's mass
	 * @throws NegativeMassException if the mass is 0 or less
	 */
	public void setMass(double mass) throws NegativeMassException {
		if(mass < 0)
			throw new NegativeMassException(mass);
		else
			this.mass = mass;
	}
	
	/**
	 * Add a force vector to the forceAccum vector of the Particle.
	 * @param force force Vec2D that will be added to the forceAcumm current vector
	 */
	public void addForce(Vec2D force) {
		this.forceAccum.setX(this.forceAccum.getX() + force.getX());
		this.forceAccum.setY(this.forceAccum.getY() + force.getY());
	}

	/**
	 * Resets to zero the forceAccum vector of the particle.
	 */
	public void clearAccum() {
		this.forceAccum.setX(0);
		this.forceAccum.setY(0);
	}

	/**
	 * Returns a String of the current position vector of the Particle.
	 */
	@Override
	public String toString() {
		return "Position at: " + this.position.toString();
	}

}
