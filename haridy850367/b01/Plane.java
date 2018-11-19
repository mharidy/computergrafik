package haridy850367.b01;

import cgtools.Vec3;

import static cgtools.Vec3.dotProduct;
import static cgtools.Vec3.subtract;

public class Plane implements Shape {

	private Vec3 dir;
	private Vec3 normal;
	private Material material;

	Plane(Vec3 dir, Vec3 normal, Material material) {
		this.dir = dir;
		this.normal = normal;
		this.material = material;
	}

	@Override
	public Hit intersect(Ray ray) {
		// grbt elseIf bs brdow mnf3tsh !
		if (dotProduct(ray.direction, normal) == 0)
			return null;

		double t = dotProduct(subtract(dir, ray.source), normal) / dotProduct(ray.direction, normal);

		if (t > ray.t0 && t < ray.t1) {
			Hit hit = new Hit(t, ray.pointAt(t), normal, material);
			return hit;
		}
		return null;
	}

}
