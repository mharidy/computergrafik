package haridy850367.b01;

import static cgtools.Vec3.vec3;

public class Background implements Shape {

	private Material material;

	Background(Material material) {
		this.material = material;
	}

	@Override
	public Hit intersect(Ray ray) {
		Hit hit = new Hit(ray.t1, ray.pointAt(ray.t1), vec3(1, 0, 0), material);
		return hit;
	}

}
