package com.rad.vf;

public class Spring implements ParticleForceGenerator {

	private Particle p;
	private double k, length;
	
	Spring(Particle other, double springCtt, double length){
		this.p 		= other;
		this.k 		= springCtt;
		this.length	= length;
	}
	
	@Override
	public void updateForce(Particle p, double time) {
		Vec2D force;
		/*p.getposition(force);*/
		
	}

}

