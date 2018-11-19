package haridy850367.a03;

import cgtools.*;

public class Hit {
	public Vec3 point;

	public double t;
	public Vec3 normal;

	public Hit(Vec3 point, double t, Vec3 normal) {
		this.t = t;
		this.point = point;
		this.normal = normal;
	}
}
