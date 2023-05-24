import java.util.ArrayList;

public class MaximumElement {

    public static <E extends Comparable<E>>E max(ArrayList<E> list){
        E maxElement = list.get(0);
        for(int i =1; i<list.size();i++) {
            if(maxElement.compareTo(list.get(i))<0) {
                maxElement=list.get(i);
            }
        }
        return maxElement;
    }

    public static void main(String[] args) {
        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(4);
        integerList.add(14);
        integerList.add(42);integerList.add(41);
        integerList.add(43);
        integerList.add(24);
        System.out.println("The biggest value is: " +max(integerList));
    }
}
