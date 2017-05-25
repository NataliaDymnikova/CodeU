import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<Integer>(-2);
        tree.add(-1, -2, false);

        // tree:
        //      -2
        //     0  -1
        //    2     1
        //   4       3
        //  6         5
        // 8           7
        //              9
        for (int i = 0; i < 10; i++) {
            boolean isLeft = i % 2 == 0;
            tree.add(i, i - 2, isLeft);
        }

        Scanner scanner = new Scanner(System.in);
        int el = scanner.nextInt();

        tree.getAncestors(el).forEach(i -> System.out.print(i.toString() + " "));
    }
}
