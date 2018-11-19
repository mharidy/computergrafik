package haridy850367.a10;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class PlaneXXX implements Shape {

    private final Vec3 ortsVektor;
    private Vec3 normalenVektor;
    private final Material mat;
    private final double width;
    private final double heigth;

    public PlaneXXX(Vec3 ortsVektor, Vec3 normalenVektor, Material mat) {
        this.ortsVektor = ortsVektor;
        this.normalenVektor = normalenVektor;
        this.mat = mat;
        width = Double.POSITIVE_INFINITY;
        heigth = Double.POSITIVE_INFINITY;
    }

    public PlaneXXX(Vec3 ortsVektor, Vec3 normalenVektor, Material mat, double xs, double ys) {
        this.ortsVektor = ortsVektor;
        this.normalenVektor = new Vec3(0,0,1);
        this.mat = mat;
        width = xs;
        heigth = ys;
    }
    
    @Override
    public Hit intersect(Ray r) {
        Hit hit;
        Vec3 hitPoint;
        
        Vec3 a = Vec3.subtract(ortsVektor, r.origin);
            double b = Vec3.dotProduct(a, normalenVektor);
            double c = Vec3.dotProduct(r.normalizedDir, normalenVektor);
            double t = b / c;

        if (width != Double.POSITIVE_INFINITY && heigth != Double.POSITIVE_INFINITY) {
            if (t > 0 && r.contains(t)) {
                hitPoint = r.pointAt(t);
                hit = new Hit(t, hitPoint, new Vec3(0,0,1), mat);

                if ((Math.abs(hit.hitpoint.x - ortsVektor.x) <= width / 2) && (Math.abs(hit.hitpoint.y - ortsVektor.y) <= heigth / 2) && (hit.hitpoint.z == ortsVektor.z)) {
                    return hit;
                }
            }
        } else {
        if (t > 0 && r.contains(t)) {
                hitPoint = r.pointAt(t);
                hit = new Hit(t, hitPoint, normalenVektor, mat);
                return hit;
            }
        }
        return null;
    }
}
