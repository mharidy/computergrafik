package haridy850367.a05;


import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Ray {
    
    public Vec3 origin;
    
    public Vec3 normalizedDir; 
    
    public Ray(Vec3 origin, Vec3 normalizedDir){
        this.origin = origin;
        this.normalizedDir= normalizedDir;
    }
    
    public Vec3 pointAt(double t){
        Vec3 v = (add(origin, (multiply(t, normalizedDir))));
        return v;
    }
    
}
