public class StringCount {

    public static <E> int toStringCount(E object) {
        return object.toString().length();
    }

    public static void main(String[] args) {
        Integer integerObj = 555;
        Double doubleObj = 1234.561;
        String stringObj = "Generics are quite useful";

        System.out.println(integerObj + " has " + toStringCount(integerObj));
        System.out.println(doubleObj + " has " + toStringCount(doubleObj));
        System.out.println(stringObj + " has " + toStringCount(stringObj));
    }
}
