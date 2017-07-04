import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import static java.util.stream.Collectors.toList;

/**
 * Word searcher in the chars grid
 */
public class WordSearcher {

    /**
     * Getting all words from the grid witch is in a dictionary
     * Used DFS, complexity	 is O((numRows*numCols)^2)
     *
     * @param numRows    -- number rows
     * @param numCols    -- number columns
     * @param chars      -- 2-dimensial array of characters
     * @param dictionary -- dictionary with words
     * @return -- list of words
     */
    static List<String> wordSearch(int numRows, int numCols, char[][] chars, Dictionary dictionary) {
        List<String> result = new LinkedList<>();
        boolean[][] isUsed = new boolean[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Set<String> words = getWords(i, j, Character.toString(chars[i][j]), numRows, numCols, chars, isUsed, dictionary);
                result.addAll(words);
            }
        }

        return new ArrayList<>(result);
    }

    /**
     * Get List of words with prefix
     *
     * @param row        -- row of last char
     * @param col        -- column of last char
     * @param prefix     -- current prefix
     * @param numRows          -- number of rows
     * @param numCols          -- number of columns
     * @param chars      -- 2-dimensial array of characters
     * @param isUsed     -- 2-dimesial array of booleans witch means is appropriate character is used
     * @param dictionary -- dictionary of words
     * @return -- list of words
     */
    private static Set<String> getWords(int row, int col, String prefix, int numRows, int numCols, char[][] chars, boolean[][] isUsed, Dictionary dictionary) {
        Set<String> result = new HashSet<>();

        if (!dictionary.isPrefix(prefix)) {
            return result;
        }

        if (dictionary.isWord(prefix)){
            result.add(prefix);
        }

        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                int row2 = row + k;
                int col2 = col + l;
                if (0 <= row2 && row2 < numRows && 0 <= col2 && col2 < numCols && !isUsed[row2][col2]) {
                    String newPrefix = prefix + chars[row2][col2];

                    isUsed[row2][col2] = true;
                    Set<String> words = getWords(row2, col2, newPrefix, numRows, numCols, chars, isUsed, dictionary);
                    isUsed[row2][col2] = false;

                    result.addAll(words);
                }
            }
        }

        return result;
    }
}
