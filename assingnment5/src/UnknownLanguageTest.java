import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class UnknownLanguageTest {
    @Test
    public void testOneWord() {
        List<String> dict = asList("art");
        List<Character> alphabet = UnknownLanguage.getAlphabet(dict);
        assertThat(alphabet, containsInAnyOrder('a', 'r', 't'));
    }

    @Test
    public void testFewWords() {
        List<String> dict = asList("art", "rat", "cat", "car");
        List<Character> alphabet = UnknownLanguage.getAlphabet(dict);
        assertThat(alphabet, contains('a', 't', 'r', 'c'));
    }

    @Test
    public void testWithOrder() {
        List<String> dict = asList("art", "rat", "cat", "car", "zta", "zaa");
        List<Character> alphabet = UnknownLanguage.getAlphabet(dict);
        assertThat(alphabet, contains('t', 'a', 'r', 'c', 'z'));
    }

    @Test
    public void testWithDifficultOrder() {
        List<String> dict = asList("a", "b", "de", "df", "db");
        List<Character> alphabet = UnknownLanguage.getAlphabet(dict);
        assertThat(alphabet, contains('a', 'e', 'f', 'b', 'd'));
    }
}