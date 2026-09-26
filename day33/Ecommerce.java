import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.*;
import java.util.stream.Collectors;

import java.util.Set;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Ecommerce {
    public static void main(String[] args) {

        List<Order> orders = Arrays.asList(
            new Order(101, "Ravi", "Electronics", 55000, "Delhi", "PAID"),
            new Order(102, "Sita", "Clothing", 12000, "Mumbai", "PENDING"),
            new Order(103, "Amit", "Furniture", 75000, "Delhi", "PAID"),
            new Order(104, "Meena", "Electronics", 30000, "Chennai", "PAID"),
            new Order(105, "Raj", "Clothing", 8000, "Delhi", "CANCELLED"),
            new Order(106, "Anita", "Furniture", 25000, "Mumbai", "PAID"),
            new Order(107, "Vikram", "Electronics", 95000, "Bangalore", "PAID"),
            new Order(108, "Meena", "Clothing", 60000, "Delhi", "PAID")
        );

        System.out.println("========== 1. FILTER ALL PAID ORDERS ==========");
        List<Order> paidOrders = orders.stream()
                                        .filter(o -> o.getStatus().equalsIgnoreCase("PAID"))
                                        .toList();
        
        for (Order order : paidOrders) {
            System.out.println(order);   
        }


        System.out.println("\n========== 2. GET CUSTOMER NAMES OF PAID ORDERS ==========");
        List<String> paidCustomerNames = orders.stream()
                                                .filter(o-> o.getStatus().equalsIgnoreCase("PAID"))
                                                .map(Order::getCustomerName)
                                                .toList();
        
        Iterator<String> i1 = paidCustomerNames.iterator();
        while (i1.hasNext()) {
            System.out.println(i1.next());
        }


        System.out.println("\n========== 3. CALCULATE TOTAL REVENUE OF PAID ORDERS ==========");
        double totalPaidRevenue = orders.stream()
                                        .filter(o-> o.getStatus().equalsIgnoreCase("PAID"))
                                        .mapToDouble(Order::getAmount)
                                        .sum();
        
        System.out.println("Total Paid Revenue : " + totalPaidRevenue);
                

        System.out.println("\n========== 4. SORT ORDERS BY AMOUNT (DESCENDING) ==========");
        List<Order> sortedOrders = orders.stream()
                                        .sorted(Comparator.comparingDouble(Order::getAmount).reversed())
                                        .toList();
        
        Iterator<Order> i2 = sortedOrders.iterator();                                
        while(i2.hasNext()){
            System.out.println(i2.next());
        }


        System.out.println("\n========== 5. DISTINCT CUSTOMER NAMES (using forEach) ==========");
        orders.stream()
            .map(Order::getCustomerName) 
            .distinct()
            .forEach(System.out::println);


        System.out.println("\n========== 6. DISTINCT CUSTOMER NAMES (toList) ==========");
        List<String> uniqueCustomers01 = orders.stream()
                                            .map(Order::getCustomerName)
                                            .distinct()
                                            .toList();

        for(String s : uniqueCustomers01){
            System.out.println(s);
        }


        System.out.println("\n========== 7. DISTINCT CUSTOMER NAMES (toSet) ==========");
        Set<String> uniqueCustomers02 = orders.stream()
                                            .map(Order::getCustomerName)
                                            .collect(Collectors.toSet());

        for(String s : uniqueCustomers02){
            System.out.println(s);
        }



        System.out.println("\n========== 8. SECOND HIGHEST ORDER BY AMOUNT ==========");
        // Best Approach 1
        Optional<Order> secondHighest = orders.stream()
                                            .sorted(Comparator.comparingDouble(Order::getAmount).reversed())
                                            .skip(1)
                                            .findFirst();
        secondHighest.ifPresent(order -> 
            System.out.println("Second Highest Order: " + order)
        );

        // Aprroach 2
        Optional<Double> secondHighestAmount = orders.stream()
                                                    .map(Order::getAmount)
                                                    .sorted(Comparator.reverseOrder())
                                                    .skip(1)
                                                    .findFirst();
        secondHighestAmount.ifPresent(amount ->
                System.out.println("\nSecond Highest Order Amount Only: " + amount)
        );
        
        // Approach 3
        Map<Order, String> secondHighestMap = orders.stream()
                                                    .sorted(Comparator.comparingDouble(Order::getAmount).reversed())
                                                    .skip(1)
                                                    .findFirst()
                                                    .map(o -> {
                                                        Map<Order, String> map = new HashMap<>();
                                                        map.put(o, o.getCustomerName());
                                                        return map;
                                                    })
                                                    .orElse(new HashMap<>());
        System.out.println("\nApproach 3 - Map (Order -> Name): " + secondHighestMap);

        // Approach 4
        Map<Order, String> secondHighestMap2 = new HashMap<>();
        orders.stream()
                .sorted(Comparator.comparingDouble(Order::getAmount).reversed())
                .skip(1)
                .findFirst()
                .ifPresent(o -> secondHighestMap2.put(o, o.getCustomerName()));
        System.out.println("\nApproach 4 (Cleaner) - Map (Order -> Name): " + secondHighestMap2);

        // Approach 5
        orders.stream()
                .sorted(Comparator.comparingDouble(Order::getAmount).reversed())
                .skip(1)
                .findFirst()
                .ifPresent(o ->
                        System.out.println("\nApproach 5 - Direct Print: "
                                + o.getCustomerName() + " - " + o.getAmount())
                );  


    

        System.out.println("\n========== 9. COLLECTORS IMPORTANT OPERATIONS ==========");
        
        // 9.1 Collect to List
        List<Order> paidOrdersCollector = orders.stream()
                                                .filter(o -> o.getStatus().equalsIgnoreCase("PAID"))
                                                .collect(Collectors.toList());

        System.out.println("\n9.1 Paid Orders (toList):");
        paidOrdersCollector.forEach(System.out::println);


        // 9.2 Collect to Set
        Set<String> cities = orders.stream()
                                   .map(Order::getCity)
                                   .collect(Collectors.toSet());

        System.out.println("\n9.2 Unique Cities (toSet): " + cities);


        // 9.3 Collect to Map (OrderId -> CustomerName)
        Map<Integer, String> idNameMap = orders.stream()
                                               .collect(Collectors.toMap(
                                                       Order::getOrderId,
                                                       Order::getCustomerName
                                               ));

        System.out.println("\n9.3 Map (OrderId -> CustomerName): " + idNameMap);


        // 9.4 Joining (All Customer Names)
        String allNames = orders.stream()
                                .map(Order::getCustomerName)
                                .collect(Collectors.joining(", "));

        System.out.println("\n9.4 Joining Names: " + allNames);


        // 9.5 Counting
        long totalOrders = orders.stream()
                                 .collect(Collectors.counting());

        System.out.println("\n9.5 Total Orders Count: " + totalOrders);


        // 9.6 Summing
        double totalRevenue = orders.stream()
                                    .collect(Collectors.summingDouble(Order::getAmount));

        System.out.println("\n9.6 Total Revenue (summingDouble): " + totalRevenue);


        // 9.7 Averaging
        double avgAmount = orders.stream()
                                 .collect(Collectors.averagingDouble(Order::getAmount));

        System.out.println("\n9.7 Average Order Amount: " + avgAmount);


        // 9.8 Max By
        Optional<Order> maxOrder = orders.stream()
                                         .collect(Collectors.maxBy(
                                                 Comparator.comparingDouble(Order::getAmount)));

        maxOrder.ifPresent(o ->
                System.out.println("\n9.8 Max Order (maxBy): " + o)
        );


        // 9.9 Min By
        Optional<Order> minOrder = orders.stream()
                                         .collect(Collectors.minBy(
                                                 Comparator.comparingDouble(Order::getAmount)));

        minOrder.ifPresent(o ->
                System.out.println("\n9.9 Min Order (minBy): " + o)
        );


        // 9.10 Partitioning (PAID vs NOT PAID)
        Map<Boolean, List<Order>> partitioned = orders.stream()
                                                       .collect(Collectors.partitioningBy(
                                                               o -> o.getStatus().equalsIgnoreCase("PAID")
                                                       ));

        System.out.println("\n9.10 Partitioned Orders (PAID / NOT PAID): " + partitioned);




        System.out.println("\n========== 10. GROUPING BY IMPORTANT OPERATIONS ==========");
        // 10.1 Group By Category
        Map<String, List<Order>> byCategory = orders.stream()
                                                    .collect(Collectors.groupingBy(Order::getCategory));

        System.out.println("\n10.1 Grouped By Category:");
        byCategory.forEach((k, v) -> {
            System.out.println(k + " -> " + v);
        });

        // 10.2 Group By City
        Map<String, List<Order>> byCity = orders.stream()
                                                .collect(Collectors.groupingBy(Order::getCity));

        System.out.println("\n10.2 Grouped By City:");
        byCity.forEach((k, v) -> {
            System.out.println(k + " -> " + v);
        });

        // 10.3 Group By Status
        Map<String, List<Order>> byStatus = orders.stream()
                                                  .collect(Collectors.groupingBy(Order::getStatus));

        System.out.println("\n10.3 Grouped By Status:");
        byStatus.forEach((k, v) -> {
            System.out.println(k + " -> " + v);
        });

        // 10.4 Group By Category + Count
        Map<String, Long> categoryCount = orders.stream()
                                                .collect(Collectors.groupingBy(
                                                        Order::getCategory,
                                                        Collectors.counting()
                                                ));

        System.out.println("\n10.4 Category Count: " + categoryCount);

        // 10.5 Group By Category + Total Revenue
        Map<String, Double> categoryRevenue = orders.stream()
                                                    .collect(Collectors.groupingBy(
                                                            Order::getCategory,
                                                            Collectors.summingDouble(Order::getAmount)
                                                    ));

        System.out.println("\n10.5 Category Revenue: " + categoryRevenue);

        // 10.6 Group By City + Max Order
        Map<String, Optional<Order>> maxOrderByCity = orders.stream()
                                                            .collect(Collectors.groupingBy(
                                                                    Order::getCity,
                                                                    Collectors.maxBy(
                                                                            Comparator.comparingDouble(Order::getAmount)
                                                                    )
                                                            ));

        System.out.println("\n10.6 Max Order By City: " + maxOrderByCity);
        



        System.out.println("\n========== 11 PARTITIONING BY IMPORTANT OPERATIONS ==========");
        // 11.1 Partition Orders -> PAID vs NOT PAID
        Map<Boolean, List<Order>> partitionedOrders = orders.stream()
                .collect(Collectors.partitioningBy(
                        o -> o.getStatus().equalsIgnoreCase("PAID")
                ));

        System.out.println("\n11.1 Partitioned Orders (PAID / NOT PAID):");
        partitionedOrders.forEach((k, v) -> 
                System.out.println(k + " -> " + v)
        );


        // 11.2 Partition + Count
        Map<Boolean, Long> partitionCount = orders.stream()
                .collect(Collectors.partitioningBy(
                        o -> o.getStatus().equalsIgnoreCase("PAID"),
                        Collectors.counting()
                ));

        System.out.println("\n11.2 Partition Count (PAID / NOT PAID): " + partitionCount);


        // 11.3 Partition + Total Revenue
        Map<Boolean, Double> partitionRevenue = orders.stream()
                .collect(Collectors.partitioningBy(
                        o -> o.getStatus().equalsIgnoreCase("PAID"),
                        Collectors.summingDouble(Order::getAmount)
                ));

        System.out.println("\n11.3 Partition Revenue (PAID / NOT PAID): " + partitionRevenue);


        // 11.4 Partition + Max Order
        Map<Boolean, Optional<Order>> partitionMaxOrder = orders.stream()
                .collect(Collectors.partitioningBy(
                        o -> o.getStatus().equalsIgnoreCase("PAID"),
                        Collectors.maxBy(
                            Comparator.comparingDouble(Order::getAmount)
                        )
                ));

        System.out.println("\n11.4 Partition Max Order (PAID / NOT PAID): " + partitionMaxOrder);


        // 11.5 Partition + Mapping (Get Only Customer Names)
        Map<Boolean, List<String>> partitionCustomerNames = orders.stream()
                .collect(Collectors.partitioningBy(
                        o -> o.getStatus().equalsIgnoreCase("PAID"),
                        Collectors.mapping(
                                Order::getCustomerName,
                                Collectors.toList()
                        )
                ));

        System.out.println("\n11.5 Partition Customer Names (PAID / NOT PAID): " + partitionCustomerNames);





        System.out.println("\n========== 12. ADVANCED STREAM OPERATIONS ==========");
        // 12.1 Multi-Level Grouping (City -> Category)
        Map<String, Map<String, List<Order>>> cityCategoryGrouping =
                orders.stream()
                      .collect(Collectors.groupingBy(
                              Order::getCity,
                              Collectors.groupingBy(Order::getCategory)
                      ));

        System.out.println("\n12.1 City -> Category -> Orders:");
        cityCategoryGrouping.forEach((city, categoryMap) -> {
            System.out.println(city + " -> " + categoryMap);
        });


        // 12.2 Top Order Per Category
        Map<String, Optional<Order>> topOrderPerCategory =
                orders.stream()
                      .collect(Collectors.groupingBy(
                              Order::getCategory,
                              Collectors.maxBy(
                                      Comparator.comparingDouble(Order::getAmount)
                              )
                      ));

        System.out.println("\n12.2 Top Order Per Category:");
        topOrderPerCategory.forEach((k, v) ->
                System.out.println(k + " -> " + v)
        );

        System.out.println("\n12.2 Top Order Per Category (only Customer Name)");
        topOrderPerCategory.forEach((k, v) ->
                System.out.println(k + " -> " + v.get().getCustomerName())
        );


        // 12.3 Convert Optional to Direct Order (Remove Optional)
        Map<String, Order> topOrderPerCategorySafe =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getCategory,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(
                                                Comparator.comparingDouble(Order::getAmount)
                                        ),
                                        opt -> opt.orElse(null)
                                )
                        ));

        System.out.println("\n12.3 Top Order Per Category (Without Optional):");
        topOrderPerCategorySafe.forEach((k, v) ->
                System.out.println(k + " -> " + v)
        );


        // 12.4 Find Highest Revenue City
        Map<String, Double> revenueByCity =
                orders.stream()
                      .collect(Collectors.groupingBy(
                              Order::getCity,
                              Collectors.summingDouble(Order::getAmount)
                      ));

        Optional<Map.Entry<String, Double>> highestRevenueCity =
                revenueByCity.entrySet()
                             .stream()
                             .max(Map.Entry.comparingByValue());

        highestRevenueCity.ifPresent(e ->
                System.out.println("\n12.4 Highest Revenue City: "
                        + e.getKey() + " -> " + e.getValue())
        );


        // 12.5 Flatten Grouped Data (Get All Orders Again)
        List<Order> flattenedOrders =
                cityCategoryGrouping.values()
                                    .stream()
                                    .flatMap(map -> map.values().stream())
                                    .flatMap(List::stream)
                                    .toList();

        System.out.println("\n12.5 Flattened Orders:");
        flattenedOrders.forEach(System.out::println);


        // 12.6 Find Duplicate Customer Names
        Set<String> duplicateCustomers =
                orders.stream()
                      .collect(Collectors.groupingBy(
                              Order::getCustomerName,
                              Collectors.counting()
                      ))
                      .entrySet()
                      .stream()
                      .filter(e -> e.getValue() > 1)
                      .map(Map.Entry::getKey)
                      .collect(Collectors.toSet());

        System.out.println("\n12.6 Duplicate Customers: " + duplicateCustomers);



        Set<String> groupBycategory01 = orders.stream()
                                        .map(s -> s.getCategory())
                                        .collect(Collectors.toSet());
        System.out.println(groupBycategory01);


        Map<String, List<Order>> groupBycategory02 = orders.stream()
                                        .collect(Collectors.groupingBy(
                                                Order::getCategory
                                        ));            
        groupBycategory02.forEach((s, k) -> {
                System.out.println("Cat: " + s);
                k.forEach(K -> System.out.println("\t" + K.getCustomerName()));
        });

        Map<String, Long> countOrdersPerCategory = orders.stream()
                                                        .collect(Collectors.groupingBy(
                                                                Order::getCategory,
                                                                (Collectors.counting())
                                                        ));
        System.out.println("\n" + countOrdersPerCategory);

        
        Map<String, Double> salesPerCategory = orders.stream()
                                                .collect(Collectors.groupingBy(
                                                        Order:: getCategory,
                                                        Collectors.summingDouble(Order::getAmount)
                                                ));

        System.out.println("\n" +  salesPerCategory);


        
        Map<String, Optional<Order>> maxValueOrderPerCategory = orders.stream()
                                                        .collect(Collectors.groupingBy(
                                                                Order::getCategory,
                                                                Collectors.maxBy(
                                                                        Comparator.comparingDouble(Order::getAmount)    
                                                                )
                                                        ));

        maxValueOrderPerCategory.forEach((x, y) -> {
                System.out.println("Category : " + x + " -> " + y.get().getAmount());
        });


        System.out.println();
        Map<String, Integer> nameLengthMoreThan4 =
                orders.stream()
                .map(Order::getCustomerName)
                .filter(name -> name.length() > 4)
                .collect(Collectors.toMap(
                        name -> name,
                        name -> name.length(),
                        (existing, replacement) -> existing
                ));

        System.out.println("Length of people having name length > 4: " + nameLengthMoreThan4);


        System.out.println();
        Map<String, Integer> result =
        orders.stream()
              .map(Order::getCustomerName)
              .filter(name -> name.length() > 4)
              .sorted(Comparator.comparingInt(String::length))
              .distinct()
              .collect(Collectors.toMap(
                      name -> name,
                      name -> name.length()
                      
              ));
              




        // Collecting names by length
        System.out.println("\nCollecting names by length");
        List<String> names01 = Arrays.asList("Ana", "Bob", "Alexander", "Brain", "Alice");
        Map<Integer, List<String>> namesByLength = names01.stream()
                                                .collect(Collectors.groupingBy(
                                                        String::length
                                                ));
        System.out.println(namesByLength);


        // Counting word occurences
        System.out.println("\nCounting word occurences");
        String sequence= "hello world hello java world world";
        String[] sequenceSplit01 = sequence.split(" ");
        List<String> s0 = Arrays.asList(sequenceSplit01);


        // approach 1
        System.out.println("Approach 1");
        System.out.println(
        Arrays.stream(sequence.split(" "))
                .collect(Collectors.groupingBy(
                        s -> s, 
                        Collectors.counting()
                ))
        );
        
        //aproach 2
        System.out.println("Approach 2");
        Map<String, Long> s10 = s0.stream().
                                        collect(Collectors.groupingBy(
                                                s -> s,
                                                Collectors.counting()
                                        ));

        System.out.println(s10);


        // Partioning Even and Odd Numbers
        System.out.println("\nPartioning Even and Odd Numbers ");
        List<Integer> l2= Arrays.asList(7, 2, 13, 14, 5, 6);
        Map<Boolean, Long> s08 = l2.stream()
                                        .collect(Collectors.partitioningBy(
                                                s -> s % 2 == 0   ,
                                                Collectors.counting() 
                                        ));
        System.out.println(s08);


        // Summing values in a Map
        System.out.println("\nSumming values in a Map ");
        Map<String,Double> items = new HashMap<>(); 
                items.put("Apple", 10.0);
                items.put("Banana",20.0);
                items.put("Orange",15.0);
        System.out.println(items.values().stream().reduce(Double::sum).orElse(0.0));

        System.out.println(items.values().stream()
                                .collect(Collectors.summingDouble(e -> e))
                        );
                
        
        // Creating a Map from Stream Elements
        System.out.println("\nCreating a Map from Stream Elements ");
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");

        System.out.println(fruits.stream().collect(Collectors.toMap(s -> s, s -> s.length())));


        // Merging all the elements of List
        System.out.println("\nMerging all the elements of List");
        List<String> words2 = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple", "peach");
        System.out.println(words2.stream().collect(Collectors.joining("\t")));


        // merging 
        System.out.println("\nMerging to Map");
        System.out.println(words2.stream().collect(Collectors.toMap(k -> k, v -> 1, (x, y) ->  x + y)));
    }
}
