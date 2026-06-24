package org.example;
/*Instructions
Inside main(), complete the following steps:
Declare two int variables named itemPrice and shippingCost, and assign them values
Create an int variable named sum
Calculate the total cost by adding itemPrice and shippingCost (store the result in sum)
Print the total cost (sum)
Tip: Use the + operator.

 */
public class CodeChallengeTwo {
    public static void main(String[] args) {
        int itemPrice = 3000;
        int shippingCost = 4000;
        int sum;


        sum = itemPrice + shippingCost;
        System.out.println("The total sum is: " + sum);
    }
}
