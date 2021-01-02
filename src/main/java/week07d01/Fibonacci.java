package week07d01;

public class Fibonacci {

    public static long fib(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The number cannot be less than 0");

        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 2) + fib(n - 1);
    }

}
