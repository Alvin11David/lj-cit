package Week_5.Exercise_4;

public class Main {

    public static void main(String[] args) {

        System.out.println("Test Case 1: Valid Divisor");
        int validResult = divide(10, 2);
        System.out.println("Result of 10 / 2 = " + validResult);
        System.out.println();

        System.out.println("Test Case 2: Division by Zero");
        try {
            int invalidResult = divide(10, 0);
            System.out.println("Result of 10 / 0 = " + invalidResult);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            System.out.println("Division by zero is not allowed.");
            throw new ArithmeticException("Cannot divide " + a + " by zero");
        }

        System.out.println("Done.");
        return a / b;
    }
}