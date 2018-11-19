package haridy850367.b01;

import cgtools.Vec3;

import static cgtools.Vec3.*;

public class Kugel implements Shape {

	private double radius;
	private Vec3 center;
	private Material material;

	Kugel(double radius, Vec3 center, Material material) {
		this.radius = radius;
		this.center = center;
		this.material = material;
	}

	public Hit intersect(Ray ray) {
		Vec3 d = ray.direction;
		Vec3 x0 = subtract(ray.source, center);

		double a = dotProduct(d, d);
		double b = dotProduct(multiply(2, x0), d);
		double c = dotProduct(x0, x0) - Math.pow(radius, 2);

		double denominator = 2 * a;
		if (denominator == 0)
			return null;

		double dis = (Math.pow(b, 2) - (4 * a * c));
		Hit hit = null;

		if (dis >= 0) {
			double t1 = (-b + Math.sqrt(dis)) / denominator;
			Vec3 norm = divide(subtract(ray.pointAt(t1), center), radius);
			if (dis == 0) {
				if (t1 > ray.t0 && t1 < ray.t1)
					hit = new Hit(t1, ray.pointAt(t1), normalize(norm), material);
			}
			if (dis > 0) {
				double t2 = (-b - Math.sqrt(dis)) / denominator;

				double t;
				double min = Math.min(t1, t2);
				double max = Math.max(t1, t2);
				if (min > ray.t0)
					t = min;
				else {
					if (max > ray.t0)
						t = max;
					else
						return null;
				}

				if (t > ray.t0 && t < ray.t1) {
					norm = divide(subtract(ray.pointAt(t), center), radius);
					hit = new Hit(t, ray.pointAt(t), normalize(norm), material);
				}
			}
		}
		// return null
		return hit;
	}

}
