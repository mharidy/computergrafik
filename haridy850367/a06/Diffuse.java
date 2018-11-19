package haridy850367.a06;

import cgtools.Random;
import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Diffuse implements Material {

    private final Vec3 albedo;
    private final Vec3 color;

    public Diffuse(Vec3 albedo, Vec3 color) {
        this.albedo = albedo;
        this.color = color;
    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
    	//return null;
        return black;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        Vec3 random; 
        Ray scattered = null;
        while (true) {
            random = new Vec3(Random.random()*2-1, Random.random()*2-1, Random.random()*2-1);
            if (random.length() <= 1) {
                scattered = new Ray(h.hitpoint, add(normalize(h.normal), random), r.t0, r.t1);
                break;
            }
        }
        return scattered;
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
    	//return null;
        return color;
    }

}
