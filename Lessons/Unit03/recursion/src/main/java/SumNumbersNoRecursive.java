import java.util.ArrayList;
import java.util.List;

public class SumNumbersNoRecursive {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(10);
        list.add(9);
        int sum = 0;
        for (Integer integer : list) sum += integer;
        System.out.println("sum-> " + sum);
    }
}