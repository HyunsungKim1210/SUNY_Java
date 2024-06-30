//JihyeYoon_115960721_jihye.yoon@stonybrook.edu

import java.util.Scanner;
import java.util.Random;

public class Craps {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        while (true) {
            System.out.print("Play Craps? (y/n) ");
            String answer = console.nextLine();
            if (answer.equals("y")) {
                if (playGame()) {
                    System.out.println(" Congratulations! You won!");
                } else {
                    System.out.println(" Sorry. You lost.");
                }
            } else {
                System.out.println("Thanks for playing!");
                break;
            }

        }
        console.close();
    }

    public static int[] roll() {
        int[] dice = new int[2];

        Random random = new Random();

        dice[0] = random.nextInt(6) + 1;
        dice[1] = random.nextInt(6) + 1;
//[4,1]
        return dice;//[4,1]

    }

    public static char evaluate(int[] dice, int p) {
        int sum = dice[0] + dice[1];
        if (p == 0) {
            if (sum == 2 || sum == 3 || sum == 12) {
                return 'l';
            } else if (sum == 7 || sum == 11) {
                return 'w';
            } else {
                return 'c';
            }
        } else {
            if (sum == p) {
                return 'w';
            } else if (sum == 7) {
                return 'l';
            } else {
                return 'c';
            }
        }
    }

    public static boolean playGame() {
        int p = 0;
        while (true) {
            int[] dice = roll();

            System.out.print("(" + dice[0] + "," + dice[1] + ")");
            char gameResult = evaluate(dice, p);
            if (gameResult == 'w') {
                return true;
            } else if (gameResult == 'l') {
                return false;
            } else if (gameResult == 'c' && p == 0) {
                p = dice[0] + dice[1];
            }
        }
    }
}