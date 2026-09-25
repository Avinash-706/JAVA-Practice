package duration;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class UseCaseExamples {

    public static void useCases() {

        System.out.println("\n===== REAL-WORLD USE CASES =====");

        // Use Case 1: Measure code execution time
        System.out.println("\n1. Measure Code Execution Time:");
        Instant startTime = Instant.now();
        
        // Simulate some work
        int sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        
        Instant endTime = Instant.now();
        Duration executionTime = Duration.between(startTime, endTime);
        
        System.out.println("Execution Time : " + executionTime);
        System.out.println("In Milliseconds : " + executionTime.toMillis());

        // Use Case 2: Subscription expiry check
        System.out.println("\n\n2. Subscription Validity Check:");
        Duration subscriptionDuration = Duration.ofDays(30);
        System.out.println("Subscription Duration : " + subscriptionDuration);
        System.out.println("Total Days : " + subscriptionDuration.toDays());
        System.out.println("Total Hours : " + subscriptionDuration.toHours());

        // Use Case 3: Meeting duration calculator
        System.out.println("\n\n3. Meeting Duration Calculator:");
        LocalTime meetingStart = LocalTime.of(14, 0);
        LocalTime meetingEnd = LocalTime.of(15, 30);
        Duration meetingDuration = Duration.between(meetingStart, meetingEnd);
        
        System.out.println("Meeting Start : " + meetingStart);
        System.out.println("Meeting End : " + meetingEnd);
        System.out.println("Meeting Duration : " + meetingDuration);
        System.out.println("In Minutes : " + meetingDuration.toMinutes());

        // Use Case 4: Timeout checker
        System.out.println("\n\n4. Timeout Checker:");
        Duration timeout = Duration.ofSeconds(30);
        Duration responseTime = Duration.ofSeconds(25);
        
        System.out.println("Timeout Limit : " + timeout);
        System.out.println("Response Time : " + responseTime);
        
        if (responseTime.compareTo(timeout) < 0) {
            System.out.println("Status: SUCCESS - Within timeout");
        } else {
            System.out.println("Status: TIMEOUT - Exceeded limit");
        }

        // Use Case 5: Working hours calculation
        System.out.println("\n\n5. Working Hours Calculation:");
        LocalDateTime workStart = LocalDateTime.of(2026, 8, 30, 9, 0);
        LocalDateTime workEnd = LocalDateTime.of(2026, 8, 30, 17, 30);
        Duration workingHours = Duration.between(workStart, workEnd);
        Duration lunchBreak = Duration.ofMinutes(30);
        Duration actualWork = workingHours.minus(lunchBreak);
        
        System.out.println("Work Start : " + workStart);
        System.out.println("Work End : " + workEnd);
        System.out.println("Total Duration : " + workingHours.toHours() + " hours " + workingHours.toMinutesPart() + " minutes");
        System.out.println("Lunch Break : " + lunchBreak.toMinutes() + " minutes");
        System.out.println("Actual Work Time : " + actualWork.toHours() + " hours");

        // Use Case 6: Video/Audio duration
        System.out.println("\n\n6. Video Duration:");
        Duration videoDuration = Duration.ofMinutes(125);
        System.out.println("Video Duration : " + videoDuration);
        System.out.println("Formatted: " + videoDuration.toHoursPart() + "h " + videoDuration.toMinutesPart() + "m");
    }
}
