package com.rad.clases;

import com.rad.vf.Body;
import com.rad.vf.Vec2D;

public class Circle extends Body {

	public Circle(Vec2D center, double radius) {
		this.radius = radius;
		this.height = radius;
		this.length = radius;
		this.setPosition(center);
	}

	public boolean contains(Vec2D coordinate) {
		double maxx = position.getX() + radius, minx = position.getX() - radius;
		double maxy = position.getY() + radius, miny = position.getY() - radius;
		return (coordinate.getX() <= maxx && coordinate.getX() >= minx)
				&& (coordinate.getY() <= maxy && coordinate.getX() >= miny);
	}

	public void setMass(double mass) {
		this.mass = 1 / mass;
	}

	public Vec2D getCenter() {
		return position;
	}

	public double getRadius() {
		return radius;
	}

	public void rotate(double theta) {
		// No influye
	}

	public AxisBox createAxisBox() {
		AxisBox box = new AxisBox(position, radius);
		return box;
	}

	public double getArea() {
		return Math.PI * Math.pow(radius, 2);
	}

	public double getPerimeter() {
		return 2 * Math.PI * radius;
	}

	public String toString() {
		return String.format("Centro (%.2f,%.2f) Radio: %.2f", getCenter().getX(), getCenter().getY(), getRadius());
	}

}
