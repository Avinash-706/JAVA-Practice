import java.util.ArrayList;
import java.util.List;

public class ListFour {

    public static List<Integer> arrayToList(int[] arr){
        List<Integer> res = new ArrayList<>();

        for(int i:arr){
            res.add(i);
        }
        return res;
    }

    public static int[] listToArray(List<Integer> arr){
        int[] res = new int[arr.size()];
        int index = 0;

        for(int i:arr){
            res[index] = i;
            index++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};

        List<Integer> list = arrayToList(arr);
        System.out.println("List -> " + list);
        
        int[] array = listToArray(list);

        for(int i : array){
            System.out.print(i + " ");
        }
    }
}
