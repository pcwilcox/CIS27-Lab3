package redblack;

/**
 * Created by Pete on 4/9/2016.
 */
public class RedBlackBSTTester
{
    public static void main(String[] args) {
        RedBlackBST<Integer, String> test = new RedBlackBST<>();

        for (int i = 1; i <= 60; i++) {
            test.put(i, "");
        }

        System.out.println(test);

        for (int i = 20; i > 0; i--) {
            test.delete(i);
        }

        System.out.println(test);
    }
}
