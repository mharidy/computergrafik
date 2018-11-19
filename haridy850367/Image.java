package haridy850367;

import cgtools.ImageWriter;
import cgtools.Vec3;
import java.io.IOException;

public class Image {

	private int width, height;
	private double[] components;
	int noOfComp;

	public Image(int width, int height) {
		noOfComp = width * height * 3;
		components = new double[noOfComp];
		this.width = width;
		this.height = height;

	}

	public void setPixel(int x, int y, Vec3 color) {
		int index = (y * width + x) * 3;
		components[index + 0] = color.x;
		components[index + 1] = color.y;
		components[index + 2] = color.z;
	}

	public Vec3 getPixel(int x, int y) {
		int kek = 3 * (x + (y * width));
		return new Vec3(components[kek], components[kek + 1], components[kek + 2]);
	}
	
	public void setPixelGamma(int x, int y, Vec3 color) {
        double gamma = 1 / 2.2;
        double red = Math.pow(color.x, gamma);
        double green = Math.pow(color.y, gamma);
        double blue = Math.pow(color.z, gamma);

        components[3 * (x + width * y)] = red;
        components[3 * (x + width * y) + 1] = green;
        components[3 * (x + width * y) + 2] = blue;
    }


	public void write(String filename) throws IOException {
		new ImageWriter(components, width, height).write(filename);
		System.out.println("Wrote image: " + filename);

	}
}
