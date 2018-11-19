package haridy850367.a03;

import cgtools.*;

public class LochKamera {

	private final double angle;
	private final int width;
	private final int height;

	public LochKamera(double angle, int width, int height) {
		this.angle = angle;
		this.height = height;

		this.width = width;

	}

	public Ray Raygenerator(double x, double y) {
		// Vec3 temp = new Vec3(x1, y1, z1);
		// Ray ray = new Ray(new Vec3(0,0,0), temp);
		// return ray;

		Ray v = new Ray(new Vec3(0, 0, 0), new Vec3(x - (width / 2), ((height / 2) - y),
				-(int) ((width / 2) / Math.tan(Math.toRadians(angle / 2)))));
		return v;
	}
}
