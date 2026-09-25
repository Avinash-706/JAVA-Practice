package localdate;

import java.time.LocalDate;

public class CompareAndCheckOperations {
    
    public static void compareAndCheck() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        // Check if Today is After Yesterday == true
        System.out.print("\nIs " + today + ".isAfter(" + yesterday + ") : ");
        if(today.isAfter(yesterday)){
            System.out.println(" Haan Ji, Yes");
        }
        else{
            System.out.println(" No, Naa Ji");
        }
        
        // Check if Yesterday is Before Today == true
        System.out.print("Is " + yesterday + ".isBefore(" + today + ") : ");
        if(yesterday.isBefore(today)){
            System.out.println(" Haan Ji, Yes");
        }
        else{
            System.out.println(" No, Naa Ji");
        }

       // Check if today is any Date
        System.out.print("Is " + today + ".equals(" + LocalDate.of(2026, 2, 8) + ") ? : ");
        if (today.equals(LocalDate.of(2026, 2, 8))) {
            System.out.println("It is "+ today.getDayOfMonth() + " " + today.getMonth() + ", " + today.getYear() + "!");
        } else {
            System.out.println("It is not " + LocalDate.of(2026, 2, 8) + ". Today is: " + today);
        }
    }
}
