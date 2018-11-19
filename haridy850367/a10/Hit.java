package haridy850367.a10;

import cgtools.Vec3;

public class Hit {
    
    public double t;
    public Vec3 hitpoint;
    public Vec3 normal;
    public Material mat;
    public Vec3 uv;
    
    public Hit(double t, Vec3 hitpoint, Vec3 normal,  Material mat){
        this.t = t;
        this.hitpoint = hitpoint;
        this.normal = normal;
      //  this.uv = uv;
        this.mat = mat;
    }
}
