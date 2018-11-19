package haridy850367.a04;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Plane implements Shape {

	private final Vec3 color;
	private final Vec3 nVektor;
	private final Vec3 vektor;

	public Plane(Vec3 vektor, Vec3 nVektor, Vec3 color) {
		this.vektor = vektor;
		this.nVektor = nVektor;
		this.color = color;
	}

	@Override
	public Hit intersect(Ray r) {
		Vec3 a = subtract(vektor, r.s); // d - p
		double b = dotProduct(a, nVektor);
		double c = dotProduct(r.dir, nVektor);
		double t = b / c;
		Vec3 hitpoint;
		// Hit hit;

		if (t > 0) {
			hitpoint = r.pointAt(t);
			Hit h = new Hit(t, hitpoint, nVektor, color);
			return h;
		} else {
			// return Hit(1,0,0,color);
			Hit h = new Hit(0, null, null, color);
			return h;
		}
	}

}
