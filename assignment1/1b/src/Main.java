import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();

        // k == 0 -> 9
        // k == 1 -> 8
        // ...
        // k == 9 -> 0
        // k > 9 || k < 0 -> null
        System.out.print(list.getKToLast(k));
    }


}
