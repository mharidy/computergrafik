package haridy850367.a07;


import cgtools.Vec3;
import static cgtools.Vec3.*;


public class BackgroundMaterial implements Material{
    
    private final Vec3 color;
    
    public BackgroundMaterial(Vec3 color){
        this.color = color;
    }

    @Override
    public Vec3 emittedRadiance(Ray ray, Hit hit) {
        return color;
    }

    @Override
    public Ray scatteredRay(Ray ray, Hit hit) {
        return null;
    }

    @Override
    public Vec3 albedo(Ray ray, Hit hit) {
		//return h,r;
        return null;
    }
    
}
