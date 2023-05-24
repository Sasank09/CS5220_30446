import java.util.Arrays;

public class Rectangle_C implements Comparable<Rectangle_C> {

    int length;
    int width;

    public Rectangle_C(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getArea(){
        return length*width;
    }

    public String toString(){
        return "Length: "+length+" width: "+width+" Area: "+getArea();
    }

    public int compareTo(Rectangle_C r) {
        if(getArea() > r.getArea()) {
            return 1;
        } else if (getArea()< r.getArea()) {
            return -1;
        }
        else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Rectangle_C[]  rectangles = {new Rectangle_C(1,2), new Rectangle_C(10,20), new Rectangle_C(12,3)};

        Arrays.sort(rectangles);
        for(Rectangle_C rec : rectangles) {
            System.out.println(rec);
        }
    }
}
