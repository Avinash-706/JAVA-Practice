## LinkedList : 
- It is a implementing class of List interface, Queue interface.
- It is present since jdk 1.2v
- It follows double linkedlist data structure.
- It stores the object in the form of nodes.
- Because of this Linkedlist is good for insertion and deletion opertion.
		
### Singly Linked List:
- Structure: Each node contains data and a pointer to the next node.
- Traversal: Only in one direction (forward).

### Doubly Linked List
- Structure: Each node contains data, a pointer to the next node, and a pointer to the previous node.
- Traversal: Can be traversed forward and backward.

### Circular Doubly Linked List
- Structure: Combines circular and doubly linked list features.
- Each node has pointers to both the next 
and previous nodes, and the last node points back to the first.
- Traversal: Both directions and continuous looping

## PriorityQueue :
- It is an implementation class of Queue interface
- It is present inside java.util package
- It was introduced in JDK 1.5	
- It follows priority-based ordering, not insertion order
- By default, it follows Min-Heap (smallest element has highest priority)
- Elements are retrieved using FIFO based on priority, not on insertion time
- It does not allow null elements
- It allows duplicate elements
- It cn store only same type of objects.
- It is internally implemented using Heap data structure
- It is used to perform priority-based queue operations
	
## Heap Data Structure :
- It is a non-linear data structure
- It follows Complete Binary Tree property

	- Heap is of two types : 
		- Min-Heap : Parent node value is smaller than or equal to its child nodes
		- Max-Heap : Parent node value is greater than or equal to its child nodes