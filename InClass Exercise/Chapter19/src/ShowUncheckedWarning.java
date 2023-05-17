//Creating a generic method
//Example credited to
//http://www.java2s.com/Tutorials/Java/Java_Language/
//8010__Java_generic_method.htm

public class ShowUncheckedWarning {
    public static void main(String[] args) {
            java.util.ArrayList list = new java.util.ArrayList();
            list.add("Java Programming");
            list.add(5);

            //with list declaration on line 8,
            // any object can be added to list because
            // it is an ArrayList of Object type.

            //Declaring an ArrayList without specifying a type
            //could lead to runtime errors.
            list.add(42);

            String s = (String) list.get(2);
           // System.out.println(s);




            //If is much preferred to specify a type a show below with list2
            java.util.ArrayList<String> list2 = new java.util.ArrayList<>();
            list2.add("Java Programming");
            //list2.add(5);  //This line is flagged as a compile error, which is
                             //is much preferred to finding an error at runtime.


    }
}
