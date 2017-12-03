package com.rad.classes;

public class Contact {
	Body[] b = new Body[2];
	public static final int TOP = 1, LEFT = 2, RIGHT = 3, BOTTOM = 4;
	public static final int X = 0, Y = 1;

	public static boolean testBodiesOverlap(Body b1, Body b2) {
		double distance = Vec2D.distanceFromSquared(b1.getPosition(), b2.getPosition());
		if (distance < Math.pow(b1.radius + b2.radius, 2))
			return true;
		return false;
	}

	/**
	 * testBoundaryOverlap recibe el cuerpo a comparar, así como el espacio
	 * delimitador de la simulación. También recibe un numero que indica el
	 * cuadrante a analizar. La equivalencia es: 1- Analiza overlap con el limite
	 * superior en Y. 2- Analiza overlap con el límite izquierdo en X. 3- Analiza
	 * overlap con el límite derecho en X. 4- Analiza overlap con el límite inferior
	 * en Y.
	 * @param Body b, Boundary limit, int quadrants.
	 */
	public static boolean testBoundaryOverlap(Body b, Boundary limit, int quadrant) {
		double vertexDifference = 10.0;
		switch (quadrant) {
		case TOP:
			vertexDifference = limit.top - (b.getPosition().getY() + b.getRadius() + b.height);
			if (vertexDifference <= 0.0) {
				b.getPosition().setY(limit.top - b.getRadius());
				return true;
			}
			break;
		case LEFT:
			vertexDifference = (b.getPosition().getX() - b.getRadius() - b.length) - limit.left;
			if (vertexDifference <= 0.0) {
				b.getPosition().setX(limit.left + b.getRadius());
				return true;
			}
			break;
		case RIGHT:
			vertexDifference = limit.right - (b.getPosition().getX() + b.getRadius() + b.length);
			if (vertexDifference <= 0.0) {
				b.getPosition().setX(limit.right - b.getRadius());
				return true;
			}
			break;
		case BOTTOM:
			vertexDifference = (b.getPosition().getY() - b.getRadius() - b.height) - limit.bottom;
			if (vertexDifference <= 0.0) {
				b.getPosition().setY(limit.bottom + b.getRadius());
				return true;
			}
			break;
		}

		return false;
	}

	/**
	 * Collision handler reacts to an object hitting a boundary modifying the current velocity vector.
	 * 
	 * @param double e: coefficient of restitution, int axis: X = 0 or Y = 1 as declared in class.
	 */
	public static void collisionHandler(Body b, double e, int axis) {
		if (axis == Y) {
			b.getVelocity().setY(b.getVelocity().getY() * -e);
		} else if(axis == X) {
			b.getVelocity().setX(b.getVelocity().getX() * -e);
		}
	}
	
	/**
	 * Collision handler calculates final velocities for two colliding bodies.
	 * 
	 * @param Bodies a, b involved in the collision.
	 * @return none, but modifies velocity vector from both bodies.
	 */
	public static void collisionHandler(Body a, Body b, double e) {

		Vec2D v1 = a.getVelocity().clone(), v2 = b.getVelocity().clone();

		Vec2D differenceVecNorm = Vec2D.getNormalized(Vec2D.substract(a.getPosition(), b.getPosition()));
		
		double v1p = Vec2D.dot(v1, differenceVecNorm);
		double v2p = Vec2D.dot(v2, differenceVecNorm);
		
		double v1pp = ((a.getMass() - e * b.getMass()) * v1p + (1 + e) * b.getMass() * v2p)
				/ (a.getMass() + b.getMass());
		double v2pp = ((b.getMass() - e * a.getMass()) * v2p + (1 + e) * a.getMass() * v1p)
				/ (a.getMass() + b.getMass());
		
		double v1x = v1.getX() + (v1pp - v1p) * differenceVecNorm.getX();
		double v1y = v1.getY() + (v1pp - v1p) * differenceVecNorm.getY();
		a.setVelocity(new Vec2D(v1x, v1y));
		
		double v2x = v2.getX() + (v2pp - v2p) * differenceVecNorm.getX();
		double v2y = v2.getY() + (v2pp - v2p) * differenceVecNorm.getY();
		b.setVelocity(new Vec2D(v2x, v2y));
	}

}
