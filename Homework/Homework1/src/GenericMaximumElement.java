/**
 * Problem2: Program to print maximum element in an array
 * @Author: Venkata Lakshmi Sasank Tipparaju (700738838)
 */

import java.util.Arrays;
import java.util.Random;

public class GenericMaximumElement {

    //Method to find maximum element in the array of type E
    public static <E extends Comparable<E>> E max(E[] list) {
        E max = list[0];
        for (E listItem : list) {
            max = listItem.compareTo(max) > 0 ? listItem : max;
        }
        return max;
    }

    public static void main(String[] args) {
        //Random Class instance to generate random elements
        Random random = new Random();

        //Initialize Integer and Double array of size 100 with values
        Integer[] integers = new Integer[100];
        Double[] doubles = new Double[100];
        for (int i = 0; i < 100; i++) {
            integers[i] = random.nextInt(10001);
            doubles[i] = random.nextDouble(1.0);
        }

        //Display Integer Array and Get Maximum element in an Array
        System.out.println("Integer Array: "+Arrays.toString(integers));
        Integer integerMax = max(integers);
        System.out.println("Maximum Integer Value: "+integerMax);
        System.out.println();

        //Display Double Array and Get Maximum element in an Array
        System.out.println("Double Array: "+Arrays.toString(doubles));
        Double doubleMax = max(doubles);
        System.out.println("Maximum Double Value: "+doubleMax);

    }
}
