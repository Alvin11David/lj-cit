/*

package Student.Grader;

import java.util.HashMap;

class User{
    String name;
    int age;
    String id;

    User(String name, int age, String id){
        this.age = age;
        this.id = id;
        this.name = name;
    }
}

class StudentServiceX{
    HashMap<String,User> userHashMap = new HashMap<>();
    StudentServiceX(HashMap<String,User> userHashMap){
        this.userHashMap = userHashMap;
    }

    public void addUser(User user){
        userHashMap.put(user.id,user);
    }


}


public class Test {
    public static void main(String[] args){
        HashMap<String,User> userHashMap = new HashMap<>();
        StudentService studentService = new StudentService(userHashMap);

        studentService.addUser(new User("Mark",20,"x00"));
        studentService.addUser(new User("JP",20,"Roo"));

        for(String id : userHashMap.keySet()){
            System.out.println(userHashMap.get(id).name + " : " + userHashMap.get(id).age);
        }
    }
}

 */