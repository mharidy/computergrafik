package haridy850367.a03;

import cgtools.*;
import static cgtools.Vec3.*;

public class Ray {

	public Vec3 s;
	public Vec3 dir;

	public Ray(Vec3 origin, Vec3 dir) {
		this.s = origin;
		this.dir = dir;
	}

	public Vec3 pointAt(double t) {
		Vec3 vec3 = (add(s, (multiply(t, dir))));
		return vec3;
	}

}