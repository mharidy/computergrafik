package haridy850367.a10;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class BackgroundMaterial implements Material{
    
    private final Texture color;
    
    public BackgroundMaterial(Texture color){
        this.color = color;
    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
        return color.color(h.uv);
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        return null;
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
        return null;
    }
    
}
