package haridy850367.a101;

import cgtools.Vec3;

import static cgtools.Vec3.dotProduct;
import static cgtools.Vec3.subtract;

public class Plane implements Shape {

    private Vec3 p;
    private Vec3 n;
    private Material material;

    Plane(Vec3 p, Vec3 n, Material material) {
        this.p = p;
        this.n = n;
        this.material = material;
    }

    @Override
    public Hit intersect(Ray r) {
        if (dotProduct(r.d, n) == 0) return null;

        double t = dotProduct(subtract(p, r.x0), n) / dotProduct(r.d, n);

        if (t > r.t0 && t < r.t1) return new Hit(t,
                r.pointAt(t),
                n,
                new Vec3(r.pointAt(t).x + 0.5, r.pointAt(t).z + 0.5, 0),
                material);

        return null;
    }

}
