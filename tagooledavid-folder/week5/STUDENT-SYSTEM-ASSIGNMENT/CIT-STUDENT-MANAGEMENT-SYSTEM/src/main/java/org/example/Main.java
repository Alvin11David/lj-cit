package org.example;

public class Main {
    public static void main(String[] args) {
        StudentRepository repository = new StudentRepository();
        StudentService service = new StudentService(repository);
        StudentApp app = new StudentApp(service);
        app.run();
    }
}