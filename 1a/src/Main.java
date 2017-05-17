import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Natasha on 17-May-17.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next();
        String str2 = scanner.next();
        boolean b = isPermutation(str1, str2);
        System.out.print(b);
    }

    private static boolean isPermutation(String str1, String str2) {
        Map<Character, Integer> dict = new HashMap<>();

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        for (char i : str1.toCharArray()) {
            dict.put(i, 1 + dict.getOrDefault(i, 0));
        }
        for (char i : str2.toCharArray()) {
            int c = dict.compute(i, (ch, j) -> j == null ? -1 : j - 1);
            if (c < 0) {
                return false;
            }
        }
        return true;
    }
}
