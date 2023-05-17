import java.util.Date;

public class GenericsExample_1 {
    public static void main(String[] args) {

        //before Java 5
        Comparable c = new Date();

        System.out.println(c.compareTo("RED"));


    }
}
