package haridy850367.a10;

import cgtools.Vec3;

public class ConstTexture implements Texture {

    public Vec3 color;
    public ConstTexture(Vec3 color){
        this.color = color;
    }
    public Vec3 color(Vec3 uv){
        return color;
    }
}
