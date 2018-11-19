package haridy850367.b01;

import cgtools.Random;
import cgtools.Vec3;

import static cgtools.Vec3.*;

public class Metall implements Material {

	private Vec3 albedo;
	private double source;

	public Metall(Vec3 albedo, double source) {
		this.albedo = albedo;
		this.source = source;
	}

	@Override
	public Vec3 emittedRadiance(Ray r, Hit h) {
		return Vec3.black;
	}

	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		Vec3 xrnd = vec3(Random.random() * 2 - 1, Random.random() * 2 - 1, Random.random() * 2 - 1);
		while (xrnd.length() > 1 || xrnd.length() < -1)
			xrnd = vec3(Random.random() * 2 - 1, Random.random() * 2 - 1, Random.random() * 2 - 1);

		Vec3 d = r.direction;
		Vec3 n = normalize(h.normal);
		double skalarND = dotProduct(d, n);

		Vec3 dr = subtract(d, multiply(skalarND * 2, n));
		Vec3 ds = add(multiply(source, xrnd), normalize(dr));
		if (dotProduct(ds, n) < 0)
			return null;
		Ray ray = new Ray(h.pos, ds);
		return ray;
	}

	@Override
	public Vec3 albedo(Ray r, Hit h) {
		return albedo;
	}
}
