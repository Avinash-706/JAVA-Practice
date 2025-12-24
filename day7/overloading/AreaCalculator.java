import java.util.Scanner;

public class AreaCalculator {

    public static void calculateArea(int side) {
        System.out.println("Area of the Sqaure  : " + Math.pow(side, 2));
    }

    public static void calculateArea(double radius) {
        System.out.println("Area of the Circle  : " + (String.format("%.4f",(22 * radius * radius) / 7)));
    }

    public static void calculateArea(int l, int b) {
        System.out.println("Area of the Rectangle  : " + l * b);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the Side of the Square : ");
        int side = sc.nextInt();
        calculateArea(side);

        System.out.print("Enter the Radius of the Circle : ");
        double radius = sc.nextInt();
        calculateArea(radius);

        System.out.print("Enter the Length of the Rectangle : ");
        int l = sc.nextInt();
        System.out.print("Enter the Breadth of the Rectangle : ");
        int b = sc.nextInt();
        calculateArea(l, b);
        
        sc.close();
    }
}
