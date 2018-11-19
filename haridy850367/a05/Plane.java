package haridy850367.a05;


import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Plane implements Shape {

    private final Vec3 ortsVektor;
    private final Vec3 normalenVektor;
    private final Material mat;

    public Plane(Vec3 ortsVektor, Vec3 normalenVektor, Material mat) {
        this.ortsVektor = ortsVektor;
        this.normalenVektor = normalenVektor;
        this.mat = mat;
    }

    @Override
    public Hit intersect(Ray r) {
        Vec3 a = subtract(ortsVektor, r.origin); // d - p
        double b = dotProduct(a, normalenVektor); 
        double c = dotProduct(r.normalizedDir, normalenVektor);
                double t = b / c;
        Vec3 hitpoint = null;
        Hit hit;

        if (t > 0) {
            hitpoint = r.pointAt(t);
            return new Hit(t, hitpoint, normalenVektor, mat);
        } else {
            return new Hit(0, null, null, mat);
        }
    }

}
