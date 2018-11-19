package haridy850367.b01;

import cgtools.Random;
import cgtools.Vec3;

import static cgtools.Vec3.*;

public class Glass implements Material {

    private static final double GLASS = 1.5;
    private static final double AIR = 1.0;

    Vec3 albedo;

    public Glass(Vec3 albedo) {
        this.albedo = albedo;
    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
    	//reutrn null;
        return black;
    }
    @Override
    public Ray scatteredRay(Ray rayR, Hit hitH) {
        Vec3 d = rayR.direction;
        Vec3 n = hitH.normal;

        double n1 = AIR;
        double n2 = GLASS;

        double nd = dotProduct(d, n);
        if (nd > 0) {
            double x = n1;
            n1 = n2;
            n2 = x;
            n = multiply(-1, n);
        }

        double refraction = n1 / n2;
        double c = dotProduct(multiply(-1, n), d);
        double sqrt = 1 - (Math.pow(refraction, 2) * (1 - Math.pow(c, 2)));

        double R0 = Math.pow((n1 - n2) / (n1 + n2), 2);
        double sch = R0 + (1 - R0) * Math.pow(1 + dotProduct(d, n), 5);
        Ray ray;

        if (sqrt >= 0 && Random.random() > sch) {
            Vec3 rd = multiply(refraction, d);
            double paren = refraction * c - Math.sqrt(sqrt);
            Vec3 dt = add(rd, multiply(paren, n));
            ray = new Ray(hitH.pos, dt);
        } else {
            Vec3 dr = subtract(d, multiply(dotProduct(d, n) * 2, n));
            ray = new Ray(hitH.pos, dr);
        }

        return ray;
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
    	//return null;
        return albedo;
    }

}
