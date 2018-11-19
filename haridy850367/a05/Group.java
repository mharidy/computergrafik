package haridy850367.a05;


import cgtools.Vec3;
import static cgtools.Vec3.*;
import java.util.ArrayList;


public class Group implements Shape{
    
    private ArrayList<Shape> shapes;
    
    public Group(ArrayList<Shape> shapes){
        this.shapes = shapes;
    }

    @Override
    public Hit intersect(Ray r) {
        Hit hit, temp;
        hit = new Hit(Double.POSITIVE_INFINITY, null, null, null);
        
        for(Shape s : shapes) {
            temp = s.intersect(r);
            if(temp.t > 0 && temp.t <= hit.t) hit = temp;
        }
        return hit;
    }
}
