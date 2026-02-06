import java.util.ArrayList;
import java.util.List;
public class ListMethod {
    public static void main(String[] args) {
        List al = new ArrayList();
        al.add(10);
        al.add(10.0);
        al.add('a');
        al.add("LPU");
        al.add(false);
        al.add(null);

        System.out.println(al);
        System.out.println(al.size());
        System.out.println(al.get(1));
    }
}
