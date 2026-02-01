import java.util.*;
import java.util.List;


public class Train {

    public static void main(String[] args) {

        String input = "TR-2345:Rajdhani:900:200000";

        TrainUtil util = new TrainUtil();

        try {
            String[] parts = input.split(":");

            String trainNumber = parts[0];
            String trainName = parts[1];
            int passengerCount = Integer.parseInt(parts[2]);
            double currentFuel = Double.parseDouble(parts[3]);

            util.validateTrainNumber(trainNumber);
            util.validateTrainName(trainName);
            util.validatePassengerCount(passengerCount, trainName);

            double fuelRequired =
                    util.calculateFuelToFillTank(trainName, currentFuel);

            System.out.println(
                    "Fuel required to fill the tank: " + fuelRequired + " liters"
            );

        } catch (InvalidTrainException e) {
            System.out.println(e.getMessage());
        }
    }
}

class TrainUtil {
    public boolean validateTrainNumber(String trainNumber) throws InvalidTrainException {
        if (trainNumber == null || trainNumber.length() != 7 || !trainNumber.startsWith("TR-")) {
            throw new InvalidTrainException("The train number " + trainNumber + " is invalid");
        }

        String digits = trainNumber.substring(3);
        int num;

        try {
            num = Integer.parseInt(digits);
        } catch (NumberFormatException e) {
            throw new InvalidTrainException("The train number " + trainNumber + " is invalid");
        }

        if (num < 1000 || num > 9999) {
            throw new InvalidTrainException("The train number " + trainNumber + " is invalid");
        }

        return true;
    }

    public boolean validateTrainName(String name) throws InvalidTrainException {
        List<String> list= Arrays.asList("Rajdhani", "Shatabdi", "Duronto", "Vande Bharat");

        if(list.contains(name)){
            return true;
        } else {
            throw new InvalidTrainException("The train name "+ name + " is invalid");
        }
    }

    public boolean validatePassengerCount(int passengerCount, String trainName)
            throws InvalidTrainException {

        Map<String, Integer> cap = new HashMap<>();
        cap.put("Rajdhani", 1200);
        cap.put("Shatabdi", 800);
        cap.put("Duronto", 1500);
        cap.put("Vande Bharat", 1000);

        int maxCapacity = cap.get(trainName);

        if (passengerCount <= 0 || passengerCount > maxCapacity) {
            throw new InvalidTrainException(
                    "The passenger count " + passengerCount + " is invalid for " + trainName
            );
        }

        return true;
    }


    public double calculateFuelToFillTank(String trainName, double currentFuelLevel)
            throws InvalidTrainException {

        Map<String, Double> fuel = new HashMap<>();
        fuel.put("Rajdhani", 500000.0);
        fuel.put("Shatabdi", 300000.0);
        fuel.put("Duronto", 600000.0);
        fuel.put("Vande Bharat", 400000.0);

        double capacity = fuel.get(trainName);

        if (currentFuelLevel < 0 || currentFuelLevel > capacity) {
            throw new InvalidTrainException("Invalid fuel level for " + trainName);
        }

        return capacity - currentFuelLevel;
    }
}


class InvalidTrainException extends Exception {
    public InvalidTrainException(String msg) {
        super(msg);
    }
}


