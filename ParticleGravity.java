package com.rad.vf;

public class ParticleGravity implements ParticleForceGenerator {
	public final Vec2D gravity;
	
	public ParticleGravity(Vec2D vector) {
		this.gravity=vector;
	}
	
	@Override
	public void updateForce(Particle p, double time) {
		
	}
	
}
