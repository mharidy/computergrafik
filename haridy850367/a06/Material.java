package haridy850367.a06;

import cgtools.Vec3;

public interface Material {
	// interface return null msh htnf3

	Vec3 emittedRadiance(Ray ray, Hit hit);

	Ray scatteredRay(Ray ray, Hit hit);

	Vec3 albedo(Ray ray, Hit hit);
}
