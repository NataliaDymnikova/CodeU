import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public class Movement {
    private int from;
    private int to;

    public Movement(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public void print() {
        System.out.println("move from " + from + " to position " + to);
    }

    public Entry<Integer, Integer> getValues() {
        return new SimpleEntry<>(from, to);
    }
}