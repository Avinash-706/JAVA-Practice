import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Students implements Comparable<Students>{
	int id;
	String name;
	double marks;
	public Students(int id, String name, double marks) {
		super();
		this.id = id;
		this.name = name;
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Students [id=" + id + ", name=" + name + ", marks=" + marks + "]";
	}
	@Override
	public int compareTo(Students s) {
		return this.id - s.id;
	}
}



public class FIThree {
	public static void main(String[] args) {
		
		List<Students> st = new ArrayList<>();
		st.add(new Students(3, "Murali", 75.5));
		st.add(new Students(4, "Aditya", 85.5));
		st.add(new Students(1, "Vishnu", 80.5));
		st.add(new Students(2, "Pramod", 90.5));
		st.add(new Students(2, "Suraj", 55.5));
		System.out.println(st);
		Collections.sort(st);
		System.out.println("\n--Sorted by id--");
		for (Students s : st) {
			System.out.println(s);
		}
		
		System.out.println("\n--Sorted by name--");
		Collections.sort(st,Comparator.comparing(s -> s.name));
		for (Students s : st) {
			System.out.println(s);
		}
		
		System.out.println("\n--Sorted by marks--");
		Collections.sort(st, Comparator.comparingDouble(s -> s.marks));
		for (Students s : st) {
			System.out.println(s);
		}
	}
}