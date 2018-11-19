package haridy850367.a10;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Background implements Shape {

    private final BackgroundMaterial mat;

    public Background(BackgroundMaterial mat) {
        this.mat = mat;
    }

    @Override
    public Hit intersect(Ray r) {
        double inclination = Math.acos(r.normalizedDir.y);
        double azimuth = Math.PI + Math.atan2(r.normalizedDir.x, r.normalizedDir.z);
        double u = azimuth / (2 * Math.PI);
        double v = inclination / Math.PI;
        //return new Hit(r.t1, r.pointAt(r.t1), multiply(-1 ,r.normalizedDir), new Vec3(u,v,0), mat);

        return new Hit(Double.POSITIVE_INFINITY, new Vec3(Double.POSITIVE_INFINITY), r.pointAt(Double.POSITIVE_INFINITY), mat);
}
}
