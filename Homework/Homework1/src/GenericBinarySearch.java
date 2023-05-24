/**
 * Problem3: Program to implement generic binarySearch() method
 *
 * @Author: Venkata Lakshmi Sasank Tipparaju (700738838)
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GenericBinarySearch {

    public static <E extends Comparable<E>> int binarySearch(E[] list, E key) {
        if (list.length != 0) {
            int start = 0, end = list.length - 1, mid;
            while (start <= end) {
                mid = start + ((end - start) / 2);
                if (key.compareTo(list[mid]) == 0) {
                    return mid;
                } else if (key.compareTo(list[mid]) > 0) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    public static String getRandomString() {
        Random random = new Random();
        String alphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
        //generating random string of length 1 to 10 characters
        int stringLength = random.nextInt(1, 11);
        StringBuilder randomString = new StringBuilder();
        while (stringLength > 0) {
            randomString.append(alphaNumericStr.charAt(random.nextInt(alphaNumericStr.length())));
            stringLength--;
        }
        return randomString.toString();
    }

    public static void main(String[] args) {
        //Random Class instance to generate random elements
        Random random = new Random();
        int arraySize = 100;
        //Scanner class to read input from user
        Scanner scanner = new Scanner(System.in);

        //Initialize Integer, String & Employee array of size 100 with random values
        Integer[] integers = new Integer[arraySize];
        String[] strings = new String[arraySize];
        Employee[] employees = new Employee[arraySize];
        for (int i = 0; i < arraySize; i++) {
            integers[i] = random.nextInt(100);
            strings[i] = getRandomString();
            employees[i] = new Employee(getRandomString(), getRandomString(), random.nextDouble(10000, 1000000));
        }

        //Sort the integers array as binary search works on sorted input
        Arrays.sort(integers);
        System.out.println("Array of Integers: " + Arrays.toString(integers));
        //get the integer input from user and perform binary search on integers array
        System.out.print("Enter an Integer(Key to find in array): ");
        int integerKey = scanner.nextInt();
        int location = binarySearch(integers, integerKey);
        String output = location == -1 ? integerKey + ": is not Found in Array" : integerKey + ": is located at Index " + location + " in the array";
        System.out.println(output);
        System.out.println();

        //Sort the strings array as binary search works on sorted input
        Arrays.sort(strings);
        System.out.println("Array of Strings: " + Arrays.toString(strings));
        //get the string input from user and perform binary search on strings array
        System.out.print("Enter an String(Key to find in array): ");
        String stringKey = scanner.next();
        location = binarySearch(strings, stringKey);
        output = location == -1 ? stringKey + ": is not Found in Array" : stringKey + ": is located at Index " + location + " in the array";
        System.out.println(output);
        System.out.println();

        //Sort the employees array based on first name and then last name. Perform binary search
        Arrays.sort(employees);
        System.out.println("Array of Employees: " + Arrays.toString(employees));
        //get the first and last name from user and perform binary search on strings array
        System.out.print("Enter First Name of Employee: ");
        String firstName = scanner.next();
        System.out.print("Enter Last Name of Employee: ");
        String lastName = scanner.next();
        String employeeName = firstName + " " + lastName;
        Employee employee = new Employee(firstName, lastName, null);
        location = binarySearch(employees, employee);
        if (location != -1) {
            System.out.println(employeeName + ": is located at Index " + location + " in the Employees Array");
            System.out.println(employees[location]);
        } else {
            System.out.println(employeeName + ": is not Found in Employees Array");
        }
        scanner.close();
    }
}


class Employee implements Comparable<Employee> {
    private final String firstName;
    private final String lastName;
    private Double salary;

    public Employee(String firstName, String lastName, Double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee employee) {
        if (firstName.compareTo(employee.firstName) == 0) {
            if (lastName.compareTo(employee.lastName) == 0) {
                return 0;
            } else if (lastName.compareTo(employee.lastName) > 0) {
                return 1;
            } else {
                return -1;
            }
        } else if (firstName.compareTo(employee.firstName) > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "First Name: " + this.firstName + " Last Name: " + this.lastName + " Salary: $" + this.salary;
    }
}