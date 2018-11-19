package haridy850367.a07;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Plane implements Shape {

    private final Vec3 ortsVektor;
    private final Vec3 normalenVektor;
    private final Material material;

    public Plane(Vec3 ortsVektor, Vec3 normalenVektor, Material material) {
        this.ortsVektor = ortsVektor;
        this.normalenVektor = normalenVektor;
        this.material = material;
    }

    @Override
    public Hit intersect(Ray r) {
        double nenner = dotProduct(r.normalizedDir, normalenVektor);
        if (nenner == 0) {
            return null;
        }
        double t = dotProduct(subtract(ortsVektor, r.origin), normalenVektor) / nenner;

        if (t <= r.t0 || t > r.t1 || t <= 0) {
            return null;
        }

        Vec3 treffer = add(r.origin, multiply(t, r.normalizedDir));
        Hit hit= new Hit(t, treffer, normalenVektor, material);
		return hit;

    }
}
