package org.example;
import java.util.Map;
import java.util.HashMap;


public class StudentService {
    private Map<String,Student> studentDatabase = new HashMap<>();
    private static int studentNumber =0;

    public void registerStudent(String name){
        studentNumber ++;
        String generatedId = "CIT"+studentNumber;
        Student newMember = new Student(generatedId,name);
        studentDatabase.put(generatedId,newMember);
        System.out.println("Student " + name + " registered with ID: " + generatedId + "  Successfully");
    }

    public void showAllStudents(){

        if (studentDatabase.isEmpty()){
            System.out.println("Currently there are no students in the Database");
        }else {
            System.out.println("                 Students in the Database ");
            System.out.println("----".repeat(20));
            for (Student student : studentDatabase.values()) {
                System.out.println("Name: " + student.getName() );
            }
        }
    }

    public void listStudentregNoAndName(){
        if(studentDatabase.isEmpty()){
            System.out.println("Currently there are no students in the Database.");
        }
        for (Map.Entry<String,Student> entry: studentDatabase.entrySet()){
            String regNo = entry.getKey();
            Student student =entry.getValue();
            System.out.println(regNo + " | " + student.getName());
        }
    }
    public Student getStudentDataByregNo(String regNo){
        Student searchItem = studentDatabase.get(regNo);


        if (searchItem != null){
            return searchItem;
        }
        return null;
    }

    public void clearDatabase(){
        System.out.println("Prepare for a data loss!!!....");
        studentDatabase.clear();
    }

    public void deleteStudent(String regNo){
        Student student = studentDatabase.get(regNo);
        if (student != null){
            System.out.println("Deleting Student with Registration Number"+regNo);
            studentDatabase.remove(regNo);
        }


    }

    public void loopThroughGradesMap(Map<Subject,Integer> grades){
        if(grades == null || grades.isEmpty()){
            System.out.println("No Grades Available.. ");

        }
        for (Map.Entry<Subject,Integer> entry: grades.entrySet()){
            Subject subject = entry.getKey();
            Integer score = entry.getValue();

            char letterGrade = Student.calculateGrade(score);

            System.out.printf("Subject: %-13s      Score: %d -> Grade: %c%n", subject, score, letterGrade);
            System.out.println();
            System.out.println();
        }
    }
    public void showAllStudentAveragesAndDetails(){

        if (studentDatabase.isEmpty()){
            System.out.println("No averages to show since database is empty..");
        }
        for (Student student: studentDatabase.values()){
            System.out.println("--".repeat(20));
            System.out.println("Registration Number: "+student.getRegNo());
            System.out.println("Name: "+ student.getName());
            System.out.println();
            loopThroughGradesMap(student.getGrades());
        }
    }

    }


