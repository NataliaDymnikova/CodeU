import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class WordSearcherTest {

    private Dictionary dict = new Dictionary() {

        List<String> words = Arrays.asList("car", "card", "cart", "cat");

        @Override
        public boolean isWord(String string) {
            return words.contains(string);
        }

        @Override
        public boolean isPrefix(String string) {
            return words.stream().anyMatch(word -> word.startsWith(string));
        }
    };

    private char chars[][] = {{'a', 'a', 'r'}, {'t', 'c', 'd'}};

    @Test
    public void testGetEmptyList() {
        int r = 1;
        int c = 1;
        List<String> words = WordSearcher.wordSearch(r, c, chars, dict);
        assertThat(words, empty());
    }

    @Test
    public void testGetWords() {
        int r = 2;
        int c = 3;
        List<String> words = WordSearcher.wordSearch(r, c, chars, dict);
        assertThat(words, containsInAnyOrder("car", "card", "cat"));
    }
}