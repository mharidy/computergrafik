package haridy850367.a101;

public interface Material {

    Texture emittedRadiance(Ray r, Hit h);

    Ray scatteredRay(Ray r, Hit h);

    Texture albedo(Ray r, Hit h);

}
