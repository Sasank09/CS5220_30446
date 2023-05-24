import java.util.ArrayList;

public class LinearSearch {

    public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
//        for(E element: list){
//            if(key.compareTo(element)==0) {
//                return list.indexOf(element);
//            }
//        }
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] integerArray = {1, 4, -1, 2, 43, 32, 21, 2};
        int searchKey = 1;
        int result = linearSearch(integerArray, searchKey);
        if (result > -1) {
            System.out.println("Location of " + searchKey + " in array is " + result);
        } else {
            System.out.println(searchKey + " is not in Array");
        }

        Character[] charArray = {'a','b','d','z'};
        searchKey = 'z';
        result = linearSearch(charArray, searchKey);
        if (result > -1) {
            System.out.println("Location of " + searchKey + " in array is " + result);
        } else {
            System.out.println(searchKey + " is not in Array");
        }
    }
}
