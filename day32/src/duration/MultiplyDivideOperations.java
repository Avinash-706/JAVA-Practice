package duration;

import java.time.Duration;

public class MultiplyDivideOperations {

    public static void multiplyDivide() {

        System.out.println("\n===== MULTIPLY & DIVIDE =====");

        Duration duration = Duration.ofHours(2);
        System.out.println("Original Duration : " + duration);

        // Multiply
        Duration multiplied = duration.multipliedBy(3);
        System.out.println("\nmultipliedBy(3) : " + multiplied);

        Duration multiplied5 = duration.multipliedBy(5);
        System.out.println("multipliedBy(5) : " + multiplied5);

        // Divide
        Duration divided = duration.dividedBy(2);
        System.out.println("\ndividedBy(2) : " + divided);

        Duration divided3 = duration.dividedBy(3);
        System.out.println("dividedBy(3) : " + divided3);

        // Example: Calculate total break time
        Duration breakDuration = Duration.ofMinutes(15);
        int numberOfBreaks = 4;
        Duration totalBreakTime = breakDuration.multipliedBy(numberOfBreaks);
        
        System.out.println("\n\nUse Case Example:");
        System.out.println("Single Break Duration : " + breakDuration);
        System.out.println("Number of Breaks : " + numberOfBreaks);
        System.out.println("Total Break Time : " + totalBreakTime);
        System.out.println("In Minutes : " + totalBreakTime.toMinutes());

        // Example: Split duration equally
        Duration totalWorkTime = Duration.ofHours(8);
        int numberOfTasks = 4;
        Duration timePerTask = totalWorkTime.dividedBy(numberOfTasks);
        
        System.out.println("\n\nSplit Duration Example:");
        System.out.println("Total Work Time : " + totalWorkTime);
        System.out.println("Number of Tasks : " + numberOfTasks);
        System.out.println("Time per Task : " + timePerTask);
        System.out.println("In Hours : " + timePerTask.toHours());

        // Chained operations
        Duration result = Duration.ofMinutes(30).multipliedBy(2).dividedBy(3);
        System.out.println("\n\nChained: ofMinutes(30).multipliedBy(2).dividedBy(3) : " + result);
    }
}
