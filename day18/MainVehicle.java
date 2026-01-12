public class MainVehicle{
    public static void main(String[] args){        
        Vehicle myCar = new Car(10); 
        
        Vehicle myBike = new Bicycle(5); 

        System.out.println("--- Testing Car ---");
        myCar.speedUp(50); 

        System.out.println("\n--- Testing Bicycle ---");
        myBike.speedUp(4);     
    }
}

class Vehicle{
    protected int speed;

    public Vehicle(int initialSpeed){
        this.speed = initialSpeed;
    }

    public void speedUp(int increase){
        speed += increase;
        System.out.println("Vehicle Speed Increased. Current Speed : " + speed);
    }
}


class Car extends Vehicle{
    public Car(int initialSpeed){
        super(initialSpeed);
    }

    @Override
    public void speedUp(int increase){
        speed += increase;
        System.out.println("Car Speed Increased to " + speed);
    }
}

class Bicycle extends Vehicle{

    public Bicycle(int initialSpeed){
        super(initialSpeed);
    }

    @Override
    public void speedUp(int increase){
        speed += increase;
        System.out.println("Bicycle Speed Increased to " + speed);
    }
}