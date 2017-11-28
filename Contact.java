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
		double distance=Vec2D.distanceFromSquared(b1.getPosition(), b2.getPosition());
		System.out.println(distance);
		if(distance<Math.pow(b1.radius+b2.radius,2)) {
			return true;
		}
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
			if (vertexDifference <= 0.0) {
				b.getPosition().setY(limit.top);
				return true;
			}
			break;
		case left:
			vertexDifference = (b.getPosition().getX() - b.length) - limit.left;
			if (vertexDifference <= 0.0) {
				b.getPosition().setX(limit.left);
				return true;
			}
			break;
		case right:
			vertexDifference = limit.right - (b.getPosition().getX() + b.length);
			if (vertexDifference <= 0.0) {
				b.getPosition().setX(limit.right);
				return true;
			}
			break;
		case bottom:
			vertexDifference = (b.getPosition().getY() - b.height) - limit.bottom;
			if (vertexDifference <= 0.0) {
				b.getPosition().setY(limit.bottom);
				return true;
			}
			break;
		}
		
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
		if (axis == Y) {
			b.getVelocity().setY(b.getVelocity().getY() * -coefficientE);
		}
		else {
			b.getVelocity().setX(b.getVelocity().getX() * -coefficientE);
		
		}
	}
	
	
	/**Elastic collision handler calculates final velocities for two colliding bodies
	 * @param Bodies a, b involved in the collision.
	 * @return none, but modifies velocity vector from both bodies.
	 * */
	public static void elasticCollisionHandler(Body a, Body b) {
				
		Vec2D va=a.getVelocity().clone(),
			vb=b.getVelocity().clone();
		double e=a.coefficientOfRestitution;
		
		System.out.println("IMPACTO\n"+ va);
		
		double v1x=(e*(va.getX()-vb.getX())-va.getX()-vb.getX())/-2.0;
		double v1y=(e*(va.getY()-vb.getY())-va.getY()-vb.getY())/-2.0;
		a.setVelocity(new Vec2D(v1x,v1y));
		
		System.out.println("VELOCIDAD LUEGO DEL IMPACTO\n"+ a.getVelocity());
		
		double v2x=(e*(va.getX()-vb.getX())+va.getX()+vb.getX())/2.0;
		double v2y=(e*(va.getY()-vb.getY())+va.getY()+vb.getY())/2.0;
		b.setVelocity(new Vec2D(v2x,v2y));
		
		//Actualiza las posiciones para prevenir que se queden pegados
		a.updateConstAcc(.6);
		b.updateConstAcc(.6);
		
		/* IMPACTO NEWTONIANO CON TRANSMISIÓN DE TODA LA ENERGÍA
		//Calculate a's velocity after impact		
		Vec2D v1a= Vec2D.multiply(va, (a.mass-b.mass)/(a.mass+b.mass));
		Vec2D v2a= Vec2D.multiply(vb, (2*b.mass)/(a.mass+b.mass));
		a.getVelocity().setX(v1a.getX()+v2a.getX());
		a.getVelocity().setY(v1a.getY()+v2a.getY());
		
		//Calculate b's velocity after impact
		Vec2D v1b= Vec2D.multiply(va, (2*a.mass)/(a.mass+b.mass));
		Vec2D v2b= Vec2D.multiply(vb, (a.mass-b.mass)/(a.mass+b.mass));
		b.getVelocity().setX(v1b.getX()-v2b.getX());
		b.getVelocity().setY(v1b.getY()-v2b.getY());
		*/
	}
	
	
	public void inelasticCollisionHandler(Body another) {
		
	}

	
	
}
