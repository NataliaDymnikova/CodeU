import static java.lang.Math.min;

/**
 * Class for counting islands in the 2-dimensional array of booleans
 */
public class CountingIslands {
    /**
     * Count islands
     *
     * @param numRows     -- number of rows
     * @param numCols     -- number of columns
     * @param isLandArray -- 2-dimensional array, where True is land, False is water
     * @return -- number of islands
     */
    public static int countingIslands(int numRows, int numCols, boolean[][] isLandArray) {
        int result = 0;
        boolean[][] copyIsLandArray = isLandArray.clone();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (copyIsLandArray[i][j]) {
                    result++;
                    deleteIsland(i, j, numRows, numCols, copyIsLandArray);
                }
            }
        }
        return result;
    }

    /**
     * Change island to the water
     *
     * @param startRow    -- start row of island
     * @param startCol    -- start column of island
     * @param numRows     -- number of rows
     * @param numCols     -- number of columns
     * @param isLandArray -- 2-dimensional array, where True is land, False is water
     */
    private static void deleteIsland(int startRow, int startCol, int numRows, int numCols, boolean[][] isLandArray) {
        if (startRow < 0 || startRow >= numRows || startCol < 0 || startCol >= numCols) {
            return;
        }

        if (!isLandArray[startRow][startCol]) {
            return;
        }

        isLandArray[startRow][startCol] = false;

        deleteIsland(startRow - 1, startCol, numRows, numCols, isLandArray);
        deleteIsland(startRow + 1, startCol, numRows, numCols, isLandArray);
        deleteIsland(startRow, startCol - 1, numRows, numCols, isLandArray);
        deleteIsland(startRow, startCol + 1, numRows, numCols, isLandArray);
    }

}
