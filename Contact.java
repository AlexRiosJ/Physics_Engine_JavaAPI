package com.rad.vf;

public class Contact {
	Body[] b=new Body[2];
	
	public boolean testCircleOverlap(Body p1, Body p2) {
		double difference=Vec2D.distanceFromSquared(p1.getPosition(), p2.getPosition());
		double distance=Math.pow(p1.radius+p2.radius,2);
		if(difference<distance)
			return true;
		return false;
	}
	
	public boolean testPolygonOverlap(Body b1,Body b2){
		
		return false;
	}
	
	
}
