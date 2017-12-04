package com.rad.classes;

/**
 * The Contact class contains methods for test the collisions with a Boundary, Bodies and
 * makes the respective calculations of the collisions between bodies.
 * 
 * @author Alejandro Ríos, Darío Arias, Alfredo Rodriguez
 */
public class Contact {
	Body[] b = new Body[2];
	public static final int TOP = 1, LEFT = 2, RIGHT = 3, BOTTOM = 4;
	public static final int X = 0, Y = 1;

	/**
	 * Tests if there are contact between two bodies.
	 * @param b1 the first body
	 * @param b2 the second body
	 * @return A boolean value depending if the bodies are colliding
	 */
	public static boolean testBodiesOverlap(Body b1, Body b2) {
		double distance = Vec2D.distanceFromSquared(b1.getPosition(), b2.getPosition());
		if (distance < Math.pow(b1.radius + b2.radius, 2))
			return true;
		return false;
	}

	/**
	 * Tests if a Body is colliding with any quadrant of the boundary.
	 * @param b The Body to be tested
	 * @param limit The Boundary where the Body will collide
	 * @param quadrant The quadrant of the Boundary to be tested
	 * @return A boolean value depending if the body is colliding
	 */
	public static boolean testBoundaryOverlap(Body b, Boundary limit, int quadrant) {
		double vertexDifference = 10.0;
		switch (quadrant) {
		case TOP:
			vertexDifference = limit.getTop() - (b.getPosition().getY() + b.getRadius() + b.getHeight());
			if (vertexDifference <= 0.0) {
				b.getPosition().setY(limit.getTop() - b.getRadius());
				return true;
			}
			break;
		case LEFT:
			vertexDifference = (b.getPosition().getX() - b.getRadius() - b.getLength()) - limit.getLeft();
			if (vertexDifference <= 0.0) {
				b.getPosition().setX(limit.getLeft() + b.getRadius());
				return true;
			}
			break;
		case RIGHT:
			vertexDifference = limit.getRight() - (b.getPosition().getX() + b.getRadius() + b.getLength());
			if (vertexDifference <= 0.0) {
				b.getPosition().setX(limit.getRight() - b.getRadius());
				return true;
			}
			break;
		case BOTTOM:
			vertexDifference = (b.getPosition().getY() - b.getRadius() - b.getHeight()) - limit.getBottom();
			if (vertexDifference <= 0.0) {
				b.getPosition().setY(limit.getBottom() + b.getRadius());
				return true;
			}
			break;
		}

		return false;
	}

	/**
	 * Handles the resultant velocity from a Body when it collides with a Boundary.
	 * @param b the Body that will be tested
	 * @param e the coefficient of restitution of the collision
	 * @param axis the axis X or Y of the Body that will collide with the Boundary
	 * @throws InvalidCoefficientOfRestitutionException if the coefficient of restitution is not between 0 and 1
	 */
	public static void collisionHandler(Body b, double e, int axis) throws InvalidCoefficientOfRestitutionException {
		if(e < 0 || e > 1) {
			throw new InvalidCoefficientOfRestitutionException(e);
		} else { 
			if (axis == Y) {
				b.getVelocity().setY(b.getVelocity().getY() * -e);
			} else if(axis == X) {
				b.getVelocity().setX(b.getVelocity().getX() * -e);
			}
		}
	}
	
	/**
	 * Handles the resultant velocity from each Body when it collides with another Body.
	 * @param a the Body a
	 * @param b the Body b
	 * @param e the coefficient of restitution of the collision
	 * @throws InvalidCoefficientOfRestitutionException if the coefficient of restitution is not between 0 and 1
	 */
	public static void collisionHandler(Body a, Body b, double e) throws InvalidCoefficientOfRestitutionException {
		if(e < 0 || e > 1) {
			throw new InvalidCoefficientOfRestitutionException(e);
		} else { 
			Vec2D v1 = a.getVelocity().clone(), v2 = b.getVelocity().clone();
	
			Vec2D differenceVecNorm = Vec2D.getNormalized(Vec2D.subtract(a.getPosition(), b.getPosition()));
			
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
	
}
