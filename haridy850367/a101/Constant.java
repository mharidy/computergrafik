package haridy850367.a101;

import cgtools.Vec3;

public class Constant implements Texture {

    Vec3 color;

    public Constant(Vec3 color) {
        this.color = color;
    }

    public Vec3 color(Vec3 uv) {
        return color;
    }

}
