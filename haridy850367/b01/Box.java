package haridy850367.b01;

import cgtools.Vec3;
import static cgtools.Vec3.vec3;

public class Box implements Shape {

	private Vec3 minimum;
	private Vec3 maximum;
	Material material;
	private Vec3 normal;

	Box(Vec3 minimum, double ausd, Material material) {
		this.minimum = minimum;
		this.maximum = vec3(minimum.x + ausd, minimum.y + ausd, minimum.z - ausd);
		this.material = material;
	}

	@Override
	public Hit intersect(Ray r) {

		double dx = r.direction.x;
		double dy = r.direction.y;
		double dz = r.direction.z;

		double x0x = r.source.x;
		double x0y = r.source.y;
		double x0z = r.source.z;

		double txMin, txMax, tyMin, tyMax, tzMin, tzMax;

		double invx = 1 / dx;
		double tx1 = (minimum.x - x0x) * invx;
		double tx2 = (maximum.x - x0x) * invx;
		txMin = Math.min(tx1, tx2);
		txMax = Math.max(tx1, tx2);

		double invy = 1 / dy;
		double ty1 = (minimum.y - x0y) * invy;
		double ty2 = (maximum.y - x0y) * invy;
		tyMin = Math.min(ty1, ty2);
		tyMax = Math.max(ty1, ty2);

		double invz = 1 / dz;
		double tz1 = (minimum.z - x0z) * invz;
		double tz2 = (maximum.z - x0z) * invz;
		tzMin = Math.min(tz1, tz2);
		tzMax = Math.max(tz1, tz2);

		double tMin = Math.max(txMin, Math.max(tyMin, tzMin));
		double tMax = Math.min(txMax, Math.min(tyMax, tzMax));
		if (tMin > tMax || tMin < r.t0 || tMin > r.t1) {
			return null;
		}

		Vec3 sch = r.pointAt(tMin);
		if (Math.abs(sch.x - maximum.x) <= 0.0001) {
			normal = vec3(1, 0, 0);
		} else if (Math.abs(sch.x - minimum.x) <= 0.0001) {
			normal = vec3(-1, 0, 0);
		} else if (Math.abs(sch.y - maximum.y) <= 0.0001) {
			normal = vec3(0, 1, 0);
		} else if (Math.abs(sch.y - minimum.y) <= 0.0001) {
			normal = vec3(0, -1, 0);
		} else if (Math.abs(sch.z - maximum.z) <= 0.0001) {
			normal = vec3(0, 0, 1);
		} else if (Math.abs(sch.z - minimum.z) <= 0.0001) {
			normal = vec3(0, 0, -1);
		}

		Hit hit = new Hit(tMin, sch, normal, material);
		return hit;
	}
}
