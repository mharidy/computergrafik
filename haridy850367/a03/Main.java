package haridy850367.a03;

import cgtools.Vec3;
import static cgtools.Vec3.*;
import java.io.IOException;
import haridy850367.Image;

public class Main {

	static int width = 160;
	static int height = 90;
	static final int samplingPoints = 100;
	static LochKamera lochKamera = new LochKamera(100, 160, 90);
	static Kugel kugel = new Kugel(new Vec3(0, 0, -50), 25);

	public static void main(String[] args) {
		Image image = new Image(width, height);
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

		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {

				image.setPixel(x, y, sampl(x, y));
				// image.setPixel(x, y, pixelColor(x, y));
			}
		}
		String filename = "doc/a03-one-sphere.png";
		try {
			image.write(filename, 2.2);
			System.out.println("Wrote image: " + filename);
		} catch (IOException error) {
			System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
		}
		/*
		 * String filename = "doc/a01-polka-dots.png"; try { image.write(filename);
		 * System.out.println("Wrote image: " + filename); } catch (IOException error) {
		 * System.out.println(String.format("Something went wrong writing: %s: %s",
		 * filename, error)); }
		 */

	}

	static Vec3 pixelColor(double x, double y) {
		Vec3 color;
		Ray ray = lochKamera.Raygenerator(x, y);
		Hit hit = kugel.intersect(ray);
		if (hit != null) {
			color = hit.normal;
		} else {
			// color = new Vec3(1, 1, 1);
			color = new Vec3(225, 0, 0);
		}
		return color;
	}

	public static Vec3 sampl(int x, int y) {
		double r = 0;
		double g = 0;
		double b = 0;

		for (int xi = 0; xi < Math.sqrt(samplingPoints); xi++) {
			for (int yi = 0; yi < Math.sqrt(samplingPoints); yi++) {
				
				double rx = cgtools.Random.random();
				double ry = cgtools.Random.random();
				//double rx = Math.random();
				//double ry = Math.random();
				double xs = x + (xi + rx) / Math.sqrt(samplingPoints);
				double ys = y + (yi + ry) / Math.sqrt(samplingPoints);

				Vec3 temp = pixelColor(xs, ys);
				r = r + temp.x;
				g = g + temp.y;
				b = b + temp.z;
			}
		}
		r /= samplingPoints;
		g /= samplingPoints;
		b /= samplingPoints;
		// after sampling
		return vec3(r, g, b);
	}
}
