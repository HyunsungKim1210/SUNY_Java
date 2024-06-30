//JihyeYoon_115960721_jihye.yoon@stonybrook.edu

import java.util.Scanner;

public class Bits {
    public static void main (String[] args) {
        Scanner console = new Scanner(System.in);

        int num = 0;
        while (num >= 0){
            System.out.print("Enter an integer greater than or equal to 0 (enter negative number to quit): ");
            num = console.nextInt();
            if (num >= 0){
                bit (num);
                System.out.println("");
            } else {
               break;
            }

        }


        console.close();
    }

    public static void bit (int num){
        int num2 = 0;
        int i=0;
        int [] bits = new int[100000000];
            for (; i < bits.length; i++) {
                bits[i] = num % 2;
                num /= 2;
              //  System.out.print(bits[i]);
                if (num == 0) {
                    break;
                }
            }
            for(;i>-1;i--){
                System.out.print(bits[i]);
            }
        }


}
