package haridy850367.a09;
import cgtools.Vec3;

public class BackgroundMaterial implements Material{
    
    private final Vec3 color;
    
    public BackgroundMaterial(Vec3 color){
        this.color = color;
    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
        return color;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        return null;
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
    	//return hit;
        return null;
    }
    
}
