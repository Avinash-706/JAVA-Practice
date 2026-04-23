/*
Q. Online Order Processing System
Each Order has:
	int orderId
	String customerName
	double orderAmount
	int itemCount
	long orderTime

Write a Java program to sort orders using Comparator based on:
	Higher orderAmount first
	If amount same → fewer itemCount first
	If itemCount same → earlier orderTime first
	If orderTime same → customerName in ascending order
	If customerName same → orderId in ascending order

*/
import java.util.*;

public class CustomComparator {

    public static void main(String[] args) {

        List<Order> orders = new ArrayList<>();

        orders.add(new Order(101, "Alice", 5000.0, 3, 1700000001000L));
        orders.add(new Order(102, "Bob", 5000.0, 2, 1700000002000L));
        orders.add(new Order(103, "Charlie", 7000.0, 5, 1699999999000L));
        orders.add(new Order(104, "Alice", 5000.0, 2, 1700000001000L));
        orders.add(new Order(105, "Alice", 5000.0, 2, 1700000001000L));

        Collections.sort(orders, new CustomSort());

        for (Order o : orders) {
            System.out.println(
                    o.orderId + " " +
                    o.customerName + " " +
                    o.orderAmount + " " +
                    o.itemCount + " " +
                    o.orderTime
            );
        }
    }
}

class Order {
    int orderId;
    String customerName;
    double orderAmount;
    int itemCount;
    long orderTime;

    Order(int orderId, String customerName, double orderAmount, int itemCount, long orderTime) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderAmount = orderAmount;
        this.itemCount = itemCount;
        this.orderTime = orderTime;
    }
}

class CustomSort implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {

        int result;

        result = Double.compare(o2.orderAmount, o1.orderAmount);
        if (result != 0) return result;

        result = Integer.compare(o1.itemCount, o2.itemCount);
        if (result != 0) return result;

        result = Long.compare(o1.orderTime, o2.orderTime);
        if (result != 0) return result;

        result = o1.customerName.compareTo(o2.customerName);
        if (result != 0) return result;

        return Integer.compare(o1.orderId, o2.orderId);
    }
}
