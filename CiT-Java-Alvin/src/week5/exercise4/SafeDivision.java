package week5.exercise4;

public class SafeDivision {
    public static int divide(int a, int b) {
        int result = 0;
        try {
            result = a / b;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Can not divide by zero");
        } finally {
            System.out.println("Done");
            return 0;
        }
    }

    public static void main(String[] args) {
        int test1 = divide(4, 0);
        System.out.println(test1);

        System.out.println();

        int test2 = divide(4, 2);
        System.out.println(test2);
    }
}
