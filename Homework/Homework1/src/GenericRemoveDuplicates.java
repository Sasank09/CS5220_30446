/**
 * Problem1: Program to remove duplicate elements in ArrayList
 * @Author: Venkata Lakshmi Sasank Tipparaju (700738838)
 */

import java.util.ArrayList;
import java.util.Random;
public class GenericRemoveDuplicates {

    //Method to remove duplicate elements in the ArrayList<E>
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> withoutDuplicates = new ArrayList<>();
        for (E listItem: list) {
            if (!withoutDuplicates.contains(listItem)) {
                withoutDuplicates.add(listItem);
            }
        }
        return withoutDuplicates;
    }
    public static void main(String[] args) {
        //Random Class instance to generate random elements
        Random random = new Random();

        //Initialize Integer and Char ArrayList of size 100 with random values
        ArrayList<Integer> integersList = new ArrayList<>();
        ArrayList<Character> charactersList = new ArrayList<>();
        for(int i=0; i<100; i++){
            integersList.add(random.nextInt(10));
            charactersList.add((char)(random.nextInt(26)+97));
        }

        //Display the content of Integer ArrayList with and without duplicates
        System.out.println("Integer ArrayList WITH DUPLICATES: "+integersList);
        ArrayList<Integer> integersResult = removeDuplicates(integersList);
        System.out.println("Integer ArrayList WITHOUT DUPLICATES: "+integersResult);
        System.out.println();

        //Display the content of Character ArrayList with and without duplicates
        System.out.println("Char ArrayList WITH DUPLICATES: "+ charactersList);
        ArrayList<Character> charactersResult  = removeDuplicates(charactersList);
        System.out.println("Char ArrayList WITHOUT DUPLICATES: "+charactersResult);
    }
}
