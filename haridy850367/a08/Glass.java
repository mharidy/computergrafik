package haridy850367.a08;

import cgtools.Random;
import cgtools.Vec3;
import static cgtools.Vec3.*;


public class Glass implements Material{
    
    private final Vec3 albedo;
    private final double brIndexLuft = 1.0;
    public final double brIndex2;
    
    public Glass() {
        albedo = white;
        brIndex2 = 1.5;
    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
    	//return null;
        return black;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        Vec3 scat = null;
        Vec3 n = h.normal;
        Ray temp;
        double n1 = brIndexLuft;
        double n2 = brIndex2;
        
        if(dotProduct(n, r.normalizedDir) > 0) {
            n2 = brIndexLuft;
            n1 = brIndex2;
            n = multiply(-1.0, n);
        }
        if(refract(r.normalizedDir, n, n1, n2) != null) {
            if(Random.random() > schlick(r.normalizedDir, n, n1, n2)) {
                scat = refract(r.normalizedDir, n, n1, n2);
            } else {
                temp = reflect(r, h);
                return temp;
            }
        } else {
            temp = reflect(r, h);
            return temp;
        }
        return new Ray(h.hitpoint, scat, 0.0001, Double.POSITIVE_INFINITY);
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
        return albedo;
    }
    
    public double schlick(Vec3 d, Vec3 n, double n1, double n2){
        Vec3 dn = normalize(d);
        Vec3 nn = normalize(n);
        double r0 = Math.pow((n1 - n2) / (n1 + n2), 2);
        double result = r0 + (1 - r0) * Math.pow((1+dotProduct(dn, nn)), 5);
        return result;
    }
    
    public Vec3 refract(Vec3 d, Vec3 n, double n1, double n2) {
        Vec3 dn = normalize(d);
        Vec3 nn = normalize(n);
        double r = n1 / n2;
        double c = -1.0 * dotProduct(dn, nn);
		//double c = 1.0 * dotProduct(dn, nn);
        
        double x = ((1 - Math.pow(r, 2) * (1 - Math.pow(c, 2))));
        if(x > 0) {
            Vec3 result = add(multiply(r,dn), multiply(r * c - Math.sqrt(x), nn));
            return result;
        } else { 
            return null;
        }
    }
    
    public Ray reflect(Ray r, Hit h) {
        Vec3 re = subtract(normalize(r.normalizedDir), multiply(2*dotProduct(normalize(h.normal), normalize(r.normalizedDir)), normalize(h.normal)));
        Ray ray= new Ray(h.hitpoint, re, 0.00001, Double.POSITIVE_INFINITY);
		return ray;
    }
}