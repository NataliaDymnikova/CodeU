import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.List;
import java.util.Map.Entry;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class ParkingTest {

    private Parking parking = new Parking();

    @Test
    public void testNoElements(){
        List<Entry<Integer, Integer>> movements = parking.RearrangingCars(new int[]{0}, new int[]{0});
        assertEquals(0, movements.size());
    }

    @Test
    public void testOneElementSamePositions(){
        List<Entry<Integer, Integer>> movements = parking.RearrangingCars(new int[]{0,1}, new int[]{0,1});
        assertEquals(0, movements.size());
    }

    @Test
    public void testOneElementDifferentPositions(){
        List<Entry<Integer, Integer>> movements = parking.RearrangingCars(new int[]{1,0}, new int[]{0,1});
        checkEntries(new int[][] {{0, 1}}, movements);
    }

    @Test
    public void testTwoElementsWithTwoMovements(){
        List<Entry<Integer, Integer>> movements = parking.RearrangingCars(new int[]{2,1,0}, new int[]{0,2,1});
        checkEntries(new int[][] {{1, 2}, {0, 1}}, movements);
    }

    @Test
    public void testMoreElementsWithoutSamePositionOfZero(){
        List<Entry<Integer, Integer>> movements = parking.RearrangingCars(new int[]{1,2,0,3}, new int[]{3,1,2,0});
        checkEntries(new int[][] {{1, 2}, {0, 1}, {3,0}}, movements);
    }

    @Test
    public void testMoreElementsWithSamePositionOfZero(){
        List<Entry<Integer, Integer>> movements = parking.RearrangingCars(new int[]{1,2,0,3}, new int[]{3,1,0,2});
        checkEntries(new int[][] {{0, 2}, {3, 0}, {1,3}, {2, 1}}, movements);
    }

    private void checkEntries(int[][] realMovements, List<Entry<Integer, Integer>> expectedMovements) {
        int size = expectedMovements.size();
        assertThat(size, is(realMovements.length));
        for (int i = 0; i < size; i++) {
            Entry<Integer, Integer> entry = expectedMovements.get(i);
            assertThat(new int[]{entry.getKey(), entry.getValue()}, is(realMovements[i]));
        }
    }
}