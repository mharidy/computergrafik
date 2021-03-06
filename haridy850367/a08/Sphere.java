package haridy850367.a08;

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
		double a = dotProduct(r.normalizedDir, r.normalizedDir);
		double b = 2 * dotProduct(r.normalizedDir, newOrigin);
		double c = dotProduct(newOrigin, newOrigin) - Math.pow(radius, 2);

		double sqrt = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
		if (Double.isNaN(sqrt))
			return null;
		double t1 = (-b + sqrt) / (2 * a);
		double t2 = (-b - sqrt) / (2 * a);
		double strahlParameter;
		
		// if (t1 < 0 || t2 < 0)
		if (t1 < 0 && t2 < 0)
			return null;
		if (t1 > 0 && t2 < 0)
			strahlParameter = t1;
		if (t2 > 0 && t1 < 0)
			strahlParameter = t2;
		else
			strahlParameter = Math.min(t1, t2);

		Vec3 hitpoint = r.pointAt(strahlParameter);
		Vec3 normal = divide(subtract(hitpoint, center), radius);
		if (r.contains(strahlParameter)) {
			Hit hit = new Hit(strahlParameter, hitpoint, normal, material);
			return hit;
		}
		return null;
	}
}
