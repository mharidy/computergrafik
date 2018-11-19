package haridy850367.a05;

import cgtools.Vec3;
import static cgtools.Vec3.*;
import java.io.IOException;
import haridy850367.Image;
import java.util.ArrayList;

public class Main {

    static int width = 1600;
    static int height = 900;
    
    static Camera cam = new Camera(80, 1600, 900);
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
        String filename = "doc/a05-diffuse-spheres.png";
        try {
            image.write(filename,2.2);
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
        Vec3 color = calculateRadiance(gr, r, 20);
        return color;
    }
    
    public static Vec3 calculateRadiance(Shape scene, Ray ray, int depth) {
        if(depth == 0) return black;
        
        Hit hit = scene.intersect(ray);
        Vec3 emission = hit.material.emittedRadiance(ray, hit);
        Ray scattered = hit.material.scatteredRay(ray, hit);
        
        if(scattered != null) return multiply(add(emission, hit.material.albedo(ray, hit)),  calculateRadiance(scene, scattered, depth-1));
        else return emission;
    }
    
    public static void setScene() {
//        Shape ground = new Plane(vec3(0.0, -0.5, 0.0), vec3(0, 1, 0), new Diffuse(new Vec3(1,1,1), gray));
//        Shape globe1 = new Sphere(vec3(-1.0, -0.25, -2.5), 0.7, new Diffuse(new Vec3(1,1,1), red));
//        Shape globe2 = new Sphere(vec3(0.0, -0.25, -2.5), 0.5, new Diffuse(new Vec3(1,1,1), green));
//        Shape globe3 = new Sphere(vec3(1.0, -0.25, -2.5), 0.7, new Diffuse(new Vec3(1,1,1), blue));
//
//        ArrayList<Shape> shapes = new ArrayList<>();
//        shapes.add(bg);
//        shapes.add(ground);
//        shapes.add(globe1);
//        shapes.add(globe2);
//        shapes.add(globe3);
//        gr = new Group(shapes);

        Shape ground = new Plane(vec3(0.0, -1.0, 0.0), vec3(0, 1, -0.2), new Diffuse(new Vec3(1,1,1), white));
        
     /*   Shape globe1 = new Sphere(vec3(0.0, 2, -6.0), 0.5, new Diffuse(new Vec3(1,1,1), new Vec3(0.5, 0.5, 0.5)));
        Shape globe2 = new Sphere(vec3(2, 0, -6.0), 0.5, new Diffuse(new Vec3(1,1,1), new Vec3(0.5, 0.5, 0.5)));
        Shape globe3 = new Sphere(vec3(-2, 0, -6.0), 0.5, new Diffuse(new Vec3(1,1,1), new Vec3(0.5, 0.5, 0.5)));
*/
        Shape globe1T = new Sphere(vec3(0.0, 2, -2.0), 0.3, new Diffuse(new Vec3(1,1,1), white));
      //  Shape globe2T = new Sphere(vec3(-0.5, -2, -3.0), 0.5, new Diffuse(new Vec3(1,1,1),yellow));
       // Shape globe3T = new Sphere(vec3(0.5, -2, -3.0), 0.5, new Diffuse(new Vec3(1,1,1), yellow));

        
        
        Shape globe4 = new Sphere(vec3(0.0, 2, -4.0), 0.5, new Diffuse(new Vec3(1,1,1), new Vec3(0.3, 0.3, 0.3)));
        Shape globe5 = new Sphere(vec3(2, 0, -4.0), 0.5, new Diffuse(new Vec3(1,1,1), new Vec3(0.3, 0.3, 0.3)));
        Shape globe6 = new Sphere(vec3(-2, 0, -4.0), 0.5, new Diffuse(new Vec3(1,1,1), new Vec3(0.3, 0.3, 0.3)));
        
        Shape globe7 = new Sphere(vec3(0.0, 2, -8.0), 0.5, new Diffuse(new Vec3(1,1,1), new Vec3(0.7, 0.7, 0.7)));
        Shape globe8 = new Sphere(vec3(2, 0, -8.0), 0.5, new Diffuse(new Vec3(1,1,1), new Vec3(0.7, 0.7, 0.7)));
      Shape globe9 = new Sphere(vec3(-2, 0, -8.0), 0.5, new Diffuse(new Vec3(1,1,1), new Vec3(0.7, 0.7, 0.7)));
      Shape globe7D = new Sphere(vec3(0.0, -2, -8.0), 0.5, new Diffuse(new Vec3(1,1,1), new Vec3(0.7, 0.7, 0.7)));

        Shape globeC = new Sphere(vec3(0.0, 0.0, -6.0), 1.0, new Diffuse(new Vec3(1,1,1), red));
        
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(bg);
        shapes.add(ground);
       shapes.add(globe1T);
      //  shapes.add(globe2T);
       // shapes.add(globe3T);
        //shapes.add(globe4);
        //shapes.add(globe5);
        shapes.add(globe7);
        shapes.add(globe8);
       shapes.add(globe9);
       shapes.add(globe7D);
        shapes.add(globeC);
        gr = new Group(shapes);
        /////////////////////////////--------------------------------------
        
        
    }
}
