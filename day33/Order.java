class Order {

    private int orderId;
    private String customerName;
    private String category;
    private double amount;
    private String city;
    private String status; // e.g., PAID, PENDING, CANCELLED

    public Order(int orderId, String customerName, String category, double amount, String city, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.category = category;
        this.amount = amount;
        this.city = city;
        this.status = status;
    }

    public int getOrderId() {
         return orderId; 
    }

    public String getCustomerName() {
         return customerName; 
    }

    public String getCategory() {
         return category; 
    }

    public double getAmount() {
         return amount; 
    }

    public String getCity() {
         return city; 
    }

    public String getStatus() {
         return status; 
    }

    
    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", customerName='" + customerName + 
        '\'' + ", category='" + category + '\'' + ", amount=" + amount + ", city='" + city + 
        '\'' + ", status='" + status + '\'' + '}';
    }
}
