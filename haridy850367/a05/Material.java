package haridy850367.a05;
import cgtools.Vec3;

public interface Material{
    // bs el interfaces
    Vec3 emittedRadiance(Ray r, Hit h);
    Ray scatteredRay(Ray r, Hit h);
    Vec3 albedo(Ray r, Hit h);
    
}
