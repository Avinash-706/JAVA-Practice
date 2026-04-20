import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class VectorList {
	public static void main(String[] args) {
		List<String> v = new Vector<String>();
		v.add("Suraj");
		v.add("mridul");
		v.add("kabir");
		v.add("girish");
		System.out.println(v);
		System.out.println("Size : " + v.size());

		for (int i = 0; i < v.size(); i++) {
			System.out.println(v.get(i));
		}

		System.out.println("-----");
		Iterator<String> itr = v.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
}
