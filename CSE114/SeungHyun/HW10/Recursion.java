public class Recursion {

    // 1. Recursive method to calculate GCD using Euclid's Algorithm
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // 2. Recursive method to calculate power of a number
    public static double power(double x, int n) {
        if (n == 0) {
            return 1;
        }
        return x * power(x, n - 1);
    }

    // 3. Recursive method to calculate Fibonacci numbers
    public static int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {

        System.out.println("gcd(36, 20) = " + gcd(36, 20));
        System.out.println("gcd(34, 0) = " + gcd(34, 0));
        System.out.println("gcd(3346, 468) = " + gcd(3346, 468));

        // Testing the power method
        System.out.println("power(4, 0) = " + power(4, 0));
        System.out.println("power(3, 5) = " + power(3, 5));
        System.out.println("power(2, 7) = " + power(2, 7));

        // Testing the fib method
        System.out.println("fib(0) = " + fib(0)); // returns 0
        System.out.println("fib(1) = " + fib(1)); // returns 1
        System.out.println("fib(2) = " + fib(2)); // returns 1
        System.out.println("fib(3) = " + fib(3)); // returns 2
        System.out.println("fib(6) = " + fib(6)); // returns 8
        System.out.println("fib(10) = " + fib(10)); // returns 55
        System.out.println("fib(15) = " + fib(15)); // returns 610
    }
}
