package haridy850367.a04;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Sphere implements Shape {

	public Vec3 center;
	public double radius;
	public Vec3 color;

	public Sphere(Vec3 center, double radius, Vec3 color) {
		this.color = color;
		this.center = center;
		this.radius = radius;

	}

	// override 3shan abstract walla l2
	@Override
	public Hit intersect(Ray r) {
		Vec3 newS = subtract(r.s, center);

		double a = dotProduct(r.dir, r.dir);
		double b = dotProduct(multiply(2, newS), r.dir);
		double c = dotProduct(newS, newS) - Math.pow(radius, 2);

		double sqrt = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
		if (Double.isNaN(sqrt))
			return new Hit(-1, null, null, red);

		double t1 = (-b + sqrt) / (2 * a);
		double t2 = (-b - sqrt) / (2 * a);
		double sParameter;

		if (t1 < 0 && t2 < 0)
			return new Hit(-1, null, null, red);
		if (t1 > 0 && t2 < 0)
			sParameter = t1;
		if (t2 > 0 && t1 < 0)
			sParameter = t2;
		else
			sParameter = Math.min(t1, t2);

		Vec3 hitpoint = r.pointAt(sParameter);
		Vec3 normal = divide(subtract(hitpoint, center), radius);
		Hit h = new Hit(sParameter, hitpoint, normal, color);
		return h;
	}
}
