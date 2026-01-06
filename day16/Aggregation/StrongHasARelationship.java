// STRONG HAS-A RELATIONSHIP : COMPOSITION

// Engine: Dependent class (exists ONLY with Car)
class Engine {

    public void start(){
        System.out.println("Engine Started");
    }

    public void stop(){
        System.out.println("Engine Stopped");
    }
}

// Car HAS-A Engine
class Car {

    // Car contains Engine object (CREATES it internally)
    private Engine engine;

    public Car(){

        // object created INSIDE Car
        // Engine lifecycle depends on Car
        engine = new Engine();
    }

    public void drive(){
        engine.start();
        System.out.println("Car is running");
    }

    public void park(){
        engine.stop();
        System.out.println("Car is Parked");
    }
}

public class StrongHasARelationship {
    public static void main(String[] args) {

        // Creating Car object
        // Engine object is automatically created
        Car car = new Car();

        car.drive();
        car.park();
    }
}