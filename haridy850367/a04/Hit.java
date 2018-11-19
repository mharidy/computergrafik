package haridy850367.a04;

import cgtools.Vec3;

public class Hit {

	public double t;
	public Vec3 hitpoint;
	public Vec3 normal;
	public Vec3 color;

	public Hit(double t, Vec3 hitpoint, Vec3 normal, Vec3 color) {
		this.t = t;
		this.hitpoint = hitpoint;
		this.color = color;
		this.normal = normal;

		// return hit();
	}
}
