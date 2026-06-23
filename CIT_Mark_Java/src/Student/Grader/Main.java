package Student.Grader;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeCalculator gradeCalculator = new GradeCalculator();
        StudentService studentService = new StudentService();
        while(true){
            screen();
            String option = scanner.nextLine();
            scanner.nextLine();
            if(option.equals("1")){
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
            } else if (option.equals("2")) {
                System.out.println("Enter Registration Number: ");
                String registrationNumber = scanner.nextLine();
                if(studentService.getStudentHashMap().containsKey(registrationNumber)){
                    System.out.println("Enter subject: ");
                    String subject = scanner.nextLine();
                    if(!(subject.toLowerCase().startsWith("maths")|| subject.toLowerCase().startsWith("eng") ||
                            subject.toLowerCase().startsWith("sci") || subject.toLowerCase().startsWith("sst"))){
                        System.out.println("Subject must be one of {maths, eng,sst,sci}");
                    }else {
                        System.out.println("Enter "+ subject + " score: ");
                        try {
                            int score = scanner.nextInt();
                            scanner.nextLine();
                            studentService.getStudentHashMap().get(registrationNumber).addScore(subject,score);
                            System.out.println("Score added successfully....");
                        }catch (InputMismatchException e){
                            System.out.println("Only Integers are allowed..");
                        }
                    }
                }else{
                    System.out.println("Registration Number Unknown...!!!");
                }
            } else if (option.equals("3")) {
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
                            System.out.println("Scores lie between 0 and 100 inclusive");
                        }
                    }catch (InputMismatchException e){
                        System.out.println("Error only integers are expected!!");
                        scanner.next();
                    }
                    System.out.println("Enter Science Score: ");
                    try {
                        sci = scanner.nextInt();
                        scanner.nextLine();
                        if(sci>=0 && sci<=100){
                            studentService.getStudentHashMap().get(registrationNumber).addScore("sci",sci);
                        }else {
                            System.out.println("Scores lie between 0 and 100 inclusive");
                        }
                    }catch (InputMismatchException e){
                        System.out.println("Error only integers are expected!!");
                        scanner.next();
                    }
                    System.out.println("Enter English Score: ");
                    try {
                        eng = scanner.nextInt();
                        scanner.nextLine();
                        if(eng>=0 && eng<=100){
                            studentService.getStudentHashMap().get(registrationNumber).addScore("eng",eng);
                        }else {
                            System.out.println("Scores lie between 0 and 100 inclusive");
                        }
                    }catch (InputMismatchException e){
                        System.out.println("Error only integers are expected!!");
                        scanner.next();
                    }
                    System.out.println("Enter SST Score: ");
                    try {
                        sst = scanner.nextInt();
                        scanner.nextLine();
                        if(sst>=0 && sst<=100){
                            studentService.getStudentHashMap().get(registrationNumber).addScore("sst",sst);
                        }else {
                            System.out.println("Scores lie between 0 and 100 inclusive");
                        }
                    }catch (InputMismatchException e){
                        System.out.println("Error only integers are expected!!");
                        scanner.next();
                    }
                    System.out.println("Scores added successfully....");
                }else{
                    System.out.println("Invalid Registration Number...!!");
                }
            } else if (option.equals("4")) {
                System.out.println("Enter Student Registration Number: ");
                String registrationNumber = scanner.nextLine();
                if(studentService.getStudentHashMap().containsKey(registrationNumber)){
                    System.out.println(studentService.getStudentHashMap().get(registrationNumber).getName() +
                            " :  Average: " + studentService.getStudentHashMap().get(registrationNumber).average());
                }else{
                    System.out.println("Student Registration Number Unknown Try Again...!!");
                }
            } else if (option.equals("5")) {
                System.out.println("Enter Student Registration Number: ");
                String registrationNumber = scanner.nextLine();
                if(studentService.getStudentHashMap().containsKey(registrationNumber)){
                    System.out.println(studentService.getStudentHashMap().get(registrationNumber).getName()+
                            " :  Grade: "+ gradeCalculator.grade(studentService.getStudentHashMap().get(registrationNumber)));
                }else {
                    System.out.println("Registration Number Unknown!!!");
                }
            } else if (option.equals("6")) {
                studentService.list();
            } else if (option.equals("7")) {
                System.out.println("Enter Registration Number: ");
                String registrationNumber = scanner.nextLine();
                if(studentService.getStudentHashMap().containsKey(registrationNumber)){
                    studentService.list(registrationNumber);
                }else {
                    System.out.println("Registration Number Unknown!!!");
                }
            } else if (option.equals("0")) {
                System.out.println("Closing System...");
                scanner.close();
                break;
            }else {
                System.out.println("Invalid Option. Please Try Again....");
            }
        }
    }
}