package haridy850367.a101;

import cgtools.Vec3;

public class Hintergrund implements Material {

    private Texture t;

    public Hintergrund(Texture t) {
        this.t = t;
    }

    @Override
    public Texture emittedRadiance(Ray r, Hit h) { return t; }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        return null;
    }

    @Override
    public Texture albedo(Ray r, Hit h) {
        return null;
    }

}
