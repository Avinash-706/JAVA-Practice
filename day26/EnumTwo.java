/**
 * Create an enum named OrderStatus with the following constants:
	PLACED
	PREPARING
	OUT_FOR_DELIVERY
	DELIVERED
	CANCELLED
Each enum constant should have:
	int code
	String message
Add:
	a parameterized constructor
	getter methods for code and message
Create a class Order with:
	int orderId
	OrderStatus status
In the main method:
	Create at least 3 Order objects
	Assign different enum values to each order

Print order details in the format:
OrderId : 101 | Status : DELIVERED | Code : 4 | Message : Order delivered successfully
 */

enum OrderStatus{
	PLACED(1,"xyz"),PREPARING(2,"ABC"),OUT_FOR_DELIVERY(3,"efh"),DELIVERED(4,"lmn"),CANCELLED(5,"lpu");
    
	private int code;
	private String message;
	private OrderStatus(int code,String message) {
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}	
}
class Order {
	int orderId;
	OrderStatus status;
	public Order(int orderId, OrderStatus status) {
		this.orderId = orderId;
		this.status = status;
	}
}
public class EnumTwo {
	public static void main(String[] args) {
		Order o1 = new Order(101, OrderStatus.CANCELLED);
		Order o2 = new Order(102, OrderStatus.OUT_FOR_DELIVERY);
		Order o3 = new Order(103, OrderStatus.PLACED);
		printOrder(o1);
		printOrder(o2);
		printOrder(o3);
		
	}
	static void printOrder(Order order) {
        System.out.println("OrderId : " + order.orderId + " | Status : " + order.status.name() + " | Code : " + order.status.getCode() + " | Message : " + order.status.getMessage() );
    }
}