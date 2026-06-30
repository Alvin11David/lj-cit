package StudentGradeProject;

public class GradeCalculator {

    private GradeCalculator() {}

    public static String calculateGrade(double average) {
        if (average >= 80) {
            return "A";
        } else if (average >= 65) {
            return "B";
        } else if (average >= 50) {
            return "C";
        } else {
            return "Fail";
        }
    }
}