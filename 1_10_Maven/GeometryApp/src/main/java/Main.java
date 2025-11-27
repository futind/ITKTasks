public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 6);
        Triangle triangle = new Triangle(3, 4, 5);

        System.out.println("Rectangle pertimeter im meters: " + GeometryUtils.convertCmToMeter(rectangle.getArea()));

        System.out.println("Circle area: " + circle.getArea() + ", perimeter: " + circle.getPerimeter());
        System.out.println("Rectangle area: " + rectangle.getArea() + ", perimeter: " + rectangle.getPerimeter());
        System.out.println("Triangle area: " + triangle.getArea() + ", perimeter: " + triangle.getPerimeter());
    }
}