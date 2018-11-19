package haridy850367.a101;

import cgtools.Vec3;

import static cgtools.Vec3.*;

public class Ray {

    final Vec3 x0;
    final Vec3 d;
    final double t0, t1;

    public Ray(Vec3 x0, Vec3 d) {
        this.x0 = x0;
        this.d = normalize(d);
        this.t0 = 0.0001;
        this.t1 = Double.POSITIVE_INFINITY;
    }

    Vec3 pointAt(double t) {
        return add(x0, multiply(t,d));
    }

}
