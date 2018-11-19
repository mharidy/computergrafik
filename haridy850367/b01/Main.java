package haridy850367.b01;

import cgtools.Mat4;
import cgtools.Random;
import cgtools.Vec3;
import haridy850367.Image;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static cgtools.Vec3.*;

public class Main {

	private static int sampling=100;

	private static Camera cam;
	private static Group group;
	private static int Nr = 4;

	public static void main(String[] args) {
		int width = 1280;
		int height = 720;
		Image image = new Image(width, height);

		Mat4 cameraPos = Mat4.translate(vec3(0, 0, 15));

		raytrace(new Camera(Math.PI / 2, width, height, cameraPos), genObjects());

		int threads = 4;
		int cores = Runtime.getRuntime().availableProcessors();
		ExecutorService executorService = Executors.newFixedThreadPool(cores);
		Future[] futures = new Future[threads];

		double start = System.currentTimeMillis();

		for (int i = 0; i != cores; i++) {
			final int core = i;
			futures[i] = executorService.submit(() -> {
				for (int x = (width / Nr) * core; x < (width / Nr) * core + (width / Nr); x++) {
					for (int y = 0; y != height; y++)
						image.setPixelGamma(x, y, pixelColor(x, y));
				}
			});
		}

		for (int j = 0; j != threads; j++) {
			try {
				try {
					futures[j].get();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		String filename = "doc/b01.png";

		try {
			image.write(filename);
			System.out.println("Wrote image: " + filename);
		} catch (IOException error) {
			System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
		}

	}

	private static void raytrace(Camera c, Group g) {
		cam = c;
		group = g;
	}

	private static Group genObjects() {
		Group gr = new Group();

		// Glass 2odam
		gr.addShape(new Kugel(.3, vec3(-1.8, -.2, 11), new Metall(red, 0.1)));

		gr.addShape(new Kugel(.3, vec3(-1.5, 0.2, 11), new Glass(blue)));

		gr.addShape(new Kugel(.3, vec3(-1.2, 0.6, 11), new Metall(yellow, 0.1)));

		gr.addShape(new Kugel(.3, vec3(-0.8, 0.9, 11), new Glass(red)));

		gr.addShape(new Kugel(.3, vec3(-0.4, 0.6, 11), new Metall(blue, 0.1)));

		gr.addShape(new Kugel(.3, vec3(0, 0.2, 11), new Glass(yellow)));

		gr.addShape(new Kugel(.3, vec3(0.4, -0.2, 11), new Metall(red, 0.1)));

		gr.addShape(new Kugel(.3, vec3(0.8, 0.2, 11), new Glass(blue)));

		gr.addShape(new Kugel(.3, vec3(1.2, 0.6, 11), new Metall(yellow, 0.1)));

		gr.addShape(new Kugel(.3, vec3(1.6, 0.9, 11), new Glass(red)));

		// Flag
		gr.addShape(new Box(vec3(1.5, -.5, 13), 0.4, new Lamb(yellow)));

		gr.addShape(new Box(vec3(1.5, -.1, 13), 0.4, new Lamb(red)));

		gr.addShape(new Box(vec3(1.5, 0.3, 13), 0.4, new Lamb(black)));

		gr.addShape(new Box(vec3(-1.8, -.5, 13), 0.4, new Lamb(yellow)));

		gr.addShape(new Box(vec3(-1.8, -.1, 13), 0.4, new Lamb(red)));

		gr.addShape(new Box(vec3(-1.8, 0.3, 13), 0.4, new Lamb(black)));

		gr.addShape(new Plane(vec3(0, -0.5, 0), vec3(0, 1, 0), new Lamb(vec3(1.1))));
		
		gr.addShape(new Background(new Hintergrund(vec3(0.2))));

		return gr;
	}

	private static Vec3 pixelColor(int x, int y) {
		Vec3 bgColor = vec3(1);

		double n = Math.sqrt(sampling);
		for (int xi = 0; xi < n; xi++) {
			for (int yi = 0; yi < n; yi++) {
				double rx = Random.random();
				double ry = Random.random();
				double xs = x + ((xi + rx) / n);
				double ys = y + ((yi + ry) / n);
				bgColor = add(bgColor, pixelColor(xs, ys));
			}
		}

		bgColor = divide(bgColor, sampling);
		return bgColor;
	}

	private static Vec3 rad(Ray r, Shape shape, int depth) {
		if (depth == 0)
			return black;

		Hit hit = shape.intersect(r);

		Vec3 emission = hit.material.emittedRadiance(r, hit);
		Ray scattered = hit.material.scatteredRay(r, hit);

		if (scattered != null) {
			return add(emission, multiply(hit.material.albedo(r, hit), rad(scattered,shape, depth - 1)));
		} else
			return emission;
	}

	private static Vec3 pixelColor(double x, double y) {
		Vec3 bgColor;
		Ray ray = cam.generateRay(x, y);
		bgColor = rad(ray, group, 5);
		return bgColor;
	}

}
