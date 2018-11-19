package haridy850367.b01;

import cgtools.Mat4;
import cgtools.Vec3;

import static cgtools.Vec3.normalize;
import static cgtools.Vec3.vec3;

class Camera {

	private double phi;
	private int width;
	private int height;
	private Mat4 mat4;

	Camera(double phi, int width, int height, Mat4 mat4) {
		this.phi = phi;
		this.width = width;
		this.height = height;
		this.mat4 = mat4;

	}

	Ray generateRay(double x, double y) {
		double a = x - width / 2;
		double b = height / 2 - y;
		double c = -((width / 2) / Math.tan(phi / 2));

		Ray ray = new Ray(mat4.transformPoint(vec3(0, 0, 0)), mat4.transformDirection(normalize(new Vec3(a, b, c))));
		return ray;
	}

}
