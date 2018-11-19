package haridy850367.a04;

import cgtools.Vec3;

public class Kamera {

	private final double angle;
	private final int width;
	private final int height;

	public Kamera(double angle, int width, int height) {
		this.angle = angle;
		this.width = width;
		this.height = height;
	}

	public Ray generateRay(double x, double y) {
		// double xr = (width / 2)-x;

		// double yr = ((height/2) - y);

		// Ray ray = new Ray(new Vec3(0,0,0), temp);
		// return ray;

		Ray r = new Ray(new Vec3(0, 0, 0), new Vec3(x - (int) (width / 2), (int) ((height / 2) - y),
				-(int) ((width / 2) / Math.tan(Math.toRadians(angle / 2)))));
		return r;
	}
}
