package haridy850367.a04;

import cgtools.Vec3;
import static cgtools.Vec3.*;
import java.io.IOException;
import haridy850367.Image;
import java.util.ArrayList;

public class Main {

	static int width = 1600;
	static int height = 900;
	// static final int samplePoints = 10;

	static Kamera kamera = new Kamera(100, 1600, 900);
	//static Background bg = new Background(new Vec3(0.0, 0.0, 0.0));
	static Group gr;

	public static void main(String[] args) {
		setScene();
		raytrace(kamera, gr, 100);
	}

	static Vec3 pixelColor(double x, double y) {
		Ray r = kamera.generateRay(x, y);
		Hit h = gr.intersect(r);
		return h.color;
	}
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/*
	 * list = new ArrayList<>(); for (int i = 0; i < 100; i++) { list.add(new
	 * Rauten()); }
	 * 
	 * Collections.sort(list, new Comparator<Rauten>() { public int compare(Rauten
	 * r1, Rauten r2) { if (r1.getArea() == r2.getArea()) { return 0; } else if
	 * (r1.getArea() > r2.getArea()) { return 1; } else { return -1; } } });
	 * 
	 * // System.out.println("xpos1111= " + xpos);
	 * 
	 * // for (int i = 0; i < 5; i++) { // System.out.println("xpos222= " + xpos);
	 * 
	 * for (int x = 0; x != width; x++) { for (int y = 0; y != height; y++) {
	 * image.setPixel(x, y, pixelColor(x, y)); // image.setPixel(x, y, pixelColor(x,
	 * y, 3, 3)); // x , y the same pixel } }
	 * 
	 * 
	 * 
	 */

	// System.out.println("xpos333= " + xpos);
	// xpos++;
	// ypos++;
	// if (i == 4) {
	// System.out.println("x= " + xpos);
	// System.out.println("y= " + ypos);
	/*
	 * for (Rauten rauten : list) { sampl(rauten);
	 * 
	 * }
	 */

	public static Vec3 sampl(int x, int y, int samplePoints) {
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

	public static void setScene() {
		/*
		 * A4.1
		 * 
		 * 
		 * Shape ground = new Plane(vec3(0.0, -1.0, 0.0), vec3(0, 1, -0.2), white);
		 * Shape k1 = new Sphere(vec3(1, -2.0, -5.0), 1, blue); Shape k2 = new
		 * Sphere(vec3(0.0, -2.1, -5.0), 1, green); Shape k3 = new Sphere(vec3(-0.5,
		 * -2.0, -5.0), 1, red); ArrayList<Shape> shapes = new ArrayList<>();
		 * shapes.add(ground); shapes.add(k1); shapes.add(k2); shapes.add(k3); gr = new
		 * Group(shapes);
		 */

		Shape ground = new Plane(vec3(0.0, -1.0, 0.0), vec3(0, 1, -0.2), white);
		Shape sky = new Plane(vec3(0.0, 1.0, 0.0), vec3(0, 1, 0.2), new Vec3(0.15, 0.15, 0.15));
		Shape left = new Plane(vec3(-1.3, 0.0, 0.0), vec3(1, 0, -0.3), new Vec3(0.3, 0.3, 0.3));
		Shape right = new Plane(vec3(1.3, 0.0, 0.0), vec3(1, 0, 0.3), new Vec3(0.3, 0.3, 0.3));

		// Shape globe1 = new Sphere(vec3(1.0, 2, -6), 1, black);
		Shape globe111 = new Sphere(vec3(-1.5, -2, -3.0), 0.5, yellow);
		Shape globe22 = new Sphere(vec3(-0.5, -2, -3.0), 0.5, yellow);
		Shape globe33 = new Sphere(vec3(0.5, -2, -3.0), 0.5, yellow);
		Shape globe44 = new Sphere(vec3(1.5, -2, -3.0), 0.5, yellow);
		
		
		Shape globet1 = new Sphere(vec3(-1.5, -2, -2.5), 0.55, red);
		Shape globet2 = new Sphere(vec3(-0.5, -2, -2.5), 0.55, red);
		Shape globet3 = new Sphere(vec3(0.5, -2, -2.5), 0.55, red);
		Shape globet4 = new Sphere(vec3(1.5, -2, -2.5), 0.55, red);

		Shape globe1 = new Sphere(vec3(-1.5, -2, -4.0), 0.5, yellow);
		Shape globe2 = new Sphere(vec3(-0.5, -2, -4.0), 0.5, yellow);
		Shape globe3 = new Sphere(vec3(0.5, -2, -4.0), 0.5, yellow);
		Shape globe4 = new Sphere(vec3(1.5, -2, -4.0), 0.5, yellow);

		Shape globe5 = new Sphere(vec3(-0.1, -1.2, -4.45), 0.45, red);
		Shape globe6 = new Sphere(vec3(-1.1, -1.2, -4.5), 0.45, red);
		Shape globe7 = new Sphere(vec3(0.9, -1.2, -4.42), 0.45, red);
		// Container

		Shape globe8 = new Sphere(vec3(-0.6, -0.35, -4.7), 0.5, yellow);
		Shape globe9 = new Sphere(vec3(0.4, -0.35, -4.7), 0.5, yellow);

		Shape globe10 = new Sphere(vec3(-0.05, 0.5, -5), 0.45, red);
////////////////////////////////////////////////////////////////////////////// Ground done
		//left
		Shape globeL1 = new Sphere(vec3(-2.1, 0.5, -4.5), 0.5, white);
		Shape globeL2 = new Sphere(vec3(-2.2, 0.5, -2.5), 0.4, white);
		Shape globeL3 = new Sphere(vec3(-2.2, 0.5, -1.75), 0.38, white);
		///////////////////////////////////////////////////////////////////////////// left done
		// Right
		
		Shape globeR1 = new Sphere(vec3(2.1, 0.5, -4.5), 0.5, white);
		Shape globeR2 = new Sphere(vec3(2.2, 0.5, -2.5), 0.4, white);
		Shape globeR3 = new Sphere(vec3(2.2, 0.5, -1.75), 0.38, white);
		
//////////////////////////////////////////////////////////////////////// Right done
		
		Shape globe12 = new Sphere(vec3(-1.5, 3, -5), 0.5, red);
		Shape globe13 = new Sphere(vec3(-3, -3, -4), 0.75, red);

		/*
		 * Shape globe4 = new Sphere(vec3(0.5, -2, -6.0), 0.5, yellow); Shape globe5 =
		 * new Sphere(vec3(0.0, -2, -6.0), 0.5, yellow); Shape globe6 = new
		 * Sphere(vec3(-1, -2, -6.0), 0.5, yellow); Shape globe7 = new Sphere(vec3(2,
		 * -2, -6.0), 0.5, yellow); Shape globe8 = new Sphere(vec3(-3, -2, -6.0), 0.5,
		 * yellow); Shape globe9 = new Sphere(vec3(-4, -2, -6.0), 0.5, yellow);
		 * 
		 * 
		 */
		// Shape globe10 = new Sphere(vec3(0.0, -2, -6.0), 0.5, yellow);

		/*
		 * Shape globe3 = new Sphere(vec3(3, 0, -6.0), 0.5, blue); Shape globe4 = new
		 * Sphere(vec3(-3, 0, -6.0), 0.5, blue);
		 * 
		 * Shape globe5 = new Sphere(vec3(0.0, 1.9, -4.0), 0.5, green); Shape globe6 =
		 * new Sphere(vec3(0.0, -1.9, -4.0), 0.5, green); Shape globe7 = new
		 * Sphere(vec3(2.6, 0, -4.0), 0.5, orange); Shape globe8 = new Sphere(vec3(-2.6,
		 * 0, -4.0), 0.5, orange);
		 * 
		 *
		 */
		ArrayList<Shape> shapes = new ArrayList<>();
		//shapes.add(bg);
		//bg l3'ytha 3shan el2awalany
		shapes.add(ground);
		shapes.add(sky);
		shapes.add(left);
		shapes.add(right);
		shapes.add(globe1);
		shapes.add(globe2);
		shapes.add(globe3);
		shapes.add(globe4);
		shapes.add(globe5);
		shapes.add(globe6);
		shapes.add(globe7);
		shapes.add(globe8);
		shapes.add(globe9);
		shapes.add(globe10);
		shapes.add(globeL1);
		shapes.add(globe12);
		shapes.add(globe13);
		
		shapes.add(globe111);
		shapes.add(globe22);
		shapes.add(globe33);
		shapes.add(globe44);
		
		shapes.add(globet1);
		shapes.add(globet2);
		shapes.add(globet3);
		shapes.add(globet4);
		
		shapes.add(globeL2);
		shapes.add(globeL3);
		
		shapes.add(globeR1);
		shapes.add(globeR2);
		shapes.add(globeR3);




		/*
		 * shapes.add(globe4); shapes.add(globe5); shapes.add(globe6);
		 * shapes.add(globe7); shapes.add(globe8); shapes.add(globe9);
		 */
		// shapes.add(globe10);
		// shapes.add(globe11);
		// shapes.add(globe12);
		gr = new Group(shapes);
	}

	public static void raytrace(Kamera cam, Group scene, int samplePoints) {
		Image image = new Image(width, height);

		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {

				image.setPixel(x, y, sampl(x, y, samplePoints));
				// image.setPixel(x, y, pixelColor(x, y)); / Before sampling
			}
		}
		String filename = "doc/TEST-N.png";
		try {
			image.write(filename, 2.2);
			System.out.println("Wrote image: " + filename);
		} catch (IOException error) {
			System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
		}
	}
}
