package haridy850367.a10;

import cgtools.Mat4;

public class Transformation {
    
    private final Mat4 toWorld;         //toWorld
    private final Mat4 toWorldN;        //toWorldN
    private final Mat4 fromWorld;       //fromWorld
    
    public Transformation(Mat4 transformation) {
        this.toWorld = transformation;
        this.toWorldN = transformation.invertFull().transpose();
        this.fromWorld = transformation.invertFull();
    }
    
    public Ray transformRay(Ray r) {
        Ray newR = new Ray(fromWorld.transformPoint(r.origin), fromWorld.transformDirection(r.normalizedDir), r.t0, r.t1);
        return newR;
    }
    
    public Hit transformHit(Hit h) {
       // Hit newH = new Hit(h.t, toWorld.transformPoint(h.hitpoint), toWorldN.transformDirection(h.normal), h.uv, h.mat);
        Hit newH = new Hit(h.t, toWorld.transformPoint(h.hitpoint), toWorldN.transformDirection(h.normal), h.mat);

    	return newH;
    }

}
