package haridy850367.a08;

import cgtools.Mat4;

public class Transformation {

	private final Mat4 toWorld; 
	private final Mat4 toWorldN; 
	private final Mat4 fromWorld; 

	public Transformation(Mat4 transformation) {
		this.toWorld = transformation;
		this.toWorldN = transformation.invertFull().transpose();
		this.fromWorld = transformation.invertFull();
	}

	public Ray transformRay(Ray r) {
		Ray newRay = new Ray(fromWorld.transformPoint(r.origin), fromWorld.transformDirection(r.normalizedDir), r.t0,
				r.t1);
		return newRay;
	}

	public Hit transformHit(Hit h) {
		Hit newHit = new Hit(h.t, toWorld.transformPoint(h.hitpoint), toWorldN.transformDirection(h.normal),
				h.material);
		return newHit;
	}

}
