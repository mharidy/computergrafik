package haridy850367.a06;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Sphere implements Shape {

	public Vec3 center;
	public double radius;
	private final Material material;

	public Sphere(Vec3 center, double radius, Material material) {
		this.center = center;
		this.radius = radius;
		this.material = material;
	}

	@Override
	public Hit intersect(Ray r) {
		Vec3 newOrigin = subtract(r.origin, center);

		double a = dotProduct(r.dir, r.dir);
		double b = 2 * dotProduct(r.dir, newOrigin);
		double c = dotProduct(newOrigin, newOrigin) - Math.pow(radius, 2);

		double sqrt = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
		if (Double.isNaN(sqrt))
			return new Hit(-1, null, null, material);

		double t1 = (-b + sqrt) / (2 * a);
		double t2 = (-b - sqrt) / (2 * a);
		double para;

		if (t1 < 0 && t2 < 0)
			return null;
		if (t1 > 0 && t2 < 0)
			para = t1;
		if (t2 > 0 && t1 < 0)
			para = t2;
		else
			para = Math.min(t1, t2);

		Vec3 hitpoint = r.pointAt(para);
		Vec3 normal = divide(subtract(hitpoint, center), radius);
		if (r.contains(para)) {
			Hit hit = new Hit(para, hitpoint, normal, material);
			return hit;
		}
		return null;
	}
}
