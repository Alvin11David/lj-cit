package Student.Grader;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        studentService.add("01X",new Student("01X","Mark"));
        studentService.add("02X",new Student("02X","JP"));
        studentService.add("03X",new Student("03X","Ivy"));

        studentService.getStudent("01X").ifPresent(student -> student.addScore(90,88,76,98));



        studentService.getStudent("02X").ifPresent(student -> student.addScore("sst",98));
        studentService.list();


    }
}