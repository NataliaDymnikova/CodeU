import java.util.LinkedList;
import java.util.List;

public class UnknownLanguage {
    /**
     * Get ordered list of characters of the language
     *
     * @param dictionary
     * @return
     */
    public static List<Character> getAlphabet(List<String> dictionary) {
        List<Character> alphabet = new LinkedList<>();
        int size = dictionary.size();
        for (int i = 0; i < size - 1; i++) {
            String word1 = dictionary.get(i);
            String word2 = dictionary.get(i + 1);

            getLettersOnTheOrder(word1, word2, alphabet);
        }
        getLettersOfWord(0, dictionary.get(size - 1), alphabet);

        return alphabet;
    }

    private static void getLettersOnTheOrder(String word1, String word2, List<Character> alphabet) {
        int beginIndex = getFirstLettersWithOrder(word1, word2, alphabet);
        getLettersOfWord(beginIndex, word1, alphabet);
        getLettersOfWord(beginIndex, word2, alphabet);
    }

    private static int getFirstLettersWithOrder(String word1, String word2, List<Character> alphabet) {
        int size = word1.length();
        for (int i = 0; i < size; i++) {
            char ch1 = word1.charAt(i);
            char ch2 = word2.charAt(i);
            if (ch1 != ch2) {
                int ind1 = alphabet.indexOf(ch1);
                int ind2 = alphabet.indexOf(ch2);
                if (ind1 == -1 && ind2 == -1) {
                    alphabet.add(ch1);
                    alphabet.add(ch2);
                } else if (ind2 == -1) {
                    alphabet.add(ind1 + 1, ch2);
                } else if (ind1 == -1) {
                    alphabet.add(ind2, ch1);
                } else if (ind1 > ind2) {
                    alphabet.remove(ind1);
                    alphabet.add(ind2, ch1);
                }
                return i + 1;
            }
            addToEndIfAbsent(ch1, alphabet);
        }
        return size;
    }

    private static void getLettersOfWord(int beginIndex, String str, List<Character> alphabet) {
        for (Character ch : str.toCharArray()) {
            addToEndIfAbsent(ch, alphabet);
        }
    }

    private static void addToEndIfAbsent(char ch1, List<Character> alphabet) {
        if (!alphabet.contains(ch1)) {
            alphabet.add(ch1);
        }
    }

}
