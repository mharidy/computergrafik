package haridy850367.a06;

import cgtools.Vec3;

public class Background implements Shape {

	private final Material mat;

	public Background(Material mat) {
		this.mat = mat;
	}

	@Override
	public Hit intersect(Ray r) {
		Hit hit = new Hit(Double.POSITIVE_INFINITY, new Vec3(Double.POSITIVE_INFINITY),
				r.pointAt(Double.POSITIVE_INFINITY), mat);
		return hit;
	}
}
