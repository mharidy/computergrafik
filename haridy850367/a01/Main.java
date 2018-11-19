package haridy850367.a01;

import cgtools.Vec3;
import haridy850367.Image;
import haridy850367.a03.Hit;
import haridy850367.a03.LochKamera;
import haridy850367.a03.Ray;
import haridy850367.a03.Sphere;

import static cgtools.Vec3.*;

import java.io.IOException;
import java.util.*;

//import static cgtools.Vec3.*;
import java.util.ArrayList;

public class Main {
	static int width = 160;
	static int height = 90;
	double radius;
	static ArrayList<Rauten> list;
	static Rauten r;
	///////////
	static LochKamera cam = new LochKamera(100, 1600, 900);
	static Sphere s = new Sphere(new Vec3(0, 0, -50), 25);

	//////////////
	public static void main(String[] args) {
		/*
		 * ArrayList<Image> list; list = new ArrayList<>(); Image image = new
		 * Image(width, height); for (int i = 0; i < 100; i++) { list.add(new
		 * Image(width, height)); }
		 */

		list = new ArrayList<>();

		/*
		 * for (int i = 0; i < 100; i++) { list.add(new Rauten(x, y,
		 * (cgtools.Random.random() * height), new Vec3(cgtools.Random.random(),
		 * cgtools.Random.random(), cgtools.Random.random()))); }
		 */
		for (int i = 0; i < 100; i++) {
			Rauten ra = new Rauten(cgtools.Random.random() * width, cgtools.Random.random() * height,
					cgtools.Random.random() * width / 10,
					new Vec3(cgtools.Random.random(), cgtools.Random.random(), cgtools.Random.random()));

			/*
			 * list.add(new Rauten(cgtools.Random.random() * width, cgtools.Random.random()
			 * * height, cgtools.Random.random() * width / 10, new
			 * Vec3(cgtools.Random.random(), cgtools.Random.random(),
			 * cgtools.Random.random())));
			 */

			list.add(ra);
		}
		Collections.sort(list, new Comparator<Rauten>() {
			public int compare(Rauten r1, Rauten r2) {
				if (r1.getDis() == r2.getDis()) {
					return 0;
				} else if (r1.getDis() > r2.getDis()) {
					return 1;
				} else {
					return -1;
				}
			}
		});

		Image image = new Image(width, height);
		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				image.setPixel(x, y, sampl(x, y));
			}
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
		String filename = "doc/t3.png";
		try {
			image.write(filename, 2.2);
			System.out.println("Wrote image: " + filename);
		} catch (IOException error) {
			System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
		}
		// }

		// }

		/*
		 * String filename = "doc/a01-polka-dots.png"; try { image.write(filename);
		 * System.out.println("Wrote image: " + filename); } catch (IOException error) {
		 * System.out.println(String.format("Something went wrong writing: %s: %s",
		 * filename, error)); }
		 */
	}

	public static Vec3 sampl(int x, int y) {
		int rate = 100;

		double r, g, b;
		r = 0;
		g = 0;
		b = 0;
		for (int ix = 0; ix < Math.sqrt(rate); ix++) {
			for (int iy = 0; iy < Math.sqrt(rate); iy++) {
				double randomX = cgtools.Random.random();
				double randomY = cgtools.Random.random();
				double samplX = x + (ix + randomX) / Math.sqrt(rate);
				double samplY = x + (iy + randomY) / Math.sqrt(rate);
				Vec3 t = pixelColor(samplX, samplY);

				r += t.x;
				g += t.y;
				b += t.z;
			}
		}
		r = r / rate;
		g = g / rate;
		b = b / rate;
		return vec3(r, g, b);

	}

	static Vec3 pixelColor(double x, double y) {

		/*
		 * if (r.test(x, y)) { System.out.println("True"); return r.getColor(); } else {
		 * return vec3(0, 0, 0); // System.out.println("false"); return vec3(1, 1, 1); }
		 * 
		 * }
		 */
		/*
		 * for (Rauten r : list) { if (r.test(x, y)) {
		 * 
		 * return r.getColor(); } } return vec3(0, 0, 0);
		 */
		Vec3 color;
		Ray r = cam.rayGenrator(x, y);
		Hit h = s.intersect(r);
		if (h != null) {
			color = h.normal;
		} else {
			color = new Vec3(0, 0, 0);
		}
		return color;

	}

}
/*
 * int xc = width / 4; int yc = height / 4; x = x - xc; y = y - yc; double d =
 * Math.sqrt((x * x) + (y * y));
 */

/*
 * if (x == 20) { double radius = 100; boolean ch = check(d, radius);
 * 
 * if (ch) { System.out.println("ch =" + ch); return vec3(0, 0, 0);
 * 
 * } }
 */

// ++++++++++++++++++++++++++++++++++++++++++++
/*
 * double testx = (x+21) % 25.0; double testy = (y+21.7) % 25.0;
 * 
 * double xc = 25.0 / 2.0; double yc = 25.0 / 2.0;
 * 
 * double xtest = testx - xc; double ytest = testy - yc;
 * 
 * // x = x - test; // y = y - yc; double d = Math.sqrt((xtest * xtest) + (ytest
 * * ytest));
 * 
 * double radius = 9; if ((d < radius)) { // System.out.println("C"); //xc =
 * width / 2; //yc = height / 2;
 * 
 * return new Vec3(1, 1, 1); } else { // System.out.println("noo++");
 * 
 * return new Vec3(0, 0, 0); }
 */
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
/*
 * for (Rauten rauten : list) { if (rauten.test(x, y)) {
 * 
 * return rauten.getColor(); } } return vec3(0, 0, 0); }
 * 
 * }
 */

/*
 * static boolean check(int x, int y) {
 * 
 * if (x == xc && y == yc) { System.out.println("x =" + x);
 * System.out.println("y =" + y); System.out.println("xc =" + xc);
 * System.out.println("yc =" + yc); return true;
 * 
 * } else { System.out.println("noo");
 * 
 * return false; } }
 */
