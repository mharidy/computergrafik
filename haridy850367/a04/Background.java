package haridy850367.a04;

import cgtools.Vec3;

public class Background implements Shape {

	private Vec3 color;

	public Background(Vec3 color) {
		this.color = color;
	}

	@Override
	public Hit intersect(Ray r) {
		// return new Hit(t1, new Vec3(t1, t1, t1), r.normalizedDir, color);

		// msh htnf3 3shan mfhash Positive

		// return new Hit(Double.POSITIVE_INFINITY, new Vec3(Double.POSITIVE_INFINITY),
		// new Vec3(Double.POSITIVE_INFINITY), color);
		
		//Double.POSITIVE_INFINITY check if it between the min and max positive double 
		
		Hit hit = new Hit(Double.POSITIVE_INFINITY, new Vec3(Double.POSITIVE_INFINITY),
				r.pointAt(Double.POSITIVE_INFINITY), color);
		// return null;
		
		return hit;
	}
	
}