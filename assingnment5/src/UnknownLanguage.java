import java.util.List;

class UnknownLanguage {
    /**
     * Get ordered list of characters of the language
     *
     * Complexity: O(n*maxlength)
     *
     * @param dictionary -- ordered list of words
     * @return -- ordered list of chars
     */
    static List<Character> getAlphabet(List<String> dictionary) {
        Tree<Character> alphabet = new Tree<>();
        int size = dictionary.size();
        for (int i = 0; i < size - 1; i++) {
            String word1 = dictionary.get(i);
            String word2 = dictionary.get(i + 1);

            getLettersInOrder(word1, word2, alphabet);
        }
        getLettersOfWord(0, dictionary.get(size - 1), alphabet);

        return alphabet.createListInOrder();
    }

    /**
     * Add chars to the alphabet in order
     * @param word1
     * @param word2
     * @param alphabet
     */
    private static void getLettersInOrder(String word1, String word2, Tree<Character> alphabet) {
        int beginIndex = getFirstLettersInOrder(word1, word2, alphabet);
        getLettersOfWord(beginIndex, word1, alphabet);
        getLettersOfWord(beginIndex, word2, alphabet);
    }

    /**
     * Add first chars which equals and on next which we could find out the order
     * @param word1
     * @param word2
     * @param alphabet
     * @return -- index of element which goes without order
     */
    private static int getFirstLettersInOrder(String word1, String word2, Tree<Character> alphabet) {
        int size = word1.length();
        for (int i = 0; i < size; i++) {
            char ch1 = word1.charAt(i);
            char ch2 = word2.charAt(i);
            if (ch1 != ch2) {
                alphabet.addElements(ch1, ch2);
                return i + 1;
            }
            alphabet.addElement(ch1);
        }
        return size;
    }

    /**
     * Add chars to alphabet without order from beginIndex to end of string
     * @param beginIndex -- index of first char which will be added
     * @param str -- string
     * @param alphabet
     */
    private static void getLettersOfWord(int beginIndex, String str, Tree<Character> alphabet) {
        int size = str.length();
        for (int i = beginIndex; i < size; i++) {
            alphabet.addElement(str.charAt(i));
        }
    }
}
