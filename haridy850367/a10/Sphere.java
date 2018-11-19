package haridy850367.a10;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Sphere implements Shape {

    public Vec3 center;
    public double radius;
    private final Material mat;

    public Sphere(Vec3 center, double radius, Material mat) {
        this.center = center;
        this.radius = radius;
        this.mat = mat;
    }

    @Override
    public Hit intersect(Ray r) {
        Vec3 newOrigin = subtract(r.origin, center);

        double a = dotProduct(r.normalizedDir, r.normalizedDir);
        double b = 2 * dotProduct(r.normalizedDir, newOrigin);
        double c = dotProduct(newOrigin, newOrigin) - Math.pow(radius, 2);

        double sqrt = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
        if (Double.isNaN(sqrt)) {
            return null;
        }

        double t1 = (-b + sqrt) / (2 * a);
        double t2 = (-b - sqrt) / (2 * a);
        double strahlParameter;

        if (t1 < 0 && t2 < 0) {
            return null;
        }
        if (t1 > 0 && t2 < 0) {
            strahlParameter = t1;
        }
        if (t2 > 0 && t1 < 0) {
            strahlParameter = t2;
        } else {
            strahlParameter = Math.min(t1, t2);
        }

        Vec3 hitpoint = r.pointAt(strahlParameter);
        Vec3 normal = divide(subtract(hitpoint, center), radius);
        if (r.contains(strahlParameter)) {
            double inclination = Math.acos(normal.y);
            double azimuth = Math.PI + Math.atan2(normal.x, normal.z);
            double u = azimuth / (2 * Math.PI);
            double v = inclination / Math.PI;
          //  return new Hit(strahlParameter, hitpoint, normal, new Vec3(u, v, 0), mat);
            return new Hit(strahlParameter, hitpoint, normal, mat);
        }
        return null;
    }
}