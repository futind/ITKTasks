
public class GeometryUtils {
    public static double convertCmToMeter(double valueInCm) {
        return valueInCm / 100.0;
    }

    public static boolean isCircleLarger(Circle c1, Circle c2) {
        return c1.getArea() > c2.getArea();
    }

    public static boolean isRectangleLarger(Rectangle r1, Rectangle r2) {
        return r1.getArea() > r2.getArea();
    }
}