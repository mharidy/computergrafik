package haridy850367.b01;

import java.util.ArrayList;
import java.util.Arrays;

public class Group implements Shape {

	private ArrayList<Shape> list;

	Group(Shape... shapes) {
		this.list = new ArrayList<>();
		list.addAll(Arrays.asList(shapes));
	}

	ArrayList<Shape> getFormList() {

		return list;
	}

	public void setFormList(ArrayList<Shape> formList) {
		this.list = formList;
	}

	void addShape(Shape shape) {
		this.list.add(shape);
	}

	@Override
	public Hit intersect(Ray ray) {
		Hit hit = null;

		for (Shape shape : list) {
			Hit hitShape = shape.intersect(ray);
			if (hit == null) {
				if (hitShape != null)
					hit = hitShape;
			} else if (hitShape != null && hitShape.t < hit.t)
				hit = hitShape;
		}

		return hit;
		// return null;
	}

}
