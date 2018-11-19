package haridy850367.a05;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Background implements Shape {

	private final Material material;

	public Background(Material material) {
		this.material = material;
	}

	@Override
	public Hit intersect(Ray r) {
		Hit h = new Hit(Double.POSITIVE_INFINITY, new Vec3(Double.POSITIVE_INFINITY),
				r.pointAt(Double.POSITIVE_INFINITY), material);
		return h;
	}
}
