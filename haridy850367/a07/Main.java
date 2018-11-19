package haridy850367.a07;

import static cgtools.Mat4.*;
import cgtools.Vec3;
import static cgtools.Vec3.*;
import java.io.IOException;
import haridy850367.Image;
import java.util.ArrayList;

public class Main {

	static int width = 1280;
	static int height = 720;

	//static Camera cam = new Camera(90, 1280, 720, translate(0, 2, 5), new Vec3(-11, 0, 0));
    static Camera cam = new Camera(90, 1280, 720, translate(12,1.2,-4), new Vec3(0,65,0));

	// static Camera cam = new Camera(90, 1280, 720, translate(-1,15,7), new
	// Vec3(-50,0,0));
	// static Camera cam = new Camera(90, 1280, 720, translate(0, 10, -2), new
	// Vec3(-90, 2, 0));
	// translate(0,+elcamera tb3d lfo2 ,-FO2), new Vec3(-90,2,0)
	// static Camera cam = new Camera(90, 1280, 720, translate(0,2,5), new
	// Vec3(-11,0,0));

	static Background bg = new Background(new BackgroundMaterial(white));
	static Group gr;

	public static void main(String[] args) {
		setScene();
		raytrace(cam, gr, 100);
	}

	public static void raytrace(Camera cam, Group scene, int samplePoints) {
		Image image = new Image(width, height);

		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {

				image.setPixel(x, y, stratifiedSampling(x, y, samplePoints));
			}
		}
		String filename = "doc/a07-1.png";
		try {
			image.write(filename, 2.2);
			System.out.println("Wrote image: " + filename);
		} catch (IOException error) {
			System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
		}
	}

	public static Vec3 stratifiedSampling(int x, int y, int samplePoints) {
		double r = 0;
		double g = 0;
		double b = 0;

		for (int xi = 0; xi < Math.sqrt(samplePoints); xi++) {
			for (int yi = 0; yi < Math.sqrt(samplePoints); yi++) {
				double rx = Math.random();
				double ry = Math.random();
				double xs = x + (xi + rx) / Math.sqrt(samplePoints);
				double ys = y + (yi + ry) / Math.sqrt(samplePoints);

				Vec3 temp = pixelColor(xs, ys);
				r += temp.x;
				g += temp.y;
				b += temp.z;
			}
		}
		r = r / samplePoints;
		g = g / samplePoints;
		b = b / samplePoints;

		return vec3(r, g, b);
	}

	static Vec3 pixelColor(double x, double y) {
		Ray r = cam.generateRay(x, y);
		Vec3 color = calculateRadiance(gr, r, 100);
		return color;
	}

	public static Vec3 calculateRadiance(Shape scene, Ray ray, int depth) {
		if (depth == 0)
			return black;

		Hit hit = scene.intersect(ray);
		Vec3 emission = hit.material.emittedRadiance(ray, hit);
		Ray scattered = hit.material.scatteredRay(ray, hit);

		if (scattered != null)
			return multiply(add(emission, hit.material.albedo(ray, hit)), calculateRadiance(scene, scattered, depth - 1));
		else
			return emission;
	}

	public static void setScene() {
		Shape ground = new Plane(vec3(0.0, 0, 0.0), vec3(0, 1, 0), new Diffuse(new Vec3(1, 1, 1), gray));
		// Shape cx = new Cylinder(new Vec3(0, 0, -4), 1, 1.5, new Diffuse(new Vec3(1),
		// red));
		// Shape cx1 = new Cylinder(new Vec3(0, 1, -4), 0.5, 1, new Diffuse(new Vec3(1),
		// red));
		// Shape s1 = new Sphere(new Vec3(-2, 0, -4), 1, new Mirror(red, 0.2));
		// Shape s2 = new Sphere(new Vec3(2, 0, -4), 1, new Mirror(red, 0.2));

		// Shape s3 = new Sphere(new Vec3(-1, 0, -3), 0.5, new Mirror(red, 0.2));
		// Shape s4 = new Sphere(new Vec3(1, 0, -3), 0.5, new Mirror(red, 0.2));

		ArrayList<Shape> shapes = new ArrayList<>();
		shapes.add(bg);
		shapes.add(ground);
		// shapes.add(cx);
		// shapes.add(cx1);
		// shapes.add(s1);
		// shapes.add(s2);
		// shapes.add(s3);
		// shapes.add(s4);

		for (double i = 0; i <= 10; i++) {
			// Shape cy1 = new Cylinder(new Vec3(-5, 0, (-3.5 * i) + 2), 0.5, 1, new
			// Diffuse(new Vec3(1), darkbrown));
			// Shape cy2 = new Cylinder(new Vec3(5, 0, (-3.5 * i) + 2), 0.5, 1, new
			// Diffuse(new Vec3(1), darkbrown));
			Shape cx1 = new Cylinder(new Vec3(0, (1 + i), (-2.5 * i) - 2), 1, 1, new Diffuse(new Vec3(1), red));

			Shape globe7 = new Sphere(vec3(-2, 2, -3.5  + 2), 0.5, new Diffuse(new Vec3(1, 1, 1), blue));
			Shape globe7T = new Sphere(vec3(2, 2, -3.5 + 2), 0.5, new Diffuse(new Vec3(1, 1, 1), green));

			Shape c1 = new Cone(new Vec3(-5, 2, (-3.5 * i) + 2), 1, 2, new Diffuse(new Vec3(1), red));
			Shape c2 = new Cone(new Vec3(5, 2, (-3.5 * i) + 2), 1, 2, new Diffuse(new Vec3(1), blue));
			Shape c3 = new Cone(new Vec3(-5, 3.5, (-3.5 * i) + 2), 1, 1, new Diffuse(new Vec3(1), green));
			Shape c4 = new Cone(new Vec3(5, 3.5, (-3.5 * i) + 2), 1, 1, new Diffuse(new Vec3(1), green));

			Shape c5 = new Cone(new Vec3(-5, 3.7, (-3.5 * i) + 2), 1, 0.5, new Diffuse(new Vec3(1), green));
			Shape c6 = new Cone(new Vec3(5, 3.7, (-3.5 * i) + 2), 1, 0.5, new Diffuse(new Vec3(1), green));

			// Shape cx = new Cylinder(new Vec3(0, (0.2 * i), -4), 1, 1, new Diffuse(new
			// Vec3(1), red));

			if (i % 2 == 0) {
				c1 = new Cone(new Vec3(-5, 2, (-3.5 * i) + 2), 1, 2, new Diffuse(new Vec3(1), blue));
				c2 = new Cone(new Vec3(5, 3, (-3.5 * i) + 2), 1, 2, new Diffuse(new Vec3(1), yellow));
				c3 = new Cone(new Vec3(-5, 3.5, (-3.5 * i) + 2), 1, 1, new Diffuse(new Vec3(1), orange));
				c4 = new Cone(new Vec3(5, 3.5, (-3.5 * i) + 2), 1, 1, new Diffuse(new Vec3(1), darkgreen));

				c5 = new Cone(new Vec3(-5, 3.7, (-3.5 * i) + 2), 1, 0.5, new Diffuse(new Vec3(1), cyan));
				c6 = new Cone(new Vec3(5, 3.7, (-3.5 * i) + 2), 1, 0.5, new Diffuse(new Vec3(1), darkgreen));
			}

			shapes.add(c1);
			shapes.add(c2);
			shapes.add(c3);
			shapes.add(c4);
			shapes.add(globe7);
			shapes.add(globe7T);


			// shapes.add(cx);
			shapes.add(cx1);

			// shapes.add(c5);
			// shapes.add(c6);
			// shapes.add(cy1);
			// shapes.add(cy2);
		}
		gr = new Group(shapes);

	}
}
