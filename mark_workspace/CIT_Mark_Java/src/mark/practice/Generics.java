package mark.practice;
import java.util.Arrays;


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

    public static <T extends Number> double average(T[] array){
        return Arrays.stream(array).mapToDouble(n -> n.doubleValue()).average().orElse(0.0);
    }

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

        Integer[] numbers = {90,34,87,10};
        System.out.println("Average: " + average(new Integer[] {90,45,90}));


    }
}
