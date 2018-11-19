package haridy850367.a07;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Ray {
    
    public Vec3 origin; 
    public Vec3 normalizedDir; 
    public double t0;
    public double t1;
    
    public Ray(Vec3 origin, Vec3 normalizedDir, double t0, double t1){
        this.origin = origin;
        this.normalizedDir= normalizedDir;
        this.t0 = t0;
        this.t1 = t1;
    }
    
    public Vec3 pointAt(double t){
        Vec3 v = (add(origin, (multiply(t, normalizedDir))));
        return v;
    }
    
    public boolean contains(double t) {
        if(t > t0 && t < t1) return true;
		//return false;
        else return false;
		//else return true;
    }
    
}
