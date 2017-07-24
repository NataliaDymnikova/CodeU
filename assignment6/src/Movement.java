import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class Movement {
    private final static Logger logger = Logger.getLogger(Movement.class.getName());

    private int from;
    private int to;

    public Movement(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public void print() {
        logger.info("move from " + from + " to " + to);
    }

    public Entry<Integer, Integer> getValues() {
        return new SimpleEntry<>(from, to);
    }
}