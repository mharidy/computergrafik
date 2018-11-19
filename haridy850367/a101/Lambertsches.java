package haridy850367.a101;

import cgtools.Random;
import cgtools.Vec3;

import static cgtools.Vec3.*;

import cgtools.ImageTexture;

public class Lambertsches implements Material {

    Texture t;

    Lambertsches(Texture planetPic2) {
        this.t = planetPic2;
    }

    @Override
    public Texture emittedRadiance(Ray r, Hit h) {
        return new Constant(black);
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        Vec3 drnd = vec3(Random.random() * 2 - 1, Random.random() * 2 - 1, Random.random() * 2 - 1);

        return new Ray(h.position, normalize(add(normalize(h.normal), drnd)));
    }

    @Override
    public Texture albedo(Ray r, Hit h) {
        return t;
    }
}
