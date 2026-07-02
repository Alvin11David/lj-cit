



package Student.Grader;

public class GradeCalculator {
    public String grade(Student student){
        if(student.average()>=90){
            return "A+";
        } else if (student.average()>=80) {
            return "A";
        } else if (student.average()>=75) {
            return "B+";
        } else if (student.average()>=70) {
            return "B";
        } else if (student.average()>=65) {
            return "C+";
        } else if (student.average()>=60) {
            return "C";
        } else if (student.average()>=55) {
            return "D";
        } else if (student.average()>=50) {
            return "E";
        }else{
            return "Fail";
        }
    }
}