package haridy850367.a101;

import cgtools.Vec3;

public class Hit {

    final double t;
    final Vec3 position;
    final Vec3 normal;
    final Vec3 texturko;
    final Material material;

    public Hit(double t, Vec3 position, Vec3 normal, Vec3 texturko, Material material) {
        this.t = t;
        this.position = position;
        this.normal = normal;
        this.texturko = texturko;
        this.material = material;
    }

    @Override
    public String toString() {
        return "Hit{" +
                "t=" + t +
                ", position=" + position +
                ", normal=" + normal +
                ", material=" + material +
                '}';
    }

}
