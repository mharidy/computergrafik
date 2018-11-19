package haridy850367.b01;

import cgtools.Vec3;

public class Hit {

	final double t;
	final Vec3 pos;
	final Vec3 normal;
	final Material material;

	public Hit(double t, Vec3 pos, Vec3 normal, Material material) {
		this.t = t;
		this.pos = pos;
		this.normal = normal;
		this.material = material;
	}

}
