package org.example;

public class JavaString {
    public static void main(String[] args) {
        String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println("The lenght of the string is " + text.length());//check the lenght
        System.out.println(text.toLowerCase());
        String small = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(small.toUpperCase());
        //find the index of a character
        System.out.println(text.charAt(1));

        //comparing strings
        String one = "text";
        String two = "text";
        String three = "no text";
        System.out.println(one.equals(two)); //true
        System.out.println(two.equals(three)); //false
    }
}
