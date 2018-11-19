package haridy850367.a101;

import cgtools.Mat4;
import cgtools.Vec3;

import static cgtools.Vec3.normalize;
import static cgtools.Vec3.vec3;

class Camera {

    private double phi;
    private int width;
    private int height;
    private Mat4 v;

    Camera(double phi, int width, int height, Mat4 v) {
        this.phi = phi;
        this.width = width;
        this.height = height;
        this.v = v;
    }

    Ray generateRay(double x, double y) {
        double a = x - width / 2;
        double b = height / 2 - y;
        double c =  -((width / 2) / Math.tan(phi / 2));

        return new Ray(v.transformPoint(vec3(0, 0, 0)), v.transformDirection(normalize(new Vec3(a, b, c))));
    }

}
