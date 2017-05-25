/**
 * Exceptions for Tree class
 */
class TreeException {
    static class NoCommonAncestor extends Exception {
        NoCommonAncestor(String str) {
            super(str);
        }
    }

    static class ElementDoesntFound extends Exception {
        ElementDoesntFound(String str) {
            super(str);
        }
    }
}
