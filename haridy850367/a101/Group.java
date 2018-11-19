package haridy850367.a101;

import java.util.ArrayList;
import java.util.Arrays;

public class Group implements Shape {

    private ArrayList<Shape> formList;
    private Transformation trans;

    Group(Transformation trans, Shape... shapes) {
        this.trans = trans;
        this.formList = new ArrayList<>();
        formList.addAll(Arrays.asList(shapes));
    }

    ArrayList<Shape> getFormList() {
        return formList;
    }

    public void setFormList(ArrayList<Shape> formList) {
        this.formList = formList;
    }

    void addShape(Shape shape) {
        this.formList.add(shape);
    }

    @Override
    public Hit intersect(Ray r) {
        Ray ray = trans.transformRay(r);

        Hit hit = null;

        for (Shape shape: formList) {
            Hit hitShape = shape.intersect(ray);
            if (hit == null) {
                if (hitShape != null) hit = hitShape;
            } else if (hitShape != null && hitShape.t < hit.t) hit = hitShape;
        }

        if (hit == null) return null;

        hit = trans.transformHit(hit);

        return hit;
    }

}
