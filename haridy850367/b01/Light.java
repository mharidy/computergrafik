package haridy850367.b01;

import cgtools.Vec3;

public class Light implements Material {

	private Vec3 albedo;

	Light(Vec3 albedo) {
		this.albedo = albedo;
	}

	@Override
	public Vec3 emittedRadiance(Ray r, Hit h) {
		return albedo;
	}

	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		// return 0;
		return null;
	}

	@Override
	public Vec3 albedo(Ray r, Hit h) {
		return null;
	}
}
