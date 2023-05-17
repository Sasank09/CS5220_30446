import java.math.BigInteger;
import java.util.ArrayList;

public class GenericsExample_2 {
    public static void main(String[] args) {
        ArrayList a = new ArrayList();
        a.add("Red");
        a.add(new BigInteger("5"));

        System.out.println("Contents of a: " + a);
        Object g = a.get(0);
        Object h = a.get(1);

        //int result = g.compareTo(h);
        //System.out.println("Result is "+ result );

    }
}
