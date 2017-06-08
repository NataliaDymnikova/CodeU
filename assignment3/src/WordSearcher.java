import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import static java.util.stream.Collectors.toList;

/**
 * Word searcher in the chars grid
 */
public class WordSearcher {

    /**
     * Getting all words from the grid wich is in a dictionary
     *
     * @param r          -- number rows
     * @param c          -- number columns
     * @param chars      -- 2-dimensial array of characters
     * @param dictionary -- dictionary with words
     * @return -- list of words
     */
    static List<String> wordSearch(int r, int c, char chars[][], Dictionary dictionary) {
        List<String> result = new LinkedList<>();
        List<List<Entry<Character, Boolean>>> characters = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            List<Entry<Character, Boolean>> row = new ArrayList<>();
            for (int j = 0; j < c; j++) {
                row.add(new SimpleEntry<>(chars[i][j], false));
            }
            characters.add(row);
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                List<String> words = getWords(i, j, Character.toString(chars[i][j]), r, c, characters, dictionary);
                result.addAll(words);
            }
        }

        return result.stream().distinct().collect(toList());
    }

    /**
     * Get List of words with prefix
     *
     * @param i          -- row of last char
     * @param j          -- column of last char
     * @param prefix     -- current prefix
     * @param r          -- number of rows
     * @param c          -- number of columns
     * @param chars      -- 2-dimensial array of characters and boolean value (is this character used in this word)
     * @param dictionary -- dictionary of words
     * @return -- list of words
     */
    private static List<String> getWords(int i, int j, String prefix, int r, int c, List<List<Entry<Character, Boolean>>> chars, Dictionary dictionary) {
        List<String> result = new LinkedList<>();

        if (!dictionary.isPrefix(prefix)) {
            return result;
        }

        result.add(prefix);
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                if (i + k >= 0 && i + k < r && j + l >= 0 && j + l < c && !chars.get(i + k).get(j + l).getValue()) {
                    Entry<Character, Boolean> entry = chars.get(i + k).get(j + l);

                    String newPrefix = prefix + entry.getKey().toString();

                    entry.setValue(true);
                    List<String> words = getWords(i + k, j + l, newPrefix, r, c, chars, dictionary);
                    entry.setValue(false);

                    result.addAll(words);
                }
            }
        }

        return result.stream().filter(dictionary::isWord).collect(toList());
    }
}
