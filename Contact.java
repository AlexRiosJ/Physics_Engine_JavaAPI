package com.rad.classes;

public class Contact {
	Body[] b = new Body[2];
	public static final int TOP = 1, LEFT = 2, RIGHT = 3, BOTTOM = 4;
	public static final int X = 0, Y = 1;

	public boolean testCircleOverlap(Body p1, Body p2) {
		double difference = Vec2D.distanceFromSquared(p1.getPosition(), p2.getPosition());
		double distance = Math.pow(p1.getRadius() + p2.getRadius(), 2);
		if (difference < distance)
			return true;
		return false;
	}

	public static boolean testBodiesOverlap(Body b1, Body b2) {
		return false;
	}

	/**
	 * testBoundaryOverlap recibe el cuerpo a comparar, así como el espacio
	 * delimitador de la simulación. También recibe un número que indica el
	 * cuadrante a analizar. La equivalencia es: 1- Analiza overlap con el limite
	 * superior en Y. 2- Analiza overlap con el límite izquierdo en X. 3- Analiza
	 * overlap con el límite derecho en X. 4- Analiza overlap con el límite inferior
	 * en Y.
	 */
	public static boolean testBoundaryOverlap(Body b, Boundary limit, int quadrant) {
		double vertexDifference = 10.0;
		switch (quadrant) {
		case TOP:
			vertexDifference = limit.top - (b.getPosition().getY() + b.getHeight());
			break;
		case LEFT:
			vertexDifference = (b.getPosition().getX() - b.getLength()) - limit.left;
			break;
		case RIGHT:
			vertexDifference = limit.right - (b.getPosition().getX() + b.getLength());
			break;
		case BOTTOM:
			vertexDifference = (b.getPosition().getY() - b.getHeight()) - limit.bottom;
			break;
		}

//		System.out.println("Diff. " + vertexDifference);

		if (vertexDifference <= 0.0)
			return true;
		return false;
	}

	/**
	 * Elastic collision handler reacts to an object hitting a boundary.
	 * @param double coefficientE: coefficient of restitution, int axis: X = 0 or Y = 1 as declared in class.
	 * @return none, but modifies velocity vector.
	 */
	public static void elasticCollisionHandler(Body b, double coefficientE, int axis) {
		if (axis == Y)
			b.getVelocity().setY(b.getVelocity().getY() * -coefficientE);
		else
			b.getVelocity().setX(b.getVelocity().getX() * -coefficientE);
	}

	/**
	 * Elastic collision handler calculates final velocities for two colliding bodies.
	 * @param Bodies a, b involved in the collision.
	 * @return none, but modifies velocity vector from both bodies.
	 */
	public static void elasticCollisionHandler(Body a, Body b) {
		// Calculate a's velocity after impact
		Vec2D v1a = Vec2D.multiply(a.getVelocity(), (a.getMass() - b.getMass() ) / (a.getMass()  + b.getMass() ));
		Vec2D v2a = Vec2D.multiply(b.getVelocity(), (2 * b.getMass() ) / (a.getMass()  + b.getMass() ));
		a.getVelocity().setX(v1a.getX() + v2a.getX());
		a.getVelocity().setY(v1a.getY() + v2a.getY());

		// Calculate b's velocity after impact
		Vec2D v1b = Vec2D.multiply(a.getVelocity(), (2 * a.getMass() ) / (a.getMass()  + b.getMass() ));
		Vec2D v2b = Vec2D.multiply(b.getVelocity(), (a.getMass()  - b.getMass() ) / (a.getMass()  + b.getMass() ));
		b.getVelocity().setX(v1b.getX() - v2b.getX());
		b.getVelocity().setY(v1b.getY() - v2b.getY());
	}

	public void inelasticCollisionHandler(Body another) {

	}

	public boolean testPolygonOverlap(Body b1, Body b2) {
		return false;
	}

}
