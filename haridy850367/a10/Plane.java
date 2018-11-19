package haridy850367.a10;

import cgtools.Vec3;

public class Plane implements Shape {
    Vec3 stuetzVektor;
    Vec3 normalenVektor;
    Material material;

    public Plane(Vec3 stuetzVektor, Vec3 normalenVektor, Material material) {
        this.stuetzVektor = stuetzVektor;
        this.normalenVektor = normalenVektor;
        this.material = material;
    }

    public Hit intersect(Ray r) {
        Vec3 a = Vec3.subtract(stuetzVektor, r.origin);
        double b = Vec3.dotProduct(r.origin, normalenVektor);
        double c = Vec3.dotProduct(a, normalenVektor);

        if (b == 0) return null;

        double ergebnis = c / b; //t = ((p - x0) * n) / d*n
        if (ergebnis < 0) return null;
        if (r.contains(ergebnis)) {
            Vec3 trefferPunkt = r.pointAt(ergebnis);

                /*return new Hit(
                        t, pos, normal,
                        new Vec3(pos.x / width + 0.5, pos.z / depth + 0.5, 0),
                        mat.get());*/

        }
        return null;
    }
}
