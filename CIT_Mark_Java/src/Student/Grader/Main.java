package Student.Grader;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {

    public static void loadDatabse(StudentService studentService) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/cit_student_db";
        String username = "postgres";
        String password = "cit2026";

        Connection connection = DriverManager.getConnection(url,username,password);
        Statement statement = connection.createStatement();
        String getAllResultsQuery = "SELECT * FROM STUDENTS";
        ResultSet resultSet = statement.executeQuery(getAllResultsQuery);
        while (resultSet.next()){
            System.out.println("Student Name: " + resultSet.getString("name"));
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
        System.out.println("3. To Add Student Scores: ");
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


        loadDatabse(studentService);


        while(true){
            screen();
            try{
                int option = scanner.nextInt();
                scanner.nextLine();
                if(option==1){
                    System.out.println("Enter Registration Number: ");
                    String registrationNumber = scanner.nextLine();
                    System.out.println("Enter Student Name: ");
                    String name = scanner.nextLine();
                    if(studentService.getStudentHashMap().containsKey(registrationNumber)){
                        System.out.println("Student Registration Number already exists...!!!");
                    }else {
                        studentService.add(registrationNumber, new Student(registrationNumber, name));
                        System.out.println("Student added successfully....");
                    }
                } else if (option==2) {
                    System.out.println("Enter Registration Number: ");
                    String registrationNumber = scanner.nextLine();
                    if(studentService.getStudentHashMap().containsKey(registrationNumber)){
                        System.out.println("Enter subject: ");
                        String subject = scanner.nextLine();
                        if(!(subject.toLowerCase().startsWith("maths")|| subject.toLowerCase().startsWith("eng") ||
                                subject.toLowerCase().startsWith("sci") || subject.toLowerCase().startsWith("sst"))){
                            System.out.println("ERROR: Subject must be one of {maths, eng,sst,sci}");
                        }else {
                            System.out.println("Enter "+ subject + " score: ");
                            try {
                                int score = scanner.nextInt();
                                scanner.nextLine();
                                if(score>=0 && score<=100){
                                    studentService.getStudentHashMap().get(registrationNumber).addScore(subject,score);
                                    System.out.println("Score added successfully....");
                                }else {
                                    System.out.println("ERROR: Score must lie between 0 and 100 inclusive...");
                                }

                            }catch (InputMismatchException e){
                                System.out.println("ERROR: Only Integers are allowed..");
                            }
                        }
                    }else{
                        System.out.println("ERROR: Registration Number Unknown...!!!");
                    }
                } else if (option==3) {
                    System.out.println("Enter Registration Number: ");
                    String registrationNumber = scanner.nextLine();
                    if(studentService.getStudentHashMap().containsKey(registrationNumber)){
                        int math,sci,eng,sst;
                        System.out.println("Enter Maths Score: ");
                        try {
                            math = scanner.nextInt();
                            scanner.nextLine();
                            if(math>=0 && math<=100){
                                studentService.getStudentHashMap().get(registrationNumber).addScore("maths",math);

                            }else {
                                System.out.println("ERROR: Scores lie between 0 and 100 inclusive");
                            }
                        }catch (InputMismatchException e){
                            System.out.println("ERROR: only integers are expected!!");
                            scanner.next();
                        }
                        System.out.println("Enter Science Score: ");
                        try {
                            sci = scanner.nextInt();
                            scanner.nextLine();
                            if(sci>=0 && sci<=100){
                                studentService.getStudentHashMap().get(registrationNumber).addScore("sci",sci);
                            }else {
                                System.out.println("ERROR: Scores lie between 0 and 100 inclusive");
                            }
                        }catch (InputMismatchException e){
                            System.out.println("ERROR: only integers are expected!!");
                            scanner.next();
                        }
                        System.out.println("Enter English Score: ");
                        try {
                            eng = scanner.nextInt();
                            scanner.nextLine();
                            if(eng>=0 && eng<=100){
                                studentService.getStudentHashMap().get(registrationNumber).addScore("eng",eng);
                            }else {
                                System.out.println("ERROR: Scores lie between 0 and 100 inclusive");
                            }
                        }catch (InputMismatchException e){
                            System.out.println("ERROR: only integers are expected!!");
                            scanner.next();
                        }
                        System.out.println("Enter SST Score: ");
                        try {
                            sst = scanner.nextInt();
                            scanner.nextLine();
                            if(sst>=0 && sst<=100){
                                studentService.getStudentHashMap().get(registrationNumber).addScore("sst",sst);
                            }else {
                                System.out.println("ERROR: Scores lie between 0 and 100 inclusive");
                            }
                        }catch (InputMismatchException e){
                            System.out.println("ERROR: only integers are expected!!");
                            scanner.next();
                        }
                        System.out.println("Scores added successfully....");
                    }else{
                        System.out.println("ERROR: Invalid Registration Number...!!");
                    }
                } else if (option==4) {
                    System.out.println("Enter Student Registration Number: ");
                    String registrationNumber = scanner.nextLine();
                    if(studentService.getStudentHashMap().containsKey(registrationNumber)){
                        System.out.println(studentService.getStudentHashMap().get(registrationNumber).getName() +
                                " :  Average: " + studentService.getStudentHashMap().get(registrationNumber).average());
                    }else{
                        System.out.println("ERROR: Student Registration Number Unknown Try Again...!!");
                    }
                } else if (option==5) {
                    System.out.println("Enter Student Registration Number: ");
                    String registrationNumber = scanner.nextLine();
                    if(studentService.getStudentHashMap().containsKey(registrationNumber)){
                        System.out.println(studentService.getStudentHashMap().get(registrationNumber).getName()+
                                " :  Grade: "+ gradeCalculator.grade(studentService.getStudentHashMap().get(registrationNumber)));
                    }else {
                        System.out.println("ERROR: Registration Number Unknown!!!");
                    }
                } else if (option==6) {
                    studentService.list();
                } else if (option==7) {
                    System.out.println("Enter Registration Number: ");
                    String registrationNumber = scanner.nextLine();
                    if(studentService.getStudentHashMap().containsKey(registrationNumber)){
                        studentService.list(registrationNumber);
                    }else {
                        System.out.println("ERROR: Registration Number Unknown!!!");
                    }
                } else if (option==0) {
                    System.out.println("Closing System...");
                    scanner.close();
                    studentService.saveToDatabase();
                    break;
                }else {
                    System.out.println("ERROR: Invalid Option. Please Try Again....");
                }
            }catch (InputMismatchException e){
                System.out.println("ERROR: Option must be integer");
            }
        }
    }






}