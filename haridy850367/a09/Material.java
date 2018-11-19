package haridy850367.a09;

import cgtools.Vec3;
public interface Material{    
    Vec3 emittedRadiance(Ray r, Hit h);
    Ray scatteredRay(Ray r, Hit h);
    Vec3 albedo(Ray r, Hit h);
}
