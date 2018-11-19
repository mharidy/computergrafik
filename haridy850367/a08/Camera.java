package haridy850367.a08;

import cgtools.Mat4;
import static cgtools.Mat4.*;
import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Camera {

	private final double angle;
	private final int width;
	private final int height;
	public final Mat4 transform;
	public final Vec3 rotation;

	public Camera(double angle, int width, int height, Mat4 transform, Vec3 rotation) {
		this.angle = angle;
		this.width = width;
		this.height = height;
		this.transform = transform;
		this.rotation = rotation;
	}

	public Ray generateRay(double x, double y) {
		Mat4 xrot = rotate(new Vec3(1, 0, 0), rotation.x);
		Mat4 yrot = rotate(new Vec3(0, 1, 0), rotation.y);
		Mat4 zrot = rotate(new Vec3(0, 0, 1), rotation.z);

		Vec3 temp = new Vec3(x - (int) (width / 2), (int) ((height / 2) - y),
				-(int) ((width / 2) / Math.tan(Math.toRadians(angle / 2))));

		Vec3 dir = xrot.transformDirection(temp);
		dir = yrot.transformDirection(dir);
		dir = zrot.transformDirection(dir);

		Ray ray = new Ray(transform.transformPoint(new Vec3(0, 0, 0)), dir, 0, Double.POSITIVE_INFINITY);
		return ray;
	}
}
