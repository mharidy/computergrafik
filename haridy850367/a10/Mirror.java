package haridy850367.a10;

import cgtools.Random;
import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Mirror implements Material{
    
    private final Vec3 albedo;
    private final double streuung;
    
    public Mirror(Vec3 albedo, double streuung) {
        this.albedo = albedo;
        this.streuung = streuung;
    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
        return new Vec3(0);
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
//        Vec3 d = normalize(r.normalizedDir);
//        Vec3 n = normalize(h.normal);
//        double x = dotProduct(n,d);
//        Vec3 t = multiply(2*x, n);
//        Vec3 erg = subtract(d, t);

        Vec3 re = subtract(normalize(r.normalizedDir), multiply(2*dotProduct(normalize(h.normal), normalize(r.normalizedDir)), normalize(h.normal)));

        if(streuung > 0) {
            Vec3 random = new Vec3(Random.random()*streuung, Random.random()*streuung, Random.random()*streuung);
            re = add(re, random);
        }  

        return new Ray(h.hitpoint, re, 0.00001, Double.POSITIVE_INFINITY);
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
        return albedo;
    }
    
}
