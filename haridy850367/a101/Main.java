package haridy850367.a101;

import cgtools.ImageTexture;
import cgtools.Mat4;
import cgtools.Random;
import cgtools.Vec3;
import haridy850367.Image;
//import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static cgtools.Mat4.rotate;
import static cgtools.Mat4.translate;
import static cgtools.Vec3.*;



public class Main {

    private static int sampling;

    private static Camera camera;
    private static Group group;
    private static ImageTexture skyPic = null;
    private static ImageTexture planetPic = null;
    private static ImageTexture planetPic2 = null;

    public static void main(String[] args) {
        int width = 720;
        int height = 405;
        Image image = new Image(width, height);

        try {
            skyPic = new ImageTexture("doc/sky.jpg");
            planetPic = new ImageTexture("doc/planetPic.png");
            planetPic2 = new ImageTexture("doc/planetPic2.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Mat4 cameraT;
        Mat4 cameraR1;
        Mat4 cameraR2;

        cameraR1 = rotate(vec3(0, 1, 0), -45);
        cameraR2 = rotate(vec3(1, 0, 0), -10);
        cameraT = translate(vec3(-15, 5, 10));
        Mat4 cameraPos = cameraT.multiply(cameraR1.multiply(cameraR2));

        //Mat4 cameraPos = Mat4.translate(vec3(0, 0, 20));

        raytrace(new Camera(Math.PI / 2, width, height, cameraPos), genObjects(), 100);

        double start = System.currentTimeMillis();
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixel(x, y, pixelColor(x, y));
            }
        }

        double end = System.currentTimeMillis();
        double time = end - start;

        double sekunde = time / 1000;
        double minute = time * 1.6666666666667E-5;
        System.out.println("sek: " + sekunde + ", min: " + minute);

        String filename = "doc/a10-2.png";

        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }

    }

    private static void raytrace(Camera c, Group g, int s) {
        camera = c;
        group = g;
        sampling = s;
    }

   // @NotNull
    private static Group genObjects() {
        Group scene = new Group(new Transformation(Mat4.translate(vec3(0))),
                new Kugel(200, vec3(0, 50, -100), new Hintergrund(new Textu( skyPic, Mat4.translate(vec3(skyPic.width / 2 +.5, skyPic.height / 2, 0))))),
                new Kugel(2, vec3(4, 3, -3), new Lambertsches((Texture) planetPic)),
                new Kugel(3, vec3(-2, 1.5, -3), new Lambertsches((Texture) planetPic2))
        );

        return scene;
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

    private static Vec3 radiance(Ray r, Shape g, int depth) {
        if (depth == 0) return black;

        Hit hit = g.intersect(r);
        Vec3 emission = hit.material.emittedRadiance(r, hit).color(hit.texturko);
        Ray scattered = hit.material.scatteredRay(r, hit);
        if (scattered != null) {
            return add(emission,
                    multiply(hit.material.albedo(r, hit).color(hit.texturko), radiance(scattered, g, depth - 1)));
        } else return emission;
    }

    private static Vec3 pixelColor(double x, double y) {
        Vec3 bgColor;
        Ray ray = camera.generateRay(x, y);
        bgColor = radiance(ray, group, 5);
        return bgColor;
    }

}

