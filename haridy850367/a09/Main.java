package haridy850367.a09;

import static cgtools.Mat4.*;
import cgtools.Vec3;
import static cgtools.Vec3.*;
import java.io.IOException;
import haridy850367.Image;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	// double myStartTime = System.nanoTime();

	final static int width = 1280;
	final static int height = 720;
	static int samplePoints = 100;
	static int radianceDepth = 50;
	static double myStartTime = System.nanoTime();

	static Camera cam = new Camera(120, width, height, translate(0, 2, 5), new Vec3(-10, 0, 0));
	static Background bg = new Background(new BackgroundMaterial(vec3(0.8, 1, 1)));
	static Group gr;

	public static void main(String[] args) {
		setScene();
		raytrace(cam, gr, samplePoints);
	}

	public static void raytrace(Camera cam, Group scene, int samplePoints) {
		// int cores = Runtime.getRuntime().availableProcessors();
		int cores = 4;

		Thread[] threads = new Thread[cores];

		ArrayList<Image> imgs = new ArrayList<>();
		Image image = new Image(width, height);

		for (int i = 0; i != cores; i++) {
			threads[i] = new Thread() {
				public void run() {

					for (int x = 0; x != width; x++) {
						for (int y = 0; y != height; y++) {
							image.setPixel(x, y, stratifiedSampling(x, y, samplePoints / cores));
						}
					}
					imgs.add(image);
				}
			};
			threads[i].start();
		}

		for (int i = 0; i != cores; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Vec3[] colorArr = new Vec3[cores];
		double r = 0;
		double g = 0;
		double b = 0;
		String filename = "doc/a09-benchmark-scene.png";
		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				for (int i = 0; i != cores; i++) {
					colorArr[i] = imgs.get(i).getPixel(x, y);
					r += colorArr[i].x;
					g += colorArr[i].y;
					b += colorArr[i].z;
				}
				r = r / cores;
				g = g / cores;
				b = b / cores;
				Vec3 color = new Vec3(r, g, b);
				image.setPixel(x, y, color);
			}
		}
		try {
			image.write(filename, 2.2);
			System.out.println("Wrote image: " + filename);
			// double xa = Runtime.getRuntime().availableProcessors();
			// double myEndTime = System.nanoTime();
			long duration = (long) (System.nanoTime() - myStartTime);
			System.out.println("d=  " + duration);
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
		Vec3 color = calculateRadiance(gr, r, radianceDepth);
		return color;
	}

	public static Vec3 calculateRadiance(Shape scene, Ray ray, int depth) {
		if (depth == 0) {
			return black;
		}

		Hit hit = scene.intersect(ray);
		Vec3 emission = hit.material.emittedRadiance(ray, hit);
		Ray scattered = hit.material.scatteredRay(ray, hit);

		if (scattered != null) {
			return multiply(add(emission, hit.material.albedo(ray, hit)),
					calculateRadiance(scene, scattered, depth - 1));
		} else {
			return emission;
		}
	}

	public static void setScene() {
		ArrayList<Shape> base = new ArrayList<>();
		Shape ground = new Plane(vec3(0.0, 0, 0.0), vec3(0, 1, 0), new Diffuse(new Vec3(1, 1, 1), gray));
		base.add(ground);
		base.add(bg);
		Group baseG = new Group(base, new Transformation(identity));

		// ---------------
		ArrayList<Shape> shapes = new ArrayList<>();
		ArrayList<Shape> shapesL = new ArrayList<>();
		ArrayList<Shape> shapesR = new ArrayList<>();
		ArrayList<Shape> shapesN = new ArrayList<>();

		Shape s1 = new Sphere(new Vec3(-2, 0, -4), 1, new Mirror(red, 0.2));
		Shape s2 = new Sphere(new Vec3(2, 0, -4), 1, new Mirror(red, 0.2));
		shapes.add(s1);
		shapes.add(s2);
		double lastPos = 0;
		for (double i = 0; i <= 5; i++) {
			Shape cy1 = new Cylinder(new Vec3(0, 0, -25), 1.5, 15, new Diffuse(new Vec3(1), blue));
			lastPos = (-4 * i);
			// System.out.println(lastPos);
			shapesN.add(cy1);
			Shape cy2 = new Cylinder(new Vec3(0, 0, -27), 0.5, 20, new Diffuse(new Vec3(1), red));
			shapesN.add(cy2);
			Shape cy3 = new Cylinder(new Vec3(0, 0, -29), 0.2, 70, new Diffuse(new Vec3(1), yellow));
			shapesN.add(cy3);
			double xa = Runtime.getRuntime().availableProcessors();
			System.out.println("fe el for" + xa);

			if (!(i % 2 == 0)) {
				Shape c1 = new Cone(new Vec3(-5, 3, (-4 * i) + 2), 1, 2, new Diffuse(new Vec3(1), blue));
				Shape c2 = new Cone(new Vec3(5, 3, (-4 * i) + 2), 1, 2, new Diffuse(new Vec3(1), green));
				Shape c3 = new Cone(new Vec3(-5, 3.5, (-4 * i) + 2), 1, 1, new Diffuse(new Vec3(1), blue));
				Shape c4 = new Cone(new Vec3(5, 3.5, (-4 * i) + 2), 1, 1, new Diffuse(new Vec3(1), green));
				shapesL.add(c1);
				shapesR.add(c2);
				shapesL.add(c3);
				shapesR.add(c4);
			} else {
				Shape c1 = new Cone(new Vec3(-5, 3, (-4 * i) + 2), 1, 2, new Diffuse(new Vec3(1), blue));
				Shape c2 = new Cone(new Vec3(5, 3, (-4 * i) + 2), 1, 2, new Diffuse(new Vec3(1), darkgreen));
				Shape c3 = new Cone(new Vec3(-5, 3.5, (-4 * i) + 2), 1, 1, new Diffuse(new Vec3(1), blue));
				Shape c4 = new Cone(new Vec3(5, 3.5, (-4 * i) + 2), 1, 1, new Diffuse(new Vec3(1), darkgreen));
				shapesL.add(c1);
				shapesR.add(c2);
				shapesL.add(c3);
				shapesR.add(c4);
			}

		}

		Group shapesLG = new Group(shapesL,
				new Transformation(translate(0, -2, 0).multiply(rotate(new Vec3(0, 0, 1), -30))));
		Group shapesRG = new Group(shapesR,
				new Transformation(translate(0, -2, 0).multiply(rotate(new Vec3(0, 0, 1), 30))));
		ArrayList<Shape> scene = new ArrayList<>();
		scene.addAll(shapesN);
		scene.add(shapesLG);
		scene.add(shapesRG);
		scene.add(baseG);
		gr = new Group(scene, new Transformation(identity));
		System.out.println("testaawey  " +  scene.size());
	}

}
