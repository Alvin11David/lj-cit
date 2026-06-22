package week5;


public class Main4 {
    public static int divide(int a, int b){
        try{
            return  a/b;
        }
        catch (ArithmeticException e){
            System.out.println("Division by zero is not allowed.");
            return 0;
        }
        finally {
            System.out.println("Done." );
        }
    }
    public static void main(String[] args){
        System.out.println(divide(10,2));
        System.out.println(divide(9,0));

    }
}