package Student.Grader;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {

    public static void handleAddingStudent(StudentService studentService) throws SQLException {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Registration Number: ");
            String registrationNumber = scanner.nextLine();
            System.out.println("Enter Student Name: ");
            String name = scanner.nextLine();
            if(!studentService.getStudentHashMap().containsKey(registrationNumber)){
                System.out.println("Student Registration Number already exists...!!!");
                return;
            }
            studentService.add(registrationNumber, new Student(registrationNumber, name));
            System.out.println("Student added successfully....");

    }

    public static void handleAddingOneScore(StudentService studentService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Registration Number: ");
        String registrationNumber = scanner.nextLine();

        if (!studentService.getStudentHashMap().containsKey(registrationNumber)) {
            System.out.println("ERROR: Registration Number Unknown...!!!");
            return;
        }

        System.out.println("Enter subject: ");
        String subject = scanner.nextLine().toLowerCase();
        if (!(subject.startsWith("maths") || subject.startsWith("eng") ||
                subject.startsWith("sci") || subject.startsWith("sst"))) {
            System.out.println("ERROR: Subject must be one of {maths, eng, sst, sci}");
            return;
        }

        System.out.println("Enter " + subject + " score: ");
        int score;
        try {
            score = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Only Integers are allowed..");
            return;
        }

        if (score < 0 || score > 100) {
            System.out.println("ERROR: Score must lie between 0 and 100 inclusive...");
            return;
        }

        studentService.getStudentHashMap().get(registrationNumber).addScore(subject, score);
        System.out.println("Score added successfully....");
    }

    public static void handleAddingAllStudentScores(StudentService studentService){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Registration Number: ");
        String registrationNumber = scanner.nextLine();
        if(!studentService.getStudentHashMap().containsKey(registrationNumber)){
            System.out.println("Registration Number Unknown...");
            return;
        }

        int math,sci,eng,sst;
        System.out.println("Enter Maths Score: ");
            try {
                math = scanner.nextInt();
                scanner.nextLine();
            }catch (InputMismatchException e){
                System.out.println("ERROR: only integers are expected!!");
                scanner.next();
                return;
            }

            if(!(math>=0 && math<=100)){
                System.out.println("ERROR: Scores lie between 0 and 100 inclusive");
                return;
            }

        System.out.println("Enter Science Score: ");
        try {
            sci = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            System.out.println("ERROR: only integers are expected!!");
            scanner.next();
            return;
        }

        if(!(sci>=0 && sci<=100)){
            System.out.println("ERROR: Scores lie between 0 and 100 inclusive");
            return;
        }

        System.out.println("Enter English Score: ");
        try {
            eng = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            System.out.println("ERROR: only integers are expected!!");
            scanner.next();
            return;
        }

        if(!(eng>=0 && eng<=100)){
            System.out.println("ERROR: Scores lie between 0 and 100 inclusive");
            return;
        }

        System.out.println("Enter SST Score: ");
        try {
            sst = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            System.out.println("ERROR: only integers are expected!!");
            scanner.next();
            return;
        }

        if(!(sst>=0 && sst<=100)){
            System.out.println("ERROR: Scores lie between 0 and 100 inclusive");
            return;
        }
        studentService.getStudentHashMap().get(registrationNumber).addScore(sst,math,sci,eng);
    }


    public static void handleGettingAverage(StudentService studentService){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student Registration Number: ");
        String registrationNumber = scanner.nextLine();
        if(!studentService.getStudentHashMap().containsKey(registrationNumber)){
            System.out.println("ERROR: Student Registration Number Unknown Try Again...!!");
            return;
        }
        System.out.println(studentService.getStudentHashMap().get(registrationNumber).getName() +
                " :  Average: " + studentService.getStudentHashMap().get(registrationNumber).average());
    }


    public static void handleGettingGrade(StudentService studentService, GradeCalculator gradeCalculator){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student Registration Number: ");
        String registrationNumber = scanner.nextLine();
        if(!studentService.getStudentHashMap().containsKey(registrationNumber)){
            System.out.println("ERROR: Registration Number Unknown!!!");
            return;
        }
        System.out.println(studentService.getStudentHashMap().get(registrationNumber).getName()+
                " :  Grade: "+ gradeCalculator.grade(studentService.getStudentHashMap().get(registrationNumber)));
    }

    public static void handleGettingStudentScore(StudentService studentService){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Registration Number: ");
        String registrationNumber = scanner.nextLine();
        if(!studentService.getStudentHashMap().containsKey(registrationNumber)) {
            System.out.println("ERROR: Registration Number Unknown!!!");
            return;
        }
        studentService.list(registrationNumber);
    }

    public static void handleClosingAndSaving(StudentService studentService) throws SQLException {
        System.out.println("Closing System...");
        studentService.saveToDatabase();
    }

    public static void loadDatabase(StudentService studentService) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/cit_student_db";
        String username = "postgres";
        String password = "cit2026";

        Connection connection = DriverManager.getConnection(url,username,password);
        Statement statement = connection.createStatement();
        String getAllResultsQuery = "SELECT * FROM STUDENTS";
        ResultSet resultSet = statement.executeQuery(getAllResultsQuery);
        while (resultSet.next()){
            //System.out.println("Student Name: " + resultSet.getString("name"));
            studentService.add(resultSet.getString("registration_number"),new Student(resultSet.getString("registration_number"),resultSet.getString("name")));
            studentService.getStudentHashMap().get(resultSet.getString("registration_number")).
                    addScore(resultSet.getInt("sst"),
                            resultSet.getInt("math"),
                            resultSet.getInt("sci"),resultSet.getInt("eng"));
        }
    }

    public static void screen(){
        System.out.println("1. To Add Student: ");
        System.out.println("2. To Add one Score");
        System.out.println("3. To Add All Student Scores: ");
        System.out.println("4. To Get Average of a Student: ");
        System.out.println("5. To Get Grade of a Student: ");
        System.out.println("6. To Get Scores of all Students: ");
        System.out.println("7. To Get scores of a Student: ");
        System.out.println("0. To Close System: ");
    }
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        GradeCalculator gradeCalculator = new GradeCalculator();
        StudentService studentService = new StudentService();
        loadDatabase(studentService);

        menuLoop:
        while(true){
            screen();
            try{
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option){
                    case 1 : handleAddingStudent(studentService);break;
                    case 2: handleAddingOneScore(studentService);break;
                    case 3: handleAddingAllStudentScores(studentService);break;
                    case 4: handleGettingAverage(studentService);break;
                    case 5: handleGettingGrade(studentService,gradeCalculator);break;
                    case 6: studentService.list();break;
                    case 7: handleGettingStudentScore(studentService);break;
                    case 0:
                            handleClosingAndSaving(studentService);
                            scanner.close();
                            break menuLoop;
                    default:System.out.println("ERROR: Invalid Option. Please Try Again....");
                }
            }catch (InputMismatchException e){
                System.out.println("ERROR: Option must be integer");
            }
        }
    }
}