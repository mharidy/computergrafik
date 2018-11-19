package haridy850367.a101;

import cgtools.Mat4;
import cgtools.Vec3;

public class Textu implements Texture {

    Texture texture;
    Mat4 transform;

    public Textu(Texture texture, Mat4 transform) {
        this.texture = texture;
        this.transform = transform;
    }

    public Vec3 color(Vec3 uv) {
        return texture.color(transform.transformPoint(uv));
    }

}
