package haridy850367.a06;

import cgtools.Vec3;

public class Hit {

	public double temp;
	public Vec3 hitpoint;
	public Vec3 normal;
	public Material material;

	public Hit(double temp, Vec3 hitpoint, Vec3 normal, Material material) {
		this.temp = temp;
		this.hitpoint = hitpoint;
		this.normal = normal;
		this.material = material;
	}
}
