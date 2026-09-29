package Practice;
import java.util.HashMap;

public class EmployeNameHashMap {
    public static void main(String[] args) {
        HashMap<Integer, String> hm = new HashMap<>();

        hm.put(1, "Kabir");
        hm.put(1, "Avinash");
        hm.put(4, "Babalpreet");
        hm.put(3, "Kshitiz");

        hm.forEach((id, name) -> System.out.println("Employee Id: " + id + ", Employee Name: " + name));

        System.out.println(hm);
    }
}
