import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountingIslandsTest {

    @Test
    public void testNoIslands() {
        int islands = CountingIslands.countingIslands(1, 1, new boolean[][]{{false}});
        assertEquals(0, islands);
    }

    @Test
    public void testIsSingleIsland() {
        int islands = CountingIslands.countingIslands(1, 1, new boolean[][]{{true}});
        assertEquals(1, islands);
    }

    @Test
    public void testIsSingleIslandOfFewLands() {
        int islands = CountingIslands.countingIslands(1, 3, new boolean[][]{{true, true, true}});
        assertEquals(1, islands);
    }

    @Test
    public void testIsSingleIslandInTheWater() {
        boolean[][] isLand = new boolean[][]{
                {false, false, true},
                {false, true, true}};
        int islands = CountingIslands.countingIslands(2, 3, isLand);
        assertEquals(1, islands);
    }

    @Test
    public void testIsSomeIslands() {
        boolean[][] isLands = new boolean[][]{
                {false, true, false, true},
                {true, true, false, false},
                {false, false, true, false},
                {false, false, true, false}};
        int islands = CountingIslands.countingIslands(4, 4, isLands);
        assertEquals(3, islands);
    }
}