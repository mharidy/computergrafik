package haridy850367.a10;

import cgtools.ImageTexture;
import cgtools.Vec3;
import haridy850367.Image;

import java.io.IOException;

public class BildTexture implements Texture {

    public ImageTexture bild;

    public BildTexture(String fileName){
        try {
            bild = new ImageTexture(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vec3 color(Vec3 uv) {
        double u = uv.x;// - Math.floor(uv.x);
        double v = uv.y; //- Math.floor(uv.y);
        return undoGamma(bild.samplePoint(u, v));
    }
    public Vec3 undoGamma(Vec3 color){
        double x = Math.pow(color.x, 2.2);
        double y = Math.pow(color.y, 2.2);
        double z = Math.pow(color.z, 2.2);
        return new Vec3(x,y,z);
    }
}

