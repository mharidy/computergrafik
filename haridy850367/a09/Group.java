package haridy850367.a09;

import java.util.ArrayList;

public class Group implements Shape {

    private final ArrayList<Shape> shapes;
    private final Transformation t;

    public Group(ArrayList<Shape> shapes, Transformation t) {
        this.shapes = shapes;
        this.t = t;
    }

    @Override
    public Hit intersect(Ray r) {
        Hit hit, temp;
        hit = null;
        
        double ti = Double.POSITIVE_INFINITY;
        r = t.transformRay(r);
        for (Shape s : shapes) {
            temp = s.intersect(r);
                if(temp != null && temp.t <= ti) {
                    ti = temp.t;
                    hit = temp;
            }
        }
        if(hit != null) hit = t.transformHit(hit);
        return hit;
    }
}
