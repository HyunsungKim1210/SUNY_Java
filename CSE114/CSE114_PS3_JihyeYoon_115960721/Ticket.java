//JihyeYoon_115960721_jihye.yoon@stonybrook.edu

import java.util.Scanner;
import java.util.Random;

public class Ticket {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        System.out.print("Please enter your age: ");
        int age = console.nextInt();

        double price = calculatePrice(age);

        console.close();
    }


    public static double ageDiscount(int age){
        if (age < 7) {
            return 1.00;
        } else if (age <= 18) {
            return 0.20;
        } else if (age <= 64) {
            return 0.00;
        } else {
            return 0.15;
        }
    }


    public static double randomly (){
        int lucky = 0;
        Random random = new Random();
        lucky = random.nextInt(10) + 1;

        if (lucky <= 4) {
            return 0.00;
        } else if (lucky <= 7) {
            return 0.10;
        } else {
            return 0.25;
        }
    }

    public static double calculatePrice(int age){
        double price = 22.0;
        double ageDiscount = ageDiscount(age);
        double randomDiscount = randomly();

        if(ageDiscount == 1.00){
            price = 0.00;
        } else {
            price -= (price * ageDiscount);
            price -= (price * randomDiscount);
        }

        if (age >= 7) {
            System.out.printf("Age Discount is: %.2f %%\n", ageDiscount * 100);
            if (randomDiscount > 0) {
                System.out.printf("Lucky Discount is: %.1f %% [You're a winner!]", randomDiscount * 100);
            }
        }
        System.out.printf("\nYou're ticket price is : %.2f", price);

        return price;
    }

}
