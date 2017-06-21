import static java.lang.Math.min;

/**
 * Class for counting islands in the 2-dimensial array of booleans
 */
public class CountingIslands {
    /**
     * Count islands
     *
     * @param numRows     -- number of rows
     * @param numCols     -- number of columns
     * @param isLandArray -- 2-dimensial array, where True is land, False is water
     * @return -- number of islands
     */
    public static int countingIslands(int numRows, int numCols, boolean[][] isLandArray) {
        int result = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (isLandArray[i][j]) {
                    result++;
                    deleteIsland(i, j, numRows, numCols, isLandArray);
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
     * @param isLandArray -- 2-dimensial array, where True is land, False is water
     */
    private static void deleteIsland(int startRow, int startCol, int numRows, int numCols, boolean[][] isLandArray) {
        if (!isLandArray[startRow][startCol]) {
            return;
        }

        isLandArray[startRow][startCol] = false;

        int minNeighborRow = startRow == 0 ? 1 : startRow - 1;
        int maxNeighborRow = min(startRow + 1, numRows - 1);
        for (int row = minNeighborRow; row <= maxNeighborRow; row += 2) {
            deleteIsland(row, startCol, numRows, numCols, isLandArray);
        }

        int minNeighborCol = startCol == 0 ? 1 : startCol - 1;
        int maxNeighborCol = min(startCol + 1, numCols - 1);
        for (int col = minNeighborCol; col <= maxNeighborCol; col += 2) {
            deleteIsland(startRow, col, numRows, numCols, isLandArray);
        }

    }

}
