package week5;
class Exceptions {
    static void checkName(String name){
        if(name.length()<=2){
            throw new IllegalArgumentException("Name must be at least 3 characters long");
        }
    }

    public static void main(String[] args){
        try {
            checkName("mk");
        }catch (IllegalArgumentException e){
            System.out.println("Name must be at least 3 characters long!!!");
        }
    }
}