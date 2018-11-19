package haridy850367.a10;
import static cgtools.Mat4.*;
import cgtools.Random;
import cgtools.Vec3;
import static cgtools.Vec3.*;
import java.io.IOException;
import haridy850367.Image;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    final static int width = 300;
    final static int height = 300;
    static int samplePoints = 10;
    static int radianceDepth = 10;
    final static String filename = "a12-test.png";

    static Camera cam = new Camera(90, width, height, translate(0, 1, 10), new Vec3(0, 0, 0));
    static Background bg;
    static Group gr;

    public static void main(String[] args) {
        setScene();
        raytrace(cam, gr, samplePoints);
    }

    public static void raytrace(Camera cam, Group scene, int samplePoints) {
        //int cores = Runtime.getRuntime().availableProcessors();
        int cores = 3;
        Thread[] threads = new Thread[cores];

        ArrayList<Image> imgs = new ArrayList<>();
        Image image = new Image(width, height);

        for (int i = 0; i != cores; i++) {
            final int core = i;
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

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                for (int i = 0; i != cores; i++) {
                    colorArr[i] = imgs.get(i).getPixel(x, y);
                    r += colorArr[i].x;
                    g += colorArr[i].y;
                    b += colorArr[i].z;
                }
                r = r/cores;
                g = g/cores;
                b = b/cores;
                Vec3 color = new Vec3(r,g,b);
                image.setPixel(x, y, color);
            }
        }
        try {
            image.write(filename);
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
        Vec3 color = calculateRadiance(gr, r, radianceDepth);
        return color;
    }

    public static Vec3 calculateRadiance(Shape scene, Ray ray, int depth) {
        if (depth == 0) {
            return black;
        }

        Hit hit = scene.intersect(ray);
        Vec3 emission = hit.mat.emittedRadiance(ray, hit);
        Ray scattered = hit.mat.scatteredRay(ray, hit);

        if (scattered != null) {
            return multiply(add(emission, hit.mat.albedo(ray, hit)), calculateRadiance(scene, scattered, depth - 1));
        } else {
            return emission;
        }
    }

    public static void setScene() {
        ArrayList<Shape> scene = new ArrayList<>();
        Shape ground = new Plane(vec3(0.0, 0, 0.0), vec3(0, 1, 0), new Diffuse(new Vec3(1, 1, 1), gray));
        scene.add(ground);
        for (double i = 0; i <= 6; i++) {
            double x1 = Random.random()*10;
            double z1 = Random.random()*10;
            Shape s1 = new Sphere(new Vec3(-5, x1, z1), 0.5, new Diffuse(new Vec3(1), green));
            Shape s2 = new Sphere(new Vec3(5, -x1, -z1), 0.5, new Diffuse(new Vec3(1), orange));
            scene.add(s1);
            scene.add(s2);
        }

        gr = new Group(scene, new Transformation(identity));
        
    }
}