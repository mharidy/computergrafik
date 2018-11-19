package haridy850367.b01;

import cgtools.Vec3;

import static cgtools.Vec3.*;

public class Ray {

    final Vec3 source;
    final Vec3 direction;
    final double t0, t1;

    public Ray(Vec3 source, Vec3 direction) {
        this.source = source;
        this.direction = normalize(direction);
        this.t0 = 0.0001;
        this.t1 = Double.POSITIVE_INFINITY;
    }

    Vec3 pointAt(double t) {
        return add(source, multiply(t,direction));
    }

}
