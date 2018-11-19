package haridy850367.a10;

import cgtools.Vec3;
import static cgtools.Vec3.*;

public class Cylinder implements Shape {
    
    public Vec3 center;
    public double radius;
    public double height;
    private final Material mat;
    
    public Cylinder(Vec3 center, double radius, double height, Material mat){
        this.center = center;
        this.radius = radius;
        this.height = height;
        this.mat = mat;
    }

    @Override
    public Hit intersect(Ray r) {
        Vec3 newOrigin = subtract(r.origin, center);
        
        double a = Math.pow(r.normalizedDir.x, 2) + Math.pow(r.normalizedDir.z, 2);
        double b = (2 * newOrigin.x * r.normalizedDir.x) + (2 * newOrigin.z * r.normalizedDir.z);
        double c = Math.pow(newOrigin.x, 2) + Math.pow(newOrigin.z, 2) - Math.pow(radius, 2);
        
        double sqrt = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
        if (Double.isNaN(sqrt)) return null;

        double t1 = (-b + sqrt) / (2 * a);
        double t2 = (-b - sqrt) / (2 * a);
        double strahlParameter;
        
        double temp = t1;
        if(t1>t2) t1=t2; t2=temp;
        
        double y1 = newOrigin.y + t1 * r.normalizedDir.y;
        double y2 = newOrigin.y + t2 * r.normalizedDir.y;
      
        
        if(y1 < -height) {
            if(y2 < -height) return null;
            else {
                double th = t1 + (t2-t1) * (y1+1) / (y1-y2);
                if (th<=0) return null;
                Vec3 hi = r.pointAt(th);
                Vec3 nor = divide(subtract(hi, center), radius);
                return new Hit(th, hi, nor, mat);
            }
        }
        if(y1 >= -height && y1 <= height) {
            if(t1<=0) return null;
            
            Vec3 hi = r.pointAt(t1);
            Vec3 nor = divide(subtract(hi, center), radius);
            return new Hit(t1, hi, nor, mat);
            
        }
        if(y1>height) {
            if(y2>height) return null;
            
            double th = t1 + (t2-t1) * (y1-1) / (y1-y2);
            if (th<=0) return null;
            
            Vec3 hi = r.pointAt(th);
            Vec3 nor = divide(subtract(hi, center), radius);
            return new Hit(th, hi, nor, mat);
        }
        return null;

    }
}
