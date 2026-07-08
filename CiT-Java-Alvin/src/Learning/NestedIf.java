package Learning;

public class NestedIf {
    public static void main(String[] args) {
        int age = 12;
        boolean isCitizen = true;

        if (age >= 18) {
            System.out.println("Old enough to vote");
            if (isCitizen) {
                System.out.println("Citizen");
            } else {
                System.out.println("Not Citizen");
            }
        } else {
            System.out.println("Young to vote");
        }
    }
}
