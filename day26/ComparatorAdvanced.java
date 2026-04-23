import java.util.*;
import java.util.function.Function;

// Product class for demonstration
class Product {
    int id;
    String name;
    double price;
    String category;
    int rating;
    
    public Product(int id, String name, double price, String category, int rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.rating = rating;
    }
    
    @Override
    public String toString() {
        return String.format("Product{id=%d, name='%s', price=%.2f, category='%s', rating=%d}", 
                           id, name, price, category, rating);
    }
}

// Traditional Comparator implementations
class PriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.price, p2.price);
    }
}

class NameComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.name.compareTo(p2.name);
    }
}

class RatingComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Integer.compare(p2.rating, p1.rating); // Descending order
    }
}

// Multi-field Comparator
class MultiFieldComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        // First by category
        int result = p1.category.compareTo(p2.category);
        if (result != 0) return result;
        
        // Then by rating (descending)
        result = Integer.compare(p2.rating, p1.rating);
        if (result != 0) return result;
        
        // Finally by price (ascending)
        return Double.compare(p1.price, p2.price);
    }
}

public class ComparatorAdvanced {
    public static void main(String[] args) {
        
        System.out.println("=== ADVANCED COMPARATOR TECHNIQUES ===\n");
        
        // Sample data
        List<Product> products = Arrays.asList(
            new Product(101, "Laptop", 75000.0, "Electronics", 4),
            new Product(102, "Mouse", 1500.0, "Electronics", 5),
            new Product(103, "Book", 500.0, "Education", 4),
            new Product(104, "Headphones", 3000.0, "Electronics", 3),
            new Product(105, "Notebook", 200.0, "Education", 5),
            new Product(106, "Keyboard", 2500.0, "Electronics", 4),
            new Product(107, "Pen", 50.0, "Education", 3)
        );
        
        System.out.println("Original List:");
        products.forEach(System.out::println);
        
        // 1. Traditional Comparator Classes
        System.out.println("\n\n1. TRADITIONAL COMPARATOR CLASSES:");
        
        List<Product> temp = new ArrayList<>(products);
        Collections.sort(temp, new PriceComparator());
        System.out.println("\nSorted by Price (ascending):");
        temp.forEach(System.out::println);
        
        Collections.sort(temp, new RatingComparator());
        System.out.println("\nSorted by Rating (descending):");
        temp.forEach(System.out::println);
        
        // 2. Anonymous Inner Classes
        System.out.println("\n\n2. ANONYMOUS INNER CLASSES:");
        
        temp = new ArrayList<>(products);
        Collections.sort(temp, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.name.compareTo(p2.name);
            }
        });
        System.out.println("\nSorted by Name (anonymous class):");
        temp.forEach(System.out::println);
        
        // 3. Lambda Expressions (Java 8+)
        System.out.println("\n\n3. LAMBDA EXPRESSIONS:");
        
        temp = new ArrayList<>(products);
        
        // Simple lambda
        Collections.sort(temp, (p1, p2) -> Double.compare(p1.price, p2.price));
        System.out.println("\nSorted by Price (lambda):");
        temp.forEach(System.out::println);
        
        // Lambda with method reference
        temp.sort(Comparator.comparing(p -> p.name));
        System.out.println("\nSorted by Name (method reference):");
        temp.forEach(System.out::println);
        
        // 4. Comparator.comparing() methods
        System.out.println("\n\n4. COMPARATOR.COMPARING() METHODS:");
        
        temp = new ArrayList<>(products);
        
        // Using Comparator.comparing()
        temp.sort(Comparator.comparing(p -> p.price));
        System.out.println("\nSorted by Price (Comparator.comparing):");
        temp.forEach(System.out::println);
        
        // Reverse order
        temp.sort(Comparator.comparing((Product p) -> p.price).reversed());
        System.out.println("\nSorted by Price (descending):");
        temp.forEach(System.out::println);
        
        // 5. Multiple Field Sorting (Chaining)
        System.out.println("\n\n5. MULTIPLE FIELD SORTING (CHAINING):");
        
        temp = new ArrayList<>(products);
        
        // Chain multiple comparators
        temp.sort(Comparator.comparing((Product p) -> p.category)
                           .thenComparing(p -> p.rating, Comparator.reverseOrder())
                           .thenComparing(p -> p.price));
        
        System.out.println("\nSorted by: Category → Rating(desc) → Price(asc):");
        temp.forEach(System.out::println);
        
        // 6. Null-safe Comparators
        System.out.println("\n\n6. NULL-SAFE COMPARATORS:");
        
        List<Product> productsWithNull = new ArrayList<>(products);
        productsWithNull.add(new Product(108, null, 1000.0, "Electronics", 4));
        
        // Null-safe comparison
        productsWithNull.sort(Comparator.comparing(
            p -> p.name, 
            Comparator.nullsLast(String::compareTo)
        ));
        
        System.out.println("\nNull-safe sorting by name (nulls last):");
        productsWithNull.forEach(System.out::println);
        
        // 7. Custom Comparator with complex logic
        System.out.println("\n\n7. CUSTOM COMPLEX COMPARATOR:");
        
        temp = new ArrayList<>(products);
        temp.sort(new MultiFieldComparator());
        
        System.out.println("\nCustom multi-field sorting:");
        temp.forEach(System.out::println);
        
        // 8. Comparator utility methods
        System.out.println("\n\n8. COMPARATOR UTILITY METHODS:");
        
        temp = new ArrayList<>(products);
        
        // Natural order
        temp.sort(Comparator.naturalOrder());
        System.out.println("\nNote: Products don't implement Comparable, so this would throw exception");
        
        // Comparing with key extractor
        temp.sort(Comparator.comparingInt(p -> p.id));
        System.out.println("\nSorted by ID (comparingInt):");
        temp.forEach(System.out::println);
        
        temp.sort(Comparator.comparingDouble(p -> p.price));
        System.out.println("\nSorted by Price (comparingDouble):");
        temp.forEach(System.out::println);
        
        // 9. TreeSet with custom Comparator
        System.out.println("\n\n9. TREESET WITH CUSTOM COMPARATOR:");
        
        TreeSet<Product> productSet = new TreeSet<>(Comparator.comparing(p -> p.price));
        productSet.addAll(products);
        
        System.out.println("\nTreeSet sorted by price:");
        productSet.forEach(System.out::println);
        
        // 10. TreeMap with custom Comparator
        System.out.println("\n\n10. TREEMAP WITH CUSTOM COMPARATOR:");
        
        TreeMap<Product, String> productMap = new TreeMap<>(
            Comparator.comparing((Product p) -> p.category)
                     .thenComparing(p -> p.name)
        );
        
        for (Product p : products) {
            productMap.put(p, "Description for " + p.name);
        }
        
        System.out.println("\nTreeMap sorted by category then name:");
        productMap.forEach((key, value) -> 
            System.out.println(key.category + " - " + key.name + " : " + value));
        
        // 11. Practical Examples
        System.out.println("\n\n11. PRACTICAL SORTING SCENARIOS:");
        
        temp = new ArrayList<>(products);
        
        // Find top 3 rated products
        temp.sort(Comparator.comparing((Product p) -> p.rating).reversed());
        System.out.println("\nTop 3 rated products:");
        temp.stream().limit(3).forEach(System.out::println);
        
        // Find cheapest product in each category
        System.out.println("\nCheapest product in each category:");
        Map<String, Optional<Product>> cheapestByCategory = products.stream()
            .collect(Collectors.groupingBy(
                p -> p.category,
                Collectors.minBy(Comparator.comparing(p -> p.price))
            ));
        
        cheapestByCategory.forEach((category, product) -> 
            System.out.println(category + ": " + product.orElse(null)));
        
        System.out.println("\n=== KEY COMPARATOR FEATURES ===");
        System.out.println("• Multiple sorting sequences possible");
        System.out.println("• Doesn't modify original class");
        System.out.println("• Can be used with lambda expressions");
        System.out.println("• Supports method chaining for complex sorting");
        System.out.println("• Null-safe comparisons available");
        System.out.println("• Works with Collections.sort(), TreeSet, TreeMap");
    }
}