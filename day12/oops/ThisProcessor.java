public class ThisProcessor {
    int x, y;

    public void initialize(){
        this.x = 1;
        this.y = 2;
        System.out.println("Variables Initialized:");
        System.out.println("x : " + x);
        System.out.println("y : " + y);
        this.modify();
    }

    public void modify() {
        int x = 10;
        int y = 20;
        System.out.println("\nInside update() – Shadowing Demonstrated:");
        System.out.println("Local x = " + x);
        System.out.println("Local y = " + y);

        this.x = x;
        this.y = y;

        System.out.println("Instance x updated to = " + this.x);
        System.out.println("Instance y updated to = " + this.y);

        this.display();
    }

    public void display() {
        System.out.println("\nFinal Values:");
        System.out.println("x = " + this.x);
        System.out.println("y = " + this.y);
    }

    public static void main(String[] args) {
        ThisProcessor ref = new ThisProcessor();
        ref.initialize();      
    }
}