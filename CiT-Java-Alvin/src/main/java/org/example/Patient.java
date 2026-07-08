package org.example;

public class Patient {
    private String name;
    private int age;
    private String bloodType;
    private boolean sick;

    Patient(String name, int age, String bloodType, boolean sick) {
        this.name = name;
        this.age = age;
        this.bloodType = bloodType;
        this.sick = sick;
    }

    //getter for name
    public String getName() {
        return name;
    }

    //getter for age
    public int getAge() {
        return age;
    }

    //getter for blood type
    public String getBloodType() {
        return bloodType;
    }

    //getter for sick
    public boolean isSick() {
        return sick;
    }

    //setter for name
    public void setName(String name) {
        this.name = name;
    }

    //setter for age
    public void setAge(int age) {
        if (age < 0) {
            System.out.println("Invalid age");
            return;
        } else {
            this.age = age;
        }
    }

    public static void main(String[] args) {
        Patient patient1 = new Patient("James", 30, "O+", true);
        System.out.println(patient1.getName());
        System.out.println(patient1.getAge());
        System.out.println(patient1.getBloodType());
        System.out.println(patient1.isSick());

        patient1.setName("James Okello");
        patient1.setAge(35);
        System.out.println(patient1.getName());
        System.out.println(patient1.getAge());

        patient1.setAge(-5);
        System.out.println(patient1.getAge());
    }
}
