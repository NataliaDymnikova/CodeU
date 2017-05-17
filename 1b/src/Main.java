import java.util.Scanner;

/**
 * Created by Natasha on 17-May-17.
 */


public class Main {
    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();

        int res = getKToLast(list, k);
        System.out.print(res);
    }

    private static <T> T getKToLast(SingleLinkedList<T> list, int k) {
        return list.getElement(list.getSize() - k - 1);
    }
}
