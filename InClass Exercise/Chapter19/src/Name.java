import java.util.Arrays;

public class Name implements Comparable<Name> {
    String first;
    String last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public String toString() {
        return "First Name: " + first + " Last Name: " + last;
    }

    public int compareTo(Name name) {
        if (first.compareTo(name.first) == 0) {
            if (last.compareTo(name.last) == 0) {
                return 0;
            } else if (last.compareTo(name.last) > 0) {
                return 1;
            } else {
                return -1;
            }
        } else if (first.compareTo(name.first) > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        Name[] names = new Name[]{new Name("Shakti", "Annam"), new Name("Sasank", "Tipparaju"), new Name("Satish", "Sattenapalli")};
        Arrays.sort(names);
        for (Name name : names) {
            System.out.println(name);
        }
    }
}
