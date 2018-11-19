package haridy850367.a06;

import cgtools.Vec3;

public class Camera {

	private final double angle;
	private final int width;
	private final int height;

	public Camera(double angle, int width, int height) {
		this.angle = angle;
		this.width = width;
		this.height = height;
	}

	public Ray generateRay(double x, double y) {
		Ray ray = new Ray(new Vec3(0, 0, 0), new Vec3(x - (int) (width / 2), (int) ((height / 2) - y),
				-(int) ((width / 2) / Math.tan(Math.toRadians(angle / 2)))), 0, Double.POSITIVE_INFINITY);

		return ray;
	}
}
