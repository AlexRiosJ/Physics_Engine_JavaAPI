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

		Vec2D va = a.getVelocity().clone(), vb = b.getVelocity().clone();

		double v1x = ((a.getMass() - e * b.getMass()) * va.getX() + (1 + e) * b.getMass() * vb.getX())
				/ (a.getMass() + b.getMass());
		double v1y = ((a.getMass() - e * b.getMass()) * va.getY() + (1 + e) * b.getMass() * vb.getY())
				/ (a.getMass() + b.getMass());
		a.setVelocity(new Vec2D(v1x, v1y));

		double v2x = ((b.getMass() - e * a.getMass()) * vb.getX() + (1 + e) * a.getMass() * va.getX())
				/ (a.getMass() + b.getMass());
		double v2y = ((b.getMass() - e * a.getMass()) * vb.getY() + (1 + e) * a.getMass() * va.getY())
				/ (a.getMass() + b.getMass());
		b.setVelocity(new Vec2D(v2x, v2y));
	}

}
