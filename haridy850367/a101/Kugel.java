package haridy850367.a101;

import cgtools.Vec3;

import static cgtools.Vec3.*;

public class Kugel implements Shape {

    private double rad;
    private Vec3 center;
    private Material material;

    Kugel(double r, Vec3 center, Material material) {
        this.rad = r;
        this.center = center;
        this.material = material;
    }

    public Hit intersect(Ray r) {
        Vec3 d = r.d;
        Vec3 x0 = subtract(r.x0, center);

        double a = dotProduct(d, d);
        double b = dotProduct(multiply(2, x0), d);
        double c = dotProduct(x0, x0) - Math.pow(rad, 2);

        double denominator = 2 * a;
        if (denominator == 0) return null;

        double dis = (Math.pow(b, 2) - (4 * a * c));
        Double tF = null;
        double t1=0;
        double t2=0;
        if (dis >= r.t0) {

            t1 = (-b + Math.sqrt(dis)) / denominator;
            t2 = (-b - Math.sqrt(dis)) / denominator;

            if (t1 < r.t0 ^ t2 < r.t0) {
                tF = Math.max(t1, t2);
            }else if (t1 > r.t0 && t2 > r.t0) {
                tF = Math.min(t1, t2);
            }
        }else if (dis == 0) {
            t1=(-b + Math.sqrt(dis)) / denominator;

            if (t1 > r.t0 && t1 < r.t1) tF = t1;
        }
        if(tF==null){
            return null;
        }


        Vec3 norm = divide(subtract(r.pointAt(tF), center), rad);
        double inclination = Math.acos(norm.y);
        double azimuth = Math.PI + Math.atan2(norm.x, norm.z);
        double u = azimuth / (2 * Math.PI);
        double v = inclination / Math.PI;
        return new Hit(tF, r.pointAt(tF), normalize(norm), new Vec3(u, v, 0), material);
    }

}
