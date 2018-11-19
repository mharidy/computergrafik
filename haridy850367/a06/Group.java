package haridy850367.a06;

import cgtools.Vec3;
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
            if(temp != null && (temp.temp > 0 && temp.temp <= hit.temp)) hit = temp;
        }
        //return null;
        return hit;
    }
}
