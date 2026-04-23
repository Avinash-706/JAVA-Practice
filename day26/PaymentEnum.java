/**
 * Payment Mode Validation
 * A shopping application supports different payment modes using an enum.
 * Requirements:
 * Create an enum named PaymentMode with constants:
 *     CASH
 *     UPI
 *     DEBIT_CARD
 *     CREDIT_CARD
 *     NET_BANKING
 *
 * Create a class Payment with:
 *     double amount
 *     PaymentMode mode
 *
 * Add:
 *     A parameterized constructor
 *     A method processPayment()
 *
 * Logic inside processPayment():
 *     If mode is CASH, print: Payment will be collected on delivery
 *     If mode is UPI or NET_BANKING, print: Online payment successful
 *     If mode is DEBIT_CARD or CREDIT_CARD, print: Card payment processed
 * Use switch-case with enum
 *
 * In main():
 *     Create at least two Payment objects with different modes
 *     Call processPayment() for each
 */

enum PaymentMode {
    CASH("Cash on Delivery", 0.0),
    UPI("Unified Payments Interface", 0.0),
    DEBIT_CARD("Debit Card Payment", 1.5),
    CREDIT_CARD("Credit Card Payment", 2.0),
    NET_BANKING("Net Banking", 0.5);
    
    private final String description;
    private final double processingFee; // percentage
    
    PaymentMode(String description, double processingFee) {
        this.description = description;
        this.processingFee = processingFee;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getProcessingFee() {
        return processingFee;
    }
}

class Payment {
    private double amount;
    private PaymentMode mode;
    
    public Payment(double amount, PaymentMode mode) {
        this.amount = amount;
        this.mode = mode;
    }
    
    public void processPayment() {
        System.out.println("\n--- Processing Payment ---");
        System.out.println("Amount: ₹" + amount);
        System.out.println("Mode: " + mode.getDescription());
        
        switch (mode) {
            case CASH:
                System.out.println("Status: Payment will be collected on delivery");
                System.out.println("Processing Fee: ₹0.00");
                break;
                
            case UPI:
            case NET_BANKING:
                System.out.println("Status: Online payment successful");
                double onlineFee = amount * mode.getProcessingFee() / 100;
                System.out.println("Processing Fee: ₹" + String.format("%.2f", onlineFee));
                break;
                
            case DEBIT_CARD:
            case CREDIT_CARD:
                System.out.println("Status: Card payment processed");
                double cardFee = amount * mode.getProcessingFee() / 100;
                System.out.println("Processing Fee: ₹" + String.format("%.2f", cardFee));
                break;
                
            default:
                System.out.println("Status: Invalid payment mode");
        }
        
        double totalAmount = amount + (amount * mode.getProcessingFee() / 100);
        System.out.println("Total Amount: ₹" + String.format("%.2f", totalAmount));
        System.out.println("Payment Mode: " + mode.name());
    }
    
    public double getAmount() {
        return amount;
    }
    
    public PaymentMode getMode() {
        return mode;
    }
}

public class PaymentEnum {
    public static void main(String[] args) {
        
        System.out.println("=== PAYMENT PROCESSING SYSTEM ===");
        
        // Create different payment objects
        Payment payment1 = new Payment(1500.0, PaymentMode.CASH);
        Payment payment2 = new Payment(2500.0, PaymentMode.UPI);
        Payment payment3 = new Payment(5000.0, PaymentMode.CREDIT_CARD);
        Payment payment4 = new Payment(3200.0, PaymentMode.DEBIT_CARD);
        Payment payment5 = new Payment(1800.0, PaymentMode.NET_BANKING);
        
        // Process all payments
        Payment[] payments = {payment1, payment2, payment3, payment4, payment5};
        
        for (Payment payment : payments) {
            payment.processPayment();
        }
        
        // Demonstrate enum methods
        System.out.println("\n\n=== PAYMENT MODE INFORMATION ===");
        
        for (PaymentMode mode : PaymentMode.values()) {
            System.out.println("Mode: " + mode.name());
            System.out.println("Description: " + mode.getDescription());
            System.out.println("Processing Fee: " + mode.getProcessingFee() + "%");
            System.out.println("Ordinal: " + mode.ordinal());
            System.out.println("---");
        }
        
        // Demonstrate valueOf
        System.out.println("\n=== ENUM PARSING ===");
        try {
            PaymentMode parsedMode = PaymentMode.valueOf("UPI");
            System.out.println("Parsed mode: " + parsedMode.getDescription());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid payment mode");
        }
        
        // Payment statistics
        System.out.println("\n=== PAYMENT STATISTICS ===");
        double totalAmount = 0;
        double totalFees = 0;
        
        for (Payment payment : payments) {
            totalAmount += payment.getAmount();
            totalFees += payment.getAmount() * payment.getMode().getProcessingFee() / 100;
        }
        
        System.out.println("Total Payments: " + payments.length);
        System.out.println("Total Amount: ₹" + String.format("%.2f", totalAmount));
        System.out.println("Total Processing Fees: ₹" + String.format("%.2f", totalFees));
        System.out.println("Grand Total: ₹" + String.format("%.2f", totalAmount + totalFees));
        
        // Count payments by mode
        System.out.println("\n=== PAYMENT MODE DISTRIBUTION ===");
        java.util.Map<PaymentMode, Integer> modeCount = new java.util.HashMap<>();
        
        for (Payment payment : payments) {
            modeCount.put(payment.getMode(), 
                         modeCount.getOrDefault(payment.getMode(), 0) + 1);
        }
        
        modeCount.forEach((mode, count) -> 
            System.out.println(mode.getDescription() + ": " + count + " payments"));
    }
}
