package haridy850367.a09;

import cgtools.Vec3;

public class Hit {
    
    public double t;
    public Vec3 hitpoint;
    public Vec3 normal;
    public Material material;
    
    public Hit(double t, Vec3 hitpoint, Vec3 normal, Material material){
        this.t = t;
        this.hitpoint = hitpoint;
        this.normal = normal;
        this.material = material;
    }
}
