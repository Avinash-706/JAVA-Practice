## Comparable
- It is an interface present inside java.lang package
- It was introduced in JDK 1.2
- It is used to define natural sorting order of objects
- It provides only one method → compareTo(Object o)
- Sorting logic is written inside the same class
- It allows only one sorting sequence
- It is used when default sorting logic is required
- It is mainly used with Collections.sort() and TreeSet / TreeMap
- It modifies the original class
- Example:
- String, Integer, Float already implement Comparable

## Comparator
- It is an interface present inside java.util package
- It was introduced in JDK 1.2
- It is used to define custom sorting order
- It provides two methods
    - compare(Object o1, Object o2)
    - equals(Object obj)
- Sorting logic is written in a separate class
- It allows multiple sorting sequences
- It is used when class source code cannot be modified
- It does not affect the original class
- It is mainly used with Collections.sort(), TreeSet, TreeMap
- Useful for sorting based on multiple fields

## Enum (Enumeration) : 
- Enum  is a special data type used to define a fixed set of constant values.
- Introduced in JDK 1.5
- enum is a keyword
- Enum constants are public static final by default
- Enum constants are objects
- Enum cannot be instantiated using new

- It is used 
    - To restrict values
    - To improve code readability
    - To avoid invalid data
    - To replace multiple public static final constants	
    - To provide type safety
	
```
values():   Returns all enum constants
valueOf():	Converts String to enum
ordinal():	Returns index position
name(): 	Returns constant name
```