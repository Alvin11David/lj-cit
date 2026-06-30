package mark.practice;



class NumberType<T extends Number>{
    T value;
    NumberType(T value){
        this.value = value;
    }

    T getValue(){
        return value;
    }
}


class Test<T>{
    T value;

    Test(T value){
        this.value = value;
    }

    Test(){
    }

    void set(T value){
        this.value = value;
    }

    T get(){
        return value;
    }
}


public class Generics {
    public static void main(String[] args){
        Test<String> test1 = new Test<>("Sekibaala Mark");
        System.out.println("Value: "+test1.get());

        Test<Integer> test2 = new Test<>();
        test2.set(900);
        System.out.println("Value: "+test2.get());


        NumberType<Integer> num1 = new NumberType<>(98);
        System.out.println("Value: " + num1.getValue());

        NumberType<Double> num2 = new NumberType<>(45.12);
        System.out.println("Value: " + num2.getValue());



    }
}
