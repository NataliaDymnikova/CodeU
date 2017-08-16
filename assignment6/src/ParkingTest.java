import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class ParkingTest {
    private Parking parking = new Parking();

    @Test
    public void testNoElements() {
        List<Movement> movements = parking.RearrangingCars(new int[]{0}, new int[]{0});
        assertEquals(0, movements.size());
    }

    @Test
    public void testOneElementSamePositions() {
        List<Movement> movements = parking.RearrangingCars(new int[]{0, 1}, new int[]{0, 1});
        assertEquals(0, movements.size());
    }

    @Test
    public void testOneElementDifferentPositions() {
        List<Movement> movements = parking.RearrangingCars(new int[]{1, 0}, new int[]{0, 1});
        checkEntries(new int[][]{{0, 1}}, movements);
    }

    @Test
    public void testTwoElementsWithTwoMovements() {
        List<Movement> movements = parking.RearrangingCars(new int[]{2, 1, 0}, new int[]{0, 2, 1});
        checkEntries(new int[][]{{1, 2}, {0, 1}}, movements);
    }

    @Test
    public void testMoreElementsWithoutSamePositionOfZero() {
        List<Movement> movements = parking.RearrangingCars(new int[]{1, 2, 0, 3}, new int[]{3, 1, 2, 0});
        checkEntries(new int[][]{{1, 2}, {0, 1}, {3, 0}}, movements);
    }

    @Test
    public void testMoreElementsWithSamePositionOfZero() {
        List<Movement> movements = parking.RearrangingCars(new int[]{1, 2, 0, 3}, new int[]{3, 1, 0, 2});
        checkEntries(new int[][]{{0, 2}, {3, 0}, {1, 3}, {2, 1}}, movements);
    }

    @Test
    public void testWithCyclicalMovements() {
        int[] beginParking = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int[] endParkingExpected = {2, 3, 4, 5, 6, 7, 8, 9, 0, 1};
        List<Movement> movements = parking.RearrangingCars(beginParking, endParkingExpected);
        int[] endParking = makeMovements(movements, beginParking);
        assertArrayEquals(endParkingExpected, endParking);
    }

    @Test
    public void bigRandomTest() {
        int[] beginParking = new int[100];
        Arrays.parallelSetAll(beginParking, operand -> operand);
        List<Integer> listToShuffle = stream(beginParking).boxed().collect(toList());
        shuffle(listToShuffle);
        int[] endParkingExpected = listToShuffle.stream().mapToInt(i -> i).toArray();

        List<Movement> movements = parking.RearrangingCars(beginParking, endParkingExpected);
        int[] endParking = makeMovements(movements, beginParking);
        assertArrayEquals(endParkingExpected, endParking);
    }

    private void checkEntries(int[][] realMovements, List<Movement> expectedMovements) {
        int size = expectedMovements.size();
        assertThat(size, is(realMovements.length));
        for (int i = 0; i < size; i++) {
            assertThat(new int[]{expectedMovements.get(i).getFrom(), expectedMovements.get(i).getTo()}, is(realMovements[i]));
        }
    }

    private int[] makeMovements(List<Movement> movements, int[] beginParking) {
        int[] endParking = beginParking.clone();
        for (Movement move : movements) {
            swap(endParking, move.getFrom(), move.getTo());
        }
        return endParking;
    }

    private void swap(int[] beginParking, int from, int to) {
        int fromValue = beginParking[from];
        beginParking[from] = beginParking[to];
        beginParking[to] = fromValue;
    }
}