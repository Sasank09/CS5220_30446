import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Bin {

    private final double maxWeight;
    private double totalWeight;
    private ArrayList<Double> container;

    public Bin() {
        this.maxWeight = 10.0;
        this.totalWeight = 0.0;
        this.container = new ArrayList<>();
    }

    public Bin(double maxWeight) {
        this.maxWeight = maxWeight;
        this.totalWeight = 0.0;
        this.container = new ArrayList<>();
    }

    @Override
    public String toString() {
        String content="";
        for(int i=0; i<getNumberOfObjects();i++) {
            content= content + container.get(i)+" ";
        }
        return content;
    }

    public Boolean addItem(double weight) {
        if(weight<=maxWeight){
            if ((this.maxWeight - this.totalWeight) >= weight) {
                this.totalWeight += weight;
                this.container.add(weight);
                return true;
            } else {
                return false;
            }
        }
        else {
            System.out.println("Object weight "+weight+" is more than maxWeight of container "+ this.maxWeight);
            System.exit(0);
            return false;
        }

    }

    public int getNumberOfObjects() {
        return this.container.size();
    }

    public static void main(String[] args) {

        //Get the input from user along with weights
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of objects: ");
        int noOfObjects = scan.nextInt();
        double[] weights = new double[noOfObjects];
        System.out.print("Enter the weight of the objects: ");
        for (int i = 0; i < noOfObjects; i++) {
            if (scan.hasNextDouble()) {
                weights[i] = scan.nextDouble();
            }
        }
        //Sort the weights in an order
        Arrays.sort(weights);
        //System.out.println("Weights of " + noOfObjects + " objects: " + Arrays.toString(weights));
        System.out.println();

        //create bins arraylist to store containers of object
        ArrayList<Bin> bins = new ArrayList<>();
        Bin binContainer = new Bin();
        bins.add(binContainer);

        //iterate through objects and fit the object in firstFit mode
        for (int i = noOfObjects - 1; i >= 0; i--) {
            boolean isAdded = false;
            for (Bin bin : bins) {
                if (bin.addItem(weights[i])) {
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded) {
                binContainer = new Bin();
                bins.add(binContainer);
                binContainer.addItem(weights[i]);
            }

        }
        //display the result
        for (int index = 0; index < bins.size(); index++) {
            System.out.println("Container " + (index + 1) + " contains objects with weight " + bins.get(index));
        }
        scan.close();
    }
}
