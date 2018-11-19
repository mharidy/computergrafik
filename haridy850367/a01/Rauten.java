package haridy850367.a01;

import cgtools.*;

public class Rauten {

	public double xpos, ypos, distance;
	Vec3 color;

	public Rauten(double x, double y, double distance, Vec3 color) {
		xpos = x;
		ypos = y;
		this.color = color;
		this.distance = distance;
	}

	public double getDis() {

		return distance;

	}

	public double getXpos() {

		return xpos;

	}

	public double getYpos() {

		return ypos;

	}

	public Vec3 getColor() {

		return color;

	}

	public boolean test(double x, double y) {
		// x = x - xpos;
		// y = y - ypos;
		/*
		 * System.out.println("x= " + x); System.out.println("xPos= " + xpos);
		 * System.out.println("y= " + y); System.out.println("yPos= " + ypos);
		 */
		// d = Math.sqrt((x * x) + (y * y));
		// d =y*x;
		// d= y+x;

		/*
		 * if (d < area) { return true;
		 * 
		 * } else { return false; }
		 */

		double xp = x - xpos;
		double yp = y - ypos;

		// System.out.println("d5l abl f");

		return (xp + yp <= distance) && (xp - yp <= distance) && ((-xp) + yp <= distance) && ((-xp) - yp <= distance);
		//statt if reutrn
	}

}
