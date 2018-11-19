package haridy850367.b01;

import cgtools.Vec3;

public class Hintergrund implements Material {

	private Vec3 bkColor;

	public Hintergrund(Vec3 bkColor) {
		this.bkColor = bkColor;
	}

	@Override
	public Vec3 emittedRadiance(Ray r, Hit h) {
		return bkColor;
	}

	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		return null;
	}

	@Override
	public Vec3 albedo(Ray r, Hit h) {
		return null;
	}

}
