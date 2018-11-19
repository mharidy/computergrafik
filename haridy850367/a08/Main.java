package haridy850367.a08;

import static cgtools.Mat4.*;
import cgtools.Vec3;
import static cgtools.Vec3.*;
import java.io.IOException;
import haridy850367.Image;
import haridy850367.a08.Camera;

import java.util.ArrayList;

public class Main {

	final static int width = 1280;
	final static int height = 720;
	static int samplePoints = 50;
	static int radianceDepth = 100;
	// translate(0, -T7T +FO2, 2odam wara)
	// static Camera cam = new Camera(90, width, height, translate(0, 2, 5), new
	// Vec3(-11, 0, 0));

	static Camera camera = new Camera(120, 1280, 720, translate(12, 8, -5), new Vec3(-10, -25, 0));
	static Background bg = new Background(new BackgroundMaterial(white));
	static Group gr;

	public static void main(String[] args) {
		setScene();
		raytrace(camera, gr, samplePoints);
	}

	public static void raytrace(Camera cam, Group scene, int samplePoints) {
		String filename = "doc/a08-2.png";
		Image image = new Image(width, height);

		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {

				image.setPixel(x, y, stratifiedSampling(x, y, samplePoints));
			}
		}
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
		Ray r = camera.generateRay(x, y);
		Vec3 color = calculateRadiance(gr, r, radianceDepth);
		return color;
	}

	public static Vec3 calculateRadiance(Shape scene, Ray ray, int depth) {
		if (depth == 0)
			return black;

		Hit hit = scene.intersect(ray);
		Vec3 emission = hit.material.emittedRadiance(ray, hit);
		Ray scattered = hit.material.scatteredRay(ray, hit);

		if (scattered != null)
			return multiply(add(emission, hit.material.albedo(ray, hit)),
					calculateRadiance(scene, scattered, depth - 1));
		else
			return emission;
	}

	public static void setScene() {
		ArrayList<Shape> base = new ArrayList<>();
		Shape ground = new Plane(vec3(0.0, 0, 0.0), vec3(0, 1, 0), new Diffuse(new Vec3(0, 0, 0), gray));
		base.add(ground);
		base.add(bg);
		Group baseG = new Group(base, new Transformation(identity));

		// ---------------

		ArrayList<Shape> shapesL = new ArrayList<>();
		ArrayList<Shape> shapesR = new ArrayList<>();
		ArrayList<Shape> shapesM = new ArrayList<>();

		
		double lastPos = 0;
		for (double i = 0; i <= 10; i++) {
			// Shape cy1 = new Cylinder(new Vec3(-5, 0, (-4 * i) + 2), 0.5, 1, new
			// Diffuse(new Vec3(1),
			// darkbrown));M
			// Shape cy2 = new Cylinder(new Vec3(5, 0, (-4 * i) + 2), 0.5, 1, new
			// Diffuse(new Vec3(1),
			// darkbrown));M
			// shapesL.add(cy1);
			// shapesR.add(cy2); 

			if (!(i % 2 == 0)) {

				// Left
				Shape c1 = new Cone(new Vec3(-5, 3, (-4 * i) + 2), 1, 2, new Diffuse(new Vec3(1), black));
				Shape c3 = new Cone(new Vec3(-5, 3.5, (-4 * i) + 2), 1, 1, new Diffuse(new Vec3(1), black));
				// Mid
				Shape c2 = new Cone(new Vec3(0.5, 3, (-4 * i) + 2), 1, 2, new Diffuse(new Vec3(1), red));
				Shape c4 = new Cone(new Vec3(0.5, 3.5, (-4 * i) + 2), 1, 1, new Diffuse(new Vec3(1), red));
				// Right
				Shape c22 = new Cone(new Vec3(5, 3, (-4 * i) + 2), 1, 2, new Diffuse(new Vec3(1), yellow));
				Shape c44 = new Cone(new Vec3(5, 3.5, (-4 * i) + 2), 1, 1, new Diffuse(new Vec3(1), yellow));
				shapesL.add(c1);
				shapesM.add(c2);
				shapesL.add(c3);
				shapesM.add(c4);
				shapesR.add(c22);
				shapesR.add(c44);
				
			} else {
				// Left
				Shape c1 = new Cone(new Vec3(-5, 3, (-4 * i) + 2), 1, 2, new Diffuse(new Vec3(1), black));
				Shape c3 = new Cone(new Vec3(-5, 3.5, (-4 * i) + 2), 1, 1, new Diffuse(new Vec3(1), black));
				// Mid
				Shape c2 = new Cone(new Vec3(0.5, 3, (-4 * i) + 2), 1, 2, new Diffuse(new Vec3(1), red));
				Shape c4 = new Cone(new Vec3(0.5, 3.5, (-4 * i) + 2), 1, 1, new Diffuse(new Vec3(1), red));
				// Right
				Shape c22 = new Cone(new Vec3(5, 3, (-4 * i) + 2), 1, 2, new Diffuse(new Vec3(1), yellow));
				Shape c44 = new Cone(new Vec3(5, 3.5, (-4 * i) + 2), 1, 1, new Diffuse(new Vec3(1), yellow));
				// double te=-4*i;
				// System.out.println(te);

				lastPos = (-4 * i);
				shapesL.add(c1);
				shapesM.add(c2);
				shapesR.add(c22);
				shapesR.add(c44);
				shapesL.add(c3);
				shapesM.add(c4);
				// c5 = new Cone(new Vec3(-5,3.7,(-3.5*i)+2), 1, 0.5, new Diffuse(new Vec3(1),
				// darkgreen));
				// c6 = new Cone(new Vec3(5,3.7,(-3.5*i)+2), 1, 0.5, new Diffuse(new Vec3(1),
				// darkgreen));
			}
		}
		//System.out.println(lastPos);
		Shape s1 = new Sphere(new Vec3(0, 3.5, (lastPos + 1)), 2, new Mirror(white, 0.2));
		shapesM.add(s1);

		Group shapesLGroup = new Group(shapesL,
				new Transformation(translate(0, -2, 0).multiply(rotate(new Vec3(0, 0, 1), -30))));
		Group shapesMGroup = new Group(shapesM,
				new Transformation(translate(0, -2, 0).multiply(rotate(new Vec3(0, 0, 1), 0))));

		Group shapesRGroup = new Group(shapesR,
				new Transformation(translate(0, -2, 0).multiply(rotate(new Vec3(0, 0, 1), 30))));

		ArrayList<Shape> scene = new ArrayList<>();
		scene.add(shapesLGroup);
		scene.add(shapesMGroup);
		scene.add(shapesRGroup);
		scene.add(baseG);
		gr = new Group(scene, new Transformation(identity));
	}
}
