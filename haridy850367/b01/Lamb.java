package haridy850367.b01;

import cgtools.Random;
import cgtools.Vec3;

import static cgtools.Vec3.*;

public class Lamb implements Material {

	private Vec3 albedo;

	Lamb(Vec3 albedo) {
		this.albedo = albedo;
	}

	@Override
	public Vec3 emittedRadiance(Ray ray, Hit hit) {
		return black;
	}

	@Override
	public Ray scatteredRay(Ray ray, Hit hit) {
		Vec3 drnd = vec3(Random.random() * 2 - 1, Random.random() * 2 - 1, Random.random() * 2 - 1);

		while (drnd.length() > 1 || drnd.length() < -1)
			drnd = vec3(Random.random() * 2 - 1, Random.random() * 2 - 1, Random.random() * 2 - 1);

		return new Ray(hit.pos, normalize(add(normalize(hit.normal), drnd)));
	}

	@Override
	public Vec3 albedo(Ray r, Hit h) {
		// return null;
		return albedo;
	}
}
