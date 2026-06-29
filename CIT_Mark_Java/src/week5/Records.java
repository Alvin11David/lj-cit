package week5;

record Student(int id, String name, String room){}


public class Records {
    public static void main(String[] args){
      Student student = new Student(1,"Kim","A2");

      //records are immutable
        System.out.println(student.toString());
    }
}
