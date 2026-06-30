package mark.practice;


class Test<T>{
    T value;



    void set(T value){
        this.value = value;
    }

    T get(){
        return value;
    }
}



public class Generics {
    public static void main(String[] args){
        Test<String> test1 = new Test<>();
        test1.set("Sekibaala Mark");
        System.out.println("Value: "+test1.get());

        Test<Integer> test2 = new Test<>();
        test2.set(900);
        System.out.println("Value: "+test2.get());

    }
}
