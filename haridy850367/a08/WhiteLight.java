package haridy850367.a08;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class WhiteLight implements Material {

	private final Vec3 color;

	public WhiteLight() {
		color = new Vec3(1, 1, 1);
		;
	}

	@Override
	public Vec3 emittedRadiance(Ray r, Hit h) {
		return color;
	}

	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		return null;
	}

	@Override
	public Vec3 albedo(Ray r, Hit h) {
		// return r;
		return null;
	}

}
