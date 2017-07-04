/**
 * Interface for dictionary of words
 */
public interface Dictionary {
    /**
     * Is string in the dictionary
     */
    boolean isWord(String string);

    /**
     * Is string a prefix of any words in the dictionary
     */
    boolean isPrefix(String string);
}
