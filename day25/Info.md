## TreeSet
- it is an implementation of Set interferface
- it is present inside java.util package
- introduced in jdk 1.2
- it follows sorted order (ASC by default)
- It does not follow insertion Order
- it does not allow duplicate 
- it does not allow null elements
- it can store only same type of object
- It is used when sorted data with uniqueness is required


## Map:
- java.util
- stores data in key value pair
- one key value pair is known as  one `entry`
- key must be unique
- values can be duplicate
- Map does not come under collection
- Map interface has only three implementing classes such as `HashMap`, `LinkedHashMap`, `TreeMap`


## HashMap: 
- It is an implementation class of Map interface
- It is present inside java.util package
- It was introduced in JDK 1.2
- It does not follow insertion order
- It does not follow sorting order
- It does not allow duplicate keys
- It allows duplicate values
- It allows only one null key
- It allows multiple null values
- It is internally implemented using HashTable (Hashing mechanism)
- It provides fast performance for put, remove, and get operations
- It is not synchronized
- It is used when fast access is required and order is not important


## LinkedHashMap
- It is an implementation class of Map interface
- It is present inside java.util package
- It was introduced in JDK 1.4
- It follows insertion order
- It does not follow sorting order
- It does not allow duplicate keys
- It allows duplicate values
- It allows only one null key
- It allows multiple null values
- It is internally implemented using HashTable + Doubly Linked List
- It provides slightly slower performance than HashMap due to order maintenance
- It is not synchronized
- It is used when uniqueness of keys + insertion order both are required


## TreeMap
- It is an implementation class of NavigableMap interface
- It is present inside java.util package
- It was introduced in JDK 1.
- It follows sorted order of keys (ascending by default)
- It does not follow insertion order
- It does not allow duplicate keys
- It allows duplicate values
- It does not allow null keys
- It allows multiple null values
- It stores keys in sorted structure
- It is used when sorted key-value pairs are required