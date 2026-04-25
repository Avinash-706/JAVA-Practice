@FunctionalInterface
interface FaceOne {
    void greet();
}

public class FunctionalInterfaceOne {
    public static void main(String[] args) {
        FaceOne ref1 = new FaceOne() {
            @Override
            public void greet() {
                System.out.println("Ref1 greet via FaceOne");
            }
            
        };

        FaceOne ref2 = () -> System.out.println("Ref2 via lambda method");

        ref1.greet();
        ref2.greet();
    }
}