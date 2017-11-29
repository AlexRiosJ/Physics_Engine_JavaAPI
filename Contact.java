package com.rad.vf;

import com.rad.clases.Circle;

public class Contact {
	Body[] b = new Body[2];
	public static final int top = 1, left = 2, right = 3, bottom = 4;
	public static final int X = 0, Y = 1;

	public static boolean testCircleOverlap(Circle p1, Body p2) {
		double difference = Vec2D.distanceFromSquared(p1.getPosition(), p2.getPosition());
		double distance = Math.pow(p1.radius + p2.radius, 2);
		if (difference < distance)
			return true;
		return false;
	}

	public static boolean testBodiesOverlap(Body b1, Body b2) {

		return false;
	}

	/**
	 * testBoundaryOverlap recibe el cuerpo a comparar, así como el espacio
	 * delimitador de la simulación. También recibe un numero que indica el
	 * cuadrante a analizar. La equivalencia es: 1- Analiza overlap con el limite
	 * superior en Y. 2- Analiza overlap con el límite izquierdo en X. 3- Analiza
	 * overlap con el límite derecho en X. 4- Analiza overlap con el límite inferior
	 * en Y.
	 */
	public static boolean testBoundaryOverlap(Body b, Boundary limit, int quadrant) {
		double vertexDifference = 10.0;
		switch (quadrant) {
		case top:
			vertexDifference = limit.top - (b.getPosition().getY() + b.height);
			break;
		case left:
			vertexDifference = (b.getPosition().getX() - b.length) - limit.left;
			break;
		case right:
			vertexDifference = limit.right - (b.getPosition().getX() + b.length);
			break;
		case bottom:
			vertexDifference = (b.getPosition().getY() - b.height) - limit.bottom;
			break;
		}

		System.out.println("Diff. " + vertexDifference);

		if (vertexDifference <= 0.0)
			return true;
		return false;
	}

	public boolean testPolygonOverlap(Body b1, Body b2) {
		return false;
	}
	
	/**Elastic collision handler reacts to an object hitting a boundary
	 * @param double coefficientE: coefficient of restitution, int axis: X=0 or Y=1 as declared in class.
	 * @return none, but modifies velocity vector.
	 * */
	public static void elasticCollisionHandler(Body b,double coefficientE, int axis) {
		if (axis == Y)
			b.getVelocity().setY(b.getVelocity().getY() * -coefficientE);
		else
			b.getVelocity().setX(b.getVelocity().getX() * -coefficientE);
	}
	
	
	/**Elastic collision handler calculates final velocities for two colliding bodies
	 * @param Bodies a, b involved in the collision.
	 * @return none, but modifies velocity vector from both bodies.
	 * */
	public static void elasticCollisionHandler(Body a, Body b) {
		//Calculate a's velocity after impact
		Vec2D v1a= Vec2D.multiply(a.getVelocity(), (a.mass-b.mass)/(a.mass+b.mass));
		Vec2D v2a= Vec2D.multiply(b.getVelocity(), (2*b.mass)/(a.mass+b.mass));
		a.getVelocity().setX(v1a.getX()+v2a.getX());
		a.getVelocity().setY(v1a.getY()+v2a.getY());
		
		//Calculate b's velocity after impact
		Vec2D v1b= Vec2D.multiply(a.getVelocity(), (2*a.mass)/(a.mass+b.mass));
		Vec2D v2b= Vec2D.multiply(b.getVelocity(), (a.mass-b.mass)/(a.mass+b.mass));
		b.getVelocity().setX(v1b.getX()-v2b.getX());
		b.getVelocity().setY(v1b.getY()-v2b.getY());
	}
	
	
	public void inelasticCollisionHandler(Body another) {
		
	}

	
	
}
