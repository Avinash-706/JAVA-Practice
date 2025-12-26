import java.util.Scanner;

public class SmartTrackerApp {
    
    public static int averageTemp(int[] temp) {
        int sum = 0;
        for(int i : temp)   sum+=i;
        return sum/temp.length;
    }

    public static int highestTemp(int[] temp) {
        int high = Integer.MIN_VALUE;
        for(int i : temp){
            high = Math.max(high, i);
        }
        return high;
    }

    public static int lowestTemp(int[] temp) {
        int high = Integer.MAX_VALUE;
        for(int i : temp){
            high = Math.min(high, i);
        }
        return high;
    }

    public static int totalSales(int[] sales) {
        int sum = 0;
        for(int i : sales){
            sum += i;
        }
        return sum;
    }

    public static int averageSales(int[] sales) {
        int sum = 0;
        for(int i : sales){
            sum += i;
        }
        return sum/sales.length;
    }

    public static int highestSales(int[] sales) {
        int high = Integer.MIN_VALUE;
        int index = 0;
        for(int i = 0; i < sales.length ; i++){
            if(high < sales[i]){
                high = sales[i];
                index = i;
            }
        }
        return index;
    }

    public static int highSalesDays(int[] sales) {
        int count = 0;
        for(int i = 0; i < sales.length ; i++){
            if(sales[i] > 100)  count = count + 1;
        }
        return count;
    }

    public static int lowestSales(int[] sales) {
        int high = Integer.MAX_VALUE;
        int index = 0;
        for(int i = 0; i < sales.length ; i++){
            if(high > sales[i]){
                high = sales[i];
                index = i;
            }
        }
        return index;
    }

    public static int findSale(int[] sales, int target) {
        for(int i = 0; i < sales.length ; i++){
            if(target == sales[i])  return i;
        }
        return -1;
    }

    // public static void updateSales(int day, int data, int[] sales) {


    // }


    public static void main(String[] args) {
        int[] sales = {120, 80, 150, 90, 200, 75, 110}; // Sales for 7 days (in ₹)
        int[] temp = {32, 28, 31, 29, 35, 27, 30}; // Temperatures (°C) for 7 days

        System.out.println("Average Temperature: " + averageTemp(temp));
        System.out.println("Highest Temperature: " + highestTemp(temp));
        System.out.println("Lowest  Temperature: " + lowestTemp(temp));
        System.out.println("Highest Temperature: " + lowestTemp(temp));
        System.out.println("Total Sales: " + totalSales(sales));
        System.out.println("Average Sales " + averageSales(sales));
        System.out.println("Average Sales " + averageSales(sales));
        System.out.println("Highest Sale: " + sales[highestSales(sales)] + " on " + highestSales(sales));
        System.out.println("Lowest Sale: " + sales[lowestSales(sales)] + " on " + lowestSales(sales));
        System.out.println("Number of high sales days (>100) : " + highSalesDays(sales));
        
        // logic
        System.out.println("Sale 150 "  +  " found on Day 3 : " + sales[findSale(sales, 150)] );

        int update_Day = 2;
        Scanner sc = new Scanner(System.in);
        int update_Data = sc.nextInt();
        sales[update_Day - 1] = update_Data;
        System.out.println("Update Sales for Day " + update_Day + " : "  +  " found on Day 3 : " + sales[update_Day -1] );



    }
    
}
