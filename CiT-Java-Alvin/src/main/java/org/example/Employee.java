package org.example;

public abstract class Employee {
   private String name;

   public Employee(String name) {
       this.name = name;
   }

   //name getter
    public String getName() {
       return name;
    }

   public void clockIn() {
       System.out.println(name + " has clocked In");
   }

   public abstract double calculateMonthlyPay();
}
