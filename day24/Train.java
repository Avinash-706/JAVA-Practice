
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Problem Statement
 * You are developing a Train Compartment Management System using Java Collections.
 * Each compartment is identified by a compartment number (Integer).
 * Tasks to Perform
	Create a LinkedList<Integer> to store compartment numbers.
	Add compartments in the following order: 101, 102, 103, 104, 105, 106

Perform the following operations step by step:
Operations
	Insert a VIP compartment (999): Immediately after compartment 102
	Remove all even-numbered compartments (Use Iterator only)
	Reverse the remaining compartments without using Collections.reverse()
	Check whether compartment 105 exists:
		If present → print its index
		If not present → print "Compartment not found"
 */

public class Train {
    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
		ll.add(101);
		ll.add(102);
		ll.add(103);
		ll.add(104);
		ll.add(105);
		ll.add(106);

		ll.add(2, 999);

		System.out.println(ll);

		ListIterator<Integer> it = ll.listIterator();

		while(it.hasNext()){
			if(it.next() % 2 == 0){
				it.remove();
			}
		}

		System.out.println("After removing Even: " + ll);

		LinkedList<Integer> rev = new LinkedList<>();
		for(Integer i : ll){
			rev.addFirst(i);
		}

		System.out.println("After reversing the LinkedList: " + rev);

		if(rev.contains(105)){
			System.out.println("105: present");
		} else {
			System.out.println("105: present");
		}
    }
}