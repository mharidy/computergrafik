package haridy850367.a06;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Plane implements Shape {

	private final Vec3 ortsVektor;
	private final Vec3 normalenVektor;
	private final Material material;

	public Plane(Vec3 ortsVektor, Vec3 normalenVektor, Material material) {
		this.ortsVektor = ortsVektor;
		this.normalenVektor = normalenVektor;
		this.material = material;
	}

	@Override
	public Hit intersect(Ray r) {
		Vec3 a = subtract(ortsVektor, r.origin); // d - p
		double b = dotProduct(a, normalenVektor);
		double c = dotProduct(r.dir, normalenVektor);
		double t = b / c;
		Vec3 hitpoint = null;

		if (t > 0) {
			hitpoint = r.pointAt(t);
			Hit hit = new Hit(t, hitpoint, normalenVektor, material);
			return hit;
		} else {
			Hit hit = new Hit(0, null, null, material);
			return hit;
		}
	}

}
