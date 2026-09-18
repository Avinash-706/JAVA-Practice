package period;

import java.time.Period;

public class MultiplyNegateOperations {

    public static void multiplyNegate() {

        System.out.println("\n===== MULTIPLY & NEGATE =====");

        Period period = Period.of(1, 3, 10);
        System.out.println("Original Period : " + period);

        // Multiply
        Period multiplied = period.multipliedBy(2);
        System.out.println("\nmultipliedBy(2) : " + multiplied);

        Period multiplied3 = period.multipliedBy(3);
        System.out.println("multipliedBy(3) : " + multiplied3);

        // Multiply by negative
        Period multipliedNegative = period.multipliedBy(-1);
        System.out.println("multipliedBy(-1) : " + multipliedNegative);

        // Negate (change sign)
        Period negated = period.negated();
        System.out.println("\nnegated() : " + negated);

        Period positiveNegated = negated.negated();
        System.out.println("negated().negated() : " + positiveNegated + " (back to original sign)");

        // Note: Period does NOT support division like Duration
        System.out.println("\n\nNote: Period does NOT have dividedBy() method");
        System.out.println("Only multipliedBy() is supported");

        // Example: Calculate warranty period for multiple products
        Period warrantyPerProduct = Period.ofMonths(6);
        int numberOfProducts = 3;
        Period totalWarranty = warrantyPerProduct.multipliedBy(numberOfProducts);
        
        System.out.println("\n\nUse Case Example:");
        System.out.println("Warranty per Product : " + warrantyPerProduct);
        System.out.println("Number of Products : " + numberOfProducts);
        System.out.println("Total Warranty Coverage : " + totalWarranty);
        System.out.println("In Months : " + totalWarranty.getMonths());
    }
}
