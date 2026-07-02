package week3.exercise1;

public class StudentProfile {
    static String studentFullName = "Alice Nakato";
    static int studentAge = 22;
    static double studentGpa = 3.75;
    static boolean hasPaidFees = true;
    static long studentRegistrationNumber = 2024001234;
    static char studentGrade = 'A';

    public static void main(String[] args) {
        System.out.println("Name: " + studentFullName);
        System.out.println("Age: " + studentAge);
        System.out.println("GPA: " + studentGpa);
        System.out.println("Fees paid: " + hasPaidFees);
        System.out.println("Reg number: "+ studentRegistrationNumber);
        System.out.println("Grade: " + studentGrade);
    }
}
