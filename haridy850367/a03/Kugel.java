package haridy850367.a03;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Kugel {

	public Vec3 center;
	public double radius;

	public Kugel(Vec3 center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	public Hit intersect(Ray r) {
		Vec3 newS = subtract(r.s, center);

		double a = dotProduct(r.dir, r.dir);
		double b = 2 * dotProduct(r.dir, newS);
		double c = dotProduct(newS, newS) - Math.pow(radius, 2);

		double sqrt = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
		if (Double.isNaN(sqrt))
			return null;

		double t1 = (-b + sqrt) / (2 * a);
		double t2 = (-b - sqrt) / (2 * a);
		double strahl;

		if (t1 < 0 && t2 < 0)
			return null;
		if (t1 > 0 && t2 < 0)
			strahl = t1;
		if (t2 > 0 && t1 < 0)
			strahl = t2;
		else
			strahl = Math.min(t1, t2);

		Vec3 hitpoint = r.pointAt(strahl);
		Vec3 normal = divide(subtract(hitpoint, center), radius);
		return new Hit(hitpoint, strahl, normal);
	}
}
