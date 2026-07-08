package week4.exercise1.exercise2;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("23", "Alvin", "CS", 3, 4.7);
        Student student2 = new Student("25", "David", "SE", 2, 4.5);
        Student student3 = new Student("29", "Bertha");

        student1.printProfile();
        student2.printProfile();
        student3.printProfile();
    }
}
