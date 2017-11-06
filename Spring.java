package com.rad.vf;

public class Spring implements ParticleForceGenerator {

	private Particle p;
	private double k,length;
	
	Spring(Particle other, double spring_ctt, double length){
		this.p=other;
		this.k=spring_ctt;
		this.length=length;
	}
	
	@Override
	public void updateForce(Particle p, double time) {
		Vec2D force;
		/*p.getposition(force);*/
		
	}

}

